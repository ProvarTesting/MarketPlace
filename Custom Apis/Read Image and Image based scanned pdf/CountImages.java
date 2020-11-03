package customapis;

import java.io.File;
import java.util.logging.Logger;

import com.provar.core.model.base.api.ValueScope;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.*;

@TestApi(title = "Count Images", summary = "", remarks = "", iconBase = "", defaultApiGroups = { "Custom" })
@TestApiParameterGroups(parameterGroups = { @TestApiParameterGroup(groupName = "inputs", title = "Inputs"),
		@TestApiParameterGroup(groupName = "result", title = "Result"), })
public class CountImages {

	@TestApiParameter(seq = 1, summary = "The first parameter's summary.", remarks = "", mandatory = true, parameterGroup = "inputs")
	public String FolderLocation;

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
		int count = 0;
		// Put our implementation logic here.
		try {
			testLogger.info("Hello from " + this.getClass().getName());
			String projectPath = testExecutionContext.getTestProjectPath();
			testLogger.info(projectPath + File.separator + FolderLocation);
			File f = new File(projectPath + File.separator + FolderLocation);

			for (File file : f.listFiles()) {
				if (file.isFile() && file.getName().endsWith(".jpg")) {
					count++;
				}
			}
			System.out.println("Number of files: " + count);
		} catch (Exception e) {
			testLogger.info(e.getMessage());
			testLogger.info(e.toString());
			e.printStackTrace();

		}
		// Store the result (if appropriate).
		String dummyResult = this.getClass().getName() + " result";
		testExecutionContext.setValue(resultName, count, resultScope);

	}

}
