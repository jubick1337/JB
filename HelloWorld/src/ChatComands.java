import java.util.HashMap;

public class ChatComands {

	public static String ping() {
		return "pong";
	}

	public static String help() {
		return "I am a simple 1337 translator. Just type /translate \"some_text\" (in russian) or /ping to check if i'm alive";
	}

	public static String translate(String[] input) {
		StringBuilder res = new StringBuilder();
		HashMap<String, String> transaltor = Library.getRusLeetDictionary();
		for (int i = 9; i < input.length; i++) {
			if (transaltor.containsKey(input[i])) {
				res.append(transaltor.get(input[i]));
			} else {
				res.append(" ");
			}
		}
		System.out.println(res.toString());
		return res.toString();
	}

	public static String executeCommand(String input) {
		switch (input.split(" ")[0]) {
		case "/help":
			return help();
		case "/translate":
			System.out.println("this a was call");

			return translate(input.toUpperCase().split(" "));
		case "/ping":
			return ping();
		default:
			break;
		}
		return "Use /help :)";
	}
}
