import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class Weather implements WeatherParser{
    private final String API_CALL_GEO = "https://api.openweathermap.org/data/2.5/forecast?lat= &lon= ";
    private final String API_CALL_CITY = "https://api.openweathermap.org/data/2.5/forecast?q=";
    private final String API_KEY = "&units=metric&APPID=59982960e70e26d7bc10874f9150f6f9";
    private final  String USER_AGENT = "Mozilla/5.0";
    private final  DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final  DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM-dd HH:mm", Locale.US);

    @Override
    public String getReadyForecastByCity(String city) {
        String result;
        try {
            String jsonRawData = downloadJsonRawData(city);
            List<String> linesOfForecast = convertRawDataToList(jsonRawData);
            result = String.format("%s:%s%s", city, System.lineSeparator(), parseForecastDataFromList(linesOfForecast));
        } catch (IllegalArgumentException e) {
            return String.format("Can't find \"%s\" city. Try another one, for example: \"Yekaterinburg\" or \"Moscow\"", city);
        } catch (Exception e) {
            e.printStackTrace();
            return "The service is not available, please try later";
        }
        return result;
    }

    @Override
    public String getReadyForecastByLocation(double lat, double lon) {
        String result;
        try {
            String jsonRawData = downloadJsonRawData(lat, lon);
            List<String> linesOfForecast = convertRawDataToList(jsonRawData);
            result = String.format("%s%s", System.lineSeparator(), parseForecastDataFromList(linesOfForecast));
        } catch (IllegalArgumentException e) {
            return String.format("Are you on Earth?");
        } catch (Exception e) {
            e.printStackTrace();
            return "The service is not available, please try later";
        }
        return result;
    }

    private String downloadJsonRawData(String city) throws Exception {
        String urlString = API_CALL_GEO + city + API_KEY;
        URL urlObject = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = connection.getResponseCode();
        if (responseCode == 404) {
            throw new IllegalArgumentException();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
    private String downloadJsonRawData(double lat, double lon) throws Exception {
        String urlString = API_CALL_GEO.replaceFirst(" ", String.valueOf(lat)).replaceFirst(" ", String.valueOf(lon))
                + API_KEY;
        URL urlObject = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = connection.getResponseCode();
        if (responseCode == 404) {
            throw new IllegalArgumentException();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
    private List<String> convertRawDataToList(String data) throws Exception {
        List<String> weatherList = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(data).get("list");
        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                String forecastTime = objNode.get("dt_txt").toString();
                if (forecastTime.contains("09:00") || forecastTime.contains("18:00")) {
                    weatherList.add(objNode.toString());
                }
            }
        }
        return weatherList;
    }

    private String parseForecastDataFromList(List<String> weatherList) throws Exception {
        final StringBuilder sb = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();

        for (String line : weatherList) {
            {
                String dateTime;
                JsonNode mainNode;
                JsonNode weatherArrNode;
                try {
                    mainNode = objectMapper.readTree(line).get("main");
                    weatherArrNode = objectMapper.readTree(line).get("weather");
                    for (final JsonNode objNode : weatherArrNode) {
                        dateTime = objectMapper.readTree(line).get("dt_txt").toString();
                        sb.append(formatForecastData(dateTime, objNode.get("main").toString(), mainNode.get("temp").asDouble()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


    private String formatForecastData(String dateTime, String description, double temperature) {
        LocalDateTime forecastDateTime = LocalDateTime.parse(dateTime.replaceAll("\"", ""), INPUT_DATE_TIME_FORMAT);
        String formattedDateTime = forecastDateTime.format(OUTPUT_DATE_TIME_FORMAT);

        String formattedTemperature;
        long roundedTemperature = Math.round(temperature);
        if (roundedTemperature > 0) {
            formattedTemperature = "+" + (Math.round(temperature));
        } else {
            formattedTemperature = String.valueOf(Math.round(temperature));
        }

        String formattedDescription = description.replaceAll("\"", "");

        return String.format("%s   %s %s%s", formattedDateTime, formattedTemperature, formattedDescription, System.lineSeparator());
    }
}