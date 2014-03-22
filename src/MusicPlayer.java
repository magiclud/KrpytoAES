import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class MusicPlayer {

	public static Clip clip;

	static String trybSzyfrowania = "OFB";
	static String hasloDoKeystora = "ala ma kota";
	static String aliasHasla = "mojAlias";
	static String sciezkaDoKeyStore = "D:\\eclipse\\Semestr4\\AES\\keyStore.ks";
	
	int cipherBytes;
	public static void main(String[] args) {

		byte[] cipherBlok = MusicSzyfrowanie.przygotujDoZakodowania(MusicSzyfrowanie.pobierzKlucz(
				sciezkaDoKeyStore, new String(aliasHasla), new String(
						hasloDoKeystora)), trybSzyfrowania);
		
		
		
		try {
			
			File file = new File(
				"D:\\Pobieranie\\14.wav");
			AudioInputStream outSteream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(outSteream);
			
			clip.start();
	//clip.stop()

			//do {
				try {
					Thread.sleep(5000);
			
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			//} while (clip.isActive());

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
