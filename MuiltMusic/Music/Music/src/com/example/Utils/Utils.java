package com.example.Utils;

import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

public class Utils {

	public static final String SDPATH=Environment.getExternalStorageDirectory()+"/"; 
	public static final String ALL = "All";//�����׽����־
	public static final String ENTRY_LIST = "entry";//���������б����
	public static Uri MUSIC_MEDIA=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;//��ȡ��ƵURL
	public static String MUSIC_Uri=MediaStore.Audio.Media.DATA; //��ȡ��Ƶ·��
	

	
}
