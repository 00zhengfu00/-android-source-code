package com.zhg.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.zhg.adapter.MusicListAdapter;
import com.zhg.client.service.DownloadService;
import com.zhg.client.service.DownloadService.MyBinder;
import com.zhg.entity.Music;
import com.zhg.service.net.HttpTool;
import com.zhg.service.xml.MusicXmlParser;

public class MusicClentActivity extends SuperActivity {
	
	private ListView lvSounds;
	private MusicListAdapter adapter;
	private File dir;
	private View lodingview;
	private Handler handler = new Handler() {
		/**
		 * �߳��д��ص���Ϣ�Ĵ�����
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				Toast.makeText(MusicClentActivity.this, "xml�������",
				//xml������ɵ���ʾ
				Toast.LENGTH_SHORT).show();
				lodingview.setVisibility(View.GONE);
				break;
			case 1:
				// xml�н�����һ��music
				Music music = (Music) msg.obj;
				//����listView
				adapter.addMusic(music);
				break;
				default:
			 lodingview.setVisibility(View.GONE);
					break;
			}

		}

	};
	private MyBinder binder;

	// �󶨺ͽ��serviceʱ�Ļص�����conn
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			binder = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = (MyBinder) service;
		}
	};

	private void findViews() {
		// ʵ����ListView����
		lvSounds = (ListView) findViewById(R.id.lvSounds);
		adapter = new MusicListAdapter(this, new ArrayList<Music>(), lvSounds);
		lvSounds.setAdapter(adapter);
		lodingview=this.findViewById(R.id.lodinginfo);
		// ����livtview��item�����¼�
		lvSounds.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long arg3) {
				// ��ȡ��ǰ�������Ӧ��music
				Music music = (Music) adapter.getItem(position);
				if (dir != null) {
					if (!music.isLoaded()) {
						// �����µ���������
						music.setSavePath(dir.getAbsolutePath()
								+ music.getMusicPath().substring(
										music.getMusicPath().lastIndexOf("/")));

						// ͨ��binder������service������������������
						binder.addTask(music);
					}
				}
			}

		});
		new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					// ���xml�ļ���������
					InputStream in = HttpTool.getStream(HttpTool.URI
							+ "sounds.xml", null, null, HttpTool.GET);
					// ����xml�ļ�
					new MusicXmlParser(handler).parse(in);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}.start();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musiclist);

		// ����service
		Intent intent = new Intent(this, DownloadService.class);
		startService(intent);
		this.getApplicationContext()
				.bindService(intent, conn, BIND_AUTO_CREATE);

		// ��������Ŀ¼
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File root = Environment.getExternalStorageDirectory();
			dir = new File(root, "musics");
			if (!dir.exists()) {
				dir.mkdir();
			}
		}
		
		

		findViews();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//��activityע��ʱ�����service�İ�
		this.getApplicationContext().unbindService(conn);
	}

	

	@Override
	protected void onResume() {
		super.onResume();
		//activity��ʾʱ  ����listview
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * �㲥�Ĵ�����
	 */
	public void updateUI(Intent intent){
		//
		if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){
			//���������ɣ���֪ͨ�������
			adapter.notifyDataSetChanged();
		}
	}

}