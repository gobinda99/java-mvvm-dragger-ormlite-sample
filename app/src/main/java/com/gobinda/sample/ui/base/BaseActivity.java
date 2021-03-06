
package com.gobinda.sample.ui.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import dagger.android.AndroidInjection;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


/**
 * Base Activity class
 *
 * @param <T>
 * @param <V>
 */
public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {


    private T mViewDataBinding;
    private V mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    //Preforms Data Binding
    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    //Injects dependency
    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }


    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * shows a toast message at the top of the screen
     *
     * @param messageResId
     * @param style
     */
    public void showToastMessage(final int messageResId, final Style style) {
        Crouton.cancelAllCroutons();
        Crouton.makeText(this, getString(messageResId), style).show();
    }

    /**
     * DataBinding of the Activity
     *
     * @return T ViewDataBinding
     */
    public T getViewDataBinding() {
        return mViewDataBinding;
    }


    /**
     * ViewModel of the Activity
     *
     * @return V BaseViewModel
     */
    public abstract V getViewModel();

    /**
     * Data Variable
     *
     * @return
     */
    public abstract int getBindingVariable();

    public abstract
    @LayoutRes
    int getLayoutId();

}

