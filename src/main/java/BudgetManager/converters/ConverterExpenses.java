package BudgetManager.converters;

import BudgetManager.database.models.Expenses;
import BudgetManager.modelFx.ExpensesFx;


public class ConverterExpenses {


    public static Expenses converToExpenses(ExpensesFx expensesFx){
    	Expenses expense = new Expenses();
    	expense.setId(expensesFx.getId());
    	expense.setName(expensesFx.getName());
    	expense.setDate(ConverterDate.convertToDate(expensesFx.getDate()));
    	expense.setAmount(Double.parseDouble(expensesFx.getAmount().replace(',', '.')));
        return expense;
    }

    public static ExpensesFx convertToExpensesFx(Expenses expense){
    	ExpensesFx expensesFx = new ExpensesFx();
    	expensesFx.setId(expense.getId());
    	expensesFx.setName(expense.getName());
    	expensesFx.setDate(ConverterDate.convertToLocalDate(expense.getDate()));
    	expensesFx.setAmount(String.valueOf(expense.getAmount()));
        return expensesFx;
    }
}