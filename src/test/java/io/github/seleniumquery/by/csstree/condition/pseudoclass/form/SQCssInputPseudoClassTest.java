/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.csstree.condition.pseudoclass.form;

import org.junit.Test;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertLocatorUtils.assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssInputPseudoClassTest {

    public static final String INPUT_PSEUDO = ":input";
    public static final String INPUT_XPATH_EXPRESSION = ".//*[(self::input or self::button or self::select or self::textarea)]";

    @Test
    public void translate() {
        assertPseudo(INPUT_PSEUDO, SQCssInputPseudoClass.class);
    }

    @Test
    public void toSQLocator__when_driver_does_NOT_have_native_support() {
        assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport(
                new SQCssInputPseudoClass(),
                INPUT_PSEUDO,
                INPUT_XPATH_EXPRESSION
        );
    }

}