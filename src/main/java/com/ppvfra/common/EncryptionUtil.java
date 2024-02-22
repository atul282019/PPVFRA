package com.ppvfra.common;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class EncryptionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionUtil.class);

    public static final String ALGO_SHA = "SHA";

    public static final String UTF8 = "UTF-8";

    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String FILE_SEPARATOR = "/";

    private static final int RADIX = 16;

    private static final int BITWISE_OP = 0xff;

    private EncryptionUtil() {
    }

    public static String generateRandomString() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    public static final String ALGORITHM_AES = "AES";

    public static String encrypt(String value, String cipherKey) {
        String encryptedCharSeq = value;
        try {
            SecretKeySpec sks = new SecretKeySpec(EncryptionUtil.hexStringToByteArray(cipherKey), ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
            cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
            byte[] encrypted = cipher.doFinal(value.getBytes());
            encryptedCharSeq = EncryptionUtil.byteArrayToHexString(encrypted);
        } catch (Exception ex) {
            LOGGER.error("Exception", ex);
        }
        return encryptedCharSeq;
    }

    public static String decrypt(String encryptedString, String cipherKey) {
        String decryptedCharSeq = encryptedString;
        try {
            SecretKeySpec sks = new SecretKeySpec(EncryptionUtil.hexStringToByteArray(cipherKey), ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte[] decrypted = cipher.doFinal(EncryptionUtil.hexStringToByteArray(encryptedString));
            decryptedCharSeq = new String(decrypted);
        } catch (Exception ex) {
            LOGGER.error("Exception", ex);
        }
        return decryptedCharSeq;
    }

    private static String byteArrayToHexString(byte[] encryptedByteArray) {
        StringBuilder sb = new StringBuilder(encryptedByteArray.length * 2);
        for (int i = 0; i < encryptedByteArray.length; i++) {
            int v = encryptedByteArray[i] & BITWISE_OP;
            if (v < RADIX) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private static byte[] hexStringToByteArray(String hexString) {
        byte[] b = new byte[hexString.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(hexString.substring(index, index + 2), RADIX);
            b[i] = (byte) v;
        }
        return b;
    }

    public static String encryptCharSequence(final String encryptionAlgo, final String charSequence) {
        final StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance(encryptionAlgo);
            md.update(charSequence.getBytes( DEFAULT_ENCODING));
            final byte[] byteData = md.digest();
            // convert the byte to hex format
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString(byteData[i], 0));
            }
        } catch (final NoSuchAlgorithmException e) {
            LOGGER.error("NoSuchAlgorithmException:: {}", e);
        } catch (final UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException:: {}", e);
        }

        return sb.toString();
    }

    public static String encode(String charSeq, final String encoding) {
        String urlStr = charSeq;
        try {
            if (StringUtils.isNotEmpty(urlStr)) {
                urlStr = URLEncoder.encode(urlStr,
                        StringUtils.isEmpty(encoding) ? DEFAULT_ENCODING : encoding);
            }
        } catch (final UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException:: {}", e);
        }
        return urlStr;
    }

    public static String decode(String charSeq, final String encoding) {
        String urlStr = charSeq;
        try {
            if (StringUtils.isNotEmpty(urlStr)) {
                urlStr = URLDecoder.decode(urlStr,
                        StringUtils.isEmpty(encoding) ? DEFAULT_ENCODING : encoding);
            }
        } catch (final UnsupportedEncodingException ex) {
            LOGGER.error("UnsupportedEncodingException:: {}", ex);
        } catch (final IllegalArgumentException ex) {
            LOGGER.error("IllegalArgumentException:: {}", ex);
        }
        return urlStr;
    }

    public static String encryptWithSHA(String plainText) {
        String encrypted = StringUtils.EMPTY;
        if (StringUtils.isNotEmpty(plainText)) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(plainText.getBytes(UTF8));
                encrypted = Base64.getEncoder().encodeToString(md.digest());
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                LOGGER.error("Could not encrypt!!", ex);
                encrypted = plainText;
            }
        }
        return encrypted;
    }
    
    public static String encryptPass(String pass) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;

    }
}
