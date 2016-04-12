package cn.it.shop.util;

import java.nio.charset.Charset;

public class EncryptAndDecrypt {

	private static EncryptAndDecrypt instance = null;

	private EncryptAndDecrypt() {
		// TODO Auto-generated constructor stub
	}

	public static synchronized EncryptAndDecrypt getInstance() {
		if (instance == null) {
			instance = new EncryptAndDecrypt();
		}
		return instance;

	}

	private static final String key = "iwant'ttotellyou";	
	private static final Charset charset = Charset.forName("UTF-8");
	private static byte[] keyBytes = key.getBytes(charset);

	public String encode(String enc) {
		byte[] b = enc.getBytes(charset);
		for (int i = 0, size = b.length; i < size; i++) {
			for (byte keyBytes0 : keyBytes) {
				b[i] = (byte) (b[i] ^ keyBytes0);
			}
		}
		return new String(b);
	}

	public String decode(String dec) {
		byte[] e = dec.getBytes(charset);
		byte[] dee = e;
		for (int i = 0, size = e.length; i < size; i++) {
			for (byte keyBytes0 : keyBytes) {
				e[i] = (byte) (dee[i] ^ keyBytes0);
			}
		}
		return new String(e);
	}
}
