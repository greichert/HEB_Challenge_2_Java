package HEB_Challenge_2;

public class Main {

	public static void main(String[] args) {
		// variables
		String rootPath = System.getProperty("user.dir");
		// String inputFilePath = "E:\\Projects\\Eclipse\\HEB_Challenge_2\\src\\HEB_Challenge_2\\Files\\input.txt";
		// String outputFilePath = "E:\\Projects\\Eclipse\\HEB_Challenge_2\\src\\HEB_Challenge_2\\Files\\output.txt";
		String inputFilePath = rootPath + "\\src\\HEB_Challenge_2\\Files\\input.txt";
		String outputFilePath = rootPath + "\\src\\HEB_Challenge_2\\Files\\output.txt";
		
		FileParser myParser = new FileParser(inputFilePath, outputFilePath);
		
		System.out.println(myParser.Output());
		System.out.println(myParser.HasErrors());
		System.out.println(myParser.ErrorCode());
	}
}
