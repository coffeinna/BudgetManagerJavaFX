package BudgetManager;

import java.io.IOException;

import BudgetManager.database.dbutils.DbManager;
import BudgetManager.utils.FxmlUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application{
	public static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";
	
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) throws IOException {
    	
    	Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);

    	Scene scene = new Scene(borderPane);
    	primaryStage.setScene(scene);
        primaryStage.setTitle("Bugdet Manager");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/icons/swinka.jpg")));
        primaryStage.show();
        
        DbManager.initDatabase();
    }
}
