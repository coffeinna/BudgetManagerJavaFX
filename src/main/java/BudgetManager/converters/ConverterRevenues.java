package BudgetManager.converters;

import BudgetManager.database.models.Revenues;
import BudgetManager.modelFx.RevenuesFx;

public class ConverterRevenues {

	public static Revenues convertRevenuesFxToRevenues(RevenuesFx revenuesFx) {
		Revenues revenues = new Revenues();
		revenues.setId(revenuesFx.getId());
    	revenues.setName(revenuesFx.getName());
    	revenues.setAmount(Double.parseDouble(revenuesFx.getAmount().replace(',', '.')));
    	revenues.setDate(ConverterDate.convertToDate(revenuesFx.getDate()));
		return revenues;
	}
	 public static RevenuesFx convertToRevenuesFx(Revenues revenues){
		 RevenuesFx revenuesFx = new RevenuesFx();
		 	revenuesFx.setId(revenues.getId());
		 	revenuesFx.setDate(ConverterDate.convertToLocalDate(revenues.getDate()));
		 	revenuesFx.setAmount(String.valueOf(revenues.getAmount()));
		 	revenuesFx.setName(revenues.getName());
	        return revenuesFx;
	    }
	
}
