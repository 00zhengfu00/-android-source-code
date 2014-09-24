package shen.gou.rong.weibo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import shen.guo.rong.util.AccessTokenKeeper;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.weibo.R;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.net.RequestListener;

public class SendWeibo extends Activity {
	StatusesAPI api;
private Button send;
private Button photoButton;
private Button add_img;
private EditText sendedit;
private String picpath=null;
private ImageView img=null;
private TextView tv_text_limit;
private  String fileName;
String file_str = Environment.getExternalStorageDirectory().getPath();
File mars_file = new File(file_str + "/my_camera");
File file_go = new File(file_str + "/my_camera/file.jpg");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_weibo);
		 
	 api=new StatusesAPI(AccessTokenKeeper.readAccessToken(this));
        this.tv_text_limit=(TextView)super.findViewById(R.id.tv_text_limit);
        this.photoButton=(Button)super.findViewById(R.id.photoButton);
		this.send=(Button)super.findViewById(R.id.send);
		this.add_img=(Button)super.findViewById(R.id.add);
		this.sendedit=(EditText)super.findViewById(R.id.sendedit);
		this.img=(ImageView)super.findViewById(R.id.add_img);
		this.photoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			    // ��֤sd���Ƿ���ȷ��װ��
			    if (Environment.MEDIA_MOUNTED.equals(Environment
			      .getExternalStorageState())) {
			     // �ȴ�����Ŀ¼������´���һ���ļ���ʱ�򣬸�Ŀ¼û�д��ڣ���ô�����ȴ�����Ŀ¼�����½��ļ���
			     if (!mars_file.exists()) {
			      mars_file.mkdirs();
			     }
			    
			     /*//��������£���������� ������Ŀ¼���������ﲻ��ϵͳ������Ϻ�����������ͼƬ·���Զ�ȥʵ��;
			     if(!file_go.exists())
			     {
			      try {
			       file_go.createNewFile();
			      } catch (IOException e) {
			      }}
			    */  
			     // ������ת��ϵͳ���յ�activityΪ��MediaStore.ACTION_IMAGE_CAPTURE ;
			     Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			     // ���������յĴ��ڷ�ʽΪ�ⲿ�洢�ʹ洢��·����

			     intent.putExtra(MediaStore.EXTRA_OUTPUT,
			       Uri.fromFile(file_go));
			     //��ת�����ս���;
			     startActivityForResult(intent, 0x1);
			    } else {
			     Toast.makeText(SendWeibo.this, "���Ȱ�װ��sd��",
			       Toast.LENGTH_LONG).show();
			    }
			}
		});
		this.add_img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SendWeibo.this,MyFile.class);
				
				SendWeibo.this.startActivity(intent); 
			}
		});
		 Intent intent = getIntent(); 		
	        picpath=intent.getStringExtra("path");
	        System.out.println("path:"+intent.getStringExtra("path"));
	        System.out.println("picpath:"+picpath);
	        if(picpath==null){
			
		}else{
		Bitmap pic=BitmapFactory.decodeFile(picpath);
		Bitmap mBitmap=Bitmap.createScaledBitmap(pic, 220, 260, true);
		//Bitmap mBitmap = Bitmap.createScaledBitmap(bmp, mScreenWidth, mScreenHeight, true);
		SendWeibo.this.img.setImageBitmap(mBitmap);
		}
		sendedit.addTextChangedListener(new TextWatcher() {
            /**
             * �����������ݱ仯��ʱ��ִ��
             */
            public void onTextChanged(CharSequence s, int start, int before,
                    int count) {
                boolean flag = false;
                // �������������
                String mText = sendedit.getText().toString();
                int len = mText.length();// ������������ݳ���
                if (len <= 140) {// �Ƚ��Ѿ���������ݳ����ǲ��ǳ����˹涨�ĳ��ȣ�140��
                    len = 140 - len;// ���㻹�����������ݸ���
                    tv_text_limit.setTextColor(Color.BLACK);// ������ʾtext��ɫ
                    if (send.getVisibility()==View.GONE) {// �жϷ��Ͱ�ť�ǲ�������״̬
                        // writer_weibo.setEnabled(true);// ���÷��Ͱ�ť
                    	send.setVisibility(View.VISIBLE);// ��ʾ���Ͱ�ť
                    }
                } else {
                    len = len - 140;// �����������ݳ�����������ĸ���
                    tv_text_limit.setTextColor(Color.RED);// ������ʾtext��ɫ
                    if (send.getVisibility()==View.VISIBLE) {// �жϷ��Ͱ�ť�ǲ�������״̬
                        // writer_weibo.setEnabled(false);// ���÷��Ͱ�ť�ǲ�����״̬
                    	send.setVisibility(View.GONE);// ���ط��Ͱ�ť
                    }
                    flag = true;
                }
                tv_text_limit.setText(flag ? "-" + len : String.valueOf(len));// ���������������ݸ�����ʾ����
            }

            /**
             * ����������ݸı��ִ��
             */
            public void afterTextChanged(Editable s) {
            }

            /**
             * ����������ݸ�ǰִ��
             */
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
            }
        });

		this.send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(picpath!=null){
				
				//api.update(sendedit.getText().toString(), null, null,new RequestListener() {
					 api.upload(sendedit.getText().toString(), picpath, null, null, new RequestListener() {	
					@Override
					public void onIOException(IOException arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onError(WeiboException arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onComplete(String arg0) {
						Intent intent=new Intent(SendWeibo.this,MainActivity.class);
						startActivity(intent);
						
						
					}
				});
				}
				else{
					api.update(sendedit.getText().toString(), null, null, new RequestListener() {
						
						@Override
						public void onIOException(IOException arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onError(WeiboException arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onComplete(String arg0) {
							// TODO Auto-generated method stub
							Intent intent=new Intent(SendWeibo.this,MainActivity.class);
							startActivity(intent);
						}
					});
				}
			}
		});
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  // TODO Auto-generated method stub
		  // �ж�������ͽ�����Ƿ���ȷ�������ȷ�Ļ�����activity����ʾ�ո������յ�ͼƬ;
		  if (requestCode == 0x1 && resultCode == this.RESULT_OK) {
		  /* ʹ��BitmapFactory.Options���ֹOOM(Out Of Memory)�����⣻
		   ����һ��BitmapFactory.Options����������bitmap��*/
		   BitmapFactory.Options myoptions=new BitmapFactory.Options();
		  /* ����Options����inJustDecodeBounds������Ϊtrue��������BitmapFactory��
		   decodeFile(String path, Options opt)���ȡͼƬ�ĸߺͿ�
		   ������������������ֵΪtrue��ʹ��BitmapFactory��decodeFile()�����޷�����һ��
		   ͼƬ��bitmap���󣬽����ǰ�ͼƬ�ĸߺͿ���Ϣ��Options����
		   */
		   myoptions.inJustDecodeBounds=true;
		   picpath=file_go.getAbsolutePath();
		    BitmapFactory.decodeFile(file_go.getAbsolutePath(),myoptions);
		   //������ͼƬ�Ŀ�͸ߣ��õ�ͼƬ�ڲ����ε����ָ����С�µ�����ͼ,���ÿ�Ϊ222��
		            int height=myoptions.outHeight*222/myoptions.outWidth;
		   myoptions.outWidth=222;
		   myoptions.outHeight=height;
		   //������������ͼƬ��ʾ�ĸߺͿ���סҪ�޸ģ�Options����inJustDecodeBounds������Ϊfalse;
		   //��Ȼ�޷���ʾͼƬ;
		   myoptions.inJustDecodeBounds=false;
		   //��û������Ÿտ�ʼ,Ҫ��Լ�ڴ滹��Ҫ�������ԣ���������ؼ���һ����
		   myoptions.inSampleSize=myoptions.outWidth/222;
		   //����������������������������С�ڴ棻
		   myoptions.inPurgeable=true;
		   myoptions.inInputShareable=true;
		   myoptions.inPreferredConfig=Bitmap.Config.ARGB_4444;// Ĭ����Bitmap.Config.ARGB_8888
		   //�ɹ��ˣ��������ʾͼƬ����
		   Bitmap bitmat = BitmapFactory.decodeFile(file_go.getAbsolutePath(),myoptions);
		   img.setImageBitmap(bitmat);

		  } else {
		   System.out.println("����ʾͼƬ");
		  }
		  super.onActivityResult(requestCode, resultCode, data);
		 }
}
