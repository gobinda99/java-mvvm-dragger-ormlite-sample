package com.gobinda.sample.data.local.db.dao;


import com.gobinda.sample.data.model.User;
import com.gobinda.sample.utils.Optional;

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
