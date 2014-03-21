import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

public class Music {

	public static void main(String[] args) {
		try {
			String nazwaPliku = "Marek_Grechuta-dni_ktorych_nie_znamy";
			InputStream is = getClass().getResourceAsStream("D:\\Pobieranie\\"+nazwaPliku);
			Player p = Manager.createPlayer(is, "audio/X-wav");
			p.prefetch();
			p.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MediaException e) {
			e.printStackTrace();
		}
	}
}
