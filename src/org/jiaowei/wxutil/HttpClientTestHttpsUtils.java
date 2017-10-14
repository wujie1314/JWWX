package org.jiaowei.wxutil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

public class HttpClientTestHttpsUtils {

	public void post(){
		 try {  
			  
	            HttpClient httpclient = new DefaultHttpClient();  
	                        //Secure Protocol implementation.  
	            SSLContext ctx = SSLContext.getInstance("SSL");  
	                        //Implementation of a trust manager for X509 certificates  
	            X509TrustManager tm = new X509TrustManager() {  

					@Override
					public void checkClientTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						// TODO Auto-generated method stub
						
					}

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						// TODO Auto-generated method stub
						return null;
					}  
	            };  
	            ctx.init(null, new TrustManager[] { tm }, null);  
	            SSLSocketFactory ssf = new SSLSocketFactory(ctx);  
	  
	            ClientConnectionManager ccm = httpclient.getConnectionManager();  
	                        //register https protocol in httpclient's scheme registry  
	            SchemeRegistry sr = ccm.getSchemeRegistry();  
	            sr.register(new Scheme("https", 443, ssf));  
	  
	            
	            
	            HttpGet httpget = new HttpGet("");  
	            HttpParams params = httpclient.getParams();  
	  
	            params.setParameter("param1", "paramValue1");  
	  
	            httpget.setParams(params);  
	            ResponseHandler responseHandler = new BasicResponseHandler();  
	            String responseBody;  
	  
	            responseBody = (String) httpclient.execute(httpget, responseHandler);  
	  
	  
	            // Create a response handler  
	  
	        } catch (NoSuchAlgorithmException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ClientProtocolException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (Exception ex) {  
	            ex.printStackTrace();  
	  
	        }  
	}
	
}
