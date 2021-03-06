
package com.gobinda.sample.ui.registerLogin;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.gobinda.sample.BR;
import com.gobinda.sample.R;
import com.gobinda.sample.di.ViewModelProviderFactory;
import com.gobinda.sample.databinding.ActivityRegisterLoginBinding;
import com.gobinda.sample.ui.base.BaseActivity;
import com.gobinda.sample.utils.DialogUtils;

import javax.inject.Inject;

import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Register Login Activity Class
 */
public class RegisterLoginActivity extends BaseActivity<ActivityRegisterLoginBinding,
        RegisterLoginViewModel> implements  RegisterLoginActionListener {

    @Inject
    ViewModelProviderFactory mFactory;

    private  RegisterLoginViewModel mViewModel;

    ActivityRegisterLoginBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mBinding.setListener(this);
        setObservablesOfViewModel();
    }

    private void setObservablesOfViewModel() {
        mViewModel.getHandleError().observe(this, errorType -> {
            clickableButton(true);
            showToastMessage(errorType.getMessageResId(), Style.ALERT);
        });
        mViewModel.getThrowableError().observe(this, throwable ->
                clickableButton(true));
        mViewModel.getAccountCreated().observe(this, booleanEvent -> {
            if (!booleanEvent.isHandled()) {
                showToastMessage(R.string.account_created_successful, Style.CONFIRM);
                resetUi();
            }
        });
        mViewModel.getLoginSucceed().observe(this, userEvent -> {
            if (userEvent.getContentIfNotHandled() != null) {
                resetUi();
                DialogUtils.showMessage(this, getString(R.string.login_successful));
            }
        });
    }


    @Override
    public RegisterLoginViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, mFactory).get(RegisterLoginViewModel.class);
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
    public void onLoginClick() {
       login();
    }

    @Override
    public void onCreateAccountClick() {
      createAccount();
    }

    private void login() {
        clickableButton(false);
        String email = mBinding.email.getText().toString();
        String password = mBinding.password.getText().toString();
        if (mViewModel.validateInput(email, password)) {
            hideKeyboard();
            mViewModel.login(email, password);
        }
    }

    private void createAccount() {
        clickableButton(false);
            String email = mBinding.email.getText().toString();
        String password = mBinding.password.getText().toString();
        if (mViewModel.validateInput(email, password)) {
            hideKeyboard();
            mViewModel.createAccount(email, password);
        }
    }


    private void resetUi() {
        mBinding.email.setText("");
        mBinding.password.setText("");
        mBinding.email.requestFocus();
        clickableButton(true);
    }


    private void clickableButton(boolean enable) {
        mBinding.login.setClickable(enable);
        mBinding.createAccount.setClickable(enable);
    }


}
