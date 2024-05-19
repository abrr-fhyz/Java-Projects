import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class ServerDisplay extends MusicPlayerController{
	private MusicPlayerController musicPlayerController;
	private boolean isPaused = true;

	ServerDisplay(MusicPlayerController musicPlayerController) {
		this.musicPlayerController = musicPlayerController;
		setPlayerCover();
	}

	void togglePlayPause() {
		String pause = getPath("pause");
		String play = getPath("play");
		isPaused = !isPaused;
		Image image;
		if(isPaused){
			image = new Image(getClass().getResourceAsStream(pause));
		}
		else{
			image = new Image(getClass().getResourceAsStream(play));
		}
		musicPlayerController.playButton.setImage(image);
	}

	private String getPath(String imageName) {
		return "assets/" + imageName + ".png";
	}

	private void setPlayerCover() {
        Image image = new Image(getClass().getResourceAsStream("assets/client.jpg"));
        musicPlayerController.defaultImage.setImage(image);
        image = new Image(getClass().getResourceAsStream(getPath("play")));
        musicPlayerController.playButton.setImage(image);
        image = new Image(getClass().getResourceAsStream(getPath("replay")));
        musicPlayerController.replayButton.setImage(image);
        image = new Image(getClass().getResourceAsStream(getPath("speaker")));
        musicPlayerController.speakerImage.setImage(image);
        image = new Image(getClass().getResourceAsStream(getPath("forward")));
        musicPlayerController.nextButton.setImage(image);
        image = new Image(getClass().getResourceAsStream(getPath("backward")));
        musicPlayerController.previousButton.setImage(image);
    }
    
}