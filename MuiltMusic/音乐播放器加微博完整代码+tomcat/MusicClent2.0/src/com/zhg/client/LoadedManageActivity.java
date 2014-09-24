package com.zhg.client;

import java.io.File;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;

import com.zhg.adapter.LoadedMusicListAdapter;
import com.zhg.db.MusicDao;
import com.zhg.db.MusicPageControl;
import com.zhg.entity.Music;

public class LoadedManageActivity extends SuperActivity {
	private MusicDao dao;
	private ListView lvSounds;
	private LoadedMusicListAdapter adapter;
	/*Pop�˵�*/
	//------------------------------------
	private MusicPageControl control;
	//��ʼ����ͼ�ķ���
	private void findViews(){
		//��ʼ��musicDao
		dao = new MusicDao(this);
		//��ʼ��listView
		lvSounds = (ListView)findViewById(R.id.lvSounds);
		//ɨ����������Ŀ¼
		dao.scanDIR();
		//ʹ�ÿռ��Ϲ���adapter
		adapter = new LoadedMusicListAdapter(this,new ArrayList<Music>());
		lvSounds.setAdapter(adapter);
		//��ӹ����¼�������
		lvSounds.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Log.i("msg", view.getCount()-1 + ":" + view.getLastVisiblePosition());
				//�������������ײ���������һҳ���ݲ����½��� 
				if(view.getCount()-1==view.getLastVisiblePosition()){
					//������һҳ����
					ArrayList<Music> musics = control.getData();
					//׷�ӵ�listView�����½���
					adapter.addItems(musics);
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				
			}
		});
		//�����Ĳ˵�����
		lvSounds.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				//��������˵���
				menu.add(0, MENU_UPDATE, 1, "�޸�");
				menu.add(0,MENU_DELETE,2,"ɾ��");
			}
		});
	}
	

	/**
	 * �����Ĳ˵���ĵ����¼�����
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		//��ȡ�����Ĳ˵����menuInfo
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo)item.getMenuInfo();
		//��menuinfo�л�ȡ���������Ĳ˵�������listview��item��position
		int position = menuInfo.position;
		//����position����adapter�л�ȡmusic����
		final Music music =(Music) adapter.getItem(position);
		// �Բ�ͬ�Ĳ˵���ֱ���
		switch(item.getItemId()){
		case MENU_DELETE:
			//�����ݱ���ɾ����¼
			dao.delete(music.getId());
			//��sd����ɾ���ļ�
			new File(music.getSavePath()).delete();
			//��adapter��ɾ����music��Ϣ
			adapter.remove(position);
			break;
		case MENU_UPDATE:
			//ʵ�����Ի����е��Զ�����ͼ
			final View view = LayoutInflater.from(this).inflate(R.layout.updatemusic_dialog,	 null);
			//ʵ�����Ի����е�����EditText
			final EditText etMusicName = (EditText)view.findViewById(R.id.etMusicName_Dialog);
			final EditText etSinger = (EditText)view.findViewById(R.id.etSinger_Dialog);
			//����ѡ�е�music��Ϣ��ʾ�ڶԻ�����
			etMusicName.setText(music.getMusicName());
			etSinger.setText(music.getSinger());
			
			//��������ʾ�Ի���
			AlertDialog.Builder builder = new Builder(this);
			builder.setTitle("������Ϣ")
			.setMessage("��������Ҫ���µ�������Ϣ")
			.setView(view)
			.setPositiveButton("ȷ��", new OnClickListener() {
				//�Ի�����ȷ����ť�ĵ����¼�
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//������Ч����֤
					if(etMusicName.getText()==null || "".equals(etMusicName.getText().toString())){
						return;
					}
					if(etSinger.getText()==null || "".equals(etSinger.getText().toString())){
						return;
					}
					//��ȡ�������û���������������͸�����
					music.setMusicName(etMusicName.getText().toString());
					music.setSinger(etSinger.getText().toString());
					//�������ݿ��еļ�¼
					dao.update(music);
					//֪ͨ�����������
					adapter.notifyDataSetChanged();
				}
			}).setNegativeButton("ȡ��", null)
			.show();
			break;
		}
		return super.onContextItemSelected(item);
	}


	/**
	 * ��̬ע��Ĺ㲥�������Ĺ㲥������
	 */
	@Override
	protected void updateUI(Intent intent) {
		
		//���������������ɵĹ㲥����ӹ㲥�л�ȡ�����ص����ֶ��󣬲���ӵ�listview�и��½���
		if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())){
			//�ӹ㲥��intent����ȡ���ֶ���
			Music music = (Music)intent.getExtras().getSerializable("music");
			//��listview��������ݣ������½���
			adapter.addItem(music);
		}
	}



	@Override
	protected void onResume() {
		super.onResume();
		//ɨ������Ŀ¼
		dao.scanDIR();
		//����listview�е�����
		adapter.changeData(new MusicPageControl(this, 7).getData());
	}


		@Override
		protected void onPause() {
			super.onPause();
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musiclist);
		//��ʼ����ͼ
		findViews();
	}

}
