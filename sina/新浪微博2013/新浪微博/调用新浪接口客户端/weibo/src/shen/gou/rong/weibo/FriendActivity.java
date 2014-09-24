package shen.gou.rong.weibo;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import shen.guo.rong.util.AccessTokenKeeper;
import shen.guo.rong.util.ContentInfo;
import shen.guo.rong.util.HomeAdapters;
import shen.guo.rong.util.Tools;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.example.weibo.R;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.FriendshipsAPI;
import com.weibo.sdk.android.net.RequestListener;

public class FriendActivity extends Activity {
private FriendshipsAPI friend;
private Tools tools;
private ListView Flist;
private  HomeAdapters adapater = null;
public ArrayList<ContentInfo> contentList = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		this.Flist=(ListView)findViewById(R.id.Flist);
		friend=new FriendshipsAPI(AccessTokenKeeper.readAccessToken(this));
		//comment=new CommentsAPI(AccessTokenKeeper.readAccessToken(this));
		this.tools=new Tools();
		//friend.fo
		//friend.friends(null,50, 0,false, listener)
		//friend.friends(0, 0, cursor, trim_status, listener)
		friend.friends(Long.parseLong(AuthActivity.uid),50, 0,false, new RequestListener() {
			
			@Override
			public void onIOException(IOException arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onError(WeiboException arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onComplete(String values) {
				System.out.println("��ȡ�����б�"+values);
				// TODO Auto-generated method stub
				try {	
				FriendActivity.this.contentList = FriendActivity.this.tools.loadHomeData2(values);
				System.out.println("�����б�contentList��"+contentList);
				Message message =new Message();
			    message.what=1;
			    handler.sendMessage(message);						
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
	}

	 
	public final  Handler handler=new Handler(){
			public void handleMessage(Message msg){
				switch(msg.what){
				case 1:
					if (contentList != null) {
			            // ����һ��Adapter����ListView�е�ÿ��Item������
						FriendActivity.this.adapater = new HomeAdapters(FriendActivity.this, contentList);
						System.out.println("�����б�contentList��"+contentList);
			            // ����listview�ϵ�item����¼�����
						
			            //��adapter��listview����
						Flist.setAdapter(adapater);
			        } 
					break;
				
				}
			}
		};
}
