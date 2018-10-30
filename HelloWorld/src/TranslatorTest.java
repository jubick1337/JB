import static org.junit.jupiter.api.Assertions.*;

class TranslatorTest
{
    @org.junit.jupiter.api.Test
    void translate()
    {
        try
        {
            System.out.println(Translator.Translate("помогите я хочу есть"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}