package BudgetManager.controllers;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import BudgetManager.modelFx.ResultModel;
import BudgetManager.utils.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultsPanelController {
	@FXML
	private Label monthLabel;
	@FXML
	private Label sumRevenuesLabel;
	@FXML
	private Label sumExpensesLabel;
	@FXML
	private Label resultLabel;
	
	private ResultModel resultModel;
	
	public void initialize() throws Exception {
		resultModel = new ResultModel();
		try{	
			this.resultModel.init();
    	}catch(Exception e) {
    		 DialogUtils.errorDialog(e.getMessage());
    	}
		initLabels();
	}
	
	public void initLabels() {
		this.monthLabel.setText(getResultLabel(new Date()));
		this.sumRevenuesLabel.textProperty().bindBidirectional(this.resultModel.getResultFxObjectProperty().revenuesValueProperty());
 	    this.sumExpensesLabel.textProperty().bindBidirectional(this.resultModel.getResultFxObjectProperty().expensesValueProperty());
 	    this.resultLabel.textProperty().bindBidirectional(this.resultModel.getResultFxObjectProperty().resultValueProperty());
 	 
	}
	 private String getResultLabel(Date date) {
	        LocalDate d1 = BudgetManager.converters.ConverterDate.convertToLocalDate(date);
	        return d1.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()) + " " + d1.getYear();
	    }

}
