package vn.ticketgo.taipv.ticketgo.presenter.pay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Payment {
    //SMLTEST
    static final String SECURE_SECRET = "198BE3F2E8C75A53F38C1C4A5B6DBA27";
    private String virtualPaymentClientUrl;
    private String secureSecret;

	// This is an array for creating hex chars
	static final char[] HEX_TABLE = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public String getVirtualPaymentClientUrl() {
		return virtualPaymentClientUrl;
	}

	public void setVirtualPaymentClientUrl(String virtualPaymentClientUrl) {
		this.virtualPaymentClientUrl = virtualPaymentClientUrl;
	}

	public String getSecureSecret() {
		return secureSecret;
	}

	public void setSecureSecret(String secureSecret) {
		this.secureSecret = secureSecret;
	}

    public String hashAllFields(Map fields) {

        // create a list and sort it
        List fieldNames = new ArrayList(fields.keySet());
        Collections.sort(fieldNames);

        // create a buffer for the md5 input and add the secure secret first
        StringBuffer buf = new StringBuffer();
        buf.append(secureSecret);

        // iterate through the list and add the remaining field values
        Iterator itr = fieldNames.iterator();

		while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
            	System.out.println(fieldName + ": " + fieldValue);
                buf.append(fieldValue);
            }
 	    }

        MessageDigest md5 = null;
        byte[] ba = null;
        System.out.println("input: " + buf.toString());
        // create the md5 hash and UTF-8 encode it
        try {
            md5 = MessageDigest.getInstance("MD5");
            ba = md5.digest(buf.toString().getBytes("UTF-8"));
         } catch (Exception e) {} // wont happen

        //return buf.toString();
        return hex(ba);

	}
    //END hashAllFields()

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    /**
     * Returns Hex output of byte array
     */
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static String hex(byte[] input) {
        // create a StringBuffer 2x the size of the hash array
        StringBuffer sb = new StringBuffer(input.length * 2);

        // retrieve the byte array data, convert it to hex
        // and add it to the StringBuffer
        for (int i = 0; i < input.length; i++) {
            sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
            sb.append(HEX_TABLE[input[i] & 0xf]);
        }
        return sb.toString();
    }
    //END hex()
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    /**
    * This method is for creating a URL query string.
    *
    * @param fields is the input parameters from the order page
    */
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getRedirectUrl(Map fields) {
    	StringBuffer buf = new StringBuffer();
    	buf.append(virtualPaymentClientUrl);
    	buf.append("?");

    	if (secureSecret != null && secureSecret.length() > 0) {
    		String secureHash = hashAllFields(fields);
    		fields.put("vpc_SecureHash", secureHash);
    	}

        // create a list
        List fieldNames = new ArrayList(fields.keySet());
        Iterator itr = fieldNames.iterator();

        // move through the list and create a series of URL key/value pairs
        while (itr.hasNext()) {
            String fieldName = (String)itr.next();
            String fieldValue = (String)fields.get(fieldName);

            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // append the URL parameters
                buf.append(URLEncoder.encode(fieldName));
                buf.append('=');
                buf.append(URLEncoder.encode(fieldValue));
            }

            // add a '&' to the end if we have more fields coming.
            if (itr.hasNext()) {
                buf.append('&');
            }
        }
        return buf.toString();
    }

    public String getResponseDescription(String vResponseCode) {
        String result = "";
        // check if a single digit response code
        if (vResponseCode.length() >= 1) {
            // Java cannot switch on a string so turn everything to a char
            char input = vResponseCode.charAt(0);
            switch (input){
                case '0' : result = "Giao dá»‹ch thÃ nh cÃ´ng"; break;
                case '1' : result = "NgÃ¢n hÃ ng tá»« chá»‘i thanh toÃ¡n: tháº»/tÃ i khoáº£n bá»‹ khÃ³a"; break;
                case '3' : result = "Tháº» háº¿t háº¡n"; break;
                case '4' : result = "Lá»—i ngÆ°á»�i mua hÃ ng: QuÃ¡ sá»‘ láº§n cho phÃ©p. (Sai OTP, quÃ¡ háº¡n má»©c trong ngÃ y)"; break;
                case '5' : result = "KhÃ´ng cÃ³ tráº£ lá»�i cá»§a NgÃ¢n hÃ ng"; break;
                case '6' : result = "Lá»—i giao tiáº¿p vá»›i NgÃ¢n hÃ ng"; break;
                case '7' : result = "TÃ i khoáº£n khÃ´ng Ä‘á»§ tiá»�n"; break;
                case '8' : result = "Lá»—i checksum dá»¯ liá»‡u"; break;
                case '9' : result = "Kiá»ƒu giao dá»‹ch khÃ´ng Ä‘Æ°á»£c há»— trá»£"; break;
                default  : result = "KhÃ´ng xÃ¡c Ä‘á»‹nh";
            }
            return result;
        } else {
            return "KhÃ´ng cÃ³ giÃ¡ trá»‹ tráº£ vá»�";
        }
    }

    public String getQueryData(String url) throws IOException {
    	URL queryURL = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(queryURL.openStream()));

        String inputLine = null;
        inputLine = in.readLine();
        in.close();
        return inputLine;
    }
}
