import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;

public class MusicSzyfrowanie {

	static byte[] dekodujPlik(Key klucz, File zaszyfrowanyPlik,
			File plikMuzyczny) {
		byte[] dane = null;
		// wektor inicjujacy
		IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);
		try {
			Cipher aesCipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
			aesCipher.init(Cipher.DECRYPT_MODE, klucz, ivSpec);

			File outFile = plikMuzyczny;
			File inFile = zaszyfrowanyPlik;
			FileOutputStream outputStream = new FileOutputStream(outFile);
			FileInputStream input = new FileInputStream(inFile);
			CipherInputStream inCipherStream = new CipherInputStream(input,
					aesCipher);
			dane = copy(inCipherStream, outputStream);

			inCipherStream.close();
			outputStream.close();
			aesCipher.doFinal(dane);
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
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dane;
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

	static byte[] zaszyfrujPlik(Key key, File plikZaszyfrowany,
			File plikMuzycznyWav) {
		byte[] dane = null;
		IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);
		try {
			// przygotowanie do zaszyfrowania
			Cipher aesCipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
			aesCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

			File inFile = plikMuzycznyWav;
			File outFile = plikZaszyfrowany;
			FileInputStream inputStream = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);
			CipherOutputStream outCipherStream = new CipherOutputStream(out,
					aesCipher);
			dane = copy(inputStream, outCipherStream);
			
			aesCipher.doFinal(dane, 0);
			out.close();
			inputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
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
		} catch (ShortBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dane;
	}

	private static byte[] copy(InputStream inStream, OutputStream outStream)
			throws IOException {
		byte[] buffer = new byte[16];
		int wczytaneBajty;

		wczytaneBajty = inStream.read(buffer);
		while (wczytaneBajty != -1) {
			outStream.write(buffer, 0, wczytaneBajty);
		}
		return buffer;
	}

}
