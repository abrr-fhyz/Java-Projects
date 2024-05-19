import javafx.fxml.FXML;
import javafx.collections.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.List;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import javafx.stage.Stage;


public class MusicPlayerController {
    //FXML attributes
    @FXML private MediaPlayer mediaPlayer;
    @FXML private ListView<String> fileList;
    //FXML sliders
    @FXML private Slider progressSlider;
    @FXML private Slider volumeSlider;
    //FXML labels
    @FXML private Label songTitle;
    @FXML private Label songDuration; 
    @FXML private Label volumeLevel;
    @FXML private Label userNameField;
    //FXML icons
    @FXML protected ImageView defaultImage;
    @FXML protected ImageView playButton;
    @FXML protected ImageView replayButton;
    @FXML protected ImageView previousButton;
    @FXML protected ImageView nextButton;
    @FXML protected ImageView speakerImage;

    //Controller attributes
    private String userName;
    private Stage thisStage;
    private AudioFile currentSong;
    private SliderController slider;
    private FileHandler songList;
    private Settings settings;
    private boolean willMusicPlay = false;

    public void initialize() {
        songList = new FileHandler(this);
        settings = new Settings(this);
        setNewSong(songList.getFirstSong());
        playSong();
    }

    public void setUserName(String userName){
        this.userName = userName;
        this.userNameField.setText(userName);
    }

    public void setStage(Stage thisStage){
        this.thisStage = thisStage;
    }

    public void newSongSelected(String newSong) {
        if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
            settings.toggle();
        }
        stopMusic();
        setNewSong(newSong);
        playSong();
    }

    public ListView<String> getFileList() {
        return this.fileList;
    }

    private void setNewSong(String songName) {
        String pathToSong = "songs/" + songName + ".mp3";
        File newSong = new File(getClass().getResource(pathToSong).getFile());
        currentSong = new AudioFile(newSong);
        songTitle.setText("Now Playing:\t" + currentSong.getAudioName());
    }

    private void playSong() {
        mediaPlayer = new MediaPlayer(currentSong.getAudioFile());
        this.slider = new SliderController(mediaPlayer, progressSlider, volumeSlider, songDuration, volumeLevel);
        if(willMusicPlay){
            mediaPlayer.play();
        }

        mediaPlayer.setOnEndOfMedia(() -> {
            playDifferentSong(true);
        });
        slider.handleSliderMovement();
        toggleState();
    }

    private void playDifferentSong(boolean playNextSong) {
        String newSong = "";
        List<String> fileListAsList = fileList.getItems();
        if(playNextSong){
        	newSong = playNextSong(fileListAsList);
        }
        else{
        	newSong = playPrevSong(fileListAsList);
        }
        if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
            settings.toggle();
        }
        stopMusic();
       	setNewSong(newSong);
        willMusicPlay = true;
        playSong();
    }

    private String playPrevSong(List<String> fileNameList) {
        String newSong = "";
        for(String fileName: fileNameList){
            if(fileName.equals(currentSong.getAudioName())){
                break;
            }
            newSong = fileName;
        }
        if(newSong.isEmpty()){
        	newSong = fileNameList.get(0);
        }
       	return newSong;
    }

    private String playNextSong(List<String> fileNameList) {
        String newSong = "";
        boolean getThisSong = false;
        for(String fileName: fileNameList){
        	if(getThisSong){
        		newSong = fileName;
        		break;
        	}
            if(fileName.equals(currentSong.getAudioName())){
                getThisSong = true;
            }
        }
        if(newSong.isEmpty()){
           	newSong = fileNameList.get(0);
        }
        return newSong;
    }

    private void stopMusic() {
        mediaPlayer.stop();
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
    	playDifferentSong(true);
    }

    @FXML
    private void playPrevious() {
    	playDifferentSong(false);
    }

    @FXML
    private void changeRole() {
        stopMusic();
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setStage(thisStage);
        musicPlayer.setName(userName);
        try{
            musicPlayer.loadClient();
        } catch(IOException e) {}
        thisStage.close();
    }
}
