package io.github.seleniumquery.by.css;

import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.CSSSelectorParser;

import java.util.Map;

import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

public class CssSelectorMatcherService {
	
	public static boolean elementMatchesStringSelector(WebDriver driver, WebElement element, String selector) {
		CSSParsedSelectorList CSSParsedSelectorList = CSSSelectorParser.parseSelector(selector);
		SelectorList selectorList = CSSParsedSelectorList.getSelectorList();
        for (int i = 0; i < selectorList.getLength(); i++) {
			if (CssSelectorMatcherService.elementMatchesSelector(driver, element, CSSParsedSelectorList.getStringMap(), selectorList.item(i))) {
				return true;
			}
		}
        return false;
	}

	public static boolean elementMatchesSelector(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selector) {
		CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
		return cssSelector.is(driver, element, stringMap, selector);	
	}

}