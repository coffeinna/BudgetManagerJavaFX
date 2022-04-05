package BudgetManager.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

public class DialogUtils {

	//public static Optional<ButtonType> confirmationDialog() {
		// TODO Auto-generated method stub
	//	return null;
	//}
	public static Optional<ButtonType> dialogExitConfirmation() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Wyjœcie");
        confirmationDialog.setHeaderText("Czy na pewno zamkn¹æ aplikacjê?");
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
	}
	
	public static void errorDialog(String error) {
		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle("B³¹d");
		errorAlert.setHeaderText("Wyst¹pi³ problem.");
		
		TextArea textArea = new TextArea(error);
		errorAlert.getDialogPane().setContent(textArea);
		errorAlert.showAndWait();
	}


}
