import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Chat;

class CommandsTest {

    @Test
    void translationTest() {
        assertEquals("I3/-\\[)", ChatCommands.executeCommand("/translate bad".toLowerCase()));
        assertEquals("|-|[]|/\\| /-\\/2€ ¥[](_)", ChatCommands.executeCommand(("/translate How are you".
                toLowerCase())));
        assertEquals("/|][d|/|(3£'|'", ChatCommands.executeCommand("/translate привет"));
        assertEquals("привет", ChatCommands.executeCommand("/translate" + ChatCommands.executeCommand("/translate привет")));
    }

    @Test
    void pingTest() {
        assertEquals("pong", ChatCommands.executeCommand("/ping"));
    }

    @Test
    void unexpectedCommandTest() {
        assertEquals("Use /help :)", ChatCommands.executeCommand("as[dlk"));
    }

    @Test
    void helpTest() {
        assertEquals("I am a simple 1337 translator. Just type /translate \"some_text\" (in russian or in english) or /ping to check if i'm alive",
                ChatCommands.executeCommand("/help"));
    }

    @Test
    void generalTest() {
        Bot bot = new Bot();
        assertEquals("translate1337_bot", bot.getBotUsername());
        assertEquals("678358194:AAEtrqdE7cU44FUKSDelchq51s8Wq0oq1FI", bot.getBotToken());
    }
}
