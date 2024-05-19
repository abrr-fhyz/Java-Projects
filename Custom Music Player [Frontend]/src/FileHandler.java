import javafx.scene.control.ListView;
import java.util.List;
import java.io.File;
import java.util.Arrays;
import javafx.collections.*;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.control.SelectionMode;

class FileHandler{
	private String directoryPath = "songs/";
	private ListView<String> fileList; 
    private MusicPlayerController musicPlayerController;
    private String firstSong;

	FileHandler(MusicPlayerController musicPlayerController){
        this.musicPlayerController = musicPlayerController;
		this.fileList = musicPlayerController.getFileList();
        handleFiles();
        selectFiles();
	}

	private void handleFiles() {
        boolean isFirstSong = true;
        File directory = new File(directoryPath);
        if(directory.isDirectory()){
            File[] files = directory.listFiles();
            ObservableList<String> songList = FXCollections.observableArrayList();
            for(File file: files){
            	AudioFile audio = new AudioFile(file);
                if(audio.getPath().endsWith(".mp3")){
                    songList.add(audio.getAudioName());
                    if(isFirstSong){
                        firstSong = audio.getAudioName();
                        isFirstSong = false;
                    }
                }
            }
            fileList.setItems(songList);
        }
    } 

    private void selectFiles() {
        fileList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        fileList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                musicPlayerController.newSongSelected(newValue);
            }
        });
    }

    String getFirstSong() {
        return this.firstSong;
    }
}
