package com.gobinda.sample;

import com.gobinda.sample.utils.Validator;

import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by gobindadeb on 28/1/18.
 */

public class EmailValidatorTest {

    @Test
    public void emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(Validator.isValidEmail(null));
    }

    @Test
    public void emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(Validator.isValidEmail(""));
    }

    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(Validator.isValidEmail("xyz@domain"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(Validator.isValidEmail("zyz@domain..com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(Validator.isValidEmail("@domain.com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoAt_ReturnsFalse() {
        assertFalse(Validator.isValidEmail("xyxdomain.com"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleAt_ReturnsFalse() {
        assertFalse(Validator.isValidEmail("xyx@@domain.com"));
    }

    @Test
    public void emailValidator_InvalidEmailMetaUserName_ReturnsFalse() {
        assertFalse(Validator.isValidEmail("x*x@domain.com"));
    }


    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(Validator.isValidEmail("xyx@domain.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSimpleDigit_ReturnsTrue() {
        assertTrue(Validator.isValidEmail("xyx9@domain.com"));
    }


    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(Validator.isValidEmail("xyz@domain.co.uk"));
    }


}


