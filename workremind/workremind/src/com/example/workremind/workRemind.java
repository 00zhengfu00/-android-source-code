package com.example.workremind;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;









import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.YuvImage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class workRemind extends Activity {
	private int intervalday = -1 ; //��������
	private int dayflags = -1 ;//�ж��Ƿ��ǵ���
	private Calendar currentcalendar = null ;//����
	private Calendar currenttime = null ;//ʱ��
	private int pendingid = -1 ;//��¼�Ǹ���״̬ʱ���id
	private Button remindTime = null ;
	private EditText edRemindTime = null ;
	private CheckBox remindOnly = null ;
	private CheckBox remindMore = null ;
	private Button   serviceStart = null ;
	private Button   serviceEnd = null ;
	private Button btnremindTimeSecond = null ;
	private EditText etRemindTimeSecond = null ; 
	private int counts = -1 ; //remindcounts         1 only     0  more 
	private	String temp =""; 
	private int flags = -1 ; //service flag           1 ����  0 ����
	private Button  btnSave = null ;//����
	private myDBhelper helper = null ;
	private String sql = null ;
	private List<myremind> list = new ArrayList<myremind>() ;
	private EditText etTitle = null ;
	private EditText etNote = null ;
	private EditText etTime =null ;
	private String lastRemindTime ="" ; //��¼ǰһ��
	private AlertDialog.Builder builder = null ;
	private Dialog dialog = null ;
	private myremind remind = null ;
	private Handler myHandler = null;
	private String  updateFlag =" " ;//���±�־
	private int     selectId = -1;
	private String olddate = null ;//������
	private String oldtime = null ;//��ʱ��
	private Calendar mycalendar = Calendar.getInstance() ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		this.etRemindTimeSecond =(EditText)super.findViewById(R.id.etRemindSecond);
		this.btnremindTimeSecond = (Button) super.findViewById(R.id.btnRemindTimeSecond) ;
		this.etTitle = (EditText)super.findViewById(R.id.edTitle);
		this.etNote = (EditText)super.findViewById(R.id.edNote) ;
		this.etTime = (EditText)super.findViewById(R.id.etRemindTime) ;
		this.serviceStart = (Button)super.findViewById(R.id.btnStart) ;
		this.serviceEnd = (Button)super.findViewById(R.id.btnEnd) ;
		this.remindOnly = (CheckBox) super.findViewById(R.id.cbRemindOnly) ;
		this.remindMore = (CheckBox) super.findViewById(R.id.cbRemindRepeat) ;
		this.remindTime = (Button) super.findViewById(R.id.btnRemindTime) ;
		this.edRemindTime = (EditText)super.findViewById(R.id.etRemindTime) ;
		this.btnSave = (Button) super.findViewById(R.id.btn_new_save) ;
		this.remindTime.setOnClickListener(new OnClickListenerimpl()) ;
		
		helper = new myDBhelper(this) ;
		updateFlag =getIntent().getStringExtra("updateflag") ;
		selectId = getIntent().getIntExtra("updateid", -1) ;	
		Log.i("****selectid",""+selectId) ;
		updateInit() ;
		
		this.btnremindTimeSecond.setOnClickListener(new OnClickListenerimpltime()) ;
		myHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				dialog.dismiss() ;
				Toast.makeText(workRemind.this, "�����ɣ�", Toast.LENGTH_LONG).show() ;
				workRemind.this.setResult(RESULT_OK);
				workRemind.this.finish();
				
			}
			
		};
