package com.zhg.client;

import java.util.ArrayList;

import com.zhg.entity.Music;
import com.zhg.utils.Musicdata;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

public class MyApplication extends Application {
	public static MediaPlayer mediaPlayer;
	private ArrayList<Music> musics = new ArrayList<Music>();
	/**
	 * ��ȡmusic����
	 * @return
	 */
	public ArrayList<Music> getMusics() {
		return musics;
	}

	public static Context context;
    @Override
	public void onCreate() {
		super.onCreate();
		context=getApplicationContext();
		setMusics(Musicdata.getMultiData(context));
		mediaPlayer=new MediaPlayer();
	}

	/**
     * ����music����
     * @param musics
     */
	public void setMusics(ArrayList<Music> musics) {
		this.musics = musics;
	}
	
	/**
	 * 
	 * ��musics������׷��һ��miusic��Ϣ
	 * @param musics
	 */
	public void append(ArrayList<Music> musics){
		if(musics!=null){
			this.musics.addAll(musics);
		}
	}
	
	/**
	 * ��musics������׷��1��music��Ϣ
	 * @param music
	 */
	public void append(Music music){
		if(music!=null){
			this.musics.add(music);
		}
	}
}
