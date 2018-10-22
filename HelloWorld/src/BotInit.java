import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotInit {

	public static void Create() {
		System.getProperties().put("proxySet", "true");
		System.getProperties().put("socksProxyHost", "127.0.0.1");
		System.getProperties().put("socksProxyPort", "1337");
		ApiContextInitializer.init();
		MyAmazingBot bot = new MyAmazingBot();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			System.out.println("trying");
			botsApi.registerBot(bot);
			System.out.println("Success");
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
