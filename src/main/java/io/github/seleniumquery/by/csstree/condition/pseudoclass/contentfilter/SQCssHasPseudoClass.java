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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.contentfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedNotYet;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;

public class SQCssHasPseudoClass extends SQCssFunctionalPseudoClassCondition implements SQCssConditionImplementedNotYet {

    public static final String PSEUDO = "has";

    public SQCssHasPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}