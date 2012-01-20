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
package com.platzhaltr.flatlinr.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

/**
 * The Class MogrifiedReaderMaker.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class MogrifiedReaderMaker {

	/** The omit lines builder. */
	private final OmitLinesBuilder omitLinesBuilder;

	/** The transform lines builder. */
	private final TransformLinesBuilder transformLinesBuilder;

	/**
	 * Instantiates a new mogrified reader maker.
	 */
	public MogrifiedReaderMaker() {
		omitLinesBuilder = new OmitLinesBuilder(this);
		transformLinesBuilder = new TransformLinesBuilder(this);
	}

	/**
	 * Omit lines.
	 *
	 * @return the omit lines builder
	 */
	public OmitLinesBuilder omitLines() {
		return omitLinesBuilder;
	}

	/**
	 * Transform lines.
	 *
	 * @return the transform lines builder
	 */
	public TransformLinesBuilder transformLines() {
		return transformLinesBuilder;
	}

	/**
	 * Builds the.
	 *
	 * @param reader
	 *            the reader
	 * @return the reader
	 */
	public Reader wrap(final Reader reader) {
		if (!omitLinesBuilder.getPredicates().isEmpty()) {
			final Predicate<String> filter = Predicates.<String>not(Predicates
					.<String>or(omitLinesBuilder.getPredicates()));
			return new PredicateFilterReader(reader, filter);
		}

		return null;
	}

	public Reader read(final File file) throws FileNotFoundException {
		return wrap(new FileReader(file));
	}

	/**
	 * The Class OmitLinesBuilder.
	 *
	 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
	 */
	protected static class OmitLinesBuilder {

		private final List<Predicate<String>> predicates = Lists
				.newLinkedList();

		/** The mogrified reader maker. */
		private final MogrifiedReaderMaker mogrifiedReaderMaker;

		/**
		 * Instantiates a new omit lines builder.
		 *
		 * @param mogrifiedReaderMaker
		 *            the mogrified reader maker
		 */
		private OmitLinesBuilder(final MogrifiedReaderMaker mogrifiedReaderMaker) {
			this.mogrifiedReaderMaker = mogrifiedReaderMaker;
		}

		/**
		 * Starting with.
		 *
		 * @param prefix
		 *            the prefix
		 * @return the filtered reader maker
		 */
		public MogrifiedReaderMaker startingWith(final String prefix) {

			predicates.add(new StartingWithPredicate(prefix));

			return mogrifiedReaderMaker;
		}

		/**
		 * Ending with.
		 *
		 * @param suffix
		 *            the suffix
		 * @return the filtered reader maker
		 */
		public MogrifiedReaderMaker endingWith(final String suffix) {

			predicates.add(new EndingWithPredicate(suffix));

			return mogrifiedReaderMaker;
		}

		/**
		 * Containing.
		 *
		 * @param needle
		 *            the needle
		 * @return the filtered reader maker
		 */
		public MogrifiedReaderMaker containing(final String needle) {

			predicates.add(new ContainingPredicate(needle));

			return mogrifiedReaderMaker;
		}

		/**
		 * Matching.
		 *
		 * @param regex
		 *            the regex
		 * @return the filtered reader maker
		 */
		public MogrifiedReaderMaker matching(final String regex) {

			predicates.add(new MatchingPredicate(regex));

			return mogrifiedReaderMaker;
		}

		/**
		 * Gets the predicates.
		 *
		 * @return the predicates
		 */
		protected List<Predicate<String>> getPredicates() {
			return predicates;
		}

	}

	/**
	 * The Class TransformLinesBuilder.
	 *
	 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
	 */
	protected static class TransformLinesBuilder {

		/** The mogrified reader maker. */
		private final MogrifiedReaderMaker mogrifiedReaderMaker;

		/** The functions. */
		private final List<Function<String, String>> functions = Lists
				.newLinkedList();

		/**
		 * Instantiates a new transform lines builder.
		 *
		 * @param mogrifiedReaderMaker
		 *            the mogrified reader maker
		 */
		public TransformLinesBuilder(
				final MogrifiedReaderMaker mogrifiedReaderMaker) {
			this.mogrifiedReaderMaker = mogrifiedReaderMaker;
		}

		/**
		 * By replacing.
		 *
		 * @param oldString
		 *            the old string
		 * @param newString
		 *            the new string
		 * @return the mogrified reader maker
		 */
		public MogrifiedReaderMaker byReplacing(final String oldString,
				final String newString) {

			functions.add(new ReplaceFunction(oldString, newString));

			return mogrifiedReaderMaker;
		}
	}

}
