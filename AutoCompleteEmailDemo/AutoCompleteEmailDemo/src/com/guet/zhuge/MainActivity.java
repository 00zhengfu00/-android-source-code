package com.guet.zhuge;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity implements TextWatcher{

	private AutoCompleteTextView autoview;
	private AutoTextViewAdapter adapter;
	
	private static final String[] AUTO_EMAILS = {"@163.com", "@sina.com", "@qq.com", "@126.com", "@gmail.com", "@apple.com"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		autoview = (AutoCompleteTextView) findViewById(R.id.auto_tv);
		adapter = new AutoTextViewAdapter(this);
		autoview.setAdapter(adapter);
		autoview.setThreshold(1);//����1���ַ�ʱ�Ϳ�ʼ��⣬Ĭ��Ϊ2��
		autoview.addTextChangedListener(this);//����autoview�ı仯
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		String input = s.toString();  
        adapter.mList.clear();  
        autoAddEmails(input);
        adapter.notifyDataSetChanged();  
        autoview.showDropDown();  
	}
	
	/**
	 * �Զ���������б�
	 * @param input
	 */
	private void autoAddEmails(String input) {
		System.out.println("input-->" + input);
		String autoEmail = "";
        if (input.length() > 0) {  
            for (int i = 0; i < AUTO_EMAILS.length; ++i) {  
            	if(input.contains("@")) {//������@����ʼ����
            		String filter = input.substring(input.indexOf("@") + 1 , input.length());//��ȡ�����������������롰@��֮������ݹ��˳���������������
            		System.out.println("filter-->" + filter);
            		if(AUTO_EMAILS[i].contains(filter)) {//���Ϲ�������
            			autoEmail = input.substring(0, input.indexOf("@")) + AUTO_EMAILS[i];//�û����롰@��֮ǰ�����ݼ����Զ��������ݼ�Ϊ���Ľ��
            			adapter.mList.add(autoEmail);
            		}
            	}else {
            		autoEmail = input + AUTO_EMAILS[i];
            		adapter.mList.add(autoEmail);
            	}
            }  
        } 
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	

}
