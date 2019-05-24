package com.gobinda.test;

import com.gobinda.test.utils.Validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by gobindadeb on 28/1/18.
 */

public class PasswordValidatorTest {

    @Test
    public void passwordValidator_NullPassword_ReturnsFalse() {
        assertFalse(Validator.isValidPassword(null));
    }

    @Test
    public void passwordValidator_EmptyString_ReturnsFalse() {
        assertFalse(Validator.isValidPassword(""));
    }

    @Test
    public void passwordValidator_InvalidPasswordNoDigit_ReturnsFalse() {
        assertFalse(Validator.isValidPassword("nQgRTypb"));
    }

    @Test
    public void passwordValidator_InvalidPasswordNoUpperCase_ReturnsFalse() {
        assertFalse(Validator.isValidPassword("nqgrt9p3"));
    }

    @Test
    public void passwordValidator_InvalidPasswordNoLowerCase_ReturnsFalse() {
        assertFalse(Validator.isValidPassword("NQGRT9P3"));
    }

    @Test
    public void passwordValidator_InvalidPasswordLessThan8Characters_ReturnsFalse() {
        assertFalse(Validator.isValidPassword("nQgRT9p"));
    }

    @Test
    public void passwordValidator_InvalidPasswordGreaterThan8Characters_ReturnsFalse() {
        assertFalse(Validator.isValidPassword("nQgRT9p3c"));
    }

    @Test
    public void passwordValidator_CorrectPasswordDigitUpercaseLowercase8Charaters_ReturnsTrue() {
        assertTrue(Validator.isValidPassword("nQgRT9p3"));
    }

}
