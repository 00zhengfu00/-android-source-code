package com.djgzhiyong.sms.data;

import java.util.ArrayList;

import com.djgzhiyong.model.MsgInfo;

public class AppData
{
	/** ������Ϣ */
	public static ArrayList<MsgInfo> ALL_MESSAGE = null;

	/** ϵͳ��Ϣ�㲥 */
	public static final String RECEIVER_MESSAGE_ACTION = "android.provider.Telephony.SMS_RECEIVED";

	/*** ��Ϣ������̨���� **/
	public static final String SERVICE_MESSAGE = "com.djgzhiyong.msg.listener";
}
