package BudgetManager.modelFx;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BudgetManager.converters.ConverterDate;
import BudgetManager.database.dao.ExpensesDao;
import BudgetManager.database.dao.RevenuesDao;
import BudgetManager.database.models.Expenses;
import BudgetManager.database.models.Revenues;
import BudgetManager.utils.DialogUtils;


public class MonthGraphModel {
	private Date date;
    private List<GraphModel> graphItems = new ArrayList<>();
    
    public MonthGraphModel(Date date) {
    	this.date = date;
    }
    public void init() throws Exception {
        initDates();
        fillGraph();
    }
	private void initDates() {
		LocalDate localDate = ConverterDate.convertToLocalDate(this.date);
        Month month = localDate.getMonth();
        for (int i = 1; i <= month.length(localDate.isLeapYear()); i++) {
            LocalDate tempDate = LocalDate.of(localDate.getYear(), localDate.getMonthValue(), i);
            GraphModel itemModel = new GraphModel(ConverterDate.convertToDate(tempDate));
            this.graphItems.add(itemModel);
        }
	}
	private void fillGraph() throws Exception{
		ExpensesDao expensesDao = new ExpensesDao();
		RevenuesDao revenuesDao = new RevenuesDao();
		List<Expenses> expenses = expensesDao.queryForAll(Expenses.class);
		List<Revenues> revenues = revenuesDao.queryForAll(Revenues.class);
		
		graphItems.forEach(i -> {
            try {
                i.setAmountExpenses(this.getSumOfExpensesByMonth(i.getDate(), expenses));
                i.setAmountRevenues(this.getSumOfRevenuesByMonth(i.getDate(), revenues));
            } catch (Exception e) {
	    		 DialogUtils.errorDialog(e.getMessage());
            }
        });
	}
    private Double getSumOfExpensesByMonth(Date date, List<Expenses> expenses ) throws Exception {
    	 double sum = 0.0;

         for (Expenses i : expenses) {
             LocalDate date1 = ConverterDate.convertToLocalDate(i.getDate());
             LocalDate date2 = ConverterDate.convertToLocalDate(date);
             if (date1.equals(date2)) {
                 sum += i.getAmount();
             }
         }
         return sum;
    }

    private Double getSumOfRevenuesByMonth(Date date, List<Revenues> revenues) throws Exception {
    	double sum = 0.0;

        for (Revenues i : revenues) {
            LocalDate date1 = ConverterDate.convertToLocalDate(i.getDate());
            LocalDate date2 = ConverterDate.convertToLocalDate(date);
            if (date1.equals(date2)) {
                sum += i.getAmount();
            }
        }
        return sum;
   }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<GraphModel> getGraphItems() {
        return graphItems;
    }

    public void setGraphItems(List<GraphModel> graphItems) {
        this.graphItems = graphItems;
    }
}
