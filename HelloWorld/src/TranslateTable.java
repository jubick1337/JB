import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class TranslateTable
{
    //defaultTable is simply if all keys in it has (.length == 1)
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
            br.readLine();
            while (true)
            {
                lines[0] = br.readLine();
                lines[1] = br.readLine();
                if (lines[0] == null || lines[1] == null)
                    break; // end of file
                //region Security of codes
                lines[2] = br.readLine();
                if (hasSplitter && lines[2] != null && !lines[2].isEmpty())
                    throw new Exception(String.format("Every 3th line should be empty but №%d isn't", PairIndex));
                //if (RightTable.containsKey(lines[0]) || LeftTable.containsKey(lines[1]))
                //    throw new Exception("Table already contains key");
                if (needsFanoCheck)
                    if (IsKeyFanoDangerous(RightTable, LeftTable, lines[0], lines[1]))
                        throw new Exception(String.format("Fano check failed on pair: [%s]->[%s]", lines[0], lines[1]));
                //endregion

                RightTable.put(lines[0], lines[1]);
                LeftTable.put(lines[1], lines[0]);
                PairIndex++;
            }
            RightMaxLen = GetMaxKeyLen(RightTable);
            LeftMaxLen = GetMaxKeyLen(LeftTable);
        }
    }



    private int GetMaxKeyLen(HashMap<String, String> table)
    {
        return table.keySet().stream().map(String::length).max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
    }

    private static boolean IsTableSimple(HashMap<String, String> table)
    {
        return table.keySet().stream().allMatch((s) -> s.length() == 1);
    }

    public static boolean IsPrefix(String string, String possiblePrefix)
    {
        return possiblePrefix.length() <= string.length() &&
                string.substring(0, possiblePrefix.length()).equals(possiblePrefix);
    }

    public static boolean IsKeyFanoDangerous(
            HashMap<String, String> right,
            HashMap<String, String> left,
            String key, String value)
    {
        return IsPrefix(key, value) || IsPrefix(value, key) ||
                IsKeyFanoDangerous(left, key) ||
                IsKeyFanoDangerous(right, key) ||
                IsKeyFanoDangerous(left, value) ||
                IsKeyFanoDangerous(right, value);
    }

    public static boolean IsKeyFanoDangerous(HashMap<String, String> table, String key)
    { // Key is a prefix of any other key already included?
        return table.keySet().stream().anyMatch(other -> IsPrefix(other, key));
    }

    public Pair<HashMap<String, String>> GetMaps()
    {
        return RightMaxLen > LeftMaxLen ?
                new Pair<>(RightTable, LeftTable) :
                new Pair<>(LeftTable, RightTable);
    }
    public Pair<Integer> GetLens()
    {
        return RightMaxLen > LeftMaxLen ?
                new Pair<>(RightMaxLen, LeftMaxLen) :
                new Pair<>(LeftMaxLen, RightMaxLen);
    }
}
