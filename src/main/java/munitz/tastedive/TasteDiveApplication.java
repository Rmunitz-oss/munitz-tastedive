package munitz.tastedive;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TasteDiveApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TasteDiveService service = new TasteDiveServiceFactory().newInstance();
        TasteDiveController controller = new TasteDiveController(service);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tastedive_application.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Taste Dive");
        stage.setScene(scene);
        stage.show();
    }
    public static void main (String [] args){
        launch(args);
    }
}
