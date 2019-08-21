package ua.sberezh;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioThread implements Runnable {

	@Override
	public void run() {
		try {
			Player p = new Player(new FileInputStream("song.mp3"));
			p.play();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
}