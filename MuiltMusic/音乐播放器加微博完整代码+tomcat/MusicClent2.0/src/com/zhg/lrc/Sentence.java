package com.zhg.lrc;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.zhg.client.MyApplication;
import com.zhg.client.SuperActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author ����
 * @version ����ʱ�䣺2011-11-14 ����2:48:12
 */
public class Sentence implements Serializable {

	private static final long serialVersionUID = 20071125L;
	private long fromTime;// ������ʼʱ��,ʱ�����Ժ���Ϊ��λ
	private long toTime;// ��һ��Ľ���ʱ��
	private String content;// ��һ�������
	private final static long DISAPPEAR_TIME = 1000L;// ��ʴ���ʾ�굽��ʧ��ʱ��

	public Sentence() {
	}
	public Sentence(String content, long fromTime, long toTime) {
		this.content = content;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}

	/**
	 * �ַ�������ת����ʵ�ַ���
	 * 
	 * @param str
	 *            ��ת�����ַ���
	 * @param newCharset
	 *            Ŀ�����
	 */
	public String changeCharset(String str) throws UnsupportedEncodingException {
		if (str != null) {
			byte[] first3bytes = str.getBytes("utf-16le");
			return new String(first3bytes, "GBK");
		}
		return null;
	}

	public Sentence(String content, long fromTime) {
		this(content, fromTime, 0);
	}

	public Sentence(String content) {
		this(content, 0, 0);
	}

	public long getFromTime() {
		return fromTime;
	}

	public void setFromTime(long fromTime) {
		this.fromTime = fromTime;
	}

	public long getToTime() {
		return toTime;
	}

	public void setToTime(long toTime) {
		this.toTime = toTime;
	}

	/**
	 * ���ĳ��ʱ���Ƿ������ĳ���м�
	 * @param time  ʱ��
	 * @return �Ƿ������
	 */
	public boolean isInTime(long time) {
		return time >= fromTime && time <= toTime;
	}

	/**
	 * �õ���һ�������
	 * @return ����
	 */
	public String getContent() {
		return content;
	}

	/**
	 * �õ�������ӵ�ʱ�䳤��,����Ϊ��λ
	 * @return ����
	 */
	public long getDuring() {
		return toTime - fromTime;
	}

	public String toString() {
		return "{" + fromTime + "(" + content + ")" + toTime + "}";
	}
}
