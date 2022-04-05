package BudgetManager.modelFx;


import BudgetManager.converters.ConverterExpenses;
import BudgetManager.database.dao.ExpensesDao;
import BudgetManager.database.dao.RevenuesDao;
import BudgetManager.database.models.Expenses;
import BudgetManager.database.models.Revenues;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;


public class ExpensesModel {
	
	private ObjectProperty<ExpensesFx> expensesFxObjectProperty= new SimpleObjectProperty<>(new ExpensesFx());
	private ObservableList<ExpensesFx> expensesFxObservableList= FXCollections.observableArrayList();

	public void init() throws Exception{
		 ExpensesDao expensesDao = new ExpensesDao();
	     List<Expenses> expenses = expensesDao.queryForAll(Expenses.class);
	     this.expensesFxObservableList.clear();
	     expenses.forEach(expense -> {
	        this.expensesFxObservableList.add(ConverterExpenses.convertToExpensesFx(expense));
	     });
	}
	

	
    public ExpensesFx getExpensesFxObjectProperty() {
        return expensesFxObjectProperty.get();
    }

    public ObjectProperty<ExpensesFx> expensesFxObjectPropertyProperty() {
        return expensesFxObjectProperty;
    }

    public void setExpensesFxObjectProperty(ExpensesFx expensesFxObjectProperty) {
        this.expensesFxObjectProperty.set(expensesFxObjectProperty);
    }

    public ObservableList<ExpensesFx> getExpensesFxObservableList() {
        return expensesFxObservableList;
    }

    public void setExpensesFxObservableList(ObservableList<ExpensesFx> expensesFxObservableList) {
        this.expensesFxObservableList = expensesFxObservableList;
    }

	
	public void saveExpensesInDataBase() throws Exception{
		ExpensesDao expensesDao = new ExpensesDao();
		Expenses expense = ConverterExpenses.converToExpenses(this.getExpensesFxObjectProperty());
        expensesDao.createOrUpdate(expense);
        this.init();

	}	
	

    public void deleteExpensesInDataBase(ExpensesFx expensesFx) throws Exception{
    	ExpensesDao expensesDao = new ExpensesDao();
    	expensesDao.deleteById(Expenses.class, expensesFx.getId());
    	this.init();
    }
}
	
	
