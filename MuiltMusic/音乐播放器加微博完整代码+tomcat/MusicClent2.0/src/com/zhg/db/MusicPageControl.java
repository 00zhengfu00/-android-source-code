package com.zhg.db;

import java.util.ArrayList;

import com.zhg.entity.Music;

import android.content.Context;

public class MusicPageControl {
	private int totalCount;//��¼������
	private int pageSize;//ÿҳ����
	private int pageCount;//��ҳ��
	private int currentPage;//��ǰҳ
	
	private MusicDao musicDao;
	
	public MusicPageControl(Context context,int pageSize){
		musicDao = new MusicDao(context);
		this.pageSize = pageSize;
		this.currentPage = 0;
		//��ȡ��¼������
		this.totalCount = musicDao.getCount();
		//������ҳ���������������ܱ�ÿҳ������������ҳ��Ҫ+1
		this.pageCount = totalCount%pageSize==0?totalCount/pageSize : totalCount/pageSize+1;
	}
	
	/**
	 * ��ȡ��ǰҳ����
	 * @return
	 */
	public ArrayList<Music> getData(){
		if(currentPage++ < pageCount){
			return musicDao.getPageData(0, pageSize);
		}
		return null;
	}
}
