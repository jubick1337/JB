import java.util.HashMap;

public class Library {
	private static String[] rusAlph = new String[] { "À", "Á", "Â", "Ã", "Ä", "Å", "¨", "Æ", "Ç", "È", "É", "Ê", "Ë",
			"Ì", "Í", "Î", "Ï", "Ð", "Ñ", "Ò", "Ó", "Ô", "Õ", "Ö", "×", "Ø", "Ù", "Ú", "Û", "Ü", "Ý", "Þ", "ß" };
	private static String[] leetAlph = new String[] { "A", "6", "B", "r", "g", "E", "`e", ">|<", "3", "u", "u*", "K",
			"JI", "M", "H", "O", "/7", "P", "C", "T", "Y", "(|)", "X", "L|", "4", "W", "W*", "`b", "b|", "b", "-)",
			"|0", "9|" };

	public static HashMap<String, String> getRusLeetDictionary() {
		HashMap<String, String> rusEng = new HashMap<String, String>();
		for (int i = 0; i < rusAlph.length; i++) {
			rusEng.put(rusAlph[i], leetAlph[i]);
		}
		return rusEng;
	}
}
