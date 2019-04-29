package pagestrategies;

import org.openqa.selenium.support.FindBy;

import com.provar.core.testapi.annotations.PageSectionLocatorStrategy;
import com.provar.core.testapi.annotations.StrategyLocator;

@PageSectionLocatorStrategy(title="SF Classic Page Section",// Name that will uniquely identify strategy file in TB 
sectionName ="{label}PageSection", // Default name for the block that is displayed while mapping in TB
priority = Integer.MAX_VALUE, // In case of multiple matches locators will be displayed in priority order
// The page section are identified based on the sectionLocators xpaths.
sectionLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="//div[contains(@class, 'pbSubsection')]")),
},
// If there is match for section, then its label is identified based on labelLocators xpath.
labelLocators = {
		@StrategyLocator(findBy = @FindBy(xpath="preceding-sibling::div[contains(@class, 'pbSubheader')][1]//h3")),
},
// While execution, first section is checked it is collapsed based on appliesIf xpath and to expand findBy xpath is used.
expanders = {
		@StrategyLocator(findBy = @FindBy(xpath="//preceding-sibling::div[contains(@class, 'pbSubheader')][1]//img"), appliesIf = @FindBy(xpath="preceding-sibling::div[contains(@class, 'pbSubheader')][1]/img[contains(@class,'showListButton')]")),
}
)
public class SFDCClassicSection {
}

