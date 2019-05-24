package com.gobinda.sample.utils;

import java.util.regex.Pattern;

/**
 * Validator Utility class
 */
public final class Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{8,8})$");

    private Validator() {
    }

    /**
     * Utility method to validate email
     * @param email
     * @return True if validation success otherwise false
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Utility method to validate password
     * @param password
     * @return True if validation success otherwise false
     */
    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

}
