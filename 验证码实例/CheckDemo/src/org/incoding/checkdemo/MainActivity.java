package org.incoding.checkdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ���ߣ��������
 * ���ͣ�www.incoding.org
 * ʱ��:2012.05.08
 * ����Ⱥ��194802363ԭ��
 * ���ܣ���Actiivty
 * */
public class MainActivity extends Activity implements Button.OnClickListener{
	
	// ������֤����Ϣ
	protected static final int UPDATA_CHECKNUM = 0x101; 
	
	CheckAction mCheckView ;
	TextView mShowPassViwe;
	EditText mEditPass;
	Button mSubmit;
	Button mRef;
	
	// ��֤�룺
	int [] checkNum = {0,0,0,0};
	
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initView();
        initCheckNum();
    }
    
    public void initView()
    {
    	mCheckView = (CheckView) findViewById(R.id.checkView);
    	mShowPassViwe = (TextView) findViewById(R.id.checkpass);
    	mEditPass = (EditText) findViewById(R.id.checkTest);
    	mSubmit = (Button) findViewById(R.id.submit);
    	mRef = (Button) findViewById(R.id.ref);
    	
    	mSubmit.setOnClickListener(this);
    	mRef.setOnClickListener(this);
    }
    
    // ��ʼ����֤�벢��ˢ�½���
    public void initCheckNum()
    {
    	checkNum = CheckGetUtil.getCheckNum();
    	mCheckView.setCheckNum(checkNum);
    	mCheckView.invaliChenkNum();
    }

    public void onClick(View v) {
		switch (v.getId())
		{		
		case R.id.submit:
			String userInput = mEditPass.getText().toString();
			if(CheckGetUtil.checkNum(userInput, checkNum))
				{
				setPassString("ͨ��");
				Toast.makeText(this, "ͨ��", 1200).show();
				}
			else
				{
				setPassString("δͨ��");
				Toast.makeText(this, "δͨ��", 1200).show();
				}
			break;
		case R.id.ref:
			initCheckNum();
			break;
		default:
			break;
		}
	}
    
    public void onResume() {
    	// ����ˢ����֤��
    	new Thread(new myThread()).start();
    	super.onResume();
    	
    }
    
    public void setPassString(String passString)
    {
    	mShowPassViwe.setText(passString);
    }
    
    /**���Լ�������߳�*/
    class myThread implements Runnable { 
         public void run() {
              while (!Thread.currentThread().isInterrupted()) {  
                   // ������Ϣ
                   Message message = new Message(); 
                   message.what = MainActivity.UPDATA_CHECKNUM; // Hander�������ռ�
                   
                   MainActivity.this.myHandler.sendMessage(message);
                   try { 
                   	// �߳�����
                       Thread.sleep(ConmentConfig.PTEDE_TIME);  
                   } catch (InterruptedException e) { 
                       Thread.currentThread().interrupt(); 
                   } 
              } 
         } 
    } 
    
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) { 
             switch (msg.what) {
                  case MainActivity.UPDATA_CHECKNUM:
                	   mCheckView.invaliChenkNum();
                       break; 
             }
             ///////////////////////////////////////////////////
             //	���Ϊʲôд���⣿��������
             //	�д��о���
             ///////////////////////////////////////////////////
             super.handleMessage(msg); 
        } 
   };
}