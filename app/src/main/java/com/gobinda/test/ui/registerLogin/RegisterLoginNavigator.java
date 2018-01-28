/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.gobinda.test.ui.registerLogin;

/**
 * Register login  Navigator interface
 * Each of the methods are called from RegisterLoginViewModel
 * and define in RegisterLoginActivity
 */
public interface RegisterLoginNavigator {

    /**
     * calls to login a account
     */
    void login();

    /**
     * calls to create a account
     */
    void createAccount();

    /**
     * calls when login is success
     */
    void loginSuccessful();

    /**
     * calls when create account is success
     */
    void createAccountSuccessful();

    /**
     * Calls when an exception occurs
     * @param throwable
     */
    void handleError(Throwable throwable);

    /**
     * To handle of validationErrorType
     * @param validationErrorType
     */
    void handleError(ValidationErrorType validationErrorType);

}
