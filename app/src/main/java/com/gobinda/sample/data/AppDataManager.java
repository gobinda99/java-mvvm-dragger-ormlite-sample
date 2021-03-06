
package com.gobinda.sample.data;

import android.content.Context;


import com.gobinda.sample.data.local.db.dao.DbHelper;
import com.gobinda.sample.data.model.User;
import com.gobinda.sample.utils.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Singleton Application Data Manager Class
 */
@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(Context context,
                          DbHelper dbHelper) {
        mContext = context;
        mDbHelper = dbHelper;
    }

    @Override
    public Observable<Boolean> createUser(User user) {
        return mDbHelper.createUser(user);
    }

    @Override
    public Observable<Optional<User>> getUser(String email) {
        return mDbHelper.getUser(email);
    }
}
