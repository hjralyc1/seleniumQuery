package io.github.seleniumquery.by.parser.translator.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.csstree.selector.combinator.SQCssGeneralAdjacentSelector;
import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssGeneralAdjacentSelectorTranslatorTest {

    @Test
    public void translate() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a ~ b").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssGeneralAdjacentSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssGeneralAdjacentSelector) cssSelector).getLeftSideSelector();
        SQCssSelector descendantSelector = ((SQCssGeneralAdjacentSelector) cssSelector).getRightSideSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) descendantSelector).getTagName(), is("b"));
    }

}