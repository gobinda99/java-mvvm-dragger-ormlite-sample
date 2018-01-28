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
