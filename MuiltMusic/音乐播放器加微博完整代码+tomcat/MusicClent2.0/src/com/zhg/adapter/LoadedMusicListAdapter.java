package com.zhg.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.zhg.client.R;
import com.zhg.entity.Music;
import com.zhg.utils.BitmapTool;
import com.zhg.utils.StrTime;

public class LoadedMusicListAdapter extends BaseAdapter {
	private ArrayList<Music> musics;
	private LayoutInflater inflater;
	
	public LoadedMusicListAdapter(Context context,ArrayList<Music> musics){
		inflater = LayoutInflater.from(context);
		if(musics!=null)
			this.musics = musics;
		else
			this.musics = new ArrayList<Music>();
	}
	
	/**
	 * �򼯺���׷��һ����¼��������listview
	 * @param music
	 */
	public void addItem(Music music){
		if(music!=null){
			musics.add(music);
			this.notifyDataSetChanged();
		}
	}
	
	/**
	 * ���ؼ����е�ȫ������
	 */
	public ArrayList<Music> getMusics(){
		return this.musics;
	}
	
	/**
	 * �Ӽ������Ƴ�һ��item��������ui
	 * @param position  ���Ƴ���item��position
	 */
	public void remove(int position){
		musics.remove(position);
		this.notifyDataSetChanged();
	}
	
	/**
	 * �򼯺���׷��һ�����ݣ�������listview
	 * @param musics
	 */
	public void addItems(ArrayList<Music> musics){
		if(musics!=null){
			this.musics.addAll(musics);
			this.notifyDataSetChanged();
		}
	}
	/**
	 * ���������е�ȫ�����ݣ�������listview
	 * @param musics
	 */
	public void changeData(ArrayList<Music> musics){
		if(musics!=null){
			this.musics = musics;			
		}else{
			this.musics = new ArrayList<Music>();
		}
		
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return musics.size();
	}

	@Override
	public Object getItem(int position) {
		return musics.get(position);
	}

	@Override
	public long getItemId(int position) {
		return musics.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			convertView = inflater.inflate(R.layout.netmusicitem, null);
			holder = new ViewHolder();
			holder.ivAlbum = (ImageView)convertView.findViewById(R.id.ivAlbum);
			holder.tvMusicName = (TextView)convertView.findViewById(R.id.tvMusicName);
			holder.tvSinger = (TextView)convertView.findViewById(R.id.tvSinger);
			holder.tvAlbum = (TextView)convertView.findViewById(R.id.tvAlbum);
			holder.tvTime = (TextView)convertView.findViewById(R.id.tvTime);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		//��ȡָ��position������
		Music music = musics.get(position);
		//��convertview���������
			holder.tvMusicName.setText(music.getMusicName());
			holder.tvSinger.setText(music.getSinger());
			holder.tvAlbum.setText(music.getAlbumName());
			String durction=music.getTime();
			holder.tvTime.setText(StrTime.getTime(durction));
			String musicalbumpath=music.getAlbumPath();
			if (musicalbumpath==null) {
				holder.ivAlbum.setImageResource(R.drawable.default_charts);
			}else {
				Bitmap bitmap=BitmapTool.getbitmap(musicalbumpath,music.getSinger());
				if (bitmap!=null) {
					holder.ivAlbum.setImageBitmap(bitmap);
				}else {
					holder.ivAlbum.setImageResource(R.drawable.default_charts);
				}
			}
		return convertView;
	}
	class ViewHolder{
		ImageView ivAlbum;
		TextView tvMusicName;
		TextView tvSinger;
		TextView tvAlbum;
		TextView tvTime;
		TextView tvLoaded;
	}
}
 