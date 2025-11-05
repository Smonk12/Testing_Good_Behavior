package fastAPI.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class TestData {

    private static final Dotenv dotenv = Dotenv.load();

    public static class Users {
        public static final String VALID_EMAIL    = dotenv.get("VALID_EMAIL");
        public static final String VALID_PASSWORD = dotenv.get("VALID_PASSWORD");

        public static final String DUMMY_NAME     = "Dummy User";
        public static final String DUMMY_EMAIL    = "dummy_" + System.currentTimeMillis() + "@example.com";
        public static final String DUMMY_PASSWORD = "DummyPass123!";
    }
}