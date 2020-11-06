package customapis;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.provar.core.model.base.api.ValueScope;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.*;

@TestApi( title="QR Code Scan"
        , summary=""
        , remarks=""
        , iconBase=""
        , defaultApiGroups={"Custom"}
        )
@TestApiParameterGroups(parameterGroups={
	    @TestApiParameterGroup(groupName="inputs", title="Inputs"),
	    @TestApiParameterGroup(groupName="result", title="Result"),
	    })
public class QRCodeScan {
    
    @TestApiParameter(seq=1, 
            summary="The first parameter's summary.",
            remarks="",
            mandatory=true,
            parameterGroup="inputs")
    public String qrCodeImage;
    
  

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
    public void execute() throws MalformedURLException, IOException, NotFoundException {

    	 BufferedImage bufferedImage;

         // if not (probably it is a URL
         if (((String) qrCodeImage).contains("http")) {
             bufferedImage = ImageIO.read((new URL((String)qrCodeImage)));
             testLogger.info("++++++URL++++");
             // if is a Base64 String
         } else {
        	 String base64String = ((String) qrCodeImage).split(",")[1];
             byte[] decoded = Base64.decodeBase64(base64String);
             bufferedImage = ImageIO.read(new ByteArrayInputStream(decoded));
         }
         testLogger.info("++++++Lum++++");
         LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
         testLogger.info("++++++Binary+++");
         BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

         Result resultBitMap = new MultiFormatReader().decode(bitmap);
         testLogger.info("++++++resultbitmap++++");
         String result=resultBitMap.getText();
         testLogger.info("++++++result++++");
        // Store the result (if appropriate).
    	//String dummyResult = this.getClass().getName() + " result";
        testExecutionContext.setValue(resultName, result, resultScope);
        
    }
    
}
