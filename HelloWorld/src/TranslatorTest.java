import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TranslatorTest {

	@Test
	void translateTest() throws Exception {
		var translator = new Translator();
		assertEquals(translator.Translate("������"), "XOPOWO");
		assertEquals(translator.Translate("���"), "KAK");
		assertEquals(translator.Translate("����� ��� ���, �� ������� ���� ���������� �����"),
				"Bb|/7Eu* EW*E 4A|0, gA OTBEgAu* -)TuX (|)PAHL|Y3KuX 6YJIOK");
	}

}
