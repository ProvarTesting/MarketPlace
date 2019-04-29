package pagestrategies;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.SearchContext;

import com.provar.core.model.base.api.IRuntimeBrowserContext;
import com.provar.core.model.base.api.IRuntimeConfiguration;
import com.provar.core.model.base.api.IRuntimeConnection;
import com.provar.core.model.base.api.PageStrategyType;
import com.provar.core.testapi.IPageStrategyActivator;
import com.provar.core.testapi.annotations.StrategyLocator;
import com.provar.core.testapi.annotations.TabLocatorStrategy;

@TabLocatorStrategy(title="SF Lightning Tab", // Name that will uniquely identify strategy file in TB 
tabName ="{label} Tab", // Default name for the block that is displayed while mapping in TB, {label} will get replaced with actual label value
priority = Integer.MAX_VALUE, // In case of multiple matches locators will be displayed in priority order
// The Tab content is identified first based on contentLocators xpaths
contentLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="//div[contains(@class, 'windowViewMode-normal') and contains(@class, 'oneContent')]")),
},
// If there is a match for content, then the header of the active tab is identified using headerLocators xpath.
headerLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="//one-app-nav-bar-item-root[contains(@class, 'slds-context-bar__item')]")),
}, 
// The labelLocators xpaths is appended to headerLocators for identifying the label of the Tab
labelLocators = { 
		@StrategyLocator(findBy = @FindBy(xpath="a/@title")),
},
// To identify the currently active tab, appliesIf qualifier is used as not(appliesIf). During execution, findBy xpath is used to activate the Tab 
activators = {
		@StrategyLocator(appliesIf = @FindBy(xpath="self::*[not(contains(@class, 'slds-is-active'))]"), findBy = @FindBy(xpath=".")),
}
) 
// IPageStrategyActivator interface can be implemented for writing custom for activating the required Tab. This is required when it is not possible to activate the Tab with single click.
public class SFDCLightningNavigationTabs implements IPageStrategyActivator{

	@Override
	public boolean doActivateBlock(IRuntimeConfiguration runtimeConfiguration, IRuntimeConnection runtimeConnection,
			IRuntimeBrowserContext browserContext, Object searchContext, Object blockFindBy, String label,
			PageStrategyType strategyType) {
		// Check if tab is active or not
		By activeTabXpath = By.xpath("//one-app-nav-bar-item-root[contains(@class, 'slds-context-bar__item')][a/@title = '"+label+"'][self::*[not(contains(@class, 'slds-is-active'))]]");
		if(((SearchContext)searchContext).findElements(activeTabXpath).size() > 0) {
			// First clicking on More tab
			By moreXpath = By.xpath("//one-app-nav-bar-menu-button[a/span/text()='More']/a");
			List<WebElement> elems = ((SearchContext)searchContext).findElements(moreXpath);
			if(elems.size() == 0) {
				// you can write custom code here
			} else {
				elems.get(0).click();
			}
			// Adding wait for Dropdown to appear
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// then clicking on required tab
			By xpath1 = By.xpath("//one-app-nav-bar-menu-item[contains(@class, 'slds-dropdown__item')][a//span/text() = '"+label+"'][a/span]");
			WebElement activator = null;
			elems = ((SearchContext)searchContext).findElements(xpath1);
			if(elems.size() == 0) {
				// you can write custom code here
			} else {
				activator = elems.get(0);
			}
			// Scrolling to element in dropdown
			((JavascriptExecutor)(browserContext.getWebDriver())).executeScript("arguments[0].scrollIntoViewIfNeeded();", activator);
			activator.click();
		}
		
		return true;
	}

}
