package BudgetManager.controllers;

import java.util.Optional;

import BudgetManager.utils.DialogUtils;
import BudgetManager.utils.FxmlUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;


public class MainController {

    @FXML
    private BorderPane borderPane;
    
    @FXML
    private SideMenuController sideMenuController;
    
    @FXML
    private void initialize() {
    	sideMenuController.setMainController(this);
    }
    
    @FXML
	public void closeApplication(ActionEvent actionEvent) {
		Platform.exit();
		System.exit(0);
	}
	
	public void setCenter(String fxmlPath) {
		borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
		
	} 
	
    public void topMenuRevenues() {
    	this.setCenter("/fxml/RevenuesPanel.fxml");
	}
    
    public void topMenuExpenses() {
    	this.setCenter("/fxml/ExpensesPanel.fxml");
	}

    public void topMenuResults() {
    	this.setCenter("/fxml/ResultPanel.fxml");
	}
    
    public void topMenuGraphMonth() {
    	this.setCenter("/fxml/MonthGraphPanel.fxml");
	}    
    
    public void topMenuGraphYear() {
    	this.setCenter("/fxml/MonthGraphPanel.fxml");
	}


    public void closeApplication() {
        Optional<ButtonType> result = DialogUtils.dialogExitConfirmation();
        if(result.get()==ButtonType.OK){
            Platform.exit();
            System.exit(0);
        }
    }
   

}
