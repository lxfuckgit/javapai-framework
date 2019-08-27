package com.javapai.framework.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 
 * 日期时间相关处理类,包括：Date、DateTime、Calendar的使用;
 * 
 * <br>
 * 常用软件系统中,日期处理是必不可少的一部分.本类日期处理主要分为对日期,日历,时间,定时器上的处理。<br>
 * 
 * <hr>
 * <strong> Date:用于保存日期信息，但不推荐进行日期操作及初始化特定日期。</strong><br>
 * 先了解几个类: <br>
 * 1、具体类(和抽象类相对)java.sql,Date()和java.util.Date();<br>
 * java.sql.Date类继承自java.util.Date,是操作数据库用的日期类型。 <br>
 * 将Date类型写入数据库的两种方法:<br>
 * 一种将java的日期类型直接转换为SQL的日期类型的方法，比较简单但适用性狭窄,注意一下例子在jdk下编译没有时间，但在jb和Eclipse下就有时间
 * ，不知怎么回事 。<br>
 * 一种是将java的date类型通过SimpleDateFormat转换为字符串，再写到sql语句中。<br>
 * 
 * <p>
 * 简单实例:<br>
 * 取得当期日期, java.util.Date date=new java.util.Date();
 * <hr>
 * 
 * 2、抽象类java.text.DateFormat 和它的一个具体子类java.text.SimpleDateFormat <br>
 * <hr>
 * 
 * 3、抽象类java.util.Calendar 和它的一个具体子类java.util.GregorianCalendar <br>
 * <hr>
 * 
 * 4、
 * <hr>
 * 
 * <br>
 * 
 * 初始化特定日期:假设我们要得到日期为2006-10-27日的对象，需要按如下方式获得。
 * 
 * Calendar cal = new GregorianCalendar(2006, 9, 27,0,0,0); <br>
 * Date date = cal.getTime(); 注意:date.getTime()取得的是当期时间的毫秒数,月份比实际的减1
 * 
 * GregorianCalendar构造方法参数依次为：年，月-1，日，小时，分，秒
 * 
 * <hr>
 * <strong> 抽象类Calendar 距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00.000，格里高利历）的偏移量。
 * </strong> Calendar及其子类GregorianCalendar:日历类，日期操作，初始化特定日期。 <br>
 * <p>
 * 简单示例:<br>
 * Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。 <br>
 * int year = cal.get(Calendar.YEAR);//取得当前年 <br>
 * int month=cal.get(Calendar.MONTH)+1;//
 * 注意：实际的月份要比Clendar得到的加1，因为java月份是从0~11[javaSE api有说明]; <br>
 * int week = cal.get(Calendar.WEEK_OF_YEAR);//一年中第几周 <br>
 * int day = al.get(Calendar.DAY_OF_MONTH);//一月的第几天 <br>
 * int hour = cal.get(Calendar.HOUR_OF_DAY);//取得小时 <br>
 * int minute = cal.get(Calendar.MINUTE);//取得分 <br>
 * int second = cal.get(Calendar.SECOND);//得取秒 <br>
 * int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 得到某个月的总天数 <br>
 * cal.add(Calendar.DATE, 1)；//日期加1天
 * 
 * <hr>
 * <strong>TimeSteape</strong>
 * 
 * <hr>
 * <strong> DateFormat及其子类SimpleDateformat:日期/文本 的格式化。</strong> <br>
 * 日期的默认显示方式不适合中国人，所以需要格式化为中国人常用的格式来显示。<br>
 * 格式化为我们熟悉的方式显示：<br>
 * DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH;mm:ss");<br>
 * String chinesedate = format.format(date);<br>
 * <hr>
 * <p>
 * 
 * @author lx
 * 
 * @version 1.0 Created Date:2010/08/10
 * @version 2.0 Updated Date:2011/05/08
 * 
 */
public final class UtilDateTime {
	/**
	 * 默认格式:yyyy-MM-dd<br>
	 */
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	/**
	 * 默认格式:yyyy-MM-dd HH:mm:ss
	 */
	private final static String FORMAT_DATE2 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 跨平台的时间格式<br>
	 * 用kk而不用hh是因为kk是24进制的而不随操作系统设置变动
	 */
	private final static String TIME_FORMAAT = "yyyy-MM-dd kk:mm:ss";
	/**
	 * 默认的日期格式器.<br>标准日期格式.<br>
	 * <br>
	 */
	//尽量减少每次需要使用时再创建SimpleDateFormat实例，用完就丢弃的习惯，因为创建这么一个实例需要耗费很大的代价。我们用静态化类实例(单例)解决性能问题(注意多线程问题)。
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
//	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat();//这样会不会更好，指定一个format,格式自己再指定，多线线问题?好像是有线程问题.
	
	/**
	 * 一个线程安全的DateFormat.<br>
	 */
	//当然，每次使用每次创建SimpleDateFormat也是线程安全的(多例)。或是sysncDateFormat的关键方法也是线程安全的。ThreadLoacl也是安全的。
	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
	
