package common;

import java.io.IOException;
import java.util.HashMap;

public class GlobalVariable {
	private static HashMap<String, Object> globalVariableMap = new HashMap<String, Object>();

	/**
	 * Save key with Object Value
	 * 
	 * @param key
	 * 			String key
	 * 
	 * @param value
	 * 			String value
	 */
	public static void put(String key, Object value) {
		globalVariableMap.put(key, value);
	}

	/**
	 * Get key value
	 * 
	 * @param key
	 * 			String key
	 * 
	 * @return key value
	 */
	public static Object getValue(String key) {
		return globalVariableMap.get(key);
	}

	/**
	 * Remove all keys from the global variable
	 *
	 * @throws IOException
	 */
	public static void clearAll() throws IOException {
		globalVariableMap.clear();
	}

	/**
	 * Check if the key exist
	 *
	 * @param key
	 * 			String key
	 *
	 * @return Boolean
	 */
	public static Boolean isKeyExist(String key) {
		return globalVariableMap.containsKey(key);

	}

	/**
	 * Check if there's no data in the global variable
	 *
	 * @return Boolean
	 */
	public static Boolean isEmpty() {
		return globalVariableMap.isEmpty();

	}

	/**
	 * Remove the saved key
	 *
	 * @param key
	 * 			String key
	 */
	public static void remove(String key) {
		globalVariableMap.remove(key);
	}

}
