package startPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	
	public static String OpenFile(String filePath) throws IOException{

		String links = "";
		
		FileReader fileReader = new FileReader(filePath);
		BufferedReader textReader = new BufferedReader(fileReader);
		
		String lines = textReader.readLine();
		while(lines != null){
			links += lines;
			lines = textReader.readLine();
		}
		
		textReader.close();
		return links;		
	}

}
