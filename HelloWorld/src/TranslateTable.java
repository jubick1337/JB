import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.HashMap;

public class TranslateTable
{
    protected HashMap<String, String> RightTable = new HashMap<>();
    protected HashMap<String, String> LeftTable = new HashMap<>();
    public final int RightMaxLen;
    public final int LeftMaxLen;

    public TranslateTable(String languageFileName) throws Exception
    {
        this(languageFileName, true, true);
    }

    public TranslateTable(String languageFileName, boolean hasSplitter, boolean needsFanoCheck) throws Exception
    {
        var path = System.getProperty("user.dir") + "\\src\\" + languageFileName;
        var lines = new String[3];
        var PairIndex = 0;

        try (var br = new BufferedReader(new FileReader(path)))
        {
            var firstLine = br.readLine();
            while (true)
            {
                lines[0] = br.readLine();
                lines[1] = br.readLine();
                if (lines[0] == null || lines[1] == null)
                    break; // end of file
                //region Security of codes
                lines[2] = br.readLine();
                if (hasSplitter && lines[2] != null && !lines[2].isEmpty())
                    throw new Exception(String.format("First and every 3th line after should be blank but №%d isn't", PairIndex * 3 + 1));
                if (needsFanoCheck)
                    if (isKeyFanoDangerous(RightTable, LeftTable, lines[0], lines[1]))
                        throw new Exception(String.format("Fano check failed on pair: [%s]->[%s]", lines[0], lines[1]));
                //endregion

                RightTable.put(lines[0], lines[1]);
                LeftTable.put(lines[1], lines[0]);
                PairIndex++;
            }
            RightMaxLen = getMaxKeyLen(RightTable);
            LeftMaxLen = getMaxKeyLen(LeftTable);
        }
    }



    private int getMaxKeyLen(HashMap<String, String> table)
    {
        return table.keySet().stream().map(String::length).max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
    }

    public static boolean isPrefix(String string, String possiblePrefix)
    {
        return possiblePrefix.length() <= string.length() &&
                string.substring(0, possiblePrefix.length()).equals(possiblePrefix);
    }

    public static boolean isKeyFanoDangerous(
            HashMap<String, String> right,
            HashMap<String, String> left,
            String key, String value)
    {
        return isPrefix(key, value) || isPrefix(value, key) ||
                isKeyFanoDangerous(left, key) ||
                isKeyFanoDangerous(right, key) ||
                isKeyFanoDangerous(left, value) ||
                isKeyFanoDangerous(right, value);
    }

    public static boolean isKeyFanoDangerous(HashMap<String, String> table, String key)
    { // Key is a prefix of any other key already included?
        return table.keySet().stream().anyMatch(other -> isPrefix(other, key));
    }

    public Pair<HashMap<String, String>> getMaps()
    {
        return RightMaxLen > LeftMaxLen ?
                new Pair<>(RightTable, LeftTable) :
                new Pair<>(LeftTable, RightTable);
    }
    public Pair<Integer> getLengths()
    {
        return RightMaxLen > LeftMaxLen ?
                new Pair<>(RightMaxLen, LeftMaxLen) :
                new Pair<>(LeftMaxLen, RightMaxLen);
    }
}
