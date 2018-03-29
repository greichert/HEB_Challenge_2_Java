package HEB_Challenge_2;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.Map.Entry;

public class FileParser 
{
	private String errorCode = "";
	private Boolean hasErrors = true;
	private StringBuilder output = new StringBuilder();
	private TreeMap<String, Integer> results;
	
	public FileParser(String inputPath, String outputPath)
	{
		this.results = new TreeMap<String, Integer>();

		// wrap everything in try catch method for error tracking
		try
		{
			// read next line
			Scanner lineScanner = new Scanner (new File(inputPath));
			while (lineScanner.hasNextLine())
			{
				// read individual words
				Scanner wordScanner = new Scanner(lineScanner.nextLine());
				while (wordScanner.hasNext())
				{
					// strip out any punctuation and set to lower case so words aren't duplicated because of capitalization
					String word = wordScanner.next();
					word = word.toLowerCase();
					word = word.replaceAll("[^a-z0-9]", "");
					
					// update key value pair. 
					// If key does not exist add it and number 1, otherwise add one to existing key/value
					if(results.containsKey(word))
					{
						// add one to value
						results.put(word, results.get(word) + 1);
					}
					else
					{
						// insert new key/value pair
						results.put(word, 1);
					}
				}
				
				// close out scanner
				wordScanner.close();
			}
			
			// close out scanner
			lineScanner.close();
			
			// sort results list
			Map<String, Integer> sortedResults = SortByValue(results);
			
				
			// build a list of keys from results for iteration
			Set<String> keys = sortedResults.keySet();
			
			// build out output stringBuilder
			for (String key: keys)
			{		
				this.output.append(key + " | ");
				
				Integer value = sortedResults.get(key);
				for (Integer count = value; count > 0; count--)
				{
					this.output.append("=");
				}
				this.output.append(" ");
				this.output.append(sortedResults.get(key).toString());
				this.output.append(System.lineSeparator());
			}
			
			// put text in writer and close
			File outputFile = new File(outputPath);
			FileWriter outputWriter = new FileWriter(outputFile);
			outputWriter.write(this.output.toString());
			outputWriter.close();			
			
			// set finishing properties
			this.hasErrors = false;
		}
		catch (Exception ex)
		{
			// set error properties and code
			this.hasErrors = true;
			this.errorCode = ex.toString();
		}
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> SortByValue(Map<K, V> map) {
        List<Entry<K, V>> mapList = new ArrayList<>(map.entrySet());
        mapList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : mapList) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
	
	public String Output()
	{
		return this.output.toString();
	}
	
	public String ErrorCode ()
	{
		return this.errorCode;
	}
	
	public boolean HasErrors ()
	{
		return this.hasErrors;
	}
}
