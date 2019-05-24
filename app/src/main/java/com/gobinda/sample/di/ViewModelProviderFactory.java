package com.gobinda.sample.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.gobinda.sample.data.DataManager;
import com.gobinda.sample.ui.registerLogin.RegisterLoginViewModel;
import com.gobinda.sample.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    final private DataManager mDataManager;
    final private SchedulerProvider mSchedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        mDataManager = dataManager;
        mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterLoginViewModel.class)) {
            return (T) new RegisterLoginViewModel(mDataManager, mSchedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
