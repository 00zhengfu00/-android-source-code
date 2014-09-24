package shen.guo.rong.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.widget.TextView;
import android.widget.Toast;

public class Tools {
	ArrayList<ContentInfo> contentList = null;

public static boolean isNetworkAvailable( Context context) {
	ConnectivityManager connect=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	if(connect==null){
		return false;
	}
	else{
		NetworkInfo[] info=connect.getAllNetworkInfo();
		if(info!=null){
			for(int i=0;i<info.length;i++){
				if(info[i].getState()==NetworkInfo.State.CONNECTED)
					return true;
			}
		}
	}
	return false;
}
public static boolean checkNetwork(final Activity context){
	if(!isNetworkAvailable(context)){
		TextView text=new TextView( context);
		text.setText("û�п������磬�����������������Ƿ���");
		new AlertDialog.Builder(context).setTitle("��ǰ����״̬").setView(text).setPositiveButton("ȷ��",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				context.startActivityForResult(
                        new Intent(
                                android.provider.Settings.ACTION_WIRELESS_SETTINGS),
                        0);
				
			}
		}).create().show();
	}
	else{
		Toast.makeText(context, "������", 4000);
	}
	return false;
	
}
//�����ҳ����
public ArrayList<ContentInfo> loadHomeData(String json) throws JSONException {             
              JSONObject jsonObject = new JSONObject(json); 
                JSONArray statusesArr = jsonObject.getJSONArray("statuses");              
                // ��Ϊ���ص���JSONArray��ʾ�����˶���weibo���ݣ����Խ���ѭ������
                for (int i = 0; i < statusesArr.length(); i++) {
                    // ��õ���΢������
                	 JSONObject statusesObj = statusesArr.getJSONObject(i);
                   // JSONObject d = data.getJSONObject(i);
                    if (statusesObj!= null) {
                        // ����һ������洢ÿ��΢������
                        ContentInfo contentInfo = new ContentInfo();
                        // ����û�����
                        JSONObject u = statusesObj.getJSONObject("user");
                        if (statusesObj.has("retweeted_status")) {
                            JSONObject r = statusesObj.getJSONObject("retweeted_status");                                
                        }
                        // ���һ��wiebo id
                        String id = statusesObj.getString("id");
                        // ��÷�weibo �û�id
                        String userId = u.getString("id");
                        // ��÷�weibo �û�������
                        String userName = u.getString("screen_name");
                        // ��÷�weibo �û���ͷ��url����
                        String userIcon = u.getString("profile_image_url");
                        // ��÷�weibo��ʱ��
                        String time = statusesObj.getString("created_at");
                        // ���weibo����
                        String text = statusesObj.getString("text");
                        Boolean haveImg = false;
                        // �ж�΢�����ڴ�ͼƬ��Ϣ
                        if (statusesObj.has("thumbnail_pic")) {
                            haveImg = true;
                            // �������ͼurl����
                            String thumbnail_pic = statusesObj.getString("thumbnail_pic");
                            contentInfo.setImage_context(thumbnail_pic);

                        }
                        
                     
                        // ͨ���ַ������췢΢����ʱ��
                        Date startDate = new Date(time);
                        // ��õ�ǰʱ��
                        Date nowDate = Calendar.getInstance().getTime();
                       // DateUtils dateutils=new DateUtils();
                        
                        // �ȽϷ���΢��ʱ��͵�ǰʱ��֮�����ʱ��
                        time =   new DateUtils().twoDateDistance(startDate,nowDate);
                      
                        if (contentList == null) {
                            // �����洢ÿ��΢���ļ���
                            contentList = new ArrayList<ContentInfo>();
                        }
                        // ��������
                        contentInfo.setId(id);
                        contentInfo.setUserId(userId);
                        contentInfo.setUserName(userName);
                        contentInfo.setTime(time);
                        contentInfo.setText(text);
                        contentInfo.setHaveImage(haveImg);
                        contentInfo.setUserIcon(userIcon);
                        // ������΢���������õ�������
                        contentList.add(contentInfo);
                    }
                }
           
        
    
    return contentList;

}
public ArrayList<ContentInfo> loadHomeData1(String json) throws JSONException {             
    JSONObject jsonObject = new JSONObject(json); 
      JSONArray statusesArr = jsonObject.getJSONArray("comments");              
      // ��Ϊ���ص���JSONArray��ʾ�����˶���weibo���ݣ����Խ���ѭ������
      for (int i = 0; i < statusesArr.length(); i++) {
          // ��õ���΢������
      	 JSONObject statusesObj = statusesArr.getJSONObject(i);
         // JSONObject d = data.getJSONObject(i);
          if (statusesObj!= null) {
              // ����һ������洢ÿ��΢������
              ContentInfo contentInfo = new ContentInfo();
              // ����û�����
              JSONObject u = statusesObj.getJSONObject("user");
              if (statusesObj.has("retweeted_status")) {
                  JSONObject r = statusesObj.getJSONObject("retweeted_status");                                
              }
              // ���һ��wiebo id
              String id = statusesObj.getString("id");
              // ��÷�weibo �û�id
              String userId = u.getString("id");
              // ��÷�weibo �û�������
              String userName = u.getString("screen_name");
              // ��÷�weibo �û���ͷ��url����
              String userIcon = u.getString("profile_image_url");
              // ��÷�weibo��ʱ��
              String time = statusesObj.getString("created_at");
              // ���weibo����
              String text = statusesObj.getString("text");
              Boolean haveImg = false;
              // �ж�΢�����ڴ�ͼƬ��Ϣ
              if (statusesObj.has("thumbnail_pic")) {
                  haveImg = true;
                  // �������ͼurl����
                  String thumbnail_pic = statusesObj.getString("thumbnail_pic");
                  contentInfo.setImage_context(thumbnail_pic);

              }
              
           
              // ͨ���ַ������췢΢����ʱ��
              Date startDate = new Date(time);
              // ��õ�ǰʱ��
              Date nowDate = Calendar.getInstance().getTime();
             // DateUtils dateutils=new DateUtils();
              
              // �ȽϷ���΢��ʱ��͵�ǰʱ��֮�����ʱ��
              time =   new DateUtils().twoDateDistance(startDate,nowDate);
            
              if (contentList == null) {
                  // �����洢ÿ��΢���ļ���
                  contentList = new ArrayList<ContentInfo>();
              }
              // ��������
              contentInfo.setId(id);
              contentInfo.setUserId(userId);
              contentInfo.setUserName(userName);
              contentInfo.setTime(time);
              contentInfo.setText(text);
              contentInfo.setHaveImage(haveImg);
              contentInfo.setUserIcon(userIcon);
              // ������΢���������õ�������
              contentList.add(contentInfo);
          }
      }
 


return contentList;

}
public ArrayList<ContentInfo> loadHomeData2(String json) throws JSONException {             
    JSONObject jsonObject = new JSONObject(json); 
      JSONArray statusesArr = jsonObject.getJSONArray("users");              
      // ��Ϊ���ص���JSONArray��ʾ�����˶���weibo���ݣ����Խ���ѭ������
      for (int i = 0; i < statusesArr.length(); i++) {
          // ��õ���΢������
      	 JSONObject statusesObj = statusesArr.getJSONObject(i);
         // JSONObject d = data.getJSONObject(i);
          if (statusesObj!= null) {
              // ����һ������洢ÿ��΢������
              ContentInfo contentInfo = new ContentInfo();
              // ����û�����
            //  JSONObject u = statusesObj.getJSONObject("status");
              // ���һ��wiebo id
              String id = statusesObj.getString("id");
              // ��÷�weibo �û�id
            //  String userId = u.getString("id");
              // ��÷�weibo �û�������
             
              // ��÷�weibo �û�������
              String userName =statusesObj.getString("screen_name");
             // System.out.println("userName:"+ userName);
              // ��÷�weibo �û���ͷ��url����
              String userIcon = statusesObj.getString("profile_image_url");
              // ��÷�weibo��ʱ��
              String time = statusesObj.getString("created_at");
             // System.out.println("time:"+ time);
              // ���weibo����
              String text=statusesObj.getString("description");
              //String text =  statusesObj.getString("description");
              //System.out.println("text:"+ text);
                      
              // ͨ���ַ������췢΢����ʱ��
              Date startDate = new Date(time);
              // ��õ�ǰʱ��
              Date nowDate = Calendar.getInstance().getTime();
             // DateUtils dateutils=new DateUtils();
              
              // �ȽϷ���΢��ʱ��͵�ǰʱ��֮�����ʱ��
              time =   new DateUtils().twoDateDistance(startDate,nowDate);
            
              if (contentList == null) {
                  // �����洢ÿ��΢���ļ���
                  contentList= new ArrayList<ContentInfo>();
              }
              // ��������
              contentInfo.setId(id);
              //contentInfo.setUserId(userId);
              contentInfo.setUserName(userName);
            //  contentInfo.setTime(time);
              contentInfo.setText(text);
             
            	  contentInfo.setText(text); 
            
              //contentInfo.setHaveImage(haveImg);
              contentInfo.setUserIcon(userIcon);
              // ������΢���������õ�������
              contentList.add(contentInfo);
              
          }
      }

return contentList;

}


}
