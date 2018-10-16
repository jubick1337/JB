import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
	public static void main(String[] args) {
		// Initialize Api Context
		System.getProperties().put("proxySet", "true");
		System.out.println("1");
		System.getProperties().put("socksProxyHost", "127.0.0.1");
		System.out.println("1");
		System.getProperties().put("socksProxyPort", "1337");
		System.out.println("1");
		ApiContextInitializer.init();
		System.out.println("1");
		System.out.println("Hekllo!");
		MyAmazingBot bot = new MyAmazingBot();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			System.out.println("trying");
			botsApi.registerBot(bot);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}