	/**
	 *  一个线程安全的DateFormat(默认格式yyyy-MM-dd HH:mm:ss).<br>
	 */
	private static ThreadLocal<DateFormat> threadLocalFormat = new ThreadLocal<DateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(FORMAT_DATE2);
		}
	};


	/**
	 * ORA标准时间格式
	 */
	private static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");

	private static final int[] dayArray = new int[] { 31, 28, 31, 30, 31, 30,31, 31, 30, 31, 30, 31 };
	
	/**
	 * 获取当前时间 默认格式: yyyy-MM-dd HH:ii:ss
	 * 
	 * @param format
	 *            格式, 例如: yyyy-MM-dd HH:ii:ss
	 * @return
	 */
	public static String getCurrentDateTime(String format) {
		Date date = new Date();
		if (format == null || "".equals(format)) {
			format = FORMAT_DATE2;
		}
		SimpleDateFormat thisFormat = new SimpleDateFormat(format);
		String currentDateTime = thisFormat.format(date);
		return currentDateTime;
	}

	/**
	 * 获取当前时间的unix 时间戳
	 * 
	 * @return
	 */
	public static long getUnixTime() {
		long time = System.currentTimeMillis();
		long unixTime = time / 1000;
		return unixTime;
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Date getTodayMorningDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 带时分秒的标准时间格式 private static final SimpleDateFormat
	 * DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss" );
	 * private static SimpleDateFormat sdf = new SimpleDateFormat(); 这种个公共
	 * sdf，可以测试出线程上非安全。
	 */
	
	public static Date add(int field,Date date,int value) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		int fieldNewValue = (c.get(field) + value);
		c.set(field, fieldNewValue);
		return c.getTime();
	}
 
	public static int get(int field,Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(field);
	}
	
	public static boolean isEqualField(int field, Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		return c1.get(field) == c2.get(field);
	}

	/**
	 * 得到两日期 相差天数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @throws ParseException
	 */
	public long getPlusDays(String startDate, String endDate) {
		long days = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);

		try {
			long date_long1 = sdf.parse(startDate).getTime();
			long date_long2 = sdf.parse(endDate).getTime();
			if (date_long1 <= date_long2) {
				/* 相差天数 */
				days = (date_long2 - date_long1) / (1000 * 60 * 60 * 24) + 1;
				System.out.println(startDate + "与" + endDate + "相差 " + days
						+ "天!");

				// /*亦可以由一个long得到一个日期月和日*/
				// Calendar calendar = Calendar.getInstance();
				// calendar.setTimeInMillis(date_long1);
				// System.out.println(calendar.get(Calendar.MONTH)+1+"-"+calendar.get(calendar.DATE));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

	/**
	 * 得到两日期 相差天数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @throws ParseException
	 */
	public long getPlusDays(Date startDate, Date endDate) {
		int days = (int) ((endDate.getTime() - startDate.getTime())
				/ (1000 * 24 * 60 * 60) + 0.5);// 0.5？？是四出五进吗？
		return days;
	}

	/**
	 * date1 and date2 in currentTime
	 * 
	 * @param date1
	 *            ,date2
	 * @return boolean
	 */
	public boolean dateIndate(Date date1, Date date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		long now = Long.parseLong(sdf.format(new Date()));
		long start = date1 == null ? now : Long.parseLong(sdf.format(date1));
		long end = date2 == null ? now : Long.parseLong(sdf.format(date2));
		return now >= start && now <= end;
	}

	/**
	 * 以默认日期格式(yyyy-MM-dd)格式化日期。<b>
	 * 
	 * @param date
	 *            String-Date
	 * @param format
	 *            日期格式，jdk规格;<br>
	 *            如果format参数为"",则默认格式为：yyyy-MM-dd
	 * 
	 * @return String 字符串型日期
	 */
	public String date2String(final java.util.Date date) {
		return date2String(date, FORMAT_DATE);
	}

	/**
	 * 以指定的format模式来格式一个Data日期，并将其转成String类型. 另可参见：DateUtil.formatDate()方法。
	 * 
	 * @param date
	 * @param format
	 *            如果指定为空，则采用常量(DateUtil.TIME_FORMAAT)的模式格式化此日期。
	 * @return
	 */
	public static synchronized String date2String(final java.util.Date date, String format) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("timestamp null illegal");
		}
		SimpleDateFormat sdf = new SimpleDateFormat();
		if (null == format || "".equals(format)) {
//			format = DATE_PATTERN;
			throw new java.lang.IllegalArgumentException("format null illegal");
		} else {
			sdf.applyPattern(format);
			// SimpleDateFormat sdf =new SimpleDateFormat(format);
		}

		return sdf.format(date);
//		return date2String(date, sdf);
	}
	
	/**
	 * 将一个日期对象转换成为指定日期、时间格式的字符串。<br>
	 * 如果日期对象(theDate)为空，返回一个空字符串，而不是一个空对象。
	 * 
	 * @param date
	 *            要转换的日期对象
	 * @param dateFormat
	 *            返回的日期字符串的格式
	 * @return 转换结果
	 */
	public static synchronized String date2String(final Date date, DateFormat dateFormat) {
		if (date == null) {
			return null;
		}
		return dateFormat.format(date);
//		return new SimpleDateFormat(dateFormat).format(date);
	}
	
	/**
	 * 取得当前时间<br>
	 * 
	 * @return 返回系统当前时间
	 */
	public static Timestamp getCurrentTime() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 读取一个固定的Timestamp时间(2001-01-01 01:01:01).
	 * @return
	 */
	public static Timestamp getFixedTimestamp() {
		String fixTimestampString = "2001-01-01 01:01:01";
        return Timestamp.valueOf(fixTimestampString);
	}

	/**
	 * 将一个java util Date转换成sql Date类型。<br>
	 * 
	 * 前面我们说的都是java.util.Date类，java.sql.Date类是操作数据库用的日期类型
	 */
	public static java.sql.Date dateToSqldate(java.util.Date UtilDateTime) {
		if (UtilDateTime != null) {
			java.sql.Date sqlDate = new java.sql.Date(UtilDateTime.getTime());
			/*
			 * String date="2005-11-10"; java.sql.Date
			 * sqlDate=java.sql.Date.valueOf(UtilDateTime);
			 */
			return sqlDate;

		} else {
			return null;
		}
	}

	/**
	 * 将一个字符串类型的日期，按指定格式转化成sql Date用于数据库。
	 * 
	 * 默认格式化为 yyyy/MM/dd
	 * 
	 * @param StringDate
	 * @param format
	 * @return
	 */
	// public static java.sql.Date StringToSqlDate(String StringDate, String
	// format) {
	// if ("".equals(format) || format == null) {
	// format = "yyyy/MM/dd";
	// }
	// java.util.Date date = parseDate(StringDate, format);
	//
	// return dateToSqldate(date);
	// }

	/**
	 * 取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String类型的日期
	 * 
	 * @return String String类型的日期
	 * 
	 */
	public String monthFirstDay(String strdate) {
		java.util.Date date=null;
		try {
			date = DateFormat.getDateInstance().parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date2String(date, "yyyy-MM") + "-01";
	}

	/**
	 * 取得指定月份的第一天(某月的一号)
	 * 
	 * @param strdate
	 *            String类型的日期
	 * 
	 * @return String String类型的日期
	 * 
	 */
	public synchronized Calendar getFirstDayOfMonth(Calendar c) {
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c;
	}
	
//	public static Date getFirstDayOfMonth(Calendar c) {
//		int year = c.get(Calendar.YEAR);
//		int month = c.get(Calendar.MONTH);
//		int day = 1;
//		c.set(year, month, day, 0, 0, 0);
//		return c.getTime();
//	}
	
	/**
	 * 取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 * @throws ParseException 
	 */
	public String monthLastDay(String strdate) throws ParseException {
		String str = monthFirstDay(strdate);
		java.util.Date date = DateFormat.getDateInstance().parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return date2String(calendar.getTime(), "");
	}

	/**
	 * 格式化日期
	 * 
	 * @param dateStr
	 *            字符型日期
	 * @param format
	 *            格式
	 * @return 返回日期
	 */
	// public static java.util.Date parseDate(String dateStr, String format) {
	// java.util.Date date = null;
	// try {
	// java.text.DateFormat df = new java.text.SimpleDateFormat(format);
	// String dt = Normal.parse(dateStr).replaceAll("-", "/");
	// if ((!dt.equals("")) && (dt.length() < format.length())) {
	// dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
	// "0");
	// }
	// date = (java.util.Date) df.parse(dt);
	// } catch (Exception e) {
	// }
	// return date;
	// }

	// public static java.util.Date parseDate(String dateStr) {
	// return parseDate(dateStr, "yyyy/MM/dd");
	// }

	public static java.util.Date parseDate(java.sql.Date date) {
		return date;
	}

	// public static java.sql.Timestamp parseTimestamp(String dateStr,
	// String format) {
	// java.util.Date date = parseDate(dateStr, format);
	// if (date != null) {
	// long t = date.getTime();
	// return new java.sql.Timestamp(t);
	// } else
	// return null;
	// }

	// public static java.sql.Timestamp parseTimestamp(String dateStr) {
	// return parseTimestamp(dateStr, "yyyy/MM/dd  HH:mm:ss");
	// }

	/**
	 * 返回某日期的年份
	 * 
	 * @param date
	 *            日期
	 * @return 返回年份
	 */
	public static int getYear(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getMonth(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回日份
	 * 
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getDay(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 读取当时日期的返回秒钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回秒钟
	 */
	public static int getSecond(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 返回字符型时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型时间
	 */
	// public static String getTime(java.util.Date date) {
	// return format(date, "HH:mm:ss");
	// }

	// /**
	// * 返回字符型日期时间
	// *
	// * @param date
	// * 日期
	// * @return 返回字符型日期时间
	// */
	// public static String getDateTime(java.util.Date date) {
	// return format(date, "yyyy/MM/dd  HH:mm:ss");
	// }

	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);//24H *60M *60S
		return c.getTime();
	}

	// }
	//
	// 在最近的一个OA中，我需要判断两个日期是否是同一周，根据一个给定的日期获得所属周的周一和周五的日期。
	//
	public static synchronized Calendar getCalendar() {
		return GregorianCalendar.getInstance();
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateMilliFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateMilliFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateMilliFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarMilliFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return parseCalendarFormat(strDate, pattern);
	}

	/** */
	/**
	 * @return String
	 */
	public static synchronized String getDateSecondFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateSecondFormat(cal);
	}

	/** */
	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateSecondFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	/** */
	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarSecondFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/** */
	/**
	 * @return String
	 */
	public static synchronized String getDateMinuteFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateMinuteFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateMinuteFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarMinuteFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateDayFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarDayFormat(String strDate) {
		String pattern = "yyyy-MM-dd";
		return parseCalendarFormat(strDate, pattern);
	}

	/** */
	/**
	 * @return String
	 */
	public static synchronized String getDateFileFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateFileFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateFileFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateW3CFormat(Calendar cal) {
		if (cal == null) {
			cal = Calendar.getInstance();
		}
		return getDateFormat(cal, FORMAT_DATE2);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarW3CFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateFormat(java.util.Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param cal
	 * @param pattern
	 * @return String
	 */
	public static synchronized String getDateFormat(Calendar cal, String pattern) {
		return date2String(cal.getTime(), pattern);
	}

	/**
	 * @param strDate
	 * @param pattern
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarFormat(String strDate,
			String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar cal = null;

		if ("".equals(pattern)) {
			sdf.applyPattern("yyyy-MM-dd_HH-mm-ss");
		} else {
			sdf.applyPattern(pattern);
		}

		sdf.applyPattern(pattern);
		try {
			sdf.parse(strDate);
			cal = sdf.getCalendar();
		} catch (Exception e) {
		}
		return cal;
	}

	public static synchronized int getLastDayOfMonth(int month) {
		if (month < 1 || month > 12) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear(-1)) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	public static synchronized int getLastDayOfMonth(int year, int month) {
		if (month < 1 || month > 12) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear(year)) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	/**
	 * 
	 * 1.被400整除是闰年; 否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
	 * 3.能被4整除同时能被100整除则不是闰年
	 * 
	 * @param year
	 *            年份，当year=-1时，代表系统当时年份。
	 */
	public static synchronized boolean isLeapYear(int year) {
		if (year == -1) {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 判断指定日期的年份是否是闰年
	 * 
	 * @param date
	 *            指定日期。
	 * @return 是否闰年
	 */
	public static synchronized boolean isLeapYear(java.util.Date date) {
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		// int year = date.getYear();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		int year = gc.get(Calendar.YEAR);
		return isLeapYear(year);
	}

	/**
	 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
	 * 3.能被4整除同时能被100整除则不是闰年
	 */
	public static synchronized boolean isLeapYear(java.util.Calendar gc) {

		int year = gc.get(Calendar.YEAR);
		return isLeapYear(year);
	}

	/**
	 * 得到指定日期的前一个工作日<br>
	 * <p>
	 * 详细设计： 1.如果date是星期日，则减3天 2.如果date是星期六，则减2天 3.否则减1天 <br>
	 * 
	 * @param date
	 *            指定日期.
	 * 
	 * @return java.util.Date 指定日期的前一个工作日.
	 */
	// public static synchronized java.util.Date getPreviousWeekDay(
	// java.util.Date date) {
	// {
	// GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	// gc.setTime(date);
	// return getPreviousWeekDay(gc);
	// switch (gc.get(Calendar.DAY_OF_WEEK)) {
	// case (Calendar.MONDAY ):
	// gc.add(Calendar.DATE, -3);
	// break;
	// case (Calendar.SUNDAY ):
	// gc.add(Calendar.DATE, -2);
	// break;
	// default:
	// gc.add(Calendar.DATE, -1);
	// break;
	// }
	// return gc.getTime();
	// }
	// }

	public static synchronized java.util.Date getPreviousWeekDay(Calendar gc) {
		/**
		 * 详细设计： 1.如果date是星期日，则减3天 2.如果date是星期六，则减2天 3.否则减1天
		 */
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, -2);
			break;
		default:
			gc.add(Calendar.DATE, -1);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 得到指定日期的后一个工作日
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的后一个工作日
	 */
	public static synchronized java.util.Date getNextWeekDay(java.util.Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.FRIDAY):
			// 1.如果date是星期五，则加3天
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.SATURDAY):
			// 2.如果date是星期六，则加2天
			gc.add(Calendar.DATE, 2);
			break;
		default:
			// 3.否则加1天
			gc.add(Calendar.DATE, 1);
			break;
		}
		return gc.getTime();
	}

	public static synchronized Calendar getNextWeekDay(Calendar gc) {
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.FRIDAY):
			// 1.如果date是星期五，则加3天
			gc.add(Calendar.DATE, 3);
			break;
		// 2.如果date是星期六，则加2天
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 2);
			break;
		default:
			// 3.否则加1天
			gc.add(Calendar.DATE, 1);
			break;
		}
		return gc;
	}

	/**
	 * 取得指定日期的下一个月的最后一天
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个月的最后一天
	 */
	public static synchronized java.util.Date getLastDayOfNextMonth(
			java.util.Date date) {
		/** */
		/**
		 * 详细设计： 1.调用getNextMonth设置当前时间 2.以1为基础，调用getLastDayOfMonth
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(UtilDateTime.getNextMonth(gc.getTime()));
		gc.setTime(UtilDateTime.getLastDayOfMonth(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * 取得指定日期的下一个星期的最后一天
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个星期的最后一天
	 */
	public static synchronized java.util.Date getLastDayOfNextWeek(
			java.util.Date date) {
		/**
		 * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getLastDayOfWeek
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(UtilDateTime.getNextWeek(gc.getTime()));
		gc.setTime(UtilDateTime.getLastDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * 取得指定日期的下一个月的第一天
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个月的第一天
	 */
	public static synchronized java.util.Date getFirstDayOfNextMonth(
			java.util.Date date) {
		/** */
		/**
		 * 详细设计： 1.调用getNextMonth设置当前时间 2.以1为基础，调用getFirstDayOfMonth
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(UtilDateTime.getNextMonth(gc.getTime()));
		gc.setTime(UtilDateTime.getFirstDayOfMonth(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * 详细设计： 1.调用getNextMonth设置当前时间 2.以1为基础，调用getFirstDayOfMonth
	 */
	public static synchronized java.util.Calendar getFirstDayOfNextMonth(
			java.util.Calendar gc) {
		gc.setTime(UtilDateTime.getNextMonth(gc.getTime()));
		gc.setTime(UtilDateTime.getFirstDayOfMonth(gc.getTime()));
		return gc;
	}

	/**
	 * 取得指定日期的下一个星期的第一天
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个星期的第一天
	 */
	public static synchronized java.util.Date getFirstDayOfNextWeek(
			java.util.Date date) {
		/**
		 * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(UtilDateTime.getNextWeek(gc.getTime()));
		gc.setTime(UtilDateTime.getFirstDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
	 */
	public static synchronized java.util.Calendar getFirstDayOfNextWeek(
			java.util.Calendar gc) {
		gc.setTime(UtilDateTime.getNextWeek(gc.getTime()));
		gc.setTime(UtilDateTime.getFirstDayOfWeek(gc.getTime()));
		return gc;
	}

	/**
	 * 取得指定日期的下一个月
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个月
	 */
	public static synchronized java.util.Date getNextMonth(java.util.Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.MONTH, 1);
		return gc.getTime();
	}

	/**
	 * 详细设计： 1.指定日期的月份加1
	 */
	public static synchronized Calendar getNextMonth(Calendar gc) {
		gc.add(Calendar.MONTH, 1);
		return gc;
	}

	/**
	 * 取得指定日期的下一天。<br>
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一天
	 */
	public static synchronized java.util.Date getNextDay(java.util.Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return gc.getTime();
	}

	/**
	 * 详细设计： 1.指定日期加1天。<br>
	 * 
	 * @param gc
	 * @return
	 */
	public static synchronized Calendar getNextDay(Calendar gc) {
		gc.add(Calendar.DATE, 1);
		return gc;
	}

	/**
	 * 取得指定日期的下一个星期
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个星期
	 */
	public static synchronized java.util.Date getNextWeek(java.util.Date date) {
		/**
		 * 详细设计： 1.指定日期加7天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 7);
		return gc.getTime();
	}

	/**
	 * 指定日期加7天
	 * 
	 * @param gc
	 * @return
	 */
	public static synchronized Calendar getNextWeek(Calendar gc) {
		gc.add(Calendar.DATE, 7);
		return gc;
	}

	/**
	 * 取得指定日期的所处星期的最后一天。<br>
	 * 
	 * <p>
	 * 详细设计：<br>
	 * 1.如果date是星期日，则加6天 。<br>
	 * 2.如果date是星期一，则加5天 。<br>
	 * 3.如果date是星期二，则加4天。<br>
	 * 4.如果date是星期三，则加3天 。<br>
	 * 5.如果date是星期四，则加2天。<br>
	 * 6.如果date是星期五，则加1天。<br>
	 * 7.如果date是星期六，则加0天。<br>
	 * <p>
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处星期的最后一天
	 */
	public static synchronized java.util.Date getLastDayOfWeek(
			java.util.Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 6);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, 5);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, 4);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, 2);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 1);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 0);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 取得指定日期的所处星期的第一天
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处星期的第一天
	 */
	public static synchronized java.util.Date getFirstDayOfWeek(
			java.util.Date date) {
		/** */
		/**
		 * 详细设计： 1.如果date是星期日，则减0天。<br>
		 * 2.如果date是星期一，则减1天。<br>
		 * 3.如果date是星期二，则减2天 。<br>
		 * 4.如果date是星期三，则减3天。<br>
		 * 5.如果date是星期四，则减4天。<br>
		 * 6.如果date是星期五，则减5天 。<br>
		 * 7.如果date是星期六，则减6天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 0);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -1);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, -4);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, -5);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, -6);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 详细设计： 1.如果date是星期日，则减0天 。<br>
	 * 2.如果date是星期一，则减1天 。<br>
	 * 3.如果date是星期二，则减2天。<br>
	 * 4.如果date是星期三，则减3天 。<br>
	 * 5.如果date是星期四，则减4天。<br>
	 * 6.如果date是星期五，则减5天。<br>
	 * 7.如果date是星期六，则减6天
	 */
	public static synchronized Calendar getFirstDayOfWeek(Calendar gc) {

		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 0);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -1);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, -4);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, -5);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, -6);
			break;
		}
		return gc;
	}

	/**
	 * 详细设计： 1.如果date在1月，则为31日 2.如果date在2月，则为28日,如果date在闰年的2月，则为29日
	 * 3.如果date在3月，则为31日 4.如果date在4月，则为30日 5.如果date在5月，则为31日 6.如果date在6月，则为30日
	 * 7.如果date在7月，则为31日 8.如果date在8月，则为31日 9.如果date在9月，则为30日 10.如果date在10月，则为31日
	 * 11.如果date在11月，则为30日 12.如果date在12月，则为31日
	 */
	public static synchronized Calendar getLastDayOfMonth(Calendar gc) {

		switch (gc.get(Calendar.MONTH)) {
		case 0:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 1:
			gc.set(Calendar.DAY_OF_MONTH, 28);
			break;
		case 2:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 3:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 4:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 5:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 6:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 7:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 8:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 9:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 10:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 11:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		// 检查闰年
		// if ( ( gc.get( Calendar.MONTH ) == Calendar.FEBRUARY ) && (
		// isLeapYear( gc.get( Calendar.YEAR ) ) ) )
		// {
		// gc.set( Calendar.DAY_OF_MONTH, 29 );
		// }
		return gc;
	}

	/**
	 * 取得指定日期的所处月份的最后一天.<br>
	 * <p>
	 * 详细设计：<br>
	 * 1.如果date在1月，则为31日.<br>
	 * 2.如果date在2月，则为28日.<br>
	 * 3.如果date在3月，则为31日.<br>
	 * 4.如果date在4月，则为30日.<br>
	 * 5.如果date在5月，则为31日.<br>
	 * 6.如果date在6月，则为30日.<br>
	 * 7.如果date在7月，则为31日.<br>
	 * 8.如果date在8月，则为31日.<br>
	 * 9.如果date在9月，则为30日.<br>
	 * 10.如果date在10月，则为31日.<br>
	 * 11.如果date在11月，则为30日.<br>
	 * 12.如果date在12月，则为31日 .<br>
	 * 1.如果date在闰年的2月，则为29日
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处月份的最后一天
	 */
	public static synchronized java.util.Date getLastDayOfMonth(
			java.util.Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.MONTH)) {
		case 0:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 1:
			gc.set(Calendar.DAY_OF_MONTH, 28);
			break;
		case 2:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 3:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 4:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 5:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 6:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 7:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 8:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 9:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 10:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 11:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		// 检查闰年
		if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY)
				&& (isLeapYear(gc.get(Calendar.YEAR)))) {
			gc.set(Calendar.DAY_OF_MONTH, 29);
		}
		return gc.getTime();
	}

	/**
	 * 取得指定日期的所处月份的第一天
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处月份的第一天
	 */
	public static synchronized java.util.Date getFirstDayOfMonth(
			java.util.Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.set(Calendar.DAY_OF_MONTH, 1);
		return gc.getTime();
	}

	/**
	 * 创建一个标准日期格式的克隆
	 * 
	 * @return 标准日期格式的克隆
	 */
	public static synchronized DateFormat getDateFormat() {
		SimpleDateFormat theDateFormat = (SimpleDateFormat) DATE_FORMAT.clone();
		theDateFormat.setLenient(false);
		return theDateFormat;
	}

	/**
	 * 创建一个标准ORA时间格式的克隆
	 * 
	 * @return 标准ORA时间格式的克隆
	 */
	public static synchronized DateFormat getOraDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT
				.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}
	
	public static String timestamp2String(Timestamp timestamp, String pattern) {
		if (timestamp == null) {
			throw new java.lang.IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = FORMAT_DATE2;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(timestamp.getTime()));
	}
	
	/**
	 * 获取当前日期.<br>
	 * 
	 * @return java.util.Date.
	 * 
	 * @deprecated 建议直接代码：new Date();
	 */
	@Deprecated
	public static Date currentDate() {
		return new Date();
	}

	/**
	 * 当前时间毫秒数.<br>
	 * @return
	 * 
	 * @deprecated 建议直接代码：new java.util.Date.Date().getTime();
	 */
	@Deprecated
	public static Long currentTime() {
		return new Date().getTime();
	}
	
	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	
	public static String currentTimestamp2String() {
		return threadLocal.get().format(currentTimestamp());
//		return timestamp2String(currentTimestamp(), FORMAT_DATE2);
	}

	public static String currentTimestamp2String(String pattern) {
		return timestamp2String(currentTimestamp(), pattern);
	}

	/**
	 * 
	 * @param string
	 * @param partten
	 *            默认格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp string2Timestamp(String strDateTime, String pattern) {
		if (strDateTime == null || strDateTime.equals("")) {
			throw new java.lang.IllegalArgumentException("Date Time Null Illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = FORMAT_DATE2;
		}

		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date = sdf.parse(strDateTime);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * 从字符串类型的日期转换为日期类型.<br>
	 * 
	 * @param stringDate
	 *            String型日期.<br>
	 * @param pattern
	 *            格式化模式.<br>
	 *            pattern默认为yyyy-MM-dd HH:mm:ss<br>
	 * @return java.util.Date对象实例.<br>
	 */
	public static java.util.Date format2Date(String stringDate, String pattern) {
		if (stringDate == null || stringDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = FORMAT_DATE2;
		}
		DateFormat df = new SimpleDateFormat(pattern);
		Date date = null;

		try {
			date = df.parse(stringDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
		
		/*另一种实现：无法指定格式化模式,仅支持 "yyyy?MM?dd"*/
//		int year = Integer.parseInt(stringDate.substring(0, 4));
//		int month = Integer.parseInt(stringDate.substring(5, 7));
//		int day = Integer.parseInt(stringDate.substring(8, 10));
//
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, year);
//		cal.set(Calendar.MONTH, month - 1);
//		cal.set(Calendar.DATE, day);
//		return cal.getTime();
	}

	public static String stringToYear(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = format2Date(strDest, FORMAT_DATE2);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return String.valueOf(c.get(Calendar.YEAR));
	}

//	public static String stringToMonth(String strDest) {
//		if (strDest == null || strDest.equals("")) {
//			throw new java.lang.IllegalArgumentException("str dest null");
//		}
//
//		Date date = string2Date(strDest, FORMAT_YMDHMS);
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		// return String.valueOf(c.get(Calendar.MONTH));
//		int month = c.get(Calendar.MONTH);
//		month = month + 1;
//		if (month < 10) {
//			return "0" + month;
//		}
//		return String.valueOf(month);
//	}

//	public static String stringToDay(String strDest) {
//		if (strDest == null || strDest.equals("")) {
//			throw new java.lang.IllegalArgumentException("str dest null");
//		}
//
//		Date date = string2Date(strDest, FORMAT_YMDHMS);
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		// return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
//		int day = c.get(Calendar.DAY_OF_MONTH);
//		if (day < 10) {
//			return "0" + day;
//		}
//		return "" + day;
//	}

	public static String date2GregorianCalendarString(Date date) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("Date is null");
		}
		long tmp = date.getTime();
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTimeInMillis(tmp);
		try {
			XMLGregorianCalendar t_XMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(ca);
			return t_XMLGregorianCalendar.normalize().toString();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new java.lang.IllegalArgumentException("Date is null");
		}

	}

	public static boolean compareDate(Date firstDate, Date secondDate) {
		if (firstDate == null || secondDate == null) {
			throw new java.lang.RuntimeException();
		}

		String strFirstDate = date2String(firstDate, "yyyy-MM-dd");
		String strSecondDate = date2String(secondDate, "yyyy-MM-dd");
		if (strFirstDate.equals(strSecondDate)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 在日期上增加的整月份
	 * @param date
	 * 		       时间
	 * @param month
	 * 		  要增加的月份
	 * @return
	 */
	public static Date dateAddIntMonth(Date date,int month){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}
	
	/**
	 * 在日期上增加的整天数
	 * @param date
	 * 		       时间
	 * @param month
	 * 		  要增加的整天数
	 * @return
	 */
	public static Date dateAddIntDay(Date date,int days){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	
	/**
	 * 在日期上增加的整小时
	 * @param date
	 * 		       时间
	 * @param month
	 * 		  要增加的整小时
	 * @return
	 */
	public static Date dateAddIntHour(Date date,int hours){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hours);
		return cal.getTime();
	}
	
	/**
	 * 在日期上增加分钟数
	 * @param date
	 * 		       时间
	 * @param month
	 * 		  要增加分钟数
	 * @return
	 */
	public static Date dateAddIntMinutes(Date date,int minutes){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}
	
	/**
	 * 获取某时间的中文星期（如：星期一、星期二），每星期的第一天是星期日
	 * 
	 * @param date ：日期
	 * @return
	 */
	public static String getWeekCS(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String[] week = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return week[calendar.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 获取当前时间的中文星期（如：星期一、星期二），每星期的第一天是星期日
	 * 
	 * @return
	 */
	public static String getWeekCSToday() {
		return getWeekCS(new Date());
	}
	
	/**
	 * 获取两个日期之间所差的天数
	 * 
	 * @param from ：开始日期
	 * @param to ：结束日期
	 * @return：所差的天数
	 */
	public static int dateNum(Date from, Date to) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(from);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date fromDate = calendar.getTime();

		calendar.setTime(to);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date toDate = calendar.getTime();
		int diff = Math.abs((int) ((fromDate.getTime() - toDate.getTime()) / (24 * 3600 * 1000)));
		return diff;
	}
	
	/**
	 * 获取上月末时间
	 * 
	 * @return Date
	 */
	public static Date getLastMonthEndDate() {
		// 取得系统当前时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// 取得系统当前时间所在月第一天时间对象
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// 日期减一,取得上月最后一天时间对象
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	
    private static void setPattern(String pattern){
        threadLocal.get().applyPattern(pattern);
    }
	
//	public static Date getStartTimeOfDate(Date currentDate) {
//		String strDateTime = date2String(currentDate,"yyyy-MM-dd") + " 00:00:00";
//		return string2Date(strDateTime,"yyyy-MM-dd hh:mm:ss");
//	}
	
//	public static Date getEndTimeOfDate(Date currentDate) {
//		String strDateTime = date2String(currentDate,"yyyy-MM-dd") + " 59:59:59";
//		return string2Date(strDateTime,"yyyy-MM-dd hh:mm:ss");
//	}

	// **************************************************************
	// 以下是副产品，我们用到的情况比较少
	// util.date类型
	// ----------------------------------------------
	// //1年前日期
	// java.util.Date myDate=new java.util.Date();
	// long myTime=(myDate.getTime()/1000)-60*60*24*365;
	// myDate.setTime(myTime*1000);
	// //明天日期
	// myDate=new java.util.Date();
	// myTime=(myDate.getTime()/1000)+60*60*24;
	// myDate.setTime(myTime*1000);
	// //两个时间之间的天数
	// SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	// java.util.Date date= myFormatter.parse("2003-05-1");
	// java.util.Date mydate= myFormatter.parse("1899-12-30");
	// long day=(date.getTime()-mydate.getTime())/(24*60*60*1000);
	// //加半小时
	// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	// java.util.Date date1 = format.parse("2002-02-28 23:16:00");
	// long Time=(date1.getTime()/1000)+60*30;
	// date1.setTime(Time*1000);
	// String mydate1=formatter.format(date1);
	// //年月周求日期
	// SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM F E");
	// java.util.Date date2= formatter2.parse("2003-05 5 星期五");
	// SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
	// String mydate2=formatter3.format(date2);
	// //求是星期几
	// mydate= myFormatter.parse("2001-1-1");
	// SimpleDateFormat formatter4 = new SimpleDateFormat("E");
	// String mydate3=formatter4.format(mydate);
	// -----------------------------------------------
	// now.getYear()；//实际年份减去1900,如果构造函数为Date(2008,2,25)则不减1900，如果构造函数为Date(17009456745)或者setTime(17009456745)还减1900
	// now.getMonth();//实际月份减去1,如果构造函数为Date(2008,2,25)则不减1，如果构造函数为Date(17009456745)或者setTime(17009456745)还减1900
	// now.getDay();//*，原来是取星期，不知sun公司是咋想的，脑袋进水了。
	// now.getDate();//这才是取1～31之间的日
	// now.getHours();//24进制的小时
	// now.getMinutes();//分
	// now.getSeconds();//秒
	// now.getTime();//返回1970年1月1日00:00:00起至今的毫秒数
	// now.setTime(long time);//真实日期为1970年1月1日午夜+time毫秒
	//
	// *************************************
	// 日历类型的子类GregorianCalendar类型
	// 构造函数GregorianCalendar(int year, int month, int date) ，无参数为但前时间
	// 注意月份的表示，一月是0，二月是1，以此类推。因此最好使用单词而不是使用数字来表示月份。父类Calendar使用常量来表示月份：JANUARY,
	// FEBRUARY...
	// 所以1903年12月17日可以写为
	// GregorianCalendar aaa = new GregorianCalendar(1903, Calendar.DECEMBER,
	// 17)
	// GregorianCalendar aaa = new GregorianCalendar(1903, 11, 17);
	// ---------------------------------------
	// import java.util.Date;
	// import java.text.DateFormat;
	// import java.util.GregorianCalendar;
	// public class a {
	// public static void main(String[] args) {
	// DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
	// GregorianCalendar gca = new GregorianCalendar();
	// //getTime()方法是将GregorianCalendar对象转换为Date对象
	// gca.setTime(new Date());
	// System.out.println("系统时间: " +df.format(gca.getTime()));
	// //set 方法能够让我们通过简单的设置星期中的哪一天这个域来将我们的时间调整为星期五.注意到这里我们使用了常量 DAY_OF_WEEK 和
	// FRIDAY来增强代码的可读性.
	// //如果当前为星期五时间不变
	// gca.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.FRIDAY);
	// System.out.println("下一个星期五: " + df.format(gca.getTime()));
	// //add 方法让我们能够在日期上加上数值.
	// gca.add(GregorianCalendar.DAY_OF_MONTH, 8);
	// System.out.println("再加8天: " + df.format(gca.getTime()));
	// //get方法取对象中的一部分
	// int i = gca.get(GregorianCalendar.DAY_OF_MONTH);
	// System.out.println(i);
	// }}
	//
	//
	// *****************************************************
	//
	// 把Date以不同地域的格式显示：java.text.DateFormat
	// getDateTimeInstance()的前两个参数分别代表日期风格和时间风格，取值为SHORT, MEDIUM, LONG, 和 FULL
	// getDateInstance()方法：Java还提供了几个选择日期格式，你可以通过使用重载的getDateInstance(int
	// style)获得。出于方便的原因，DateFormat提供了几种预置的常量，你可以使用这些常量参数SHORT, MEDIUM, LONG,
	// 和FULL
	// -----------------------------------------------
	// import java.util.Locale;
	// import java.text.DateFormat;
	// import java.util.Date;
	// public class a {
	// public static void main(String[] args) {
	// Date now=new Date();
	// Locale localeCN=Locale.CHINA;
	// DateFormat
	// df=DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL,localeCN);
	// System.out.println(df.format(now));
	// //结果为2004年5月17日 星期一 下午16时38分32秒 CST
	// }}
	// ******************************************************

}
