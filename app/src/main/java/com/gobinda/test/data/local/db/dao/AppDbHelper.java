package com.gobinda.test.data.local.db.dao;

import com.gobinda.test.data.model.User;
import com.gobinda.test.utils.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Single Instance Application Database Helper Class
 */
@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoUtils mDao;

    @Inject
    public AppDbHelper(DaoUtils dao) {
        mDao = dao;
    }


    @Override
    public Observable<Boolean> createUser(User user) {
        return Observable.fromCallable(() ->
                mDao.getByFieldValue(User.Column.EMAIL, user.getEmail(), User.class) == null
                && mDao.create(user) == 1);
    }

    @Override
    public Observable<Optional<User>> getUser(String email) {
        return Observable.fromCallable(() ->
                new Optional<>(mDao.getByFieldValue(User.Column.EMAIL, email, User.class)));
    }
}
