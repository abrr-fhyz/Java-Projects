import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;

class SliderController{
	private Slider progressSlider;
	private Slider volumeSlider;
	private MediaPlayer mediaPlayer;
	private Label songDuration;
	private Label volumeLevel;
    private boolean isClient = false;

	SliderController(MediaPlayer mediaPlayer, Slider progressSlider, Slider volumeSlider, Label songDuration, Label volumeLevel){
		this.mediaPlayer = mediaPlayer;
		this.progressSlider = progressSlider;
		this.songDuration = songDuration;
		this.volumeSlider = volumeSlider;
		this.volumeLevel = volumeLevel;
		handleVolumeSlider();
	}


	void handleSliderMovement() {
        progressSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (progressSlider.isValueChanging()) {
                mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(newValue.doubleValue() / 100.0));
            }
        });
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds() * 100;
            progressSlider.setValue(percentage);
            handleSliderColor(percentage, progressSlider);
            handleSongDuration(newValue.toSeconds());   
        });
    }


	private void handleSliderColor(double position, Slider thisSlider){
        if(Double.isNaN(position)){
            position = 0;
        }
        String style = String.format("-fx-background-color: linear-gradient(to right, green %f%%, lightgreen %f%%);", position, position);
        thisSlider.lookup(".track").setStyle(style);
        thisSlider.lookup(".thumb").setStyle("-fx-background-color: green;");
        thisSlider.lookup(".thumb").setStyle("-fx-fill: green;");
    }

    private void handleVolumeSlider() {
    	volumeSlider.setValue(mediaPlayer.getVolume());
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(newValue.doubleValue());
            int currentVolume = (int) (newValue.doubleValue() * 100);
            volumeLevel.setText(" " + String.valueOf(currentVolume));
            handleSliderColor((double) currentVolume, volumeSlider);
        });
    }

    private void handleSongDuration(double current) {
        int end = (int)mediaPlayer.getTotalDuration().toSeconds();
        int now = (int)current;
        String time;
        if(now%60 > 9){
            time = now/60 + ":" + now%60 + "/";
        } else {
            time = now/60 + ":0" + now%60 + "/";
        }
        if(end%60 > 9){
            time += end/60 + ":" + end%60;
        } else {
            time += end/60 + ":0" + end%60;
        }
        time += "  "; 
        songDuration.setText(time);
    }
}