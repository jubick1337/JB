import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {

    @Test
    void translationTest() {
        assertEquals("I3/-\\[)", new ChatCommands().executeCommand("/translate bad".toLowerCase()));
        assertEquals("|-|[]|/\\| /-\\/2€ ¥[](_)", new ChatCommands().executeCommand(("/translate How are you".
                toLowerCase())));
        assertEquals("kek", new ChatCommands().executeCommand("/translate " + new ChatCommands().executeCommand("/translate kek")));
    }

    @Test
    void pingTest() {
        assertEquals("pong", new ChatCommands().executeCommand("/ping"));
    }

    @Test
    void unexpectedCommandTest() {
        assertEquals("Use /help :)", new ChatCommands().executeCommand("as[dlk"));
    }

    @Test
    void helpTest() {
        assertEquals("I am a simple 1337 translator. Just type /translate \"some_text\" (in russian or in english) or /ping to check if i'm alive",
                new ChatCommands().executeCommand("/help"));
    }

    @Test
    void generalTest() {
        Bot bot = new Bot();
        assertEquals("translate1337_bot", bot.getBotUsername());
        assertEquals("678358194:AAEtrqdE7cU44FUKSDelchq51s8Wq0oq1FI", bot.getBotToken());
    }

    @Test
    void weatherTest(){
        assertNotEquals("", new Weather().getReadyForecastByCity("Moscow"));
        assertEquals("Can't find \"asdalksj\" city. Try another one, for example: \"Yekaterinburg\" or \"Moscow\"", new Weather().getReadyForecastByCity("asdalksj"));
    }
}
