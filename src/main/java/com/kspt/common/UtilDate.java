package com.kspt.common;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期工具类
 * 
 * @author jackliu
 * @version 2.2.1
 * 
 */
public class UtilDate {

	/**
	 * 获得指定日期的时间措
	 * 
	 * @param text
	 *            日期格式yyyy-MM-dd
	 * @return 时间措
	 * 
	 */
	public static long getTimes(String text) {
		return getTimes(text, "yyyy-MM-dd");
	}

	public static Timestamp parserTimestamp(String text) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = formatter.parse(String.valueOf(text));
		} catch (ParseException e) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = formatter.parse(String.valueOf(text));
			} catch (ParseException e1) {
				e.printStackTrace();
			}
		}
		if (date == null) {
			return null;
		}
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}

	public static Timestamp parserTimestamp24(String text) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = formatter.parse(String.valueOf(text));
		} catch (ParseException e) {

		}
		if (date == null) {
			return null;
		}
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}

	/**
	 * 按照指定格式获得指定日期的时间措
	 * 
	 * @param text
	 *            text
	 * @param format
	 *            日期格式
	 * @return 时间措
	 * 
	 */
	public static long getTimes(String text, String format) {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(format);
		try {
			return datetimeFormat.parse(text).getTime();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获得年份
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static String yearFormat(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy");
		return datetimeFormat.format(date);
	}

	/**
	 * 获得年份
	 * 
	 * @param timestamp
	 * @return
	 * 
	 */
	public static String yearFormat(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy");
		return datetimeFormat.format(new Date(timestamp.getTime()));
	}

	/**
	 * 获得月份
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static String monthFormat(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("MM");
		return datetimeFormat.format(date);
	}

	/**
	 * 获得月份
	 * 
	 * @param timestamp
	 * @return
	 * 
	 */
	public static String monthFormat(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("MM");
		return datetimeFormat.format(new Date(timestamp.getTime()));
	}

	/**
	 * 获得日
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static String dayFormat(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("dd");
		return datetimeFormat.format(date);
	}

	/**
	 * 获得日
	 * 
	 * @param timestamp
	 * @return
	 * 
	 */
	public static String dayFormat(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("dd");
		return datetimeFormat.format(new Date(timestamp.getTime()));
	}

	/**
	 * 获得日期时间
	 * 
	 * @param date
	 * @return 日期格式yyyy-MM-dd HH:mm:ss(old:yyyy-MM-dd hh:mm:ss a)
	 * 
	 */
	public static String datetimeFormat(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return datetimeFormat.format(date);
	}

	public static String datetimeFormat(String date) {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return datetimeFormat.format(date);
	}

	/**
	 * 获得日期时间
	 * 
	 * @param date
	 * @return 日期格式yyyy-MM-dd hh:mm:ss a
	 * 
	 */
	public static String datetimeFormat24(Date date) {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return datetimeFormat.format(date);
	}

	public static String datetimeFormat2(Date date) {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		return datetimeFormat.format(date);
	}

	/**
	 * 获得日期时间
	 * 
	 * @param timestamp
	 * @return 格式yyyy-MM-dd hh:mm:ss
	 * 
	 */
	public static String datetimeFormat(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}
		// SimpleDateFormat datetimeFormat = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String value = datetimeFormat.format(new Date(timestamp.getTime()));
		if (value.endsWith("00:00:00")) {
			return dateFormat(timestamp);
		} else {
			return value;
		}
	}
	public static String datetimeFormatHM(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}
		
		Date compareDate = new Date(timestamp.getTime());		
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");
		String hourAndMin = timeFormat.format(compareDate);
		String value= dateFormat(timestamp)+" " + hourAndMin;
		return value;
	}
	public static String datetimeFormatHM(Date date) {
		if (date == null) {
			return "";
		}
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");
		String hourAndMin = timeFormat.format(date);
		String value= dateFormat(date) +" "+ hourAndMin;
		return value;
	}

	/**
	 * 获得日期时间
	 * 
	 * @param timestamp
	 * @return 格式yyyy-MM-dd hh:mm:ss a
	 * 
	 */
	public static String datetimeFormat24(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}
		// SimpleDateFormat datetimeFormat = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		return datetimeFormat.format(new Date(timestamp.getTime()));
	}

	public static String timeFormat(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}
		// SimpleDateFormat datetimeFormat = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("HH:mm");
		return datetimeFormat.format(new Date(timestamp.getTime()));
	}

	/**
	 * 获得日期
	 * 
	 * @param date
	 * @return 格式yyyy-MM-dd
	 * 
	 */
	public static String dateFormat(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		return datetimeFormat.format(date);
	}
	public static String dateFormat(Date date,String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(format);
		return datetimeFormat.format(date);
	}
	public static String dateFormatChinese(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy年MM月dd日");
		return datetimeFormat.format(date);
	}

	/**
	 * 获得日期
	 * 
	 * @param timestamp
	 * @return 格式yyyy-MM-dd
	 * 
	 */
	public static Date dateFormat(String datestr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// SimpleDateFormat dateFormat2=new
		// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		try {
			date = dateFormat.parse(datestr);
		} catch (ParseException e) {
			// SimpleDateFormat dateFormat2=new SimpleDateFormat("yyyy-MM-dd");
			// try {
			// date=dateFormat2.parse(datestr);
			// } catch (ParseException e1) {
			// e1.printStackTrace();
			return null;
			// }
			// return date;
		}
		return date;
	}

	public static Date dateFormat2(String datestr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// SimpleDateFormat dateFormat2=new
		// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		try {
			date = dateFormat.parse(datestr);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	/**
	 * 获得日期
	 * 
	 * @param timestamp
	 * @return 格式yyyy-MM-dd
	 * 
	 */
	public static String dateFormat(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		return datetimeFormat.format(new Date(timestamp.getTime()));
	}

	/**
	 * 获得月、日
	 * 
	 * @param timestamp
	 * @return 格式MM-dd
	 * 
	 */
	public static String monthDayFormat(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		}
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("MM-dd");
		return datetimeFormat.format(new Date(timestamp.getTime()));
	}

	/**
	 * 获得当前日期是星期几(数值)
	 * 
	 * @return
	 * 
	 */
	public static int getDayOfWeek() {
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		return getDayOfWeek(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + 1, gregorianCalendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 获得指定日期是星期几(数值)
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * 
	 */
	public static int getDayOfWeek(int year, int month, int day) {
		month = month - 1; // 从0开始
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		gregorianCalendar.set(year, month, day);
		return gregorianCalendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获得当前日期是星期几(符号)
	 * 
	 * @return
	 * 
	 */
	public static String getDayOfWeekSymbols() {
		Locale cn = Locale.CHINA;
		return new DateFormatSymbols(cn).getWeekdays()[getDayOfWeek()];
	}

	/**
	 * 获得指定日期是星期几(符号)
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * 
	 */
	public static String getDayOfWeekSymbols(int year, int month, int day) {
		Locale cn = Locale.CHINA;
		return new DateFormatSymbols(cn).getWeekdays()[getDayOfWeek(year, month, day)];
	}

	/**
	 * 获得当前月份最大天数
	 * 
	 * @return
	 * 
	 */
	public static int getMaxDayOfMonth() {
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		return getMaxDayOfMonth(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获得指定月份最大天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 * 
	 */
	public static int getMaxDayOfMonth(int year, int month) {
		month = month - 1;
		Calendar gregorianCalendar1 = GregorianCalendar.getInstance();
		Calendar gregorianCalendar2 = GregorianCalendar.getInstance();
		for (int i = 31; i > 27; i--) {
			gregorianCalendar1.set(year, month, i);
			gregorianCalendar2.set(year, month, 1);
			if (gregorianCalendar1.get(Calendar.MONTH) == gregorianCalendar2.get(Calendar.MONTH)) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 当前是第几周
	 * 
	 * @return
	 * 
	 */
	public static int getWeekOfYear() {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		return getWeekOfYear(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + 1, gregorianCalendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 当前指定的日期是第几周
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * 
	 */
	public static int getWeekOfYear(int year, int month, int day) {
		month = month - 1; // 从0开始
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		gregorianCalendar.set(year, month, day);
		return gregorianCalendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 当前指定的日期是第几个季度
	 * 
	 * @param month
	 * @return
	 * 
	 */
	public static int getQuarterOfYear(int month) {
		if (month > 0 && month < 4)
			return 1;
		else if (month > 3 && month < 7)
			return 2;
		else if (month > 6 && month < 10)
			return 3;
		else
			return 4;
	}

	/**
	 * 当前指定的日期是第几个季度
	 * 
	 * @return
	 * 
	 */
	public static int getQuarterOfYear() {
		return getQuarterOfYear(getMonth(new Date()));
	}

	/**
	 * 得到指定日期的年份
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static int getYear(Date date) {
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		gregorianCalendar.setTime(date);
		return gregorianCalendar.get(Calendar.YEAR);
	}

	/**
	 * 得到指定日期的小时
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static int getHour(Date date) {
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		gregorianCalendar.setTime(date);
		// gregorianCalendar.setGregorianChange(date);
		return gregorianCalendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到指定日期的月份
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static int getMonth(Date date) {
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		gregorianCalendar.setTime(date);
		return gregorianCalendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到指定日期的天
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static int getDay(Date date) {
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		gregorianCalendar.setTime(date);
		return gregorianCalendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到指定日期的小时
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static int getHourOfDay(Date date) {
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		gregorianCalendar.setTime(date);
		return gregorianCalendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到指定日期的分钟
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static int getMinute(Date date) {
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		gregorianCalendar.setTime(date);
		return gregorianCalendar.get(Calendar.MINUTE);
	}

	/**
	 * 获得指定时间与当前时间比较后的别名，精确到分钟
	 * 
	 * @param timestamp
	 * @return text 如:昨天10:58、去年3月8日15:13
	 * 
	 */
	public static String getAliasDatetime(Timestamp timestamp) {
		Date compareDate = new Date(timestamp.getTime());
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");
		String hourAndMin = timeFormat.format(compareDate);
		System.out.println(getAliasDate(timestamp) + hourAndMin);
		return getAliasDate(timestamp) + hourAndMin;
	}

	/**
	 * 获得指定时间与当前时间比较后的别名，精确到分钟
	 * 
	 * @param date
	 *            日期格式：yyyy-MM-dd HH:mm
	 * @return text 如:昨天10:58、去年3月8日15:13
	 * 
	 */
	public static String getAliasDatetime(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(format.parse(date).getTime());
		} catch (Exception e) {
			return date;
		}
		return getAliasDatetime(timestamp);
	}

	/**
	 * 获得指定日期与当前日期比较后的别名，精确到天
	 * 
	 * @param timestamp
	 * @return text 如:昨天、去年3月8日
	 * 
	 */
	public static String getAliasDate(Timestamp timestamp) {
		long compare = getTimes(timestamp.toString());
		long now = getTimes(dateFormat(new Date()));
		long difOfDay = (compare - now) / (1000 * 60 * 60 * 24);
		Date compareDate = new Date(timestamp.getTime());
		String result = "";
		if (difOfDay == 0) {
			result = "今天";
		} else if (difOfDay == -1) {
			result = "昨天";
		} else if (difOfDay == -2) {
			result = "前天";
		} else if (difOfDay == 1) {
			result = "明天";
		} else {
			int nowYear = Integer.parseInt(yearFormat(new Date()));
			int compareYear = Integer.parseInt(yearFormat(compareDate));
			int difOfYear = compareYear - nowYear;
			SimpleDateFormat dateFormat = new SimpleDateFormat("M月d日");
			String zhDate = dateFormat.format(compareDate);
			if (difOfYear == 0) {
				result = "今年";
			} else if (difOfYear == -1) {
				result = "去年";
			} else if (difOfYear == -2) {
				result = "前年";
			} else if (difOfYear == 1) {
				result = "明年";
			} else {
				result = compareYear + "年";
			}
			result += zhDate;
		}
		return result;
	}

	/**
	 * 获得指定日期与当前日期比较后的别名，精确到天
	 * 
	 * @param date
	 *            日期格式：yyyy-MM-dd
	 * @return text 如:昨天、今天、去年3月8日
	 * 
	 */
	public static String getAliasDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(format.parse(date).getTime());
		} catch (Exception e) {
			return date;
		}
		return getAliasDate(timestamp);
	}

}
