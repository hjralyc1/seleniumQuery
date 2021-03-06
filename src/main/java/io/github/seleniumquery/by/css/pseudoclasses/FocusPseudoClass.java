package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:focus
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class FocusPseudoClass implements PseudoClass<ConditionSimpleComponent> {

	private static final String FOCUS_PSEUDO_CLASS_NO_COLON = "focus";

    public static final ElementFilter FOCUS_FILTER = new PseudoClassFilter(new FocusPseudoClass());

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FOCUS_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		WebElement currentlyActiveElement = driver.switchTo().activeElement();
		return element.equals(currentlyActiveElement);
	}

	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		UnsupportedXPathPseudoClassException.xPathFiltersAreNotImplementedYed(":focus");
		
		// #no-xpath
		return new ConditionSimpleComponent(FOCUS_FILTER);
	}
	
}