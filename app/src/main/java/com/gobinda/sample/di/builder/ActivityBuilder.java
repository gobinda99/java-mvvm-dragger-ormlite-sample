package com.gobinda.sample.di.builder;

import com.gobinda.sample.ui.registerLogin.RegisterLoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract RegisterLoginActivity bindLoginActivity();

}
