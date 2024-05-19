import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;



public class MusicPlayer{
    private Stage parentStage;
    private String userName = ""; 

    @FXML private TextField nameField;

    public void setStage(Stage parentStage){
        this.parentStage = parentStage;
    }

    public void setName(String name){
        this.userName = name;
    }

    private void generateUI(String resource, String type) {
        try {
            int sceneWidth = 675, sceneHeight = 450;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Parent root = loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root, sceneWidth, sceneHeight);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Personal Music Player [" + type + "]");
            if (type.equals("CLIENT")) {
                sendListener(primaryStage, loader);
            }
            else {
                sendPlayer(primaryStage, loader);
            }
            primaryStage.setMinWidth(sceneWidth);
            primaryStage.setMaxWidth(sceneWidth);
            primaryStage.setMinHeight(sceneHeight);
            primaryStage.setMaxHeight(sceneHeight);
            primaryStage.show();
            parentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendListener(Stage primaryStage, FXMLLoader loader) {
        ListenerController controller = loader.getController();
        controller.setStage(primaryStage);
        if(this.nameField != null)
            userName = nameField.getText();
        if(userName.equals("")){
            userName = "Default User";
        }
        controller.setUserName(userName);
    }

    private void sendPlayer(Stage primaryStage, FXMLLoader loader) {
        MusicPlayerController controller = loader.getController();
        controller.setStage(primaryStage);
        if(this.nameField != null)
            userName = nameField.getText();
        if(userName.equals("")){
            userName = "Default User";
        }
        controller.setUserName(userName);
    }

    @FXML
    public void loadServer() throws IOException {
        String resource = "gui/player.fxml";
        String type = "SERVER";
        generateUI(resource, type);
    }

    @FXML
    public void loadClient() throws IOException {
        String resource = "gui/listener.fxml";
        String type = "CLIENT";
        generateUI(resource, type);
    }
}