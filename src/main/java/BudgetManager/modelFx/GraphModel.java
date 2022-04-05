package BudgetManager.modelFx;

import java.util.Date;

public class GraphModel {
	 	private Date date;
	    private double amountExpenses;
	    private double amountRevenues;
	    

	    public GraphModel(Date date) {
	    	this.date = date;
	    }
	    
	    public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		
		public double getAmountExpenses() {
			return amountExpenses;
		}
		public void setAmountExpenses(double amountExpenses) {
			this.amountExpenses = amountExpenses;
		}
	    
		public double getAmountRevenues() {
			return amountRevenues;
		}
		public void setAmountRevenues(double amountRevenues) {
			this.amountRevenues = amountRevenues;
		}
	
}
