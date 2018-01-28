package com.gobinda.test.data.local.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gobinda.test.data.model.User;
import com.gobinda.test.utils.Constants;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * This ORMLiteHelper helper class is similar to SQLLiteHelper
 * Here OrmLite is an Object Relation Model to the SQLite database
 *
 */
public class ORMLiteHelper extends OrmLiteSqliteOpenHelper {


    public ORMLiteHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return super.getConnectionSource();
    }
}
