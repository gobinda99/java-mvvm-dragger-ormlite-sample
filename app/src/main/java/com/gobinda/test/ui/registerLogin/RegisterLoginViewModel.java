package com.gobinda.test.ui.registerLogin;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.gobinda.test.data.DataManager;
import com.gobinda.test.data.model.User;
import com.gobinda.test.ui.base.BaseViewModel;
import com.gobinda.test.utils.Event;
import com.gobinda.test.utils.Validator;
import com.gobinda.test.utils.rx.SchedulerProvider;

import timber.log.Timber;

/**
 * Register Login View Model
 */
public class RegisterLoginViewModel extends BaseViewModel<RegisterLoginNavigator> {


    private final MutableLiveData<Throwable> mThrowableError = new MutableLiveData<>();
    private final MutableLiveData<ValidationErrorType> mHandleError = new MutableLiveData<>();
    private final MutableLiveData<Event<User>> mLoginSucceed = new MutableLiveData<>();
    private final MutableLiveData<Event<Boolean>> mAccountCreated = new MutableLiveData<>();

    public RegisterLoginViewModel(DataManager dataManager,
                                  SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

//    /**
//     * This method is called from xml of the  RegisterLoginActivity
//     * When the login button is clicked
//     */
//    public void onLoginClick() {
//        getNavigator().login();
//    }
//
//
//    /**
//     * This method is called from xml of the  RegisterLoginActivity
//     * When the Create account button is clicked
//     */
//    public void onCreateAccountClick() {
//        getNavigator().createAccount();
//    }


    /**
     * This method is called after input validation success
     *
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
//                        getNavigator().createAccountSuccessful();
                        mAccountCreated.setValue(new Event<>(created));
                    } else {
                        mHandleError.setValue(ValidationErrorType.EMAIL_ALREADY_EXIST);
                    }
                }, throwable -> {
                    setIsLoading(false);
                    mThrowableError.setValue(throwable);
                    Timber.e(throwable, "Create user exp");
                }));

    }


    /**
     * This method is called after input validation success
     *
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
                        mHandleError.setValue(ValidationErrorType.EMAIL_NOT_EXIST);
                    } else {
                        if (optUser.get().getPassword().equals(password)) {
//                            getNavigator().loginSuccessful();
                            mLoginSucceed.setValue(new Event<>(optUser.get()));
                        } else {
                            mHandleError.setValue(ValidationErrorType.PASSWORD_NOT_MATCHED);
                        }
                    }
                }, throwable -> {
                    setIsLoading(false);
                    mThrowableError.setValue(throwable);
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
            mHandleError.setValue(ValidationErrorType.EMAIL_EMPTY);
            return false;
        }
        if (!Validator.isValidEmail(email)) {
            mHandleError.setValue(ValidationErrorType.EMAIL_INCORRECT);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            mHandleError.setValue(ValidationErrorType.PASSWORD_EMPTY);
            return false;
        }

        if (!Validator.isValidPassword(password)) {
            mHandleError.setValue(ValidationErrorType.PASSWORD_INCORRECT);
            return false;
        }

        Timber.d("Validation Success for %s : %s", email, password);

        return true;
    }

    LiveData<Throwable> getThrowableError() {
        return mThrowableError;
    }

    LiveData<ValidationErrorType> getHandleError() {
        return mHandleError;
    }

    MutableLiveData<Event<User>> getLoginSucceed() {
        return mLoginSucceed;
    }

    MutableLiveData<Event<Boolean>> getAccountCreated() {
        return mAccountCreated;
    }
}
