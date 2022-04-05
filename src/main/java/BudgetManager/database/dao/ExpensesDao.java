package BudgetManager.database.dao;

import java.sql.SQLException;
import java.util.Date;

import com.j256.ormlite.dao.Dao;

import BudgetManager.converters.ConverterDate;
import BudgetManager.database.models.Expenses;

public class ExpensesDao extends CommonDao{
	
	public ExpensesDao() {
		super();
	}
	public double getExpensesSumBetweenDate(Date minDate, Date maxDate) throws Exception {
        double exp = 0.0;
        try {
            Dao<Expenses, Integer> dao = getDao(Expenses.class);
            exp = dao.
                    queryRawValue(
                            String.format("SELECT SUM(KWOTA) FROM EXPENSES WHERE DATA >= '%s' AND DATA <= '%s'",
                                    ConverterDate.formatDateToDBQuery(minDate),
                                    ConverterDate.formatDateToDBQuery(maxDate)
                            ));
            System.out.println(exp);
            System.out.println("minDate = " + minDate + ", maxDate = " + maxDate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            this.closeDbConnection();
        }
        return exp;
    }

}
