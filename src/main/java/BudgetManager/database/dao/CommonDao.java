package BudgetManager.database.dao;


import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import BudgetManager.database.dbutils.DbManager;
import BudgetManager.database.models.BaseModel;
import BudgetManager.utils.FxmlUtils;




public abstract class CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected final ConnectionSource connectionSource;

    public CommonDao() {
        this.connectionSource = DbManager.getConnectionSource();
    }

    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel) throws Exception {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new Exception("Problem z utworzeniem lub zaktualizowaniem wpisu do bazy danych.");
        } finally {
				this.closeDbConnection();
				}
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws Exception {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new Exception("Problem z odœwie¿eniem bazy danych.");
        } finally {
			this.closeDbConnection();}
    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws Exception {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new Exception("Problem z usuniêciem bazy danych.");
        } finally {
			this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id) throws Exception {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new Exception("Problem z usuniêciem bazy danych.");
        } finally {
			this.closeDbConnection();
			}
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws Exception {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new Exception("Nie znaleziono w bazie danych.");
        } finally {
			this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws Exception {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new Exception("Problem z pobraniem danych z bazy.");
        } finally {
			this.closeDbConnection();
        }
    }


    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws Exception {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new Exception("Problem z baz¹ danych.");
        } finally {
			this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls) throws Exception {
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }

    public void closeDbConnection() throws Exception {
        try {
            this.connectionSource.close();
        } catch (Exception e) {
            throw new Exception("Problem z baz¹ danych.");
        }
    }
}
