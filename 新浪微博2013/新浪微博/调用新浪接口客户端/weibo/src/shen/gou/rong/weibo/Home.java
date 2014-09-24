package shen.gou.rong.weibo;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import shen.guo.rong.util.AccessTokenKeeper;
import shen.guo.rong.util.ContentInfo;
import shen.guo.rong.util.HomeAdapters;
import shen.guo.rong.util.Tools;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.weibo.R;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.api.WeiboAPI.FEATURE;
import com.weibo.sdk.android.net.RequestListener;

public class Home extends Activity {
private	StatusesAPI api;
private LinearLayout load_progress = null;
private ListView home_lv = null;
// ������Ҫ��ʾ�Ķ���΢������
public ArrayList<ContentInfo> contentList = null;
private Button refresh_weibo, writer_weibo;
private  HomeAdapters adapater = null;
private Tools tools;



public void getWeibo(){

	api=new StatusesAPI(AccessTokenKeeper.readAccessToken(this));
	this.tools=new Tools();
	api.friendsTimeline(0, 0, 20,1, false, FEATURE.ALL, false, new RequestListener() {
		
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
		
			
			System.out.println("values"+values);
		
		
		try {
			
			Home.this.contentList = Home.this.tools.loadHomeData(values);
			
			
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
public  final Handler handler=new Handler(){
	public void handleMessage(Message msg){
		switch(msg.what){
		case 1:
			if (contentList != null) {
	            // ����һ��Adapter����ListView�е�ÿ��Item������
				Home.this.adapater = new HomeAdapters(Home.this, contentList);
				System.out.println("��ȡ΢����"+contentList);
	            // ����listview�ϵ�item����¼�����
	            home_lv.setOnItemClickListener(new OnItemClickListener() {
	                @Override
	                public void onItemClick(AdapterView<?> parent, View view,
	                        int position, long id) {
	                	
	                    Object obj = view.getTag();
	                    if (obj != null) {
	                        // ���һ��weibo���ݵ�id��Ψһ��ʶ��
	                    	 String weiboId = obj.toString();
	                        // ��ת��һ�������΢����ʾҳ��
	                        Intent intent = new Intent(Home.this,ContentActivity.class);
	                        Bundle bundle = new Bundle();
	                        // ����������
	                        bundle.putString("weiboId", weiboId);
	                        // ������
	                        intent.putExtras(bundle);
	                        startActivity(intent);
	                    }
	                }
	            });
	            //��adapter��listview����
	            home_lv.setAdapter(adapater);
	        }
	        // ���ؽ�����
	        load_progress.setVisibility(View.GONE);
	    
			break;
		}
	}
};

	@Override
 	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		home_lv=(ListView)super.findViewById(R.id.home_lv);
		load_progress=(LinearLayout)super.findViewById(R.id.load_progress);
		getWeibo();
		this.refresh_weibo=(Button)super.findViewById(R.id.refresh);
		this.writer_weibo=(Button)super.findViewById(R.id.writer);
		this.writer_weibo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Home.this,SendWeibo.class);
				startActivity(intent);
				
			}
		});
		refresh_weibo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getWeibo();
			}
		});
		

	}
}
   

