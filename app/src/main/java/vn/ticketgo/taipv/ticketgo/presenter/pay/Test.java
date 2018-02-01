package vn.ticketgo.taipv.ticketgo.presenter.pay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String vpc_Host = "https://migs.mastercard.com.au/vpcdps";
		int vpc_Port = 80;
		String vpc_Host = "http://payment.smartlink.com.vn/apigw/vpcdps";
		String fileName = "";
		
		if (vpc_Host.substring(0,8).equalsIgnoreCase("HTTPS://")) {
            // remove 'HTTPS://' from host URL
            vpc_Host = vpc_Host.substring(8);
            // get the filename from the last section of vpc_URL
            fileName = vpc_Host.substring(vpc_Host.indexOf("/"));
            // get the IP address of the VPC machine
            vpc_Host = vpc_Host.substring(0,vpc_Host.indexOf("/"));
        }
		if (vpc_Host.substring(0,7).equalsIgnoreCase("HTTP://")) {
            // remove 'HTTPS://' from host URL
            vpc_Host = vpc_Host.substring(7);
            // get the filename from the last section of vpc_URL
            fileName = vpc_Host.substring(vpc_Host.indexOf("/"));
            // get the IP address of the VPC machine
            vpc_Host = vpc_Host.substring(0,vpc_Host.indexOf("/"));
        }
		
		System.out.println("vpc_Host: " + vpc_Host);
		System.out.println("fileName: " + fileName);
		
		Socket s;
		try {
			s = new Socket(vpc_Host, vpc_Port);
			InputStream is;
	        OutputStream os;
	        os = s.getOutputStream();
	        is = s.getInputStream();
	        
	        String data = "vpc_Merchant=SMLTEST";
	        
	        String req = "POST " + fileName + " HTTP/1.0\r\n"
            + "User-Agent: HTTP Client\r\n"
            + "Content-Type: application/x-www-form-urlencoded\r\n"
            + "Content-Length: " + data.length() + "\r\n\r\n"
            + data;

			os.write(req.getBytes());
			String res = new String(readAll(is));
			
			System.out.println("res: " + res);
//			// check if a successful connection
//			if (res.indexOf("200") < 0) {
//			throw new IOException("Connection Refused - " + res);
//			}
//			
//			if (res.indexOf("404 Not Found") > 0) {
//			throw new IOException("File Not Found Error - " + res);
//			}
//			
//			int resIndex = res.indexOf("\r\n\r\n");
//			String body = res.substring(resIndex + 4, res.length());
//
//			System.out.println("Body: " + body);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static byte[] readAll(InputStream is) throws IOException {
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        
        while (true) {
            int len = is.read(buf);
            if (len < 0) {
                break;
            }
            baos.write(buf, 0, len);
        }
        return baos.toByteArray();
    }
}
