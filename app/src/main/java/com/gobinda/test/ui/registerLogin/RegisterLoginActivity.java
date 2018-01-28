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

import android.os.Bundle;

import com.gobinda.test.BR;
import com.gobinda.test.R;
import com.gobinda.test.databinding.ActivityRegisterLoginBinding;
import com.gobinda.test.ui.base.BaseActivity;
import com.gobinda.test.utils.DialogUtils;

import javax.inject.Inject;

import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Register Login Activity Class
 */
public class RegisterLoginActivity extends BaseActivity<ActivityRegisterLoginBinding, RegisterLoginViewModel> implements RegisterLoginNavigator {

    @Inject
    RegisterLoginViewModel mViewModel;

    ActivityRegisterLoginBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
    }


    @Override
    public RegisterLoginViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_login;
    }

    @Override
    public void login() {
        clickableButton(false);
        String email = mBinding.email.getText().toString();
        String password = mBinding.password.getText().toString();
        if (mViewModel.validateInput(email, password)) {
            hideKeyboard();
            mViewModel.login(email, password);
        }
    }

    @Override
    public void createAccount() {
        clickableButton(false);
        String email = mBinding.email.getText().toString();
        String password = mBinding.password.getText().toString();
        if (mViewModel.validateInput(email, password)) {
            hideKeyboard();
            mViewModel.createAccount(email, password);
        }
    }

    @Override
    public void loginSuccessful() {
        resetUi();
        DialogUtils.showMessage(this, getString(R.string.login_successful));
    }

    private void resetUi() {
        mBinding.email.setText("");
        mBinding.password.setText("");
        mBinding.email.requestFocus();
        clickableButton(true);
    }

    @Override
    public void createAccountSuccessful() {
        showToastMessage(R.string.account_created_successful, Style.CONFIRM);
        resetUi();
    }

    @Override
    public void handleError(Throwable throwable) {
        clickableButton(true);
    }

    @Override
    public void handleError(ValidationErrorType validationErrorType) {
        clickableButton(true);
        showToastMessage(validationErrorType.getMessageResId(), Style.ALERT);
    }

    private void clickableButton(boolean enable) {
        mBinding.login.setClickable(enable);
        mBinding.createAccount.setClickable(enable);
    }


}
