package com.zhg.utils;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.zhg.entity.Music;

public class Musicdata {
	
	public static ArrayList<Music> getMultiData(Context context ) {
		 String musicName=null; // ��������
		 String musicAlbum;// ר������
		 String musicArtist;// ������
		 String musicDuration;// ��������
		 String musicAlbumArtPath;// ר��ͼƬ·��
		 String musicAlbumKey;//
		 ArrayList<Music> musics=new ArrayList<Music>();
		// ѭ���ҳ����еĸ�������Ϣ
		ContentResolver resolver = context.getContentResolver();
		Cursor musicCursor = resolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				null);
		int musicColumnIndex;
		// �����α�����
		if (null != musicCursor && musicCursor.getCount() > 0) {
			for (musicCursor.moveToFirst(); !musicCursor.isAfterLast(); musicCursor
					.moveToNext()) {
				Music music=new Music();
				// ȡ�����ֵ�����
				music.setId(musicCursor.getInt(musicCursor.getColumnIndex("_id")));
				musicColumnIndex = musicCursor
						.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
			   musicName = musicCursor.getString(musicColumnIndex);
               music.setMusicName(musicName);
               musicColumnIndex = musicCursor
						.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
               music.setSavePath(musicCursor.getString(musicColumnIndex));
               musicColumnIndex = musicCursor
						.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
				musicAlbum = musicCursor.getString(musicColumnIndex);
               music.setAlbumName(musicAlbum);                
				musicColumnIndex = musicCursor
						.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
				musicArtist = musicCursor.getString(musicColumnIndex);
               music.setSinger(musicArtist);
				musicColumnIndex = musicCursor
						.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
				musicDuration = musicCursor.getString(musicColumnIndex);
				music.setTime(musicDuration);
				// ȡ�ø�����Ӧ��ר��Key ��������ר��ͼƬ̫ռ�ڴ� �Ͳ��ڲ����б�����ʾ��
				musicColumnIndex = musicCursor
						.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_KEY);
				musicAlbumKey = musicCursor.getString(musicColumnIndex);
				String[] argArr = { musicAlbumKey };
				ContentResolver albumResolver = context.getContentResolver();
				Cursor albumCursor = albumResolver.query(
						MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null,
						MediaStore.Audio.AudioColumns.ALBUM_KEY + " = ?",
						argArr, null);
				if (null != albumCursor && albumCursor.getCount() > 0) {
					albumCursor.moveToFirst();
					int albumArtIndex = albumCursor
							.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART);
					musicAlbumArtPath = albumCursor.getString(albumArtIndex);
					if (null != musicAlbumArtPath) {
						music.setAlbumPath(musicAlbumArtPath);
					} else {
						music.setAlbumPath(null);
					}
				} else {
					music.setAlbumPath(null);
				}
				albumCursor.close();
				musics.add(music);
			}
			musicCursor.close();
		}

		return musics;
	}
	public static ArrayList<Music> getMultiDataBycurrsor(Context context,Cursor musicCursor ) {
		 ArrayList<Music> musics=new ArrayList<Music>();
			// ѭ���ҳ����еĸ�������Ϣ
//			ContentResolver resolver = context.getContentResolver();
//			Cursor musicCursor = resolver.query(
//					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id, null, null,
//					null);
			int musicColumnIndex;
			// �����α�����
			if (null != musicCursor && musicCursor.getCount() > 0) {
				for (musicCursor.moveToFirst(); !musicCursor.isAfterLast(); musicCursor
						.moveToNext()) {
					Music music=new Music();
					// ȡ�����ֵ�����
					music.setId(musicCursor.getInt(musicCursor.getColumnIndex("_id")));
					musicColumnIndex = musicCursor
							.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
	               music.setMusicName(musicCursor.getString(musicColumnIndex));
	               musicColumnIndex = musicCursor
							.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
	               music.setSavePath(musicCursor.getString(musicColumnIndex));
	               musicColumnIndex = musicCursor
							.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
	               music.setAlbumName(musicCursor.getString(musicColumnIndex));                
					musicColumnIndex = musicCursor
							.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
	               music.setSinger(musicCursor.getString(musicColumnIndex));
					musicColumnIndex = musicCursor
							.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
					music.setTime(musicCursor.getString(musicColumnIndex));
					// ȡ�ø�����Ӧ��ר��Key ��������ר��ͼƬ̫ռ�ڴ� �Ͳ��ڲ����б�����ʾ��
					musicColumnIndex = musicCursor
							.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_KEY);
					String[] argArr = { musicCursor.getString(musicColumnIndex) };
					ContentResolver albumResolver = context.getContentResolver();
					Cursor albumCursor = albumResolver.query(
							MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null,
							MediaStore.Audio.AudioColumns.ALBUM_KEY + " = ?",
							argArr, null);
					if (null != albumCursor && albumCursor.getCount() > 0) {
						albumCursor.moveToFirst();
						int albumArtIndex = albumCursor
								.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART);
						if (null != albumCursor.getString(albumArtIndex)) {
							music.setAlbumPath(albumCursor.getString(albumArtIndex));
						} else {
							music.setAlbumPath(null);
						}
					} else {
						music.setAlbumPath(null);
					}
					albumCursor.close();
					musics.add(music);
				}
				musicCursor.close();
			}
			return musics;
	}
}
