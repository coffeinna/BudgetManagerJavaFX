package BudgetManager.database.dao;

import java.sql.SQLException;
import java.util.Date;

import com.j256.ormlite.dao.Dao;

import BudgetManager.converters.ConverterDate;
import BudgetManager.database.models.Revenues;


public class RevenuesDao extends CommonDao{
	public RevenuesDao() {
		super();
	}
	public double getRevenuesSumBetweenDate(Date minDate, Date maxDate) throws Exception {
        double rev = 0.0;
        try {
            Dao<Revenues, Integer> dao = getDao(Revenues.class);
            rev = dao.
                    queryRawValue(
                            String.format("SELECT SUM(KWOTA) FROM REVENUES WHERE DATA >= '%s' AND DATA <= '%s'",
                                    ConverterDate.formatDateToDBQuery(minDate),
                                    ConverterDate.formatDateToDBQuery(maxDate)
                            ));
            System.out.println(rev);
            System.out.println("minDate = " + minDate + ", maxDate = " + maxDate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            this.closeDbConnection();
        }
        return rev;
    }
}