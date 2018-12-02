public class Main {
	public static void main(String[] args) {
		//LaunchBot();
		SampleTest();
	}

	private static void LaunchBot()
	{
		// Initialize Api Context
		BotInit botInit = new BotInit();
		botInit.Create();
	}


	public static void SampleTest()
	{
		var str = "Hello world, how are you?";
		str = str.toLowerCase();
		var counts = new int[]{1, 2, 3, 10, 100};
		PerformAndPrint(str, counts);
	}

	public static void PerformAndPrint(String str, int[] translateCounts)
	{
		PrintThemAll(PerformManyTests(str, translateCounts));
	}
	private static void PrintThemAll(String[] strings)
	{
		for (String str : strings)
			System.out.println(str);
	}

	public static String[] PerformManyTests(String str, int[] translateCounts)
	{
		var result = new String[translateCounts.length];
		for(var i = 0; i < translateCounts.length; i++)
			result[i] = Test(str, translateCounts[i]);
		return result;
	}

	public static String Test(String str, int translatesCount)
	{
		var result = str;
		for(var i = 0; i < translatesCount; i++)
		{
			result = NewTranslator.Translate(result);
		}
		return result;
	}

}