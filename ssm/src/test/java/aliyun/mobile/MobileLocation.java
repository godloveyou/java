package aliyun.mobile;

import java.util.HashMap;
import java.util.Map;


/**
 * https://market.aliyun.com/products/56928004/cmapi022837.html?spm=5176.730005.
 * 0.0.HTOmDI#sku=yuncode1683700003
 * 
 * @author david
 *
 */
public class MobileLocation {
	public static void main(String[] args) {

	}

	public void open() {
		String host = "http://a47d37e54c.market.alicloudapi.com";
		String path = "/api/pin/authlbsopen/";
		String method = "GET";
		String appcode = "你自己的AppCode";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", "15812345678");

		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/
			 * src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/
			 * pom.xml
			 */
//			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//			System.out.println(response.toString());
			// 获取response的body
			// System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
