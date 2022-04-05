package BudgetManager.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import BudgetManager.modelFx.MonthGraphModel;
import BudgetManager.modelFx.YearGraphModel;
import BudgetManager.utils.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class MonthGraphPanelController {
	
	 @FXML
	 private BarChart<String, Number> monthGraph;
	 @FXML
	 private NumberAxis osKwota;
	 @FXML
	 private CategoryAxis osDni;

	 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
	 
	 public void initialize() {
		 MonthGraphModel monthGraphModel = new MonthGraphModel(new Date());
		 
		 try{
			 monthGraphModel.init();
	    	}catch(Exception e) {
	    		 DialogUtils.errorDialog(e.getMessage());
	    	}
		 XYChart.Series series1 = new XYChart.Series();
	     series1.setName("Wydatki");
	     monthGraphModel.getGraphItems().forEach(i -> series1.getData().add(new XYChart.Data(sdf.format(i.getDate()), i.getAmountExpenses())));
	     XYChart.Series series2 = new XYChart.Series();
	     series2.setName("Przychody");
	     monthGraphModel.getGraphItems().forEach(i -> series2.getData().add(new XYChart.Data(sdf.format(i.getDate()), i.getAmountRevenues())));
	    // XYChart.Series series3 = new XYChart.Series();
	    // series1.setName("Oszczêdnoœci");
	     monthGraph.getData().addAll(series1, series2);
	 }

	 
}
