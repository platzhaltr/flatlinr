/*
 * Copyright 2011 Oliver Schrenk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.platzhaltr.flatlinr.core;

import com.platzhaltr.flatlinr.api.Leaf;
import com.platzhaltr.flatlinr.api.TraverseStrategy;

/**
 * Determines the selection of the next {@link Node}. Contains default methods
 * to match a leaf against the start of the current (sub-)line.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public abstract class BaseTraverseStrategy implements TraverseStrategy {

	/**
	 * Checks if the leaf matches the start of the line.
	 * 
	 * @param leaf
	 *            the leaf
	 * @param onlyLeaf
	 *            the only leaf
	 * @param line
	 *            the line
	 * @return true, if leaf is matching
	 */
	protected boolean isMatchingLeaf(final Leaf leaf, final boolean onlyLeaf,
			final String line) {
		if (leaf instanceof ConstantLeaf) {
			final ConstantLeaf constantLeaf = (ConstantLeaf) leaf;
			return line.startsWith(constantLeaf.getConstant());
		} else if (leaf instanceof DelimitedLeaf) {
			final DelimitedLeaf delimitedLeaf = (DelimitedLeaf) leaf;
			return onlyLeaf
					|| (line.indexOf(delimitedLeaf.getDelimiter()) >= 0);
		}

		throw new IllegalStateException("No matching leaf found");
	}

}
