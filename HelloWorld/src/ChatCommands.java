class ChatCommands
{
	private static String ping()
	{
		return "pong";
	}

	private static String help() {
		return "I am a simple 1337 translator. Just type /translate \"some_text\" (in russian) or /ping to check if i'm alive";
	}

	private static String Translate(String text)
	{
		try
		{
			return NewTranslator.Translate(text.toLowerCase());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "I AM DEAD";
	}

	static String executeCommand(String input)
	{
		switch (input.split(" ")[0])
		{
			case "/help":
				return help();
			case "/translate":
					System.out.println("Here we go");
					var text = input.substring(input.indexOf(" ") + 1);
					return Translate(text);
			case "/ping":
				return ping();
			default:
				break;
		}
		return "Use /help :)";
	}
}
