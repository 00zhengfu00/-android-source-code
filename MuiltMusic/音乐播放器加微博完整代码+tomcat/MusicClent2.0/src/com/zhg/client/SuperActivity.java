package com.zhg.client;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class SuperActivity extends Activity {
	//menuId
	public static final int MENU_UPDATE = 1;
	public static final int MENU_DELETE = 2;
	public static final int MENU_ADDTOGROUP = 3;
	public static final int MENU_PLAY = 4;
	public static final int MENU_PLAYALL = 5;
	public static final int MENU_ADDGROUP=6;
	public static final int MENU_DELETEGROUP =7;
	public static final int MNUE_CLEARGROUP = 8;
	
	public static final String ACTION_PLAY = "com.zhg.action.PLAY";
	public static final String ACTION_PAUSE = "com.zhg.action.PAUSE";
	public static final String ACTION_STOP = "com.zhg.action.STOP";
	public static final String ACTION_SEEK = "com.zhg.action.SEEK";
	public static final String ACTION_PREVIOUS = "com.zhg.action.PREVIOUS";
	public static final String ACTION_NEXT = "com.zhg.action.NEXT";
	public static final String ACTION_JUMR = "com.zhg.action.JUMP";
	public static final String ACTION_FIND = "com.zhg.action.FIND";
	public static final String ACTION_UPDATE = "com.zhg.action.UPDATE";
	public static final String ACTION_UPDATE_ALL = "com.zhg.action.UPDATE_ALL";
	public static final String ACTION_UPDATE_TIME = "com.zhg.action.UPDATE_TIME";
	public static final String ACTION_UPDATE_LRC = "com.zhg.action.UPDATE_LRC";
	public static final String ACTION_SERVICESTOPED = "com.zhg.action.SERVICESTOPED";
	public static final String ACTION_LISTCHANGED = "com.zhg.action.LISTCHANGED";
	
	
	
	/**
	 * �ڲ��࣬���ڽ��չ㲥
	 * @author 3gtarena
	 *
	 */
	private class InnerReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			//���յ��㲥�����activity�ж����updateUI����
			updateUI(intent);
		}
	}
	
	//�����㲥������
	protected InnerReceiver mReceiver;
	/**
	 * �㲥�Ĵ�����������������ʵ��
	 * @param intent
	 */
	protected void updateUI(Intent intent){
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//����������ͼ
		SuperActivity.this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
		setContentView(R.layout.musiclist);
		//ʵ�����㲥������
		mReceiver  = new InnerReceiver();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//activity��ͣʱ�����ע��Ĺ㲥������
		unregisterReceiver(mReceiver);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//activity��ʾʱ ע��㲥������
		IntentFilter filter = new IntentFilter();
		filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		registerReceiver(mReceiver, filter);
	}
	
	
}
