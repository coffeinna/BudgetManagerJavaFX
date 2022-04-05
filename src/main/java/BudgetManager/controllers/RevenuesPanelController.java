package BudgetManager.controllers;

import java.time.LocalDate;

import BudgetManager.modelFx.ExpensesFx;
import BudgetManager.modelFx.ExpensesModel;
import BudgetManager.modelFx.RevenuesFx;
import BudgetManager.modelFx.RevenuesModel;
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

public class RevenuesPanelController {

	@FXML
	private Button revenuesSaveButton;
	@FXML
	private TextField revenuesNameTextField;
	@FXML
	private TextField revenuesAmountTextField;
	@FXML
	private DatePicker revenuesDatePicker;
	@FXML
	private TableColumn<RevenuesFx, LocalDate> revenuesDateColumn;
	@FXML
	private TableColumn<RevenuesFx, String> revenuesAmountColumn;
	@FXML
	private TableColumn<RevenuesFx, String> revenuesNameColumn;
    @FXML
    private TableColumn<RevenuesFx, RevenuesFx> revenuesDeleteColumn;
    @FXML
    private TableView<RevenuesFx> revenuesTableView;
    
    private RevenuesModel revenuesModel;
    
    public void initialize() throws Exception {
    	this.revenuesModel = new RevenuesModel();
    	try{
    		this.revenuesModel.init();
    	}catch(Exception e) {
    		 DialogUtils.errorDialog(e.getMessage());
    	}
    	this.revenuesModel.revenuesFxObjectPropertyProperty().get().nameProperty().bind(this.revenuesNameTextField.textProperty());
    	this.revenuesModel.revenuesFxObjectPropertyProperty().get().amountProperty().bind(this.revenuesAmountTextField.textProperty());
    	this.revenuesModel.revenuesFxObjectPropertyProperty().get().dateProperty().bind(this.revenuesDatePicker.valueProperty());
    	
    	this.revenuesTableView.setItems(this.revenuesModel.getRevenuesFxObservableList());
    	this.revenuesNameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
    	this.revenuesAmountColumn.setCellValueFactory(cellData->cellData.getValue().amountProperty());
    	this.revenuesDateColumn.setCellValueFactory(cellData->cellData.getValue().dateProperty());
    	this.revenuesDeleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.revenuesDeleteColumn.setCellFactory(param -> new TableCell<RevenuesFx, RevenuesFx>() {
        Button button = createDeleteButton();
        
        @Override
        protected void updateItem(RevenuesFx item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
                return;
            }

            if (!empty) {
                setGraphic(button);
                button.setOnAction(event -> {
                    try {
                        revenuesModel.deleteRevenuesInDataBase(item);
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
    
    public void revenuesSaveAction() throws Exception {
    	try {
    		this.revenuesModel.saveRevenuesInDataBase();
    	}catch (Exception e) {
    		DialogUtils.errorDialog(e.getMessage());
    	}
    	this.revenuesAmountTextField.clear();
    	this.revenuesNameTextField.clear();
    	this.revenuesDatePicker.setValue(null);
    }
}
