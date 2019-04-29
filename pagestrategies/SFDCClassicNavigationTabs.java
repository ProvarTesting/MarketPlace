package pagestrategies;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.provar.core.model.base.api.IRuntimeBrowserContext;
import com.provar.core.model.base.api.IRuntimeConfiguration;
import com.provar.core.model.base.api.IRuntimeConnection;
import com.provar.core.model.base.api.PageStrategyType;
import com.provar.core.testapi.IPageStrategyActivator;
import com.provar.core.testapi.annotations.StrategyLocator;
import com.provar.core.testapi.annotations.TabLocatorStrategy;

@TabLocatorStrategy(title="SF Classic Tab", // Name that will uniquely identify strategy file in TB 
tabName ="{label} Tab", // Default name for the block that is displayed while mapping in TB
priority = Integer.MAX_VALUE, // In case of multiple matches locators will be displayed in priority order
// The Tab content is identified first based on contentLocators xpaths
contentLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="//div[contains(@class, 'bodyDiv brdPalette brandPrimary')]")),
},
// If there is a match for content, then the header of the active tab is identified using headerLocators xpath.
headerLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="//li[contains(@id, 'Tab') and not(contains(@id,'MoreTabs'))]")),
},
// The labelLocators xpaths is appended to headerLocators for identifying the label of the Tab
labelLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="a/text()")),
},
// To identify the currently active tab, appliesIf qualifier is used as not(appliesIf). During execution, findBy xpath is used to activate the Tab 
activators = {
		@StrategyLocator(findBy = @FindBy(xpath="a"), appliesIf = @FindBy(xpath="self::*[not(contains(@class, 'zen-active'))]")),
}
)
public class SFDCClassicNavigationTabs {

}
