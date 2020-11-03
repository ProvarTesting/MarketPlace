package pageobjects;

import java.io.File;

import org.openqa.selenium.WebDriver;

import com.provar.core.testapi.annotations.Page;

@Page(title = "Verify Downloaded File", summary = "", relativeUrl = "", connection = "MyConn")
public class VerifyDownloadedFile {

	WebDriver driver;

	public VerifyDownloadedFile(WebDriver driver) {
		this.driver = driver;
	}

// To verify the file downloaded in Downloads folder of the system.
//Filename should be with extension.
	public String VerifyFileDownloaded(String FileName) {
		String flag = "false";
		String home = System.getProperty("user.home");
		String path = home + "/Downloads/";
		File dir = new File(path);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(FileName))
				return flag = "true";
		}

		return flag;
	}
}
