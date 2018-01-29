package cn.nilaile.ssm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeUtils
 * 
 */
public class TimeUtils {
	private final static long MINUTE = 60 * 1000;// 1分钟
	private final static long HOUR = 60 * MINUTE;// 1小时
	private final static long DAY = 24 * HOUR;// 1天
	private final static long MONTH = 31 * DAY;// 月
	private final static long YEAR = 12 * MONTH;// 年


	public static final SimpleDateFormat DATE_FORMAT_DATE_D = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATE_FORMAT_DATE_M = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat DATE_FORMAT_DATE_S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private TimeUtils() {
		throw new AssertionError();
	}

	/**
	 * long time to string
	 * 
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public  static  synchronized  String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static String getTime(long timeInMillis) {
		return getTime(timeInMillis, DATE_FORMAT_DATE_S);
	}

	/**
	 * get current time in milliseconds
	 * 
	 * @return
	 */
	public static long getCurrentTimeInLong() {
		return System.currentTimeMillis();
	}

	/**
	 * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @return
	 */
	public static String getCurrentTimeInString() {
		return getTime(getCurrentTimeInLong());
	}

	/**
	 * get current time in milliseconds
	 * 
	 * @return
	 */
	public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
		return getTime(getCurrentTimeInLong(), dateFormat);
	}

	
	public static String getTimeFormatText(Date date) {
		if (date == null) {
			return null;
		}
		long diff = System.currentTimeMillis()- date.getTime();
		long r = 0;
		if (diff > YEAR) {
			r = (diff / YEAR);
			return r + "年前";
		}
		if (diff > MONTH) {
			r = (diff / MONTH);
			return r + "个月前";
		}
		if (diff > DAY) {
			r = (diff / DAY);
			return r + "天前";
		}
		if (diff > HOUR) {
			r = (diff / HOUR);
			return r + "小时前";
		}
		if (diff > MINUTE) {
			r = (diff / MINUTE);
			return r + "分钟前";
		}
		return "刚刚";
	}
}
