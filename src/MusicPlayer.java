import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class MusicPlayer {

	public static Clip clip;

	public static void main(String[] args) {

		try {
			
			File file = new File(
					"D:\\eclipse\\Semestr4\\AES\\Music.wav");
			AudioInputStream outSteream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(outSteream);

			clip.start();

			do {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} while (clip.isActive());

		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
