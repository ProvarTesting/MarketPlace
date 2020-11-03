package customapis;

import java.util.logging.Logger;

import com.provar.core.model.base.api.ValueScope;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

@TestApi(title = "Conver Pdf To Image", summary = "", remarks = "", iconBase = "", defaultApiGroups = { "Custom" })
@TestApiParameterGroups(parameterGroups = { @TestApiParameterGroup(groupName = "inputs", title = "Inputs"),
		@TestApiParameterGroup(groupName = "result", title = "Result"), })
public class ConverPdfToImage {

	@TestApiParameter(seq = 1, summary = "The first parameter's summary.", remarks = "", mandatory = true, parameterGroup = "inputs")
	public String PdfLocation;

	@TestApiParameter(seq = 2, summary = "The second parameter's summary.", remarks = "", mandatory = true, parameterGroup = "inputs")
	public String ImageStorageLocation;

	@TestApiParameter(seq = 10, summary = "The name that the result will be stored under.", remarks = "", mandatory = true, parameterGroup = "result")
	public String resultName;

	@TestApiParameter(seq = 11, summary = "The lifespan of the result.", remarks = "", mandatory = true, parameterGroup = "result", defaultValue = "Test")
	public ValueScope resultScope;

	/**
	 * Used to write to the test execution log.
	 */
	@TestLogger
	public Logger testLogger;

	/**
	 * Provides access to facilities, mainly to set and get variable values.
	 */
	@TestExecutionContext
	public ITestExecutionContext testExecutionContext;

	@TestApiExecutor
	public void execute() {

		// Put our implementation logic here.
		testLogger.info("Hello from " + this.getClass().getName());

		try {
			String projectPath = testExecutionContext.getTestProjectPath();
			testLogger.info(projectPath);
			String sourceDir = projectPath + File.separator + PdfLocation;
			testLogger.info(sourceDir);
			String destinationDir = projectPath + File.separator + ImageStorageLocation + File.separator;
			testLogger.info(destinationDir);
			File sourceFile = new File(sourceDir);
			File destinationFile = new File(destinationDir);
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
			}
			if (sourceFile.exists()) {
				System.out.println("Images copied to Folder: " + destinationFile.getName());
				PDDocument document = PDDocument.load(sourceDir);
				List<PDPage> list = document.getDocumentCatalog().getAllPages();
				System.out.println("Total files to be converted -> " + list.size());

				String fileName = sourceFile.getName().replace(".pdf", "");
				int pageNumber = 1;
				for (PDPage page : list) {
					BufferedImage image = page.convertToImage();
					File outputfile = new File(destinationDir + fileName + "_" + pageNumber + ".jpg");
					System.out.println("Image Created -> " + outputfile.getName());
					ImageIO.write(image, "png", outputfile);
					pageNumber++;
				}
				document.close();
				System.out.println("Converted Images are saved at -> " + destinationFile.getAbsolutePath());
			} else {
				System.err.println(sourceFile.getName() + " File not exists");
				testLogger.info("Do Again");
			}

		} catch (Exception e) {
			e.printStackTrace();
			testLogger.info("catch error");
		}
		// Store the result (if appropriate).
		String dummyResult = this.getClass().getName() + " result";
		testExecutionContext.setValue(resultName, dummyResult, resultScope);

	}

}
