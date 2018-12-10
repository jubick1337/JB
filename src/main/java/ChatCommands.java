class ChatCommands
{
    private String ping()
    {
        return "pong";
    }

    private String weather(String city){
        System.out.println(city);
        return new Weather().getReadyForecast(city);
    }

    private String help() {
        return "I am a simple 1337 translator. Just type /translate \"some_text\" (in russian or in english) or /ping to check if i'm alive";
    }

    private String translate(String text)
    {
        try
        {
            return new NewTranslator().translate(text.toLowerCase());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "I AM DEAD";
    }

    String executeCommand(String input)
    {
        switch (input.split(" ")[0])
        {
            case "/weather":
                return weather(input.split(" ")[1]);
            case "/help":
                return help();
            case "/translate":
                System.out.println("Here we go");
                var text = input.substring(input.indexOf(" ") + 1);
                return translate(text);
            case "/ping":
                return ping();
            default:
                break;
        }
        return "Use /help :)";
    }
}
