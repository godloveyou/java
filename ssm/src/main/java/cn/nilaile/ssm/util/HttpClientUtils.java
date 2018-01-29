package cn.nilaile.ssm.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;  
/**
 * 使用HttpClient4.3.5发送GET/POST/请求
 * 支持Https请求
 * @author david
 * @since 2018-01-29
 */
public class HttpClientUtils {
	
	public static void main(String[] args) throws Exception {
		HttpClientUtils.get("http://www.feeair.com");
		Map<String,String> params = new HashMap<String,String>();
		params.put("LonginSn", "15537954009");
		params.put("LoginPassword", "ddvidd223434");
		params.put("v_code", "83sye");
		HttpClientUtils.postHttps("https://feeair.com/public/login/login_loginon.xhtml", params);
	}
	
	public static String post(CloseableHttpClient client,String url,Map<String,String> params){
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse res = null;
		UrlEncodedFormEntity uefEntity;  
		String result = "";
		try {
			//创建参数队列  
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
	        Set<String> set = params.keySet();
	        Iterator<String> it = set.iterator();
	        while(it.hasNext()){
	        	String key = it.next();
	        	String v = params.get(key);
	        	   formparams.add(new BasicNameValuePair(key, v));  
	        }
	        uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
	        post.setEntity(uefEntity);
            
	        res = client.execute(post);
			HttpEntity entity = res.getEntity();
			System.out.println("返回结果*****************************************************************");
			result = EntityUtils.toString(entity,"utf-8");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				res.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 发送Http请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postHttp(String url,Map<String,String> params){
		CloseableHttpClient client = HttpClients.createDefault();
		return post(client,url,params);
	}
	
	/**
	 * 发送Https post请求 
	 * (不需要导入证书，SSL信任所有证书)
	 */
	public static String postHttps(String url,Map<String,String> params){
		CloseableHttpClient client = createSSLClientDefault(); //不需要导入证书，SSL信任所有证书
		return post(client,url,params);
	}
	
	/**
	 * 发送get请求
	 * @param url
	 * @return
	 */
	public static String get(String url){
	    CloseableHttpClient httpclient = HttpClients.createDefault(); 
	    HttpGet get = new HttpGet(url);
	    String result = "";
	    CloseableHttpResponse res = null;
	     try {
	    	res = httpclient.execute(get);
	    	HttpEntity entity = res.getEntity();
	    	if(null!=entity){
	    		//打印响应内容长度    
                result = EntityUtils.toString(entity,"utf-8");
                System.out.println(result);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				res.close();
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	   return result;
	}
	
	/**  
     * HttpClient连接SSL，需要导入证书的方法  
     * 若SSL需要导入信任的证书，使用该方法  
     * @param url  
     */  
    public static void ssl(String url) {  
        CloseableHttpClient httpclient = null;  
        try {  
            KeyStore trustStore = KeyStore.getInstance(KeyStore  
                    .getDefaultType());  
            FileInputStream instream = new FileInputStream(new File(  
                    "E:\\tomcat.keystore"));  
            try {  
                // 加载keyStore d:\\tomcat.keystore  
                trustStore.load(instream, "123456".toCharArray());  
            } catch (CertificateException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    instream.close();  
                } catch (Exception ignore) {  
                }  
            }  
            // 相信自己的CA和所有自签名的证书  
            SSLContext sslcontext = SSLContexts  
                    .custom()  
                    .loadTrustMaterial(trustStore,  
                            new TrustSelfSignedStrategy()).build();  
            // 只允许使用TLSv1协议  
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
                    sslcontext,  
                    new String[] { "TLSv1" },  
                    null,  
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);  
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf)  
                    .build();  
            // 创建http请求(get方式)  
            HttpGet httpget = new HttpGet(url);  
            System.out.println("executing request" + httpget.getRequestLine());  
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                HttpEntity entity = response.getEntity();  
                System.out.println("----------------------------------------");  
                System.out.println(response.getStatusLine());  
                if (entity != null) {  
                    System.out.println("Response content length: "  
                            + entity.getContentLength());  
                    System.out.println(EntityUtils.toString(entity));  
                    EntityUtils.consume(entity);  
                }  
            } finally {  
                response.close();  
            }  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (KeyManagementException e) {  
            e.printStackTrace();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (KeyStoreException e) {  
            e.printStackTrace();  
        } finally {  
            if (httpclient != null) {  
                try {  
                    httpclient.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    /**  
     * 不需要导入证书，SSL信任所有证书，使用该方法  
     *   
     * @return  
     */  
    public static CloseableHttpClient createSSLClientDefault() {  
        try {  
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(  
                    null, new TrustStrategy() {  
                        // 信任所有证书  
                        public boolean isTrusted(X509Certificate[] chain,  
                                String authType) throws CertificateException {  
                            return true;  
                        }  
                    }).build();  
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
                    sslContext);  
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();  
  
        } catch (KeyManagementException e) {  
  
            e.printStackTrace();  
  
        } catch (NoSuchAlgorithmException e) {  
  
            e.printStackTrace();  
  
        } catch (KeyStoreException e) {  
  
            e.printStackTrace();  
  
        }  
        return HttpClients.createDefault();  
  
    }  
}
