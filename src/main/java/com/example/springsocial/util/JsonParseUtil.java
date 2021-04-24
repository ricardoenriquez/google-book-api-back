package com.example.springsocial.util;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

public class JsonParseUtil {

	private DocumentContext json;

	public JsonParseUtil(String msg) {
		final Configuration configuration = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS)
				.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);

		json = JsonPath.parse(msg, configuration);
	}

	/**
	 * Extracts the value of the JSON for the given path.
	 *
	 * @param path The path.
	 * @return The value.
	 */
	public <T> T read(final String path) {
		return json.read(path);
	}

	/**
	 * Extracts the value of the JSON for the given path.
	 *
	 * @param path  The path.
	 * @param clazz The expected type.
	 * @return The value.
	 */
	public <T> T read(final String path, final Class<T> clazz) {
		return json.read(path, clazz);
	}

	/**
	 * Extracts the value of the JSON for the given root path.
	 *
	 * @param root  The root path.
	 * @param path  The child path.
	 * @param clazz The expected type.
	 * @return The value.
	 */
	public <T> T read(final String root, final String path, final Class<T> clazz) {
		return read(root + "." + path, clazz);
	}

}
