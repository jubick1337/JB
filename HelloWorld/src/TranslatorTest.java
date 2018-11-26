import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TranslatorTest {

	@Test
	void translateTest() throws Exception {
		var translator = new Translator();
		assertEquals(translator.Translate("хорошо"), "XOPOWO");
		assertEquals(translator.Translate("как"), "KAK");
		assertEquals(translator.Translate("выпей еще чаю, да отведай этих французких булок"),
				"Bb|/7Eu* EW*E 4A|0, gA OTBEgAu* -)TuX (|)PAHL|Y3KuX 6YJIOK");
	}

}
