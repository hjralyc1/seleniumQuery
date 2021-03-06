package io.github.seleniumquery.by.css.combinators;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;
import io.github.seleniumquery.by.xpath.XPathComponentCompilerService;
import io.github.seleniumquery.by.xpath.component.DescendantGeneralComponent;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * E F
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class DescendantCssSelector implements CssSelector<DescendantSelector, TagComponent> {

	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, DescendantSelector descendantSelector) {
		if (CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, descendantSelector.getSimpleSelector())) {
	
			WebElement ancestor = SelectorUtils.parent(element);
	
			while (ancestor != null) {
				if (CssSelectorMatcherService.elementMatchesSelector(driver, ancestor, stringMap, descendantSelector.getAncestorSelector())) {
					return true;
				}
				ancestor = SelectorUtils.parent(ancestor);
			}
		}
		return false;
	}

	@Override
	public TagComponent toXPath(Map<String, String> stringMap, DescendantSelector descendantSelector) {
		Selector ancestorCSSSelector = descendantSelector.getAncestorSelector();
		TagComponent ancestorCompiled = XPathComponentCompilerService.compileSelector(stringMap, ancestorCSSSelector);
		
		SimpleSelector descendantCSSSelector = descendantSelector.getSimpleSelector();
		TagComponent childrenCompiled = XPathComponentCompilerService.compileSelector(stringMap, descendantCSSSelector);

		return DescendantGeneralComponent.combine(ancestorCompiled, childrenCompiled);
	}
	
}