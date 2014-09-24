package com.zhg.utils;

import com.zhg.client.MusicPlayerActivity;
import com.zhg.client.R;
import com.zhg.client.service.MusicPlayerService;
import com.zhg.entity.Music;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.RemoteViews;

public class MyNotiofation {
	public static final int NOTIFICATION_ID = 123321456;
	public static void getNotif(Context context,Music music,NotificationManager manager){
		Notification notification=new Notification(android.R.drawable.ic_media_play, music.getMusicName()	
				, System.currentTimeMillis());
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.notification);
	int nowplaystatus=MusicPlayerService.status;
		// ����֪ͨ��ʾ������  
		Bitmap bitmap=BitmapTool.getbitmap(music.getAlbumPath(),music.getSinger());
		if (bitmap!=null) {
			remoteViews.setImageViewBitmap(R.id.image, bitmap);
		}else {
			remoteViews.setImageViewResource(R.id.image, R.drawable.default_charts);
		}
		//ǰһ��
		remoteViews.setOnClickPendingIntent(R.id.btnPrevious_player, 
				PendingIntent.getBroadcast(context, NOTIFICATION_ID, new Intent("com.zhg.action.PREVIOUS"), PendingIntent.FLAG_ONE_SHOT));
		//��ͣ����
		remoteViews.setOnClickPendingIntent(R.id.btnPlay_player, getinten(context, remoteViews, nowplaystatus,manager,music));
		//��һ��
		remoteViews.setOnClickPendingIntent(R.id.btnNext_player, 
				PendingIntent.getBroadcast(context, NOTIFICATION_ID, new Intent("com.zhg.action.NEXT"), PendingIntent.FLAG_ONE_SHOT));
		remoteViews.setTextViewText(R.id.no_musicname, "            "+music.getSinger()+" : "+music.getMusicName());
//		remoteViews.setTextViewText(R.id.no_album, music.getAlbumName());
//		remoteViews.setTextViewText(R.id.no_artist, music.getSinger());
		// ������ָ����֪ͨ
		notification.contentView = remoteViews;
		// ָ�����֪ͨ�������Ǹ�Activity
		notification.contentIntent = PendingIntent.getActivity(
				context, 0, new Intent(
						context,MusicPlayerActivity
						.class),
				PendingIntent.FLAG_UPDATE_CURRENT);
		// ָ��֪ͨ�������
		notification.flags |= Notification.FLAG_NO_CLEAR;
		// ָ��֪ͨ�������
		// notification.flags|=Notification.FLAG_NO_CLEAR;
		// ֪ͨ��ʾ��ʱ�򲥷�Ĭ������
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		// ��NotificationManagerע��һ��notification������NOTIFICATION_ID��Ϊ�����Ψһ��ʾ
		manager.notify(NOTIFICATION_ID, notification);
	}
	private static PendingIntent getinten(Context context,
			RemoteViews remoteViews, int nowplaystatus,NotificationManager manager,Music music) {
		PendingIntent intent=null;
		if (nowplaystatus==3) {
			intent=PendingIntent.getBroadcast(context, 0, new Intent("com.zhg.action.PAUSE"), PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setImageViewResource(R.id.btnPlay_player, R.drawable.desktop_pause);
		}else{
			intent=	PendingIntent.getBroadcast(context, 1, new Intent("com.zhg.action.PLAY"), PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setImageViewResource(R.id.btnPlay_player, R.drawable.desktop_play);
		}
		return intent;
	}

}
