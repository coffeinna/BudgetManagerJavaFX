package BudgetManager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class SideMenuController {


private static final String REVENUESPANEL_FXML = "/fxml/RevenuesPanel.fxml";
private static final String EXPENSESPANEL_FXML = "/fxml/ExpensesPanel.fxml";
private static final String RESULTPANEL_FXML = "/fxml/ResultPanel.fxml";
private static final String MONTHGRAPHPANEL_FXML = "/fxml/MonthGraphPanel.fxml";
private static final String YEARGRAHPPANEL_FXML = "/fxml/YearGraphPanel.fxml";

private MainController mainController;

public void setMainController(MainController mainController) {
    this.mainController = mainController;
}

@FXML
private ToggleGroup toggleButtons;

@FXML
public void revenuesButtonAction() {
	mainController.setCenter(REVENUESPANEL_FXML);
}
@FXML
public void expensesButtonAction() {
	mainController.setCenter(EXPENSESPANEL_FXML);
}
@FXML
public void resultButtonAction() {
	mainController.setCenter(RESULTPANEL_FXML);
}
@FXML
public void monthGraphButtonAction() {
	mainController.setCenter(MONTHGRAPHPANEL_FXML);
}
@FXML
public void yearGraphButtonAction() {
	mainController.setCenter(YEARGRAHPPANEL_FXML);
}
}
