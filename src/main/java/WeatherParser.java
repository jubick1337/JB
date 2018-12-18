interface WeatherParser {
    String getReadyForecastByLocation(double lat, double lan);
    String getReadyForecastByCity(String city);
}