package com.platzhaltr.flatlinr.api;

import java.util.Map;

public interface Record {

	String getName();

	Map<String, String> getPartials();

	void addPartial(final String name, final String value);

	String getPartial(final String name);
}
