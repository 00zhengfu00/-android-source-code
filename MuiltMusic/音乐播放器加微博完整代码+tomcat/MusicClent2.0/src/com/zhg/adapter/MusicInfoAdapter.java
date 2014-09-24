package com.zhg.adapter;

import java.util.List;
import java.util.Map;

import com.zhg.client.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MusicInfoAdapter extends BaseAdapter {
	//���ڱ���������
	Context listViewContext;
	// ���ڴ洢���ֵĶ�Ӧ��Ϣ
	private LayoutInflater mLayoutInflater;  
	// �����������ݱ���
	private List<Map<String, Object>> musicDataList;

	public MusicInfoAdapter(Context context,
			List<Map<String, Object>> musicDataList) {
		//����������ʵ����һ��LayoutInflater
		this.mLayoutInflater = LayoutInflater.from(context);
		// ���������ݱ�������
		this.musicDataList = musicDataList;
	}
	public int getCount() {
		return musicDataList.size();
	}
	public Object getItem(int position) {
		return musicDataList.get(position);
	}
	public long getItemId(int position) {
		return musicDataList.get(position).hashCode();
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		//����ʵ����
		ViewHolder holder;
		//�ж��Ƿ�Ϊ��һ������
		if (convertView == null) {  //��һ���������ʼ�����ʵ��������
			holder = new ViewHolder();    
			convertView = mLayoutInflater.inflate(R.layout.music_list_item, null);
			holder.musicAlbum = (TextView) convertView.findViewById(R.id.musicAlbum);
			holder.musicArtist = (TextView) convertView.findViewById(R.id.musicArtist);
			holder.musicName = (TextView) convertView.findViewById(R.id.musicName);
			holder.musicDuration=(TextView)convertView.findViewById(R.id.showdurction);
			convertView.setTag(holder);
		} else {//���ǵ�һ�Σ�ֱ��ȡ��������Զ���
			holder = (ViewHolder) convertView.getTag();
		}
		//������������
		holder.musicName.setText((String) musicDataList.get(position).get("musicName"));
		holder.musicAlbum.setText((String) musicDataList.get(position).get("musicAlbum"));
		holder.musicArtist.setText((String) musicDataList.get(position).get("musicArtist"));
		String durction=(String) musicDataList.get(position).get("musicdurction");
		int durctions=Integer.parseInt(durction);
		int mintue=durctions/1000/60;
		int second=(durctions-mintue*60000)/1000;
		if (second<10) {
			holder.musicDuration.setText("0"+mintue+":0"+second);
		}else {
		holder.musicDuration.setText("0"+mintue+":"+second);
		}
		return convertView;
	}
	
	
	
	public class ViewHolder {
		public TextView musicName;//��������
		public TextView musicAlbum;//ר������
		public TextView musicArtist;//��������
		public TextView musicDuration;//����ʱ��
		public ImageView musicAlbumArtPath;//ר��ͼƬ
		public RatingBar musicRatingBar;//�ȶ�
	}
	
}
