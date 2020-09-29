package customapis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import com.provar.core.model.base.api.ValueScope;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.*;

@TestApi(title = "Delete File", summary = "", remarks = "", iconBase = "", defaultApiGroups = { "Custom" })
@TestApiParameterGroups(parameterGroups = { @TestApiParameterGroup(groupName = "inputs", title = "Inputs"),
		@TestApiParameterGroup(groupName = "result", title = "Result"), })
public class DeleteFile {

	@TestApiParameter(seq = 1, summary = "The first parameter's summary.", remarks = "", mandatory = true, parameterGroup = "inputs")
	public String fileName;

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

	FileWriter fileWriter = null;

	File reportsDir;

	@TestApiExecutor
	public void execute() {

		String dummyResult = "false";

		reportsDir = new File(testExecutionContext.getProjectPath() + File.separator + "templates");

		int c = 1;

		testLogger.info("Going to directory--" + reportsDir);
		
		File file = new File(reportsDir + File.separator + fileName);
		try {
			while (c <= 5) {
				if (file.delete()) {
					testLogger.info("---Deleted file---" + reportsDir + File.separator + fileName);
					dummyResult = "true";
					break;
				}
				testLogger.info(
						"---Deleting file---" + reportsDir + File.separator + fileName + " " + "attempt:" + " " + c);
				testLogger.info("---" + fileName + "---" + "may not be present in the directory");
				c++;
			}
		} catch (Exception e) {
			testLogger.info("--Exception Occured is --" + e);
		} finally {
			
			testExecutionContext.setValue(resultName, dummyResult, resultScope);

		}
	}
}
