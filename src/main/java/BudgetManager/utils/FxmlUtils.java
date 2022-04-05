package BudgetManager.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


public class FxmlUtils {
    public static Pane fxmlLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        try {
            return loader.load();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }
    public static FXMLLoader getLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        return loader;
    }

    
}
