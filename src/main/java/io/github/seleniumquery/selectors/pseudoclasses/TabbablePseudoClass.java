package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :tabbable elements are all those :focusable, except those that have a negative tabindex.
 * 
 * From http://api.jqueryui.com/tabbable-selector/: "Elements with a negative tab index are :focusable, but not :tabbable."
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class TabbablePseudoClass implements PseudoClass {
	
	private static final TabbablePseudoClass instance = new TabbablePseudoClass();
	public static TabbablePseudoClass getInstance() {
		return instance;
	}
	private TabbablePseudoClass() { }
	
	private static final String TABBABLE_PSEUDO_CLASS_NO_COLON = "tabbable";
	private static final FocusablePseudoClass focusable = FocusablePseudoClass.getInstance();
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return TABBABLE_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		boolean isFocusable = focusable.isPseudoClass(driver, element, pseudoClassSelector);
		if (!isFocusable) {
			return false;
		}
		// at this point, it is focusable!
		String tabindex = element.getAttribute("tabindex");
		if (tabindex == null) {
			return true;
		}
		// at this point, there is a tabindex
		boolean tabindexIsNegativeInteger = tabindex.matches("\\s*-\\d+\\s*");
		return !tabindexIsNegativeInteger;
	}
	
	private static final CssFilter tabbablePseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// no browser supports :tabbable natively
		return CompiledCssSelector.createFilterOnlySelector(tabbablePseudoClassFilter);
	}
	
}