package com.djgzhiyong.date;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateTime
{
	/*** �� �� ʱ �� ***/
	public static String getTime(String dateRes)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM��dd��  HH:mm");
		long time = Long.parseLong(dateRes);
		Date date = new Date(time);
		String str = dateFormat.format(date);
		return str;
	}

	/** ��ȡ��ǰϵͳʱ�� */
	public static long getSystemTime()
	{
		long time = System.currentTimeMillis();
		return time;
	}
}
