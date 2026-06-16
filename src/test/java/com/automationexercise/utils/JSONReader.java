package com.automationexercise.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Reads key/value test data from JSON files under src/test/resources/testData/.
 *
 * Files are loaded from the classpath (getResourceAsStream), so this works
 * regardless of the current working directory or OS — unlike hard-coded
 * Windows-style relative paths (e.g. "src\\test\\resources\\testData\\ExistingUser.json")
 * that break on Linux/CI when the JVM's working directory isn't the project root.
 *
 * Each file is parsed once and cached in memory (parsed JSON is small and
 * read-only), avoiding re-opening/re-parsing the same file on every call —
 * the old code parsed AccountDetails.json 13 separate times for a single
 * registration.
 */
public class JSONReader {

    private static final String TEST_DATA_PATH = "testData/";

    private static final Map<String, JSONObject> CACHE = new ConcurrentHashMap<>();

    private JSONReader() {}


    public static String readJsonValue(String fileName, String key) throws IOException, ParseException {
        JSONObject json = CACHE.computeIfAbsent(fileName, JSONReader::loadJsonObject);
        Object value = json.get(key);
        return value != null ? value.toString() : null;
    }

    private static JSONObject loadJsonObject(String fileName) {
        String resourcePath = TEST_DATA_PATH + fileName;
        try (InputStream inputStream = JSONReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalStateException(
                        "Test data file not found on classpath: " + resourcePath
                                + " (expected at src/test/resources/" + resourcePath + ")");
            }
            try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                Object parsed = new JSONParser().parse(reader);
                return (JSONObject) parsed;
            }
        } catch (IOException | ParseException e) {
            throw new IllegalStateException("Failed to read/parse test data file: " + resourcePath, e);
        }
    }

    public static String existingUser(String data) throws IOException, ParseException {
        return readJsonValue("ExistingUser.json", data);
    }

    public static String accountDetails(String data) throws IOException, ParseException {
        return readJsonValue("AccountDetails.json", data);
    }

    public static String paymentDetails(String data) throws IOException, ParseException {
        return readJsonValue("PaymentDetails.json", data);
    }

    public static String poloBrandProducts(String data) throws IOException, ParseException {
        return readJsonValue("PoloBrandProducts.json", data);
    }

    public static String madameBrandProducts(String data) throws IOException, ParseException {
        return readJsonValue("MadameBrandProducts.json", data);
    }
}