package pagestrategies;

import org.openqa.selenium.support.FindBy;

import com.provar.core.testapi.annotations.DialogLocatorStrategy;
import com.provar.core.testapi.annotations.StrategyLocator;

@DialogLocatorStrategy(title="SF Lightning Dialog", // Name that will uniquely identify strategy file in TB
dialogName="LightningDialog",// Default name for the block that is displayed while mapping in TB
priority = Integer.MAX_VALUE, // In case of multiple matches locators will be displayed in priority order
// The Dialog is identified based on the dialogLocators xpath.
dialogLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="//div[contains(@class,'slds-modal slds-fade-in-open')]")),
},
// The labelLocators are optional for Dialog, If present in the strategy file, then the label is identified based on the the labelLocators xpath.
labelLocators = {
		@StrategyLocator(findBy = @FindBy(xpath=".//div[contains(@class, 'slds-modal__header')]/h2")),
}
)
public class SFDCLightningDialog {

}
