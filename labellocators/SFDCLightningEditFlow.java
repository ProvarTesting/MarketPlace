package pagestrategies;

import org.openqa.selenium.support.FindBy;

import com.provar.core.testapi.annotations.LabelLocatorStrategy;
import com.provar.core.testapi.annotations.StrategyLocator;

@LabelLocatorStrategy(title = "SF Lightning Edit Label", // Name that will uniquely identify strategy file in TB 
		priority = Integer.MAX_VALUE, // In case of multiple matches locators will be displayed in priority order
		// labelLocators xpaths will be appended to control locator to identify label 
		labelLocators = { 
				@StrategyLocator(name = "a", findBy = @FindBy(xpath = "label[contains(@class, 'label')]/span")),
				@StrategyLocator(name = "b", findBy = @FindBy(xpath="a[contains(@class,'rowLink')]/div[contains(@class, 'middle')]/div[contains(@class, 'fields')]//ul/li//a")),
				@StrategyLocator(name = "c", findBy = @FindBy(xpath = "span[contains(@class, 'label')]/span")),
				@StrategyLocator(name = "d", findBy = @FindBy(xpath = "div/span[contains(@class, 'label')]")),
				},
		// controlLocators xpaths are matched with current node and for correct match label xpath is appended to find the label locator.
		// preceding should be true in case control is preceding label
		controlLocators = {				
				@StrategyLocator(name = "input", findBy = @FindBy(xpath = "input[contains(@class, 'input')]")),
				@StrategyLocator(name = "check", findBy = @FindBy(xpath="div[contains(@class,'left slds-float--left')]//label[contains(@class, 'slds-checkbox')]//span[contains(@class,'slds-checkbox--faux')]"), preceding = true),
				@StrategyLocator(name = "radio", findBy = @FindBy(xpath="div/span[contains(@class, 'slds-radio--faux')]", preceding = true)),
				@StrategyLocator(name = "link", findBy = @FindBy(xpath = "div[contains(@class,'uiMenu')]//a")),
				})
public class SFDCLightningEditFlow {
}
