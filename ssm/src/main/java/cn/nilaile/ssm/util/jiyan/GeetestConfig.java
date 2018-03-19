package cn.nilaile.ssm.util.jiyan;

public class GeetestConfig {
	// 填入自己的captcha_id和private_key
	private static final String geetest_id = "0aef406b3312d9972d10cafadb2f4781";
	private static final String geetest_key = "1fce8898b2a4d28141490b0c8b47199f";
	private static final boolean newfailback = true;

	public static final String getGeetest_id() {
		return geetest_id;
	}

	public static final String getGeetest_key() {
		return geetest_key;
	}
	
	public static final boolean isnewfailback() {
		return newfailback;
	}
}
