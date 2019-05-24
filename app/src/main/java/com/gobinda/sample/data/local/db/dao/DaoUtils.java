package com.gobinda.sample.data.local.db.dao;


import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Singleton Dao Helper class
 */
@Singleton
public class DaoUtils {


    private ORMLiteHelper mORMLiteHelper = null;

    @Inject
    public DaoUtils(ORMLiteHelper ormLiteHelper) {
        mORMLiteHelper = ormLiteHelper;
    }

    public ConnectionSource getConnectionSource() {
        return mORMLiteHelper.getConnectionSource();
    }

    public void shutdown() {
        if (mORMLiteHelper != null) {
            OpenHelperManager.releaseHelper();
            mORMLiteHelper = null;
        }
    }


    @SuppressWarnings("unchecked")
    public <T> Dao<T, ?> getDao(T ob) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) mORMLiteHelper.getDao(ob.getClass());
        return dao;
    }


    /**
     * Method to Get Dao of the Database Object
     *
     * @param clazz class  of the Model class
     * @return Respective Dao of the Model Class
     * @throws SQLException
     */
    public <T> Dao<T, ?> getDao(Class<T> clazz) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) mORMLiteHelper.getDao(clazz);
        return dao;
    }

    @SuppressWarnings("unchecked")
    public <T> T createOrUpdate(T ob) throws SQLException {
        Dao<Object, ?> dao = (Dao<Object, ?>) mORMLiteHelper.getDao(ob.getClass());
        return (T) dao.createOrUpdate(ob);
    }

    @SuppressWarnings("unchecked")
    public <T> int create(T ob) throws SQLException {
        Dao<Object, ?> dao = (Dao<Object, ?>) mORMLiteHelper.getDao(ob.getClass());
        return (int) dao.create(ob);
    }

    @SuppressWarnings("unchecked")
    public <T> int update(T ob) throws SQLException {
        Dao<Object, ?> dao = (Dao<Object, ?>) mORMLiteHelper.getDao(ob.getClass());
        return dao.update(ob);
    }

    @SuppressWarnings("unchecked")
    public <T> int delete(T ob) throws SQLException {
        Dao<Object, ?> dao = (Dao<Object, ?>) mORMLiteHelper.getDao(ob.getClass());
        return dao.delete(ob);
    }

    @SuppressWarnings("unchecked")
    public <T> int refresh(T ob) throws SQLException {

        Dao<T, ?> dao = (Dao<T, ?>) mORMLiteHelper.getDao(ob.getClass());
        return dao.refresh(ob);
    }


    @SuppressWarnings("unchecked")
    public <T> T getById(Object aId, Class<T> clazz) throws SQLException {

        Dao<T, Object> dao = mORMLiteHelper.getDao(clazz);
        return dao.queryForId(aId);
    }

    @SuppressWarnings("unchecked")
    public <T> T getByFieldValue(String column, Object value, Class<T> clazz) throws SQLException {
        Dao<T, Object> dao = mORMLiteHelper.getDao(clazz);

        QueryBuilder<T, Object> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq(column, value);
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        List<T> result = dao.query(preparedQuery);

        if (result == null || result.size() == 0)
            return null;
        else
            return result.get(0);
    }

    public <T> List<T> getAll(Class<T> clazz) throws SQLException {
        Dao<T, ?> dao = mORMLiteHelper.getDao(clazz);
        return dao.queryForAll();
    }


}

