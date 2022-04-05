package BudgetManager.modelFx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BudgetManager.converters.ConverterDate;
import BudgetManager.database.dao.ExpensesDao;
import BudgetManager.database.dao.RevenuesDao;
import BudgetManager.utils.DialogUtils;



public class YearGraphModel {
	private Date date;
    private List<GraphModel> graphItems = new ArrayList<>();
    
    public YearGraphModel(Date date) {
    	this.date = date;
    }
    public void init() throws Exception {
        initDates();
        fillGraph();
    }
	private void fillGraph() throws Exception {
		graphItems.forEach(i -> {
            try {
                i.setAmountExpenses(this.getSumOfExpensesByMonth(i.getDate()));
                i.setAmountRevenues(this.getSumOfRevenuesByMonth(i.getDate()));
            } catch (Exception e) {
	    		 DialogUtils.errorDialog(e.getMessage());
            }
        });
	}
	private double getSumOfExpensesByMonth(Date date) throws Exception {
		ExpensesDao expensesDao = new ExpensesDao();
		LocalDate d1 = ConverterDate.convertToLocalDate(date);
        LocalDate fromDate = LocalDate.of(d1.getYear(), d1.getMonthValue(), 1);
        LocalDate upDate = LocalDate.of(d1.getYear(), d1.getMonthValue(), d1.getMonth().length(d1.isLeapYear()));

		return expensesDao.getExpensesSumBetweenDate(ConverterDate.convertToDate(fromDate),ConverterDate.convertToDate(upDate));
	}
	private double getSumOfRevenuesByMonth(Date date) throws Exception {
		RevenuesDao revenuesDao = new RevenuesDao();
		LocalDate d1 = ConverterDate.convertToLocalDate(date);
        LocalDate fromDate = LocalDate.of(d1.getYear(), d1.getMonthValue(), 1);
        LocalDate upDate = LocalDate.of(d1.getYear(), d1.getMonthValue(), d1.getMonth().length(d1.isLeapYear()));

		return revenuesDao.getRevenuesSumBetweenDate(ConverterDate.convertToDate(fromDate),ConverterDate.convertToDate(upDate));
	}
	private void initDates() {        
		LocalDate localDate = ConverterDate.convertToLocalDate(this.date);

    int months = 12;
    for (int month = 1; month <= months; month++) {
        LocalDate tempDate = LocalDate.of(localDate.getYear(), month, 1);
        GraphModel itemModel = new GraphModel(ConverterDate.convertToDate(tempDate));
        this.graphItems.add(itemModel);
    }
		
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
