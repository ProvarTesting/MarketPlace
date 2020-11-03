package customapis;


import java.util.logging.Logger;
import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;
import com.provar.core.model.base.api.ValueScope;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.*;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.bytedeco.javacpp.tesseract.Tesseract;

@TestApi( title="Read Image"
        , summary=""
        , remarks=""
        , iconBase=""
        , defaultApiGroups={"Custom"}
        )
@TestApiParameterGroups(parameterGroups={
	    @TestApiParameterGroup(groupName="inputs", title="Inputs"),
	    @TestApiParameterGroup(groupName="result", title="Result"),
	    })
public class ReadImage {
    
    @TestApiParameter(seq=1, 
            summary="The first parameter's summary.",
            remarks="",
            mandatory=true,
            parameterGroup="inputs")
    public String ImageLocation;
    


    @TestApiParameter(seq=10, 
            summary="The name that the result will be stored under.",
            remarks="",
            mandatory=true,
            parameterGroup="result")
    public String resultName;

    @TestApiParameter(seq=11, 
            summary="The lifespan of the result.",
            remarks="",
            mandatory=true,
            parameterGroup="result",
            defaultValue="Test")
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
    	String ProjectPath = testExecutionContext.getProjectPath();
		testLogger.info(ProjectPath);
		// Put our implementation logic here.
		testLogger.info("Hello from " + this.getClass().getName());
		testLogger.info("Hello from " + this.getClass().getName());
		BytePointer outText;

		TessBaseAPI api = new TessBaseAPI();
		// Initialize tesseract-ocr with English, without specifying tessdata path

		if (api.Init(ProjectPath + "\\tessdata", "ENG") != 0) {
			System.err.println("Could not initialize tesseract.");
			System.exit(1);
		}

		// Open input image with leptonica library
		PIX image = pixRead(ImageLocation);
		api.SetImage(image);
		// Get OCR result
		outText = api.GetUTF8Text();
		String string = outText.getString();

		testLogger.info("Custom Api Output is :\n" + string);

		// Destroy used object and release memory
		api.End();
		outText.deallocate();
		pixDestroy(image);
		// Store the result (if appropriate).
		String dummyResult = string;
		testExecutionContext.setValue(resultName, dummyResult, resultScope);

	
        
    }
    
}
