package pagestrategies;

import org.openqa.selenium.support.FindBy;

import com.provar.core.testapi.annotations.StrategyLocator;
import com.provar.core.testapi.annotations.TabLocatorStrategy;

@TabLocatorStrategy(title="SF Lightning Nested Tab Testing",  // Name that will uniquely identify strategy file in TB 
tabName ="{label} Tabs", // Default name for the block that is displayed while mapping in TB, {label} will get replaced with actual label value
priority = Integer.MAX_VALUE, // In case of multiple matches locators will be displayed in priority order
// The Tab content is identified first based on contentLocators xpaths
contentLocators = {		
		@StrategyLocator(findBy = @FindBy(xpath="//div[contains(@class,'region-main')]//section[contains(@class,'tabs__content active')]/div[contains(@class, 'flexipageComponent')]")),
},
// If there is a match for content, then the header of the active tab is identified using headerLocators xpath.
headerLocators = {	
		@StrategyLocator(findBy = @FindBy(xpath="//div[contains(@class, 'windowViewMode-normal') and contains(@class, 'active')]//li[contains(@class, 'tabs__item') and contains(@class, 'uiTabItem')]")),
},
// The labelLocators xpaths is appended to headerLocators for identifying the label of the Tab
labelLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="a/@title")),		
},
// To identify the currently active tab, appliesIf qualifier is used as not(appliesIf). During execution, findBy xpath is used to activate the Tab 
activators = {		
		@StrategyLocator(findBy = @FindBy(xpath="."), appliesIf = @FindBy(xpath="self::*[not(contains(@class, 'active'))]")),
}
)
public class SFDCLightningDetailsTab {

}
