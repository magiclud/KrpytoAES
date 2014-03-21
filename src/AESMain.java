import java.io.Console;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AESMain {

	public static void main(String[] args) throws InvalidKeyException,
			NoSuchAlgorithmException, KeyStoreException, CertificateException,
			IOException, UnrecoverableKeyException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			InvalidAlgorithmParameterException {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		String trybSzyfrowania = "OFB";
		String hasloDoKeystora = "ala ma kota";
		String aliasHasla = "mojAlias";
		String sciezkaDoKeyStore = " D:\\eclipse\\Semestr4\\AES\\keyStore.ks";
		// Console console = System.console();
		// console.printf("Podaj tryb szyfrowania np. OFB lub CTR \n");
		// String trybSzyfrowania = console.readLine();
		// console.printf("Podaj sciezke do keystora przechowujcego klucz \n");
		// String sciezkaDoKeyStore = console.readLine();
		// char[] hasloDoKeystora = console
		// .readPassword("Haslo do KeyStore'a: ");
		// char aliasHasla[] = console
		// .readPassword("Identyikator  do klucza z keystore'a: \n");

		// String hasloDoKeystora = "ala ma kota";
		// String aliasHasla = "mojAlias";
		// String sciezkaDoKeyStore = "
		// D:\eclipse\Semestr4\KryptographyLista1\keyStore.ks";

		String wiadomosc = AES.oczytZPlikuWiadomosci();
		byte[] kryptogram = AES.zakoduj(wiadomosc, AES.pobierzKlucz(
				sciezkaDoKeyStore, new String(aliasHasla), new String(
						hasloDoKeystora)), trybSzyfrowania);
		AES.zapiszZakodowanaWiadomoscDoPilku(kryptogram);

		byte[] zdekodowanyTekst = AES.dekoduj(kryptogram, AES.pobierzKlucz(
				sciezkaDoKeyStore, new String(aliasHasla), new String(
						hasloDoKeystora)), trybSzyfrowania);

		System.out.println("Wiadomosc: " + wiadomosc);
		System.out.println("Kryptogram: " + new String(kryptogram));
		System.out.println("Odszyfrowana wiadomosc: "
				+ new String(zdekodowanyTekst));

	}
}
