package BudgetManager.controllers;


import java.time.LocalDate;

import BudgetManager.modelFx.ExpensesFx;
import BudgetManager.modelFx.ExpensesModel;
import BudgetManager.utils.DialogUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ExpensesPanelController {
	
	@FXML
	private Button expensesSaveButton;
	@FXML
	private TextField expensesNameTextField;
	@FXML
	private TextField expensesAmountTextField;
	@FXML
	private DatePicker expensesDatePicker;
	@FXML
	private TableColumn<ExpensesFx, LocalDate> expensesDateColumn;
	@FXML
	private TableColumn<ExpensesFx, String> expensesAmountColumn;
	@FXML
	private TableColumn<ExpensesFx, String> expensesNameColumn;
    @FXML
    private TableColumn<ExpensesFx, ExpensesFx> expensesDeleteColumn;
    @FXML
    private TableView<ExpensesFx> expensesTableView;
    
    private ExpensesModel expensesModel;
    
	public void initialize() throws Exception {
		this.expensesModel = new ExpensesModel();
		try{	
			this.expensesModel.init();
    	}catch(Exception e) {
    		 DialogUtils.errorDialog(e.getMessage());
    	}
		this.expensesModel.expensesFxObjectPropertyProperty().get().nameProperty().bind(this.expensesNameTextField.textProperty());
    	this.expensesModel.expensesFxObjectPropertyProperty().get().amountProperty().bind(this.expensesAmountTextField.textProperty());
    	this.expensesModel.expensesFxObjectPropertyProperty().get().dateProperty().bind(this.expensesDatePicker.valueProperty());
    	
    	this.expensesTableView.setItems(this.expensesModel.getExpensesFxObservableList());
    	this.expensesNameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
    	this.expensesAmountColumn.setCellValueFactory(cellData->cellData.getValue().amountProperty());
    	this.expensesDateColumn.setCellValueFactory(cellData->cellData.getValue().dateProperty());
    	this.expensesDeleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.expensesDeleteColumn.setCellFactory(param -> new TableCell<ExpensesFx, ExpensesFx>() {
        Button button = createDeleteButton();
        
        @Override
        protected void updateItem(ExpensesFx item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
                return;
            }

            if (!empty) {
                setGraphic(button);
                button.setOnAction(event -> {
                    try {
                    	expensesModel.deleteExpensesInDataBase(item);
                    } catch (Exception e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                });
            }
        }
    });
    }  
    public static Button createDeleteButton(){
        Button button = new Button();
        button.getStyleClass().add("delete_button");
        Image image = new Image(("/icons/clear.png").toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }
    
    public void expensesSaveAction() throws Exception {
    	try {
    		this.expensesModel.saveExpensesInDataBase();
    	}catch (Exception e) {
    		DialogUtils.errorDialog(e.getMessage());
    	}
    	this.expensesAmountTextField.clear();
    	this.expensesNameTextField.clear();
    	this.expensesDatePicker.setValue(null);
    }
}