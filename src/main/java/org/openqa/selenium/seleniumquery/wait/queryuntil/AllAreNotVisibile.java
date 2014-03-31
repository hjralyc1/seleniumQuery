package org.openqa.selenium.seleniumquery.wait.queryuntil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;

import com.google.common.base.Function;

public class AllAreNotVisibile {
	
	public static List<WebElement> queryUntilAllAreNotVisible(final SeleniumQueryObject seleniumQueryObject) {
		return SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, List<WebElement>>() {
			@Override
			public List<WebElement> apply(By selector) {
				List<WebElement> elements = seleniumQueryObject.getWebDriver().findElements(seleniumQueryObject.getBy());
				for (WebElement webElement : elements) {
					if (webElement.isDisplayed()) {
						return null;
					}
				}
				return elements;
			}
		}, "to have all elements not be not visible.");
	}

}