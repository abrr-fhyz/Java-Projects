import javafx.scene.media.Media;
import java.io.File;

class AudioFile{
	private File audioFile;
	private String audioFileName;
	private String pathToFile;
	private String directoryPath = "songs/";

	AudioFile(File audioFile){
		this.audioFile = audioFile;
		this.audioFileName = audioFile.getName().replace("%20", " ").replaceFirst("\\.mp3$", "");
		this.pathToFile = directoryPath + audioFile.getName().replace("%20", " ");
	}

	String getAudioName(){
		return this.audioFileName;
	}

	String getPath(){
		return this.pathToFile;
	}

	Media getAudioFile(){
		return new Media(getClass().getResource(pathToFile).toString());
	}
}