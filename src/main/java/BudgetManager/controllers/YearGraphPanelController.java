package BudgetManager.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import BudgetManager.modelFx.YearGraphModel;
import BudgetManager.utils.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class YearGraphPanelController {
	
	 @FXML
	 private LineChart<String, Number> yearGraph;
	 @FXML
	 private NumberAxis osKwota;
	 @FXML
	 private CategoryAxis osDni;

	 SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
	    
	 public void initialize() {
		 YearGraphModel yearGraphModel = new YearGraphModel(new Date());
		 
		 try{
	    		yearGraphModel.init();
	    	}catch(Exception e) {
	    		 DialogUtils.errorDialog(e.getMessage());
	    	}
		 XYChart.Series series1 = new XYChart.Series();
	     series1.setName("Wydatki");
	     yearGraphModel.getGraphItems().forEach(i -> series1.getData().add(new XYChart.Data(sdf.format(i.getDate()), i.getAmountExpenses())));
	     XYChart.Series series2 = new XYChart.Series();
	     series2.setName("Przychody");
	     yearGraphModel.getGraphItems().forEach(i -> series2.getData().add(new XYChart.Data(sdf.format(i.getDate()), i.getAmountRevenues())));
	    // XYChart.Series series3 = new XYChart.Series();
	    // series1.setName("Oszczêdnoœci");
	     yearGraph.getData().addAll(series1, series2);
	 }

	 
}
