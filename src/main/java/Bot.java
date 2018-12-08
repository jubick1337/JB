import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update)
    {
        if (update.hasMessage() && update.getMessage().hasText())
        {
            String message_text = update.getMessage().getText();
            SendMessage answer_message = new SendMessage().setChatId(update.getMessage().getChatId())
                    .setText(new ChatCommands().executeCommand(message_text));
            try
            {
                execute(answer_message);
            }
            catch (TelegramApiException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "translate1337_bot";
    }

    @Override
    public String getBotToken() {
        return "678358194:AAEtrqdE7cU44FUKSDelchq51s8Wq0oq1FI";
    }
}