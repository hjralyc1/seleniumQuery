package io.github.seleniumquery.by.xpath;

import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a list of {@link io.github.seleniumquery.by.xpath.component.XPathComponent}. In other words, multiple XPath expressions
 * that should be "composed" or "merged" to become a single expression.
 * 
 * @since 0.9.0
 * 
 * @author acdcjunior
 * @author ricardo-sc
 */
public class TagComponentList {
	
	private static final String XPATH_EXPRESSION_OR = " | ";
	private static final String XPATH_CONDITIONAL_OR = ") or (";
	
	private List<TagComponent> tagComponents;
	
	TagComponentList(List<TagComponent> tagComponents) {
		this.tagComponents = tagComponents;
	}
	
	public List<WebElement> findWebElements(SearchContext context) {
		Set<WebElement> elements = new LinkedHashSet<WebElement>();
		for (TagComponent tagComponent : tagComponents) {
			List<WebElement> elementsFound = tagComponent.findWebElements(context);
			elements.addAll(elementsFound);
		}
		return new ArrayList<WebElement>(elements);
	}
	
	public String toXPath() {
		StringBuilder compoundXPathExpression = new StringBuilder();
		for (TagComponent tagComponent : tagComponents) {
			compoundXPathExpression.append(tagComponent.toXPath()).append(XPATH_EXPRESSION_OR);
		}
		removeTrailingExpressionOr(compoundXPathExpression);
		return compoundXPathExpression.toString(); 
	}

	/**
	 * Removes a trailing XPATH_EXPRESSION_OR, if necessary.
	 */
	private void removeTrailingExpressionOr(StringBuilder compoundXPathExpression) {
		if (!tagComponents.isEmpty()) {
			int xPathExpressionLength = compoundXPathExpression.length();
			compoundXPathExpression.delete(xPathExpressionLength - XPATH_EXPRESSION_OR.length(), xPathExpressionLength);
		}
	}
	
	public String toXPathCondition() {
		StringBuilder compoundXPathCondition = new StringBuilder();
		compoundXPathCondition.append('(');
		for (TagComponent tagComponent : tagComponents) {
			compoundXPathCondition.append(tagComponent.toXPathCondition()).append(XPATH_CONDITIONAL_OR);
		}
		removeTrailingConditionalOr(compoundXPathCondition);
		compoundXPathCondition.append(')');
		return compoundXPathCondition.toString(); 
	}

	/**
	 * Removes a trailing XPATH_CONDITIONAL_OR, if necessary.
	 */
	private void removeTrailingConditionalOr(StringBuilder sb) {
		if (!tagComponents.isEmpty()) {
			sb.delete(sb.length()-XPATH_CONDITIONAL_OR.length(), sb.length());
		}
	}

}