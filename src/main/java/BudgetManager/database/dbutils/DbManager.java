package BudgetManager.database.dbutils;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import BudgetManager.database.models.Expenses;
import BudgetManager.database.models.Revenues;




public class DbManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_HD = "jdbc:h2:C:\\Users\\Agata\\.eclipse\\workplace\\BudgetManager/DB";
    private static final String USER = "admin";
    private static final String PASS = "admin";
    
    private static ConnectionSource connectionSource;
    
	public static void initDatabase() {
        createConnectionSource();
        //dropTable();
        createTable();
        closeConnectionSource();
	}
    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD, USER, PASS);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource(){
		if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Expenses.class);
            TableUtils.createTableIfNotExists(connectionSource, Revenues.class);

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, Expenses.class, true);
            TableUtils.dropTable(connectionSource, Revenues.class, true);

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
