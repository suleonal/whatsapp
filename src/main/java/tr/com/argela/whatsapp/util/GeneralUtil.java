package tr.com.argela.whatsapp.util;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class GeneralUtil {
    
    public static String getMD5Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }

    public static String getGUID() {
        return getMD5Hash(System.nanoTime() + "");
    }
}
