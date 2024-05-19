import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class ClientDisplay extends ListenerController{
	private ListenerController listenerController;
	private boolean isPaused = true;

	ClientDisplay(ListenerController listenerController) {
		this.listenerController = listenerController;
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
		listenerController.playButton.setImage(image);
	}

	private String getPath(String imageName) {
		return "assets/" + imageName + ".png";
	}

	private void setPlayerCover() {
        Image image = new Image(getClass().getResourceAsStream(getPath("server")));
        listenerController.defaultImage.setImage(image);
        image = new Image(getClass().getResourceAsStream(getPath("play")));
        listenerController.playButton.setImage(image);
        image = new Image(getClass().getResourceAsStream(getPath("replay")));
        listenerController.replayButton.setImage(image);
        image = new Image(getClass().getResourceAsStream(getPath("speaker")));
        listenerController.speakerImage.setImage(image);
        image = new Image(getClass().getResourceAsStream(getPath("forward")));
        listenerController.nextButton.setImage(image);
        image = new Image(getClass().getResourceAsStream(getPath("backward")));
        listenerController.previousButton.setImage(image);
    }
    
}