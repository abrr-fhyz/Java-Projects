class Settings {
    private ServerDisplay serverDisplay;
    private ClientDisplay clientDisplay;

    Settings(MusicPlayerController musicPlayerController) {
        serverDisplay = new ServerDisplay(musicPlayerController);
    }

    Settings(ListenerController listenerController){
        clientDisplay = new ClientDisplay(listenerController);
    }

    void toggle(){
        if (serverDisplay != null) {
            serverDisplay.togglePlayPause();
        } else if (clientDisplay != null) {
            clientDisplay.togglePlayPause();
        }
    }
    //themes and stuff and other things add here

}
