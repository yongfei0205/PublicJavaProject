package org.xiaoguo.spark.http.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Jsoup;
import org.xiaoguo.spark.http.grab.HttpResponse;

public class HttpUtils {

	public static HttpResponse doPost(String reqUrl, Map<String, String> parameters) {
		try{
			return doPost(reqUrl, generatorParamString(parameters));
		}
		catch (Exception e) {
			return new HttpResponse();
		}
	}

	/**
	 * UTF-8±àÂë
	 *
	 * @param link
	 * @return
	 */
	public static String doGet(String link) {
		return doGet(link, "UTF-8");
	}

	/**
	 *
	 * @param link
	 * @param charset
	 * @return
	 */
	public static String doGet(String link, String charset) {
		try {
			URL url = new URL(link);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
			BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int i = 0; (i = in.read(buf)) > 0;) {
				out.write(buf, 0, i);
			}
			out.flush();
			String s = new String(out.toByteArray(), charset);
			return s;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	// private static String doHttp(String url,String charset,String data,

	private static HttpResponse doPost(String url, String postData)  {
		HttpResponse res = new HttpResponse();
		String data = null;
		try {
			URL dataUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Proxy-Connection", "Keep-Alive");
			// con.setRequestProperty("Accept-Charset",
			// "GB2312,utf-8;q=0.7,*;q=0.7");
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");
			con.setRequestProperty("Content-type", "texst/html;charset=UTF-8");
			con.setRequestProperty("user-agent", "Opera/9.80 (Windows NT 6.1; U; zh-cn) Presto/2.10.229 Version/11.61");
			con.setDoOutput(true);
			con.setDoInput(true);
			// OutputStream os = con.getOutputStream();
			// DataOutputStream dos = new DataOutputStream(os);
			// dos.write(postData.getBytes());
			// dos.flush();

			// dos.close();
			res.stateCode = con.getResponseCode();
			if (res.stateCode == 200) {
				InputStream is = con.getInputStream();
				DataInputStream dis = new DataInputStream(is);

				{
					byte d[] = new byte[dis.available()];
					dis.read(d);
					data = new String(d, "utf-8");
					// System.out.println(data);
					con.disconnect();
				}
				res.document=Jsoup.parse(data,url);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	/**
	 *
	 * @param parameters
	 * @return
	 */
	private static String generatorParamString(Map<String, String> parameters) {
		StringBuffer params = new StringBuffer();
		if (parameters != null) {
			for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				String value = parameters.get(name);
				params.append(name + "=");
				try {
					params.append(URLEncoder.encode(value, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e.getMessage(), e);
				} catch (Exception e) {
					String message = String.format("'%s'='%s'", name, value);
					throw new RuntimeException(message, e);
				}
				if (iter.hasNext()) {
					params.append("&");
				}
			}
		}
		return params.toString();
	}

	private static String getContent(HttpURLConnection urlConn) {
		try {
			String responseContent = null;
			InputStream in = urlConn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
			return responseContent;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
