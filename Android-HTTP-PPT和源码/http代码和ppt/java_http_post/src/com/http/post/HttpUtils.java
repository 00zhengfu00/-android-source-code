package com.http.post;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

	// ����������˵�url
	private static String PATH = "http://61.4.185.48:81/g/";
	private static URL url;

	public HttpUtils() {
		// TODO Auto-generated constructor stub
	}

	static {
		try {
			url = new URL(PATH);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param params
	 *            ��д��url�Ĳ���
	 * @param encode
	 *            �ֽڱ���
	 * @return
	 * @throws IOException 
	 */
	public static String sendPostMessage(
			String encode) throws IOException {
		// ��ΪStringBuffer��ʼ�����ַ���
		StringBuffer buffer = new StringBuffer();
		 
			// System.out.println(buffer.toString());
			// ɾ��������һ��&
			
			System.out.println("-->>"+buffer.toString());
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(30000);
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoInput(true);// ��ʾ�ӷ�������ȡ����
			urlConnection.setDoOutput(true);// ��ʾ�������д����
			// ����ϴ���Ϣ���ֽڴ�С�Լ�����
			byte[] mydata = buffer.toString().getBytes();
			// ��ʾ������������������ı�����
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("Content-Length",
					String.valueOf(mydata.length));
			// ��������,��������������
			OutputStream outputStream = urlConnection.getOutputStream();
			outputStream.write(mydata,0,mydata.length);
			outputStream.close();
			// ��÷�������Ӧ�Ľ����״̬��
			int responseCode = urlConnection.getResponseCode();
			if (responseCode == 200) {
				System.out.println("����ɹ� ��");
				System.out.println("********************************");
				return changeInputStream(urlConnection.getInputStream(), encode);

			}
		 
		 return "";
	}
/**
	 * ��һ��������ת����ָ��������ַ���
	 * 
	 * @param inputStream
	 * @param encode
	 * @return
	 */
	private static String changeInputStream(InputStream inputStream,
			String encode) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
		if (inputStream != null) {
			try {
				while ((len = inputStream.read(data)) != -1) {
					outputStream.write(data, 0, len);
				}
				result = new String(outputStream.toByteArray(), encode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.print(result);
		return result;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
		try {
			HttpUtils.sendPostMessage("GBK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
