package startPackage;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import DaoPackage.ImageService;
import Entities.ImageTable;

public class screenShot {

	ArrayList<Image> imageList = new ArrayList<>();
	ImageTable imageTable = new ImageTable();
	ImageService imageService = new ImageService();
	
	public void getScreenShot(String linkPath, int number) throws IOException {
		System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
		
		BufferedImage img = null;
		File newFile = new File("./sources/Photo" + number + ".png");
		WebDriver driver = new ChromeDriver();
		
		driver.get(linkPath);
//		driver.manage().window().maximize();
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File soucrs = ts.getScreenshotAs(OutputType.FILE);		
		FileUtils.copyFile(soucrs, newFile);
		img = ImageIO.read(newFile);
		imageTable.setLinks(linkPath);
		try {
			imageTable.setImage(ConvertFile(img));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageService.insert(imageTable);
//		imageList.add(img);
//		FileUtils.forceDelete(newFile);
//		FileUtils.copyFile(soucrs, new File("./sources/Photo" + number + ".png"));
		driver.close();
	}
	
	private byte[] ConvertFile(BufferedImage image) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( image, "jpg", baos );
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}

}
