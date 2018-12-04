import java.util.HashMap;

public class NewTranslator
{
    private static String defaultTablePath = "leet.txt";
    private static TranslateTable defaultTable;
    static
    {
        try
        {
            defaultTable = new TranslateTable(defaultTablePath);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setDefaultTable(TranslateTable newTable)
    {
        defaultTable = newTable;
    }

    public static String tryToFindExistingKey(TranslateTable table, String str)
    {
        var maps = table.getMaps();
        var lens = table.getLengths();
        var strLen = str.length();
        var limit = 0;
        var i = 0;
        //var seemsFirst;
        //var seemsSecond;
        while(i < strLen)
        {
            limit = Integer.min(lens.first + i, strLen - i);
            for(var j = 1; j <= limit; j++)
            {
                var sub = str.substring(i, i + j);
                if (maps.first.containsKey(sub) || (j <= lens.second && maps.second.containsKey(sub)))
                    return sub;
            }
            i++;
            //lesser_limit = Integer.min(lens.second + i, strLen);
//            for(var j = 1; j <= lesser_limit; j++)
//            {
//                var sub = str.substring(i, i + j);
//                if (maps.first.containsKey(sub) ^ maps.second.containsKey(sub))
//                    return sub;
//            }
//            greater_limit = Integer.min(lens.first + i, strLen);
//            for(var j = lesser_limit + 1; j <= greater_limit; j++)
//            {
//                var sub = str.substring(i, i + j);
//                if(maps.first.containsKey(sub)) return sub;
//            }
//            i += greater_limit;
        }
        return str.substring(0, 1); //can't translate or
    }

    public static String translate(String str)
    {
        return translate(defaultTable, str);
    }

    public static String translate(TranslateTable table, String str)
    {
        var key = tryToFindExistingKey(table, str);
        if(table.RightTable.containsKey(key))
            return directTranslate(table.RightTable, table.RightMaxLen, str);
        else if(table.LeftTable.containsKey(key))
            return directTranslate(table.LeftTable, table.LeftMaxLen, str);
        else // All chars aren't in table, we have to skip them all unchanged... And get sample string!
            return str;
    }

    public static String directTranslate(HashMap<String, String> map, Integer maxKeyLen, String str)
    {
        var builder = new StringBuilder();
        //var translated = "";    //debug
        var len = str.length();
        var i = 0;
        var limit = 0;
        var met_unreadable = false;
        while(i < len)
        {
            limit = Integer.min(maxKeyLen + i, len - i);
            met_unreadable = true;
            for(var j = 1; j <= limit; j++)
            {
                var sub = str.substring(i, i + j);
                if(map.containsKey(sub))
                {
                    met_unreadable = false;
                    builder.append(map.get(sub));
                    i += j;
                    break;
                }
            }
            if(met_unreadable)
            {
                builder.append(str.charAt(i));
                i++;
            }
            //translated = str.substring(0, i);
        }
        return builder.toString();
    }
}
