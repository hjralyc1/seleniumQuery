package io.github.seleniumquery.by.css.conditionals;

import com.steadystate.css.parser.selectors.ConditionalSelectorImpl;
import io.github.seleniumquery.by.css.CssConditionalSelector;
import io.github.seleniumquery.by.xpath.component.ConditionComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * E.firstCondition.secondCondition
 *
 * see {@link Condition#SAC_AND_CONDITION}
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class AndConditionalCssSelector implements CssConditionalSelector<CombinatorCondition, ConditionComponent> {

	private ConditionalCssSelector conditionalEvaluator;
	
	public AndConditionalCssSelector(ConditionalCssSelector conditionalEvaluator) {
		this.conditionalEvaluator = conditionalEvaluator;
	}

	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
																					(SimpleSelector) selectorUpToThisPoint,
																						combinatorCondition.getFirstCondition());
		
		return conditionalEvaluator.isCondition(driver, element, stringMap, selectorUpToThisPoint, combinatorCondition.getFirstCondition())
		    && conditionalEvaluator.isCondition(driver, element, stringMap, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition());
	}

	@Override
	public ConditionComponent conditionToXPath(Map<String, String> stringMap, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
				(SimpleSelector) selectorUpToThisPoint,
				combinatorCondition.getFirstCondition());

		ConditionComponent firstCondition = conditionalEvaluator.conditionToXPath(stringMap, selectorUpToThisPoint, combinatorCondition.getFirstCondition());
		ConditionComponent secondCondition = conditionalEvaluator.conditionToXPath(stringMap, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition());
		return firstCondition.cloneAndCombineTo(secondCondition);
	}

}