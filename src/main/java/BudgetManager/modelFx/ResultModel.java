package BudgetManager.modelFx;

import java.sql.SQLException;
import java.util.Date;

import BudgetManager.converters.ConverterDate;
import BudgetManager.database.dao.ExpensesDao;
import BudgetManager.database.dao.RevenuesDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ResultModel {
	 private ObjectProperty<ResultFx> resultFxObjectProperty = new SimpleObjectProperty<>(new ResultFx());
	 
	 public void init() throws SQLException, Exception{
		 Date today = new Date();
		 ExpensesDao expensesDao = new ExpensesDao();
		 RevenuesDao revenuesDao = new RevenuesDao();
		 double expensesSum = expensesDao.getExpensesSumBetweenDate(ConverterDate.resetDate(today),ConverterDate.lastDayOfMonth(today));       
		 double revenuesSum = revenuesDao.getRevenuesSumBetweenDate(ConverterDate.resetDate(today),ConverterDate.lastDayOfMonth(today));       
		 double result = revenuesSum - expensesSum;
		 
		 this.resultFxObjectProperty.get().setExpensesValue(String.valueOf(expensesSum));
		 this.resultFxObjectProperty.get().setRevenuesValue(String.valueOf(revenuesSum));
		 this.resultFxObjectProperty.get().setResultValue(String.valueOf(result));
		 System.out.println(this.resultFxObjectProperty.get().toString());
	 }
	 
	 public ResultFx getResultFxObjectProperty() {
	        return resultFxObjectProperty.get();
	    }

	    public ObjectProperty<ResultFx> resultFxObjectPropertyProperty() {
	        return resultFxObjectProperty;
	    }

	    public void setResultFxObjectProperty(ResultFx resultFxObjectProperty) {
	        this.resultFxObjectProperty.set(resultFxObjectProperty);
	    } 
	 
}
