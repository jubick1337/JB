import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

class Translator
{
    private static HashMap<String, String> straightMap;
    private static String[] straightKeys;
    private static boolean isReady = Initialize();

    private static boolean Initialize()
    {
        //TODO: maybe should we read 3 lines instead of 2?
        //TODO: we gonna be safe with possible file-stuff-happened-bugs then
        // --Jarl
        try
        {
            var lines = Files.readAllLines(Paths.get("language.txt"));
            var lines_count = lines.size();
            if (lines_count % 2 == 1)
                throw new Exception("Wrong line count");
            else
            {
                straightKeys = new String[lines_count / 2];
                straightMap = new HashMap<>();
                for (var i = 0; i < lines_count / 2; i++)
                {
                    straightMap.put(lines.get(i * 2), lines.get(i * 2 + 1));
                    straightKeys[i] = lines.get(i * 2);
                }
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    static String Translate(String str) throws Exception
    {
        var builder = new StringBuilder();
        var i = 0;
        while (i < str.length())
        {
            if (str.charAt(i) == ' ')
            {
                builder.append(' ');
                i++;
            }
            else
            {
                var max_key = FindLongestKey(i, str);
                builder.append(straightMap.get(max_key));
                i += max_key.length();
            }
        }
        return builder.toString();
    }

    private static String FindLongestKey(int i, String str) throws Exception
    {
        //TODO: We have to optimize it
        // --Jarl
        var str_len = str.length();
        var key_len = 0;
        var max_len = 0;
        var max_key = "";
        for (var key : straightKeys)
        {
            key_len = key.length();
            if (key_len > max_len && key_len + i <= str_len)
                if (str.substring(i, i + key_len).equals(key))
                {
                    //TODO: should we check only key's postfix?
                    // --Jarl
                    max_len = key_len;
                    max_key = key;
                }
        }
        if (max_key.equals(""))
            throw new Exception("Unknown symbols detected");
        return max_key;
    }
}
