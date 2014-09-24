package com.zhg.client;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.zhg.adapter.LoadedMusicListAdapter;
import com.zhg.adapter.menu.MyMenu;
import com.zhg.db.MusicGroupDao;
import com.zhg.db.MusicItemDao;
import com.zhg.entity.Music;
import com.zhg.entity.MusicGroup;
import com.zhg.entity.MusicItem;
import com.zhg.utils.Musicdata;

public class LocalPlayListAct extends SuperActivity implements Runnable {
	private ListView lvSounds;
	private LoadedMusicListAdapter adapter;
	private ContentResolver resolver;
	private AlertDialog dialog;
	private View lodingview;
	ArrayList<Music> musics = null;
	public static final int MENU_SCANN = 20;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ��ȡý�����Ϣ
			// ����listview
			lodingview.setVisibility(View.GONE);
			adapter.changeData(musics);
		}

	};

	private void findViews() {
		// ʵ����listView
		lvSounds = (ListView) findViewById(R.id.lvSounds);
		lodingview = this.findViewById(R.id.lodinginfo);
		// ʹ�ÿռ��Ϲ���adapter
		resolver = getContentResolver();
		ArrayList<Music> musics = new ArrayList<Music>();
		adapter = new LoadedMusicListAdapter(this, musics);

		// ����listview��adapter����ʾ����
		lvSounds.setAdapter(adapter);
		lvSounds.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				createContextMenu(menu, v, menuInfo);
			}
		});
		lvSounds.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(SuperActivity.ACTION_JUMR);
				intent.putExtra("position", position);
				sendBroadcast(intent);
				LocalPlayListAct.this.startActivity(new Intent(
						LocalPlayListAct.this, MusicPlayerActivity.class));
				LocalPlayListAct.this.finish();
			}
		});
		// ʵ�����Ի���
		Builder builder = new Builder(this);
		dialog = builder.setTitle("��ʾ��Ϣ").setMessage("����ɨ��sdCard�����Ժ�...")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setCancelable(false).create();
	}

	private void createContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("��ѡ�����Ĳ���");
		menu.setHeaderIcon(android.R.drawable.ic_menu_slideshow);
		menu.add(0, MENU_PLAY, 1, "����");
		menu.add(0, MENU_PLAYALL, 2, "����ȫ��");
		menu.add(0, MENU_ADDTOGROUP, 3, "��ӵ�����");
		menu.add(0, MENU_DELETE, 4, "ɾ��");
	}

	@SuppressWarnings("unused")
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		final MusicItemDao musicItemDao = new MusicItemDao(
				LocalPlayListAct.this);
		// ��ȡmenuinfo
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		// ��menuinfo�л�ȡlistview�д��������Ĳ˵���item��position
		int position = menuInfo.position;
		// ����position��adapter�л�ȡmusic����
		final Music music = (Music) adapter.getItem(position);
		Intent it = new Intent(ACTION_LISTCHANGED);
		Intent intent = new Intent(this, MusicPlayerActivity.class);
		// �жϱ������Ĳ˵���
		switch (item.getItemId()) {
		case MENU_PLAY:
			// ����app�еĲ����б�
			ArrayList<Music> musics = new ArrayList<Music>();
			musics.add(music);
			((MyApplication) getApplication()).setMusics(musics);
			// �������ſ��ƽ���

			startActivity(intent);
			// ���͹㲥��֪ͨ�����б��Ѿ�����
			sendBroadcast(it);
			break;
		case MENU_PLAYALL:
			// ��ȡȫ��������Ϣ
			ArrayList<Music> musicList = adapter.getMusics();
			// ����app�еĲ����б�
			((MyApplication) getApplication()).setMusics(musicList);
			// �������ſ��ƽ���

			startActivity(intent);
			// ���͹㲥��֪ͨ�����б��Ѿ�����
			sendBroadcast(it);
			break;
		case MENU_DELETE:
			// ��ý�����ɾ�������ֵļ�¼
			resolver.delete(
					ContentUris.withAppendedId(
							MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
							music.getId()), null, null);
			// ��sdcard��ɾ���ļ�
			new File(music.getSavePath()).delete();
			// ����listView
			adapter.remove(position);
			// �����ַ����б����Ƴ�������Ϣ
			musicItemDao.deleteItemByMusicid(music.getId());
			break;
		case MENU_ADDTOGROUP:
			// ��ѯ���е�musicgroup
			ArrayList<MusicGroup> groups = new MusicGroupDao(this).getGroups();
			// �����е�musicgrouptitle������һ��������
			final String[] titles = new String[groups.size()];
			for (int i = 0; i < titles.length; i++) {
				titles[i] = groups.get(i).getId() + "��"
						+ groups.get(i).getTitle();
			}
			// ������ѡ�Ի���
			AlertDialog.Builder builder = new Builder(this);

			final AlertDialog dialog = builder.setTitle("ѡ�����")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setSingleChoiceItems(titles, -1, new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// ��ȡ��id
							int groupId = Integer.parseInt(titles[which]
									.substring(0, titles[which].indexOf("��")));
							// ��ȡ����id
							int musicId = music.getId();
							// ����musicItem����
							MusicItem musicItem = new MusicItem();
							musicItem.setGroupId(groupId);
							musicItem.setMusicId(musicId);
							// ������ݵ�musicitem��
							musicItemDao.addMusicItem(musicItem);
							// �رնԻ���
							dialog.cancel();
						}
					}).setNegativeButton("ȡ��", null).show();
			break;
		}

		return true;
	}
	@Override
	protected void updateUI(Intent intent) {
		if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(intent.getAction())) {
			// ��ȡý�����Ϣ
			ArrayList<Music> musics = Musicdata
					.getMultiData(LocalPlayListAct.this);
			// ����listview
			adapter.changeData(musics);
			// ȡ���Ի���
			dialog.cancel();
		} else if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(intent
				.getAction())) {
			// ��ʾ�Ի���
			dialog.show();
		}
	}
	MyMenu menu;
   Context context;
	// ------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		findViews();
		context=this;
		int width = getWindowManager().getDefaultDisplay().getWidth() - 10; // �˵��Ŀ��
		int heigth = getWindowManager().getDefaultDisplay().getHeight() / 3; // �˵��ĸ߶�
		menu=new MyMenu(this, width, heigth);
		menu.setOutsideTouchable(true);
	}
	@Override
	protected void onResume() {
		super.onResume();
		// ע��㲥������
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
		filter.addDataScheme("file");
		registerReceiver(mReceiver, filter);
		Thread getnewdata = new Thread(this);
		getnewdata.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (!menu.isShowing() && keyCode == KeyEvent.KEYCODE_MENU) {
			show();
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (menu != null) {
				if (menu.isShowing()) {
					menu.dismiss();
					return true;
				} else {
					return super.onKeyDown(keyCode, event);
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	private void show() {
		menu.showAtLocation(lvSounds, Gravity.BOTTOM
				| Gravity.CENTER_HORIZONTAL, 0, 0);
	}

	@Override
	public void run() {
		musics = Musicdata.getMultiData(LocalPlayListAct.this);
		handler.sendEmptyMessage(0);
	}
}
