package fastAPI.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class TestData {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static class Users {
        public static final String VALID_EMAIL    = get("VALID_EMAIL", "test@example.com");
        public static final String VALID_PASSWORD = get("VALID_PASSWORD", "Test123!");

        public static final String DUMMY_NAME     = "Dummy User";
        public static final String DUMMY_EMAIL    = "dummy_" + System.currentTimeMillis() + "@example.com";
        public static final String DUMMY_PASSWORD = "DummyPass123!";
    }

    private static String get(String key, String fallback) {
        String value = dotenv.get(key);
        return value != null ? value : fallback;
    }
}