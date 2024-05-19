import javafx.fxml.FXML;
import javafx.collections.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;



public class ListenerController {
    //FXML attributes
    @FXML private MediaPlayer mediaPlayer;
    //FXML sliders
    @FXML private Slider progressSlider;
    @FXML private Slider volumeSlider;
    //FXML labels
    @FXML private Label songTitle;
    @FXML private Label songDuration; 
    @FXML private Label volumeLevel;
    @FXML private Label serverStatus;
    @FXML private Label userNameField;
    @FXML private TextArea statusText;
    //FXML icons
    @FXML protected ImageView defaultImage;
    @FXML protected ImageView playButton;
    @FXML protected ImageView replayButton;
    @FXML protected ImageView previousButton;
    @FXML protected ImageView nextButton;
    @FXML protected ImageView speakerImage;

    //Controller attributes
    private AudioFile currentSong;
    private SliderController slider;
    private Settings settings;
    private boolean willMusicPlay = false;
    private String userName;
    private Stage thisStage;

    public void initialize() {
        settings = new Settings(this);
        //example
        String pathToSong = "songs/Aaste.mp3";
        File aaste = new File(getClass().getResource(pathToSong).getFile());
        AudioFile eitaexample = new AudioFile(aaste);
        setNewSong(eitaexample);
        //
        playSong();
    }

    public void setStage(Stage thisStage){
        this.thisStage = thisStage;
    }

    public void setUserName(String userName){
        this.userName = userName;
        this.userNameField.setText(userName);
    }

    private void setNewSong(AudioFile recievedAudio) {
        currentSong = recievedAudio;
        songTitle.setText("Now Playing:\t" + currentSong.getAudioName());
    }

    private void playSong() {
        mediaPlayer = new MediaPlayer(currentSong.getAudioFile());
        this.slider = new SliderController(mediaPlayer, progressSlider, volumeSlider, songDuration, volumeLevel);
        if(willMusicPlay){
            mediaPlayer.play();
        }

        mediaPlayer.setOnEndOfMedia(() -> {
            statusText.setText("The Song is Over. Want to hear another one?");
            statusText.setStyle("-fx-font-weight: bold; -fx-wrap-text: true; -fx-font-family: 'Agency FB'");
        });
        slider.handleSliderMovement();
        toggleState();
    }

    private void stopMusic() {
        mediaPlayer.stop();
    }

    private void permissionDenied() {
        statusText.setText("PERMISSION DENIED:\nCan't change songs as Listener");
        statusText.setStyle("-fx-font-weight: bold; -fx-wrap-text: true; -fx-font-family: 'Agency FB'");
    }

    @FXML
    private void serverFound() {
        serverStatus.setText("Server is Connected");
        serverStatus.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-family: Consolas;");
    }

    @FXML
    private void serverNotFound() {
        serverStatus.setText("Server Not Connected");
        serverStatus.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-family: Consolas;");
    }

    @FXML
   	private void changeRole() {
        stopMusic();
    	MusicPlayer musicPlayer = new MusicPlayer();
    	musicPlayer.setStage(thisStage);
    	try{
    		musicPlayer.loadServer();
    	} catch(IOException e) {}
    	thisStage.close();
    }

    @FXML
    private void toggleState() {
        if(mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING && willMusicPlay){
            mediaPlayer.play();
        } else {
            mediaPlayer.pause();
        }
        settings.toggle();
        willMusicPlay = true;
    }

    @FXML
    private void restartMusic() {
        stopMusic();
        mediaPlayer.play();
    }

    @FXML
    private void playNext() {
    	permissionDenied();
    }

    @FXML
    private void playPrevious() {
    	permissionDenied();
    }
}
