class ChatComands {

	private static String ping() {
		return "pong";
	}

	private static String help() {
		return "I am a simple 1337 translator. Just type /translate \"some_text\" (in russian) or /ping to check if i'm alive";
	}

	private static String translate(String text) {
		try {
			return Tools.Translate(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "I AM DEAD";
	}
//	{
//		StringBuilder res = new StringBuilder();
//		HashMap<String, String> translator = Library.getRusLeetDictionary();
//
//		for (int i = 9; i < input.length; i++)
//			//			if (translator.containsKey(input[i]))
//			//			{
//			//				res.append(translator.get(input[i]));
//			//			}
//			//			else
//			//			{
//			//				res.append(" ");
//			//			}
//			res.append(translator.getOrDefault(input[i], " "));
//		System.out.println(res.toString());
//		return res.toString();
//	}

	static String executeCommand(String input) {
		switch (input.split(" ")[0]) {
		case "/help":
			return help();
		case "/translate":
			System.out.println("Here we go");
			String text = input.substring(input.indexOf(" ")).toLowerCase();
			return translate(text);
		case "/ping":
			return ping();
		default:
			break;
		}
		return "Use /help :)";
	}
}
