package customapis;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Logger;

import com.provar.core.model.base.api.ValueScope;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.TestApi;
import com.provar.core.testapi.annotations.TestApiExecutor;
import com.provar.core.testapi.annotations.TestApiParameter;
import com.provar.core.testapi.annotations.TestApiParameterGroup;
import com.provar.core.testapi.annotations.TestApiParameterGroups;
import com.provar.core.testapi.annotations.TestExecutionContext;
import com.provar.core.testapi.annotations.TestLogger;

@TestApi( title="Get First Date"
        , summary=""
        , remarks=""
        , iconBase=""
        , defaultApiGroups={"Custom"}
        )
@TestApiParameterGroups(parameterGroups={
	    @TestApiParameterGroup(groupName="inputs", title="Inputs"),
	    @TestApiParameterGroup(groupName="result", title="Result"),
	    })
public class GetFirstDate {
    
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

    	// Put our implementation logic here.
    	testLogger.info("Executing Get First Date Of Current Month " + this.getClass().getName());
    	
    	LocalDate todaydate = LocalDate.now();
    	testLogger.info("Month's first date in yyyy-mm-dd: " + todaydate.withDayOfMonth(1));

        testExecutionContext.setValue(resultName,todaydate.withDayOfMonth(1).toString(), resultScope);
        
    }
    
}
