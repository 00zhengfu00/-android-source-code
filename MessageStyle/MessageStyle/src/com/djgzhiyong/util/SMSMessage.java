package com.djgzhiyong.util;

import java.util.ArrayList;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import com.djgzhiyong.sms.data.DataWrite;
import com.djgzhiyong.widget.WidgetUtil;

public class SMSMessage
{
	/****
	 * ������Ϣ
	 * 
	 * @param context
	 * @param number
	 *            �Է�����
	 * @param content
	 *            ����
	 */
	public static void sendMessage(Context context, String number,
			String content)
	{
		SmsManager sm = SmsManager.getDefault();
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, new Intent(),
				0);
		ArrayList<String> ms = sm.divideMessage(content);

		for (String str : ms)
		{
			sm.sendTextMessage(number, null, str, pi, null);
		}
		DataWrite.insertMessae(context, 2, number, content);
		WidgetUtil.showToastView(context, "��Ϣ�ѷ���");
	}
}