//		
		
		
		
		this.btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(updateFlag.equals("1")){//��ɸ���
					Log.i("******updateid******", selectId+"") ;
					String sql = "select * from remind where _id="+selectId ;
					Cursor cursor = helper.select(sql);
					if(cursor.getCount()>0){
						cursor.moveToFirst() ;
						olddate = cursor.getString(cursor.getColumnIndex("RemindTime"));
						oldtime = cursor.getString(cursor.getColumnIndex("RemindTimeSecond"));
					}
					
					if(workRemind.this.etTitle.getText().toString().length()==0){
						
						Toast.makeText(workRemind.this, "���ⲻ��Ϊ�գ�", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					if(workRemind.this.etNote.getText().toString().length()==0){
						Toast.makeText(workRemind.this, "��ע���ݲ���Ϊ�գ�", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					
					
					if(workRemind.this.etTime.getText().toString().length()==0){
						Toast.makeText(workRemind.this, "���ڲ���Ϊ�գ�", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					if(workRemind.this.etRemindTimeSecond.getText().toString().length()==0){
						Toast.makeText(workRemind.this, "ʱ�䲻��Ϊ�գ�", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					
					if(counts == -1){
						
						Toast.makeText(workRemind.this, "���������ѷ�ʽ��", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					if(flags == -1){
						
						Toast.makeText(workRemind.this, "��ȷ�Ϸ���ʽ��", Toast.LENGTH_SHORT).show() ;
						return ;
					}	
					if(checkupdate(workRemind.this.etTime.getText().toString(),workRemind.this.etRemindTimeSecond.getText().toString())){
						
						Toast.makeText(workRemind.this, "��ʱ������й�������!!", Toast.LENGTH_SHORT).show() ;
						return ;
						
					}
					
					
					
			//����
					String sql1 = "update remind set  Title='"+workRemind.this.etTitle.getText().toString()+"', Note='"+workRemind.this.etNote.getText().toString()+"',RemindTime='"+ workRemind.this.etTime.getText().toString()+"',RemindTimeSecond='"+workRemind.this.etRemindTimeSecond.getText().toString()+"',RemindCounts="+counts+",ServiceFlag="+flags+"  where _id="+selectId ;
					myDBhelper db = new myDBhelper(workRemind.this) ;
					db.update(sql1) ;
					
					if(flags==0){//����id�ж�ȡ���ĸ�����
					      Intent intent = new Intent(workRemind.this, CallAlarm.class);  
			                PendingIntent pendingIntent = PendingIntent.getBroadcast(  
			                        workRemind.this, selectId, intent, 0);  
			                AlarmManager am;  
			                /* ��ȡ���ӹ����ʵ�� */  
			                am = (AlarmManager) getSystemService(ALARM_SERVICE);  
			                /* ȡ�� */  
			                am.cancel(pendingIntent);  
					
					}
					//��������
					if(flags==1){
						
						if(counts==1){
							Intent intent = new Intent(workRemind.this,CallAlarm.class) ;
							intent.putExtra("title", workRemind.this.etTitle.getText().toString());
							PendingIntent sender = PendingIntent.getBroadcast(workRemind.this, selectId, intent, 0) ;
							AlarmManager am ; 
							am = (AlarmManager)getSystemService(Context.ALARM_SERVICE) ;
							am.set(AlarmManager.RTC_WAKEUP, mycalendar.getTimeInMillis(), sender) ;
							Log.i("******pendingid",pendingid+"");
						}else{
							Intent intent = new Intent(workRemind.this,CallAlarm.class) ;
							intent.putExtra("title", workRemind.this.etTitle.getText().toString());
							PendingIntent sender = PendingIntent.getBroadcast(workRemind.this, selectId, intent, 0) ;
							AlarmManager am ; 
							am = (AlarmManager)getSystemService(Context.ALARM_SERVICE) ;
							am.setRepeating(AlarmManager.RTC_WAKEUP,  mycalendar.getTimeInMillis(), 24*60*60*1000, sender) ;
						}
						
					}else{
						
						
						
					}
					//�㲥
					//�жϡ�������
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					workRemind.this.setResult(RESULT_OK);
					workRemind.this.finish() ;
					
					
				}else{
			
				 if(workRemind.this.etTitle.getText().toString().length()==0){
						
						Toast.makeText(workRemind.this, "���ⲻ��Ϊ�գ�", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					if(workRemind.this.etNote.getText().toString().length()==0){
						Toast.makeText(workRemind.this, "��ע���ݲ���Ϊ�գ�", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					
					
					if(workRemind.this.etTime.getText().toString().length()==0){
						Toast.makeText(workRemind.this, "���ڲ���Ϊ�գ�", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					if(workRemind.this.etRemindTimeSecond.getText().toString().length()==0){
						Toast.makeText(workRemind.this, "ʱ�䲻��Ϊ�գ�", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					
					if(counts == -1){
						
						Toast.makeText(workRemind.this, "���������ѷ�ʽ��", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					if(flags == -1){
						
						Toast.makeText(workRemind.this, "��ȷ�Ϸ���ʽ��", Toast.LENGTH_SHORT).show() ;
						return ;
					}	
					//��ͬ����ʱ��Ĳ����ظ�
					if(check(workRemind.this.etTime.getText().toString(),workRemind.this.etRemindTimeSecond.getText().toString())){
						Toast.makeText(workRemind.this, "��ʱ������й������ѣ�", Toast.LENGTH_SHORT).show() ;
						return ;
						
					}
					//�ж�ǰһ�� ����һ�εĲ����ظ�
					if(check(temp.toString(),workRemind.this.etRemindTimeSecond.getText().toString())){
						
						Toast.makeText(workRemind.this, "��ʱ������й�������!!", Toast.LENGTH_SHORT).show() ;
						return ;
						
					}
					//�ж������ظ���
					if(checkTimeMore(0, workRemind.this.etRemindTimeSecond.getText().toString())){
						Toast.makeText(workRemind.this, "��ʱ������й�������!!", Toast.LENGTH_SHORT).show() ;
						return ;
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					LayoutInflater inflater = LayoutInflater.from(workRemind.this) ;
					
					View view = inflater.inflate(R.layout.progress_dialog, null);
					builder = new AlertDialog.Builder(workRemind.this);
					dialog = builder.setView(view).create();
					dialog.show();
				
				new Thread(
						new Runnable() {
							
							
							@Override
							public void run() {
									
												
												
												
												
				
							sql = "insert into remind (Title,Note,RemindTime,RemindTimeSecond,RemindCounts,ServiceFlag) values('"
									+ workRemind.this.etTitle.getText()
											.toString()
									+ "','"
									+ workRemind.this.etNote.getText()
											.toString()
									+ "','"
									+ workRemind.this.etTime.getText()
											.toString()
									+ "','"
									+ workRemind.this.etRemindTimeSecond
											.getText().toString()
									+ "',"
									+ counts + "," + flags + ")";	
				
			helper.insert(sql) ;	
			
			//ͨ��id�ֱ治ͬ��pendingintent
			
		
			
				
			
				
				
				
				
				
				if(!updateFlag.equals("1")){
		
				
				String sql = "select * from remind" ;
				myDBhelper myhelper = new  myDBhelper(workRemind.this) ;
				Cursor cursor = myhelper.select(sql) ;
				if(cursor.getCount()>0){
					cursor.moveToLast();
					Log.i("******lastid",cursor.getInt(cursor.getColumnIndex("_id"))+"") ;
					pendingid = cursor.getInt(cursor.getColumnIndex("_id")) ;
				}
				
				}
				
		
				if(flags==1){
					
					if(counts==1){
						Intent intent = new Intent(workRemind.this,CallAlarm.class) ;
						intent.putExtra("title", workRemind.this.etTitle.getText().toString());
						PendingIntent sender = PendingIntent.getBroadcast(workRemind.this, pendingid, intent, 0) ;
						AlarmManager am ; 
						am = (AlarmManager)getSystemService(Context.ALARM_SERVICE) ;
						am.set(AlarmManager.RTC_WAKEUP, mycalendar.getTimeInMillis(), sender) ;
						Log.i("******pendingid",pendingid+"");
					}else{
						Intent intent = new Intent(workRemind.this,CallAlarm.class) ;
						intent.putExtra("title", workRemind.this.etTitle.getText().toString());
						PendingIntent sender = PendingIntent.getBroadcast(workRemind.this, pendingid, intent, 0) ;
						AlarmManager am ; 
						am = (AlarmManager)getSystemService(Context.ALARM_SERVICE) ;
						am.setRepeating(AlarmManager.RTC_WAKEUP,  mycalendar.getTimeInMillis(), 24*60*60*1000, sender) ;
					}
					
				}else{
					
					
					
				}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			myHandler.sendMessage(new Message()) ;
								
								
					//Looper.loop() ;			
							}
						}
						
						
						
						).start() ;
				
				
			
				//*******************************������ʱ����
				Log.i("date******",Long.parseLong("201306121224")+"") ;//���Գ�����
				
				
				
				
				
				
				

	
			}
				
			}
		}) ;
		

		this.serviceStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(updateFlag.equals("1")){
					Toast.makeText(workRemind.this	, "���·��������������������ڼ�ʱ�䣬�����޷�����ʱ��Ŷ��", Toast.LENGTH_SHORT).show() ;
					workRemind.this.flags = 1 ;
					return ;
				}
				workRemind.this.flags = 1 ;
				Toast.makeText(workRemind.this	, "��������", Toast.LENGTH_SHORT).show() ;
			}
		}) ;
		
		
		this.serviceEnd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				workRemind.this.flags = 0 ;
				Toast.makeText(workRemind.this	, "����رգ�", Toast.LENGTH_SHORT).show() ;
			}
		});
		
		this.remindOnly.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(remindMore.isChecked()){
					
					remindMore.setChecked(false) ;
					
				}
				workRemind.this.counts= 1  ; //remindcounts = 1 ;
			}
		}) ;
		
		
		this.remindMore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(remindOnly.isChecked()){
					
					remindOnly.setChecked(false) ;
					
				}
				
				workRemind.this.counts = 0  ;  //remindcounts = 0 ;
				
			}
		}) ;
		
		
		
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	private class OnClickListenerimpl implements OnClickListener{//����������

		@Override
		public void onClick(View v) {
			
			final Calendar c = Calendar.getInstance();
			currentcalendar = Calendar.getInstance();
			int mYear = c.get(Calendar.YEAR); //��ȡ��ǰ���
			int mMonth = c.get(Calendar.MONTH);//��ȡ��ǰ�·�
		    int mDay = c.get(Calendar.DAY_OF_MONTH);//��ȡ��ǰ��

  
            Dialog dialog=new DatePickerDialog(workRemind.this, new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					String time = "" ;
					time = time + year ;
				//	Calculator.this.sday=year+"-"+(monthOfYear+1)+"-"+dayOfMonth;//������ʼ����	
					if(monthOfYear<9){
						
						time=time+"-0"+(monthOfYear+1) ;
					}else{
						time = time + "-"+(monthOfYear+1) ;
					}
					if(dayOfMonth<10){
						time = time + "-0"+dayOfMonth ;
						
					}else{
						time = time + "-"+dayOfMonth ;
					}
					
					//temp.set(year, monthOfYear, dayOfMonth-1);//��ȡѡ��ʱ��
//					int mYear1 = temp.get(Calendar.YEAR); //��ȡ��ǰ���
//					int mMonth1 = temp.get(Calendar.MONTH);//��ȡ��ǰ�·�
//				    int mDay1 = temp.get(Calendar.DAY_OF_MONTH);//��ȡ��ǰ��
//					//private String lastRemindTime = null ; //��¼ǰһ��
				    lastRemindTime=lastRemindTime+year ;//����ǰһ��ʱ��
				    if(monthOfYear<9){
				    	
				    	lastRemindTime=lastRemindTime+"-0"+(monthOfYear+1);
				    }else{
				    	
				    	lastRemindTime=lastRemindTime+"-"+(monthOfYear+1);
				    }
				    
				    
				    if(dayOfMonth<11){
				    	lastRemindTime=lastRemindTime+"-0"+(dayOfMonth-1);
				    }else{
				    	
				    	lastRemindTime=lastRemindTime+"-"+(dayOfMonth-1);
				    }
				    Log.i("**********display*********", currentcalendar.get(Calendar.YEAR)+"-"+year+currentcalendar.get(Calendar.MONTH)+"-"+monthOfYear+currentcalendar.get(Calendar.DAY_OF_MONTH)+"-"+dayOfMonth);
				  
			
				    
				    
				    
				    
				    
				    
				    
				
				    temp=lastRemindTime.substring(0, 10).toString();
				    lastRemindTime="" ;
				    c.getTimeInMillis();
				    c.set(Calendar.YEAR, year) ;
					c.set(Calendar.MONTH, monthOfYear);
					c.set(Calendar.DAY_OF_MONTH,dayOfMonth) ;
					Log.i("*********",currentcalendar.getTimeInMillis()+"    "+c.getTimeInMillis()) ;
				    if(currentcalendar.getTimeInMillis()>c.getTimeInMillis()){
				    	Toast.makeText(workRemind.this, "����С�ڵ�ǰ���ڣ�", Toast.LENGTH_SHORT).show() ;
				    	return ;
			    }
				    if(currentcalendar.get(Calendar.YEAR)==year && currentcalendar.get(Calendar.MONTH)==monthOfYear && currentcalendar.get(Calendar.DAY_OF_MONTH)==dayOfMonth){
				    	dayflags = 1 ; //1��ʾΪ�ǵ���
				    	Log.i("***isday**",currentcalendar.get(Calendar.DAY_OF_MONTH)+"-"+dayOfMonth+"");
				    }
				    //�ǵ����
				    mycalendar.set(Calendar.YEAR, year);
				    mycalendar.set(Calendar.MONTH,monthOfYear);
				    mycalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth) ;
				    Log.i("*********dayofmonth**�ǵ���",dayOfMonth+"");
				    //temp.add(Calendar.)
					workRemind.this.edRemindTime.setText(time);
					
					
					
					
					
					
					
					
					
					
					
					
//					workRemind.this.edRemindTime.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
   
				}
			},mYear, mMonth, mDay);
            dialog.show();
		
			
			
		}
		
	}
	private class OnClickListenerimpltime implements OnClickListener{//Сʱ �ͷ�������

		@Override
		public void onClick(View v) {
			final Calendar c = Calendar.getInstance();   
			currenttime = Calendar.getInstance() ;
			final int mHour = c.get(Calendar.HOUR_OF_DAY);  
		    final int mMinute = c.get(Calendar.MINUTE);  
		    Dialog dialog = new TimePickerDialog(workRemind.this, new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					String time = null;
					if( hourOfDay < 10){
						time = "0"+hourOfDay ;
					}else{
						
						time = hourOfDay+"" ;
					}
					if(minute<10){
						time=time+":0"+minute;
					}else{
						
						time = time +":"+minute ;
					}
				workRemind.this.etRemindTimeSecond.setText(time) ;
				//mycalendar.setTimeInMillis(System.currentTimeMillis()) ;
				mycalendar.set(Calendar.HOUR_OF_DAY, hourOfDay) ;
				mycalendar.set(Calendar.MINUTE, minute);
				mycalendar.set(Calendar.SECOND,0) ;
				mycalendar.set(Calendar.MILLISECOND, 0) ;
				Log.i("****day_of_year",mycalendar.get(Calendar.DAY_OF_YEAR)+"");
				if(mycalendar.getTimeInMillis()<currenttime.getTimeInMillis()){
					mycalendar.set(Calendar.DAY_OF_YEAR, mycalendar.get(Calendar.DAY_OF_YEAR)+1) ;
					Log.i("****day_of_year****",mycalendar.get(Calendar.DAY_OF_YEAR)+"");	
					
					if(dayflags==1){//���ʱ��С�ڵ�ǰʱ�䣬������ѡ�е��죬������ʾ���˳��һ��
						Calendar calendar = Calendar.getInstance() ;
						calendar.add(Calendar.DATE, 1);
						mycalendar = calendar ;
						mycalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
						mycalendar.set(Calendar.MINUTE, minute);//�������ӵ�ʱ�� Ҫ���¼���
						Log.i("****����ʱ��***",mycalendar.get(Calendar.MONTH)+1+"--"+mycalendar.get(Calendar.DAY_OF_MONTH)+"---"+mycalendar.get(Calendar.MINUTE));
						int y = calendar.get(Calendar.YEAR) ;
						int m= calendar.get(Calendar.MONTH) ;
						int d = calendar.get(Calendar.DAY_OF_MONTH) ;
						String time1 = "" ;
						time1 = time1 + y ;
					//	Calculator.this.sday=year+"-"+(monthOfYear+1)+"-"+dayOfMonth;//������ʼ����	
						if(m<9){
							
							time1=time1+"-0"+(m+1) ;
						}else{
							time1 = time1 + "-"+(m+1) ;
						}
						if(d<10){
							time1 = time1 + "-0"+d ;
							
						}else{
							time1 = time1 + "-"+d ;
						}
						
						workRemind.this.edRemindTime.setText(time1);
						
						
					}
					
					
				}
				
				//..............
				mycalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				mycalendar.set(Calendar.MINUTE, minute);//�������ӷǵ���ʱ��
				Log.i("****�ǵ����ʱ������***",mycalendar.get(Calendar.MONTH)+1+"--"+mycalendar.get(Calendar.DAY_OF_MONTH)+"---"+mycalendar.get(Calendar.MINUTE));
				
				
				Log.i("*******selecttime", mycalendar.getTimeInMillis()+"") ;
				
				}
			}, mHour, mMinute, true	) ;
		    dialog.show();
			
		}
		
	}
	
	private boolean check(String remindTime,String remindTimeSecond){
		Boolean isFlag = false ;
		String sql = "select * from remind where RemindTime='"+remindTime+"' and RemindTimeSecond='"+remindTimeSecond+"' and ServiceFlag="+1 ;//�ж�ʱ����ͬ������Ϊ����
		Cursor cursor = helper.select(sql);
		if(cursor.getCount()>0){
			//Toast.makeText(workRemind.this, "��ʱ������й������ѣ�", Toast.LENGTH_SHORT).show() ;
			isFlag = true ;
			
		}
		
		
		
		return isFlag ;
		
	}
	private boolean checkupdate(String remindTime,String remindTimeSecond){
		Boolean isFlag = false ;
		if(remindTime.equals(olddate)&&remindTimeSecond.equals(oldtime)){
			
			return isFlag;
		}
		String sql = "select * from remind where RemindTime='"+remindTime+"' and RemindTimeSecond='"+remindTimeSecond+"'" ;//�ж�ʱ����ͬ������Ϊ����
		Cursor cursor = helper.select(sql);
		if(cursor.getCount()>0){

			isFlag = true ;
			
		}
		
		
		
		return isFlag ;
		
	}
	private boolean checkTimeMore(int counts,String remindTimeSecond){
		Boolean isFlag = false ;
		String sql = "select * from remind where RemindTimeSecond='"+remindTimeSecond+"' and RemindCounts="+counts+" and ServiceFlag="+1 ;//�ж�ʱ����ͬ������Ϊ����
		Cursor cursor = helper.select(sql);
		if(cursor.getCount()>0){
			//Toast.makeText(workRemind.this, "��ʱ������й������ѣ�", Toast.LENGTH_SHORT).show() ;
			isFlag = true ;
			
		}
		
		
		
		return isFlag ;
		
		
		
		
	}
	
	
	
	
	
	public void updateInit(){
		if(updateFlag.equals("1")){
			
			String sql = "select * from remind where _id="+selectId ;
			myDBhelper myhelper = new  myDBhelper(this) ;
			Cursor cursor = myhelper.select(sql) ;
			if(cursor.getCount()>0){
				cursor.moveToFirst();
				remind = new myremind()  ;
				remind.setTitle(cursor.getString(cursor.getColumnIndex("Title"))) ;
				remind.setRemindDate(cursor.getString(cursor.getColumnIndex("RemindTime")));
				remind.setRemindTime(cursor.getString(cursor.getColumnIndex("RemindTimeSecond")));
				remind.setserviceflag(cursor.getString(cursor.getColumnIndex("ServiceFlag"))) ;
				
				
				remind.setId(cursor.getInt(cursor.getColumnIndex("_id"))) ;
				remind.setRemindCounts(cursor.getInt(cursor.getColumnIndex("RemindCounts")));
				remind.setNote(cursor.getString(cursor.getColumnIndex("Note"))) ;
				workRemind.this.etTitle.setText(remind.getTitle()) ;
				workRemind.this.etNote.setText(remind.getNote()) ;
				workRemind.this.etTime.setText(remind.getRemindDate()) ;
				workRemind.this.etRemindTimeSecond.setText(remind.getRemindTime()) ;
				if(remind.getRemindCounts()==1){
					
					workRemind.this.remindOnly.setChecked(true);
					counts = 1 ;
					
				}else{
					workRemind.this.remindMore.setChecked(true) ;
					counts = 0 ;
				}
				
				if(remind.getserviceflag().equals("1")){
					flags = 1 ;
				}else{
					flags =  0;
				}
				
			}
			
			
	
	 }
	
	}
	
	
	
}
