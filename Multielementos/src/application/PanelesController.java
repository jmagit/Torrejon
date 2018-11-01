package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PanelesController {
	private static final String MEDIA_URL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
	@FXML
	MediaView player;
	
	public void VerVideo(ActionEvent e) {
		Media media = new Media(MEDIA_URL);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		//MediaView player = new MediaControl(mediaPlayer);
		player.setMediaPlayer(mediaPlayer);
		//mediaPlayer.play();
	}
}
