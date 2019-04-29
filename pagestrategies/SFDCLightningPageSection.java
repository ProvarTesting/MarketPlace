package pagestrategies;

import org.openqa.selenium.support.FindBy;

import com.provar.core.testapi.annotations.PageSectionLocatorStrategy;
import com.provar.core.testapi.annotations.StrategyLocator;

@PageSectionLocatorStrategy(title="SF Lightning Section",  // Name that will uniquely identify strategy file in TB 
sectionName ="{label} Section",// Default name for the block that is displayed while mapping in TB, {label} will get replaced with actual label value
priority = Integer.MAX_VALUE, // In case of multiple matches locators will be displayed in priority order
// The content of the sections is identified by the sectionLocators xpath
sectionLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="//div[contains(@class, 'forcePageBlockSection')]")),
},
// The labelLocators xpaths are evaluated relative to the section to determine the label of the section
labelLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="h3//span[contains(@class, '__section-header-title')]")),
},
// The appliesIf xpath is used during execution to identify if we need to expand the section, if yes then click operation is performed on the element evaluated by findBy xpath.
expanders = {
		@StrategyLocator(findBy = @FindBy(xpath="h3/button/lightning-icon"), appliesIf = @FindBy(xpath="self::div[not(contains(@class, 'slds-is-open'))]")),
}
)
public class SFDCLightningPageSection {
}
