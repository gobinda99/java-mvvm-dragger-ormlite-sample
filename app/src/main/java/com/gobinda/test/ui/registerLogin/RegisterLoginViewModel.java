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


import android.text.TextUtils;

import com.gobinda.test.data.DataManager;
import com.gobinda.test.data.model.User;
import com.gobinda.test.ui.base.BaseViewModel;
import com.gobinda.test.utils.Validator;
import com.gobinda.test.utils.rx.SchedulerProvider;

import timber.log.Timber;

/**
 * Register Login View Model
 */
public class RegisterLoginViewModel extends BaseViewModel<RegisterLoginNavigator> {

    public RegisterLoginViewModel(DataManager dataManager,
                                  SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    /**
     * This method is called from xml of the  RegisterLoginActivity
     * When the login button is clicked
     */
    public void onLoginClick() {
        getNavigator().login();
    }


    /**
     * This method is called from xml of the  RegisterLoginActivity
     * When the Create account button is clicked
     */
    public void onCreateAccountClick() {
        getNavigator().createAccount();
    }


    /**
     * This method is called after input validation success
     * @param email
     * @param password
     */
    void createAccount(String email, String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().createUser(new User(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(created -> {
                    setIsLoading(false);
                    if (created) {
                        getNavigator().createAccountSuccessful();
                    } else {
                        getNavigator().handleError(ValidationErrorType.EMAIL_ALREADY_EXIST);
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                    Timber.e(throwable, "Create user exp");
                }));

    }


    /**
     * This method is called after input validation success
     * @param email
     * @param password
     */
    void login(String email, final String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getUser(email).subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(optUser -> {
                    setIsLoading(false);
                    if (optUser.isEmpty()) {
                        getNavigator().handleError(ValidationErrorType.EMAIL_NOT_EXIST);
                    } else {
                        if (optUser.get().getPassword().equals(password)) {
                            getNavigator().loginSuccessful();
                        } else {
                            getNavigator().handleError(ValidationErrorType.PASSWORD_NOT_MATCHED);
                        }
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                    Timber.e(throwable, "Get user exp");
                }));

    }

    /**
     * This method is called to validate User's input credentials
     *
     * @param email
     * @param password
     * @return
     */
    boolean validateInput(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            getNavigator().handleError(ValidationErrorType.EMAIL_EMPTY);
            return false;
        }
        if (!Validator.isValidEmail(email)) {
            getNavigator().handleError(ValidationErrorType.EMAIL_INCORRECT);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            getNavigator().handleError(ValidationErrorType.PASSWORD_EMPTY);
            return false;
        }

        if (!Validator.isValidPassword(password)) {
            getNavigator().handleError(ValidationErrorType.PASSWORD_INCORRECT);
            return false;
        }

        Timber.d("Validation Success for %s : %s", email, password);

        return true;
    }


}
