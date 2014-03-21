import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

public class AES {

	static byte[] dekoduj(byte[] kryptogram, Key klucz, String trybSzyfrowania) {

		try {
		//wektor inicjujacy
			IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);
			Cipher cipher = Cipher.getInstance("AES/" + trybSzyfrowania
					+ "/NoPadding", "BC");
			cipher.init(Cipher.DECRYPT_MODE, klucz, ivSpec);
			return cipher.doFinal(kryptogram);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kryptogram;
	}

	static Key pobierzKlucz(String sciezkaDoKeyStore, String aliasHasla,
			String hasloDoKeystora) {

		try {
			KeyStore ks = KeyStore.getInstance("UBER", "BC");
			InputStream inputStream = new FileInputStream(sciezkaDoKeyStore);
			ks.load(inputStream, hasloDoKeystora.toCharArray());

			// klucz zostal juz wygenerowany przy liscie 1 - java project:
			// KryptographyLista1
			// klucz jest 128-bitowy wygenerowany szyfrem strumieniowym ARC4
			return ks.getKey(aliasHasla, hasloDoKeystora.toCharArray());
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	static byte[] zakoduj(String wiadomosc, Key key, String trybSzyfrowania) {
		try {
			IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);

		
			Cipher aesCipher = Cipher.getInstance("AES/" + trybSzyfrowania
					+ "/NoPadding", "BC");
			aesCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			return aesCipher.doFinal(wiadomosc.getBytes());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void zapiszZakodowanaWiadomoscDoPilku(byte[] kryptogram, String plikZWiadomoscia) {

		File kryptogramFile = new File(plikZWiadomoscia);
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(kryptogramFile);

			stream.write(kryptogram);
			stream.close();
			// System.out.println("sceizka do kryptogramu"
			// + kryptogramFile.getAbsolutePath());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static String oczytZPlikuWiadomosci(String plikZWiadomoscia) {

		FileReader file;
		BufferedReader bufferedReader;
		String wiadomosc = "";
		try {
			file = new FileReader(plikZWiadomoscia);
			bufferedReader = new BufferedReader(file);
			String wczytaneDane = bufferedReader.readLine();
			do {
				wiadomosc = wiadomosc + wczytaneDane;
				//System.out.println(wczytaneDane);
				wczytaneDane = bufferedReader.readLine();
			
			} while (wczytaneDane != null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wiadomosc;
	}
}
