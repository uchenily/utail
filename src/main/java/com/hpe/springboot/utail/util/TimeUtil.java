package com.hpe.springboot.utail.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	/**
	 * 获取时间, 字符类型, 格式 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String getFormatedDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		return sdf.format(date);
	}
}
