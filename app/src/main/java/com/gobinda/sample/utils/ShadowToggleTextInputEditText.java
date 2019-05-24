package com.gobinda.sample.utils;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.View;

import com.gobinda.sample.R;

/**
 * Custom ShadowToggleTextInputEditText to show shadow on test when on focused
 *
 */
public class ShadowToggleTextInputEditText extends TextInputEditText implements View.OnFocusChangeListener{

    public ShadowToggleTextInputEditText(Context context) {
        super(context);
        setOnFocusChangeListener(this);
    }

    public ShadowToggleTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnFocusChangeListener(this);

    }

    public ShadowToggleTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean focus) {
        setTextAppearance(view.getContext(), focus?  R.style.TextShadowDisable : R.style.TextShadowEnable);
    }
}
