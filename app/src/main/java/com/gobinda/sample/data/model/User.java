package com.gobinda.sample.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Database Model Class User
 */
@DatabaseTable(tableName = "users")
public class User {

    public interface Column {
        String ID = "id";
        String EMAIL = "email";
        String PASSWORD = "password";
    }

    @DatabaseField(generatedId = true, columnName = Column.ID)
    protected long mId;

    @DatabaseField(columnName = Column.EMAIL, columnDefinition = "TEXT NOT NULL UNIQUE ON CONFLICT IGNORE")
    protected String mEmail;

    @DatabaseField(columnName = Column.PASSWORD, canBeNull = false)
    protected String mPassword;

    public User() {
    }

    public User(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public long getId() {
        return mId;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

}
