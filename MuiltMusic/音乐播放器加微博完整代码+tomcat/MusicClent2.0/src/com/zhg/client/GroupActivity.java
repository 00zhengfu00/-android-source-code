package com.zhg.client;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;

import com.zhg.adapter.GroupAdapter;
import com.zhg.db.MusicGroupDao;
import com.zhg.db.MusicItemDao;
import com.zhg.entity.Music;
import com.zhg.entity.MusicGroup;
import com.zhg.entity.MusicItem;

public class GroupActivity extends SuperActivity {
	private ExpandableListView elvGroup;
	private GroupAdapter adapter ;
	private MusicGroupDao dao;
	public static final int MENU_CLEARGROUP = 100;
	/**
	 * ��ʼ����ͼ
	 */
	private void findViews(){
		//ʵ����elvGroup����
		elvGroup = (ExpandableListView)findViewById(R.id.elvGroup);
		//elvGroup.setGroupIndicator(this.getResources().getDrawable(R.drawable.groupindicator));
		//ʹ�ÿռ��Ϲ���adapter
		adapter = new GroupAdapter(this, new ArrayList<MusicGroup>());
		//��adapter��listview
		elvGroup.setAdapter(adapter);
		//���������Ĳ˵�
		elvGroup.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo)menuInfo;
				long packedPosition = info.packedPosition;
				int type = ExpandableListView.getPackedPositionType(packedPosition);
				if(type==ExpandableListView.PACKED_POSITION_TYPE_CHILD){
					menu.add(0, MENU_DELETE, 1, "�Ƴ�����");
				}
				menu.add(0, MENU_PLAY, 2, "���ŷ���");
				menu.add(0,MENU_CLEARGROUP,3,"��շ���");
				menu.add(0,MENU_DELETEGROUP,4,"ɾ������");
			}
		});
		
		dao = new MusicGroupDao(this);
		
		
	}
	
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		//���menuinfo����
		ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo)item.getMenuInfo();
		//���һ��packedPosition
		long packedPosition = info.packedPosition;
		//����packedPosition����ȡExpandablelistview�б�ѡ�����groupPosition
		//��id
		int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
		
		//childid
		int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);
	
		//��ȡ�����
		MusicGroup group = (MusicGroup)adapter.getGroup(groupPosition);
		
		MusicItemDao musicDao = new MusicItemDao(this);
		switch(item.getItemId()){
		case MENU_CLEARGROUP://����������е�����������Ϣ
			//����groupId�Ƴ��б��е�����
			musicDao.deleteItemByGroupid(group.getId());
			//���½���
			adapter.changeData(dao.getGroups());
			break;
		case MENU_DELETE://���б����Ƴ�һ������
			
			if(childPosition!=-1){
				//��ȡmusicitem����
//				group.getItems().get(childPosition);
				MusicItem musicItem = (MusicItem)adapter.getChild(groupPosition, childPosition);
				//��ȡ��musicitem��id��ɾ��
				musicDao.deleteItemById(musicItem.getId());
				//���½���
				adapter.changeData(dao.getGroups());
			}
			break;
		case MENU_DELETEGROUP://ɾ����������
			//����groupId����musicgroup���У�ɾ������
			dao.deleteGroup(group.getId());
			//���½���
			adapter.changeData(dao.getGroups());
			break;
		case MENU_PLAY:
			//��ȡapplication����
			MyApplication app = (MyApplication)getApplication();
			//��ȡ��ǰ���������musicitem��Ϣ
			ArrayList<MusicItem> items = group.getItems();
			//�����ǰ��������Ϣ���������ǰ���������musicitem��Ϣ
			if(items!=null && items.size()>0){
				ArrayList<Music> musics = new ArrayList<Music>();
				int musicColumnIndex;
				for(MusicItem it : items){
					//���ݵ�ǰmusicitem��musicid����ý����ѯmusic��Ϣ
					//String[] projection = {"_id","_data","artist","title","duration"};
					Cursor musicCursor = getContentResolver().query(ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, it.getMusicId()), null, null, null, null);
					if(musicCursor.moveToNext()){
						Music music = new Music();
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
						//��music��Ϣ��ӵ�����
						musics.add(music);
					}
				}
				//����app�д洢��music����
				app.setMusics(musics);
			}
			Intent intent=new Intent(SuperActivity.ACTION_LISTCHANGED);
			sendBroadcast(intent);
			//�������ֲ��ſ���ҳ��
			Intent activityIntent = new Intent(this,MusicPlayerActivity.class);
			startActivity(activityIntent);
			break;
		}
		return true;
	}



	@Override
	protected void updateUI(Intent intent) {
		super.updateUI(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grouplayout);
		findViews();
	}

	/**
	 * ����ϵͳ�˵��ķ���
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0,MENU_ADDGROUP,1,"��ӷ���").setIcon(android.R.drawable.ic_menu_add);
		return true;
	}

	/**
	 * ϵͳ�˵�����¼�������
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//��ʾһ���Ի������������
		final EditText etInput = new EditText(this);
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("������Ϣ")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setMessage("�����������")
		.setView(etInput)
		.setPositiveButton("ȷ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//��Ч����֤
				if(etInput.getText()==null||"".equals(etInput.getText().toString())){
					return ;
				}
				//����û�����ķ�����
				String groupTitle = etInput.getText().toString();
				//����һ��musicgroup����
				MusicGroup group = new MusicGroup();
				group.setTitle(groupTitle);
				//��ӵ����ݿ�
				dao.addGroup(group);
				//����UI
				adapter.changeData(dao.getGroups());
			}
		}).setNegativeButton("ȡ��", null)
		.show();
		
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		//��ѯ����
		ArrayList<MusicGroup> groups = dao.getGroups();
		//֪ͨadapter��������
		adapter.changeData(groups);
	}

}
