package startPackage;

import DaoPackage.ImageService;
import Entities.ImageTable;

import java.io.File;
import java.util.Scanner;

public class Start {

	static ImageTable imageTable = new ImageTable();
	static ImageService service = new ImageService();

	public static void main(String[] args) throws Exception {

		screenShot screen = new screenShot();

		Scanner inpute = new Scanner(System.in);
		System.out.println("Insert a list of URLs separated by (;) or a link of text file with the URLs inside it. Thanks  ");

		String userInpute = inpute.next();
		String position = userInpute.substring(0, 7);
		String link = null;
		String filePath = null;

		if (!position.equals("http://")) {
			File file = new File(userInpute);
			filePath = file.getAbsolutePath();
			link = ReadFile.OpenFile(filePath);
		} else {
			link = userInpute;
		}
		String[] listString = link.split(";");
		inpute.close();
		for (int i = 0; i < listString.length; i++) {
			screen.getScreenShot(listString[i], i + 1);
		}
		
		
		
	}
}
