package com.zhg.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhg.client.R;
import com.zhg.db.MusicDao;
import com.zhg.entity.Music;
import com.zhg.service.net.HttpTool;
import com.zhg.utils.ImageLoader;
import com.zhg.utils.ImageLoader.ImageCallback;

public class MusicListAdapter extends BaseAdapter {
	private ArrayList<Music> sounds;
	private LayoutInflater inflater;
	private ImageLoader loader;
	private ListView lvSounds;
	private MusicDao musicDao;

	public MusicListAdapter(Context context, ArrayList<Music> sounds,
			ListView lvSounds) {
		this.inflater = LayoutInflater.from(context);
		this.sounds = sounds;
		this.loader = new ImageLoader();
		this.lvSounds = lvSounds;
		this.musicDao = new MusicDao(context);
	}

	public void addMusic(Music music) {
		if (sounds != null) {
			sounds.add(music);
			this.notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		return sounds.size();
	}

	@Override
	public Object getItem(int position) {
		return sounds.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.netmusicitem, null);
			holder = new ViewHolder();
			holder.ivAlbum = (ImageView) convertView.findViewById(R.id.ivAlbum);
			holder.tvMusicName = (TextView) convertView
					.findViewById(R.id.tvMusicName);
			holder.tvSinger = (TextView) convertView
					.findViewById(R.id.tvSinger);
			holder.tvAlbum = (TextView) convertView.findViewById(R.id.tvAlbum);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
			holder.tvLoaded = (TextView) convertView
					.findViewById(R.id.tvLoaded);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// ȡ����
		Music music = sounds.get(position);
		// ��������ʾ��converView��
		// ��imageview�����õ�ǰ���ֵ�ͼƬ·��Ϊtag
		holder.ivAlbum.setTag(HttpTool.URI + music.getAlbumPath());
		// ʹ���첽ͼƬ���ض��󣬼���ͼƬ
		Bitmap bitmap = loader.loadImage(HttpTool.URI + music.getAlbumPath(),
				2,
				// �ص�����
				new ImageCallback() {
					@Override
					public void loadedImage(String path, Bitmap bitmap) {
						// TODO Auto-generated method stub
						// ���ݻش���·��������listview�е�imageview
						ImageView iv = (ImageView) lvSounds
								.findViewWithTag(path);
						// ����ҵ�imageview�һش���λͼ��Ϊnull������imageview����ʾ��λͼ
						if (iv != null && bitmap != null) {
							iv.setImageBitmap(bitmap);
						} else {
							iv.setImageResource(R.drawable.default_charts);
						}
					}
				});

		// ������ص�ͼƬ��Ϊnull������ʾ��������ʾĬ��ͼƬ
		if (bitmap != null) {
			holder.ivAlbum.setImageBitmap(bitmap);
		} else {
			holder.ivAlbum.setImageResource(R.drawable.default_charts);
		}
		// loadimgAnsy.ShowImg(holder.ivAlbum, HttpTool.URI +
		// music.getAlbumPath());

		// �������ֵ�������Ϣ
		holder.tvMusicName.setText(music.getMusicName());
		holder.tvAlbum.setText(music.getAlbumName());
		holder.tvSinger.setText(music.getSinger());
		holder.tvTime.setText(music.getTime());
		if (musicDao.exists(music.getId())) {
			holder.tvLoaded.setText("������");
			holder.tvLoaded.setTextColor(Color.RED);
			music.setLoaded(true);
		} else {
			holder.tvLoaded.setText("δ����");
			holder.tvLoaded.setTextColor(Color.BLACK);

		}
		return convertView;
	}

	class ViewHolder {
		ImageView ivAlbum;
		TextView tvMusicName;
		TextView tvSinger;
		TextView tvAlbum;
		TextView tvTime;
		TextView tvLoaded;

	}
}
