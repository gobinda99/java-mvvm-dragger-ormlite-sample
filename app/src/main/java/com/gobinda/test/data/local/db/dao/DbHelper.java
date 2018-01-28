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

import io.reactivex.Observable;


/**
 * Database Helper interface
 */
public interface DbHelper {

    /**
     * Create a record to the user table
     * @param user
     * @return true if created otherwise false
     */
    Observable<Boolean> createUser(final User user);

    /**
     * Finds a records from the user table that match @param email
     * @param email
     * @return User or null in optional
     */
    Observable<Optional<User>> getUser(String email);

}
