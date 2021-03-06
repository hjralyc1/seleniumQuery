package io.github.seleniumquery.by.css.combinators;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;
import io.github.seleniumquery.by.xpath.XPathComponentCompilerService;
import io.github.seleniumquery.by.xpath.component.AdjacentComponent;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.SiblingSelector;

import java.util.List;
import java.util.Map;

/**
 * E ~ PRE
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class GeneralAdjacentCssSelector implements CssSelector<SiblingSelector, TagComponent> {

	/**
	 * http://www.w3.org/TR/css3-selectors/#general-sibling-combinators
	 * <div class="example">
	 *		<p>Example:</p>
	 *    
	 *		<pre>h1 ~ pre</pre>
	 *       
	 *      <p>represents a <code>pre</code> element following an <code>h1</code>. It is a correct and valid, but partial, description of:</p>
	 *      <pre>&lt;h1&gt;Definition of the function a&lt;/h1&gt;
	 *      &lt;p&gt;Function a(x) has to be applied to all figures in the table.&lt;/p&gt;
	 *      &lt;pre&gt;function a(x) = 12x/13.5&lt;/pre&gt;</pre>
	 * </div>
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		boolean elementMatchesSelectorSecondPart = CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, siblingSelector.getSiblingSelector());
		if (!elementMatchesSelectorSecondPart) {
			return false;
		}
		
		List<WebElement> previousSiblings = SelectorUtils.getPreviousSiblings(element);
		for (WebElement previousSibling : previousSiblings) {
			boolean previousSiblingMatchesSelectorFirstPart = CssSelectorMatcherService.elementMatchesSelector(driver, previousSibling, stringMap, siblingSelector.getSelector());
			if (previousSiblingMatchesSelectorFirstPart) {
				return true;
			}
		}
		return false;
	}

	@Override
	public TagComponent toXPath(Map<String, String> stringMap, SiblingSelector siblingSelector) {
		TagComponent previousElementCompiled = XPathComponentCompilerService.compileSelector(stringMap, siblingSelector.getSelector());
		TagComponent siblingElementCompiled = XPathComponentCompilerService.compileSelector(stringMap, siblingSelector.getSiblingSelector());

		return AdjacentComponent.combine(previousElementCompiled, siblingElementCompiled);
	}

}