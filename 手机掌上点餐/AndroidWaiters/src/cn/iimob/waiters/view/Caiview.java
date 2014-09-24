package cn.iimob.waiters.view;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.iimob.waiters.R;
import cn.iimob.waiters.db.DBHelpe;
import cn.iimob.waiters.db.DB_Price;

public class Caiview extends Activity implements View.OnClickListener,OnTouchListener
{
 private TextView tv_price,tv_name,tv_jiesao;
 private Button btn_yuding,btn_caiview_return;
 private Animation animation;
 private Intent intent;
 private String str_intent;
 private ImageButton image;
 private String str_name,str_price,str_number;
 private RelativeLayout relative;
 private EditText edit;
 private boolean isfirst=false;
 public static int number=0;
 public boolean cup=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.caiview);
		this.intview();
	    animation=AnimationUtils.loadAnimation(Caiview.this, R.anim.anim_click_info);
		intent = getIntent();
		Bundle b = intent.getBundleExtra("cainame");
		str_intent = b.getString("name");
		this.whichkindofvegetable();
		System.out.println(str_intent);
		
		
	}
	
	
	
	private void whichkindofvegetable()
	{
		if(str_intent.equals("songshuyu"))
		 { tv_price.setText("��66Ԫ/��");
		   image.setBackgroundResource(R.drawable.songshuyu);
		   tv_name.setText("�����~");
		   tv_jiesao.setText("�����������ƶ�����,ͨ���Ի��㡢���㡢���������Ϊԭ�ϣ�" +
		   		"����ȥ�ۡ��������࣬����ˮϴ�������ظ������µ�������ͷ���£�Ȼ���ٴ���򢴦�µ���" +
		   		"����ͷ���뵶���õ����ģ������������⣬�����ز�ϸ�̣���β�������͹�ը�����ɫ���ٽ��Ͻ�֭ƴ�̶��ɵ���ʳ��" +
		   		"ɫ�����ޣ��������㣬�����ʿڡ�");
		  }
		
		if(str_intent.equals("huangjinjuan"))
		{ tv_price.setText("��72Ԫ/��");
		   tv_name.setText("�S�����");
		   image.setBackgroundResource(R.drawable.huangjinjuan);
		   tv_jiesao.setText("��Һ���ȣ������ĵ�Ƥƽ��Ư�� ����ϸһ�㣬�������ĵ����п�ƽ��," +
		   		"����ѡ�����ַ��߷��ݵķ���,��������ˬ��ڡ�");
		   }
		
		if(str_intent.equals("dongguamianqizhuxie"))
		{  tv_price.setText("��86Ԫ/��");
		   tv_name.setText("����з");
		   image.setBackgroundResource(R.drawable.dongguamianqizhuxie);
		   tv_jiesao.setText("зζ�������зḻά���غ�17�ְ����ᣬ���������ᡢ������ĺ�������һ�㹽�࣬�������������������ߣ����������͵��̴����ر�����ʵ�壨�������ϲ��֣�����ȡ����ж���������Գɷݡ�" +
		   		             "����������ǡ����ʡ���������ǿ���������ٽ������γɿ������ɷ����ӻ�˥�ϡ����ݵȵȡ�");
		   }
		if(str_intent.equals("xiandanguahuan"))
		{  tv_price.setText("��71Ԫ/��");
		image.setBackgroundResource(R.drawable.xiandanguahuan);
		   tv_name.setText("�y���ϭh");
		   tv_jiesao.setText("�׹�Ҳ��Խ�ϡ��˹ϣ��������Ȼ�̵������ⶾ�������ȼ��ڵ������߲ˡ��ð׹����̵�����˿��һ�����������λ�̵��������ʪ���ǳ��ʺ����������Ȳ�ˮ�ȵù��ࡢ��ʳ��θ�ڻ�С�㲻˳����ʿʳ��" +
		   		             "��3-4�˷��ϲˣ�10���̵���1����˿��1���׹ϣ�1������2Ƭˮ��6��");
		   }
		
		if(str_intent.equals("youyu"))
		{  tv_price.setText("��45Ԫ/��");
		   tv_name.setText("�������~");
		   image.setBackgroundResource(R.drawable.ganbianyouyu);
		   tv_jiesao.setText("�����պõ�������ؾ�ˮ�֣����⽦�ͣ�����������ը��Ӳ���̳����͡��������ͣ��н���Ǻ�������ǡ��Ÿɺ����������ۣ����ٷ��������ҿڣ��������������㣨���ɱ�ֽ�����ʱ��ζ���������ͳ���װ�̡� ");
		   }
		
		if(str_intent.equals("xiaren"))
		{  tv_price.setText("��50Ԫ/��");
		image.setBackgroundResource(R.drawable.qingchaoxiaren);
		   tv_name.setText("�峴�r��");
		   tv_jiesao.setText("�峴Ϻ�ʣ��հ�˵�һ�֣�����Ϻ��Ϊ��Ҫ���ϳ��ƵĲ��ȡ�" +
		   		"�峴Ϻ�������嵭ˬ�ڣ��������������׽��ˣ�������ʳ�ͻ�ӭ���������Ͽ��������ϲ�ö��仯������������Ϻ�ʺ��зḻ�ļء��⡢þ���׵ȿ����ʼ�����ά���أ�������Ϻ���к������Ϻ���أ���Ŀǰ���ֵ���ǿ��һ�ֿ����������Ƕ�����ʮ�������ʳ�ġ�");
		   }
		
		if(str_intent.equals("zhetou"))
		{  tv_price.setText("��26Ԫ/��");
		image.setBackgroundResource(R.drawable.zherou);
		   tv_name.setText("������ͷ");
		   tv_jiesao.setText("�廨��300�ˣ�����100�ˡ�����1�������2�ˣ���3�ˡ��Ͼ�15�ˡ�������3�ˡ�ζ��3�ˡ����10�ˡ���ĩ3��");
		   }
		if(str_intent.equals("qingchaoyoucai"))
		{  tv_price.setText("��18Ԫ/��");
		image.setBackgroundResource(R.drawable.qingchaoyoucai);
		   tv_name.setText("�峴�Ͳ�");
		   tv_jiesao.setText("�������������׽��ˣ�������ʳ�ͻ�ӭ���������Ͽ��������ϲ�ö��仯����������");
		   }
		if(str_intent.equals("danhuangniangao"))
		{  tv_price.setText("��25Ԫ/��");
		image.setBackgroundResource(R.drawable.danhuangniangao);
		   tv_name.setText("�������");
		   tv_jiesao.setText("�������������׽��ˣ�������ʳ�ͻ�ӭ���������Ͽ��������ϲ�ö��仯����������");
		   }
		if(str_intent.equals("xiaojidunmogu"))
		{  tv_price.setText("��85Ԫ/��");
		image.setBackgroundResource(R.drawable.xiaojidunmogu);
		   tv_name.setText("С����Ģ��");
		   tv_jiesao.setText("�������������׽��ˣ�������ʳ�ͻ�ӭ���������Ͽ��������ϲ�ö��仯����������");
		   }
		if(str_intent.equals("shuiguohui"))
		{  tv_price.setText("��22Ԫ/��");
		image.setBackgroundResource(R.drawable.shuiguohui);
		   tv_name.setText("ˮ����");
		   tv_jiesao.setText("�������������׽��ˣ�������ʳ�ͻ�ӭ���������Ͽ��������ϲ�ö��仯����������");
		   }
		if(str_intent.equals("xianchaohuamo"))
		{  tv_price.setText("��45Ԫ/��");
		image.setBackgroundResource(R.drawable.xianchaohuamo);
		   tv_name.setText("�ʳ���Ģ");
		   tv_jiesao.setText("�������������׽��ˣ�������ʳ�ͻ�ӭ���������Ͽ��������ϲ�ö��仯����������");
		   }
		if(str_intent.equals("xianchengzhi"))
		{  tv_price.setText("��19Ԫ/��");
		  image.setBackgroundResource(R.drawable.juzhizhi);
		   tv_name.setText("�ʳ�֭");
		   tv_jiesao.setText("�������������׽��ˣ�������ʳ�ͻ�ӭ���������Ͽ��������ϲ�ö��仯����������");
		   cup=true;
		   }
		if(str_intent.equals("putaojiu"))
		{  tv_price.setText("��35Ԫ/��");
		  image.setBackgroundResource(R.drawable.putaojiu);
		   tv_name.setText("���Ѿ�");
		   tv_jiesao.setText("�������������׽��ˣ�������ʳ�ͻ�ӭ���������Ͽ��������ϲ�ö��仯����������");
		   cup=true;
		   }
		if(str_intent.equals("laqiezi"))
		{  tv_price.setText("��15Ԫ/��");
		  image.setBackgroundResource(R.drawable.laqiezi);
		   tv_name.setText("������");
		   tv_jiesao.setText("�������������׽��ˣ�������ʳ�ͻ�ӭ���������Ͽ��������ϲ�ö��仯����������");
		   }
		
	}
	
	
	
	
	private void intview()
	{
	 tv_price=(TextView)findViewById(R.id.tv_price);
	 tv_name=(TextView)findViewById(R.id.tv_name);
	 tv_jiesao=(TextView)findViewById(R.id.tv_jiesao);
	 btn_yuding=(Button)findViewById(R.id.btn_yuding);
	 image=(ImageButton)findViewById(R.id.imageButton1);
	// linearlayout=(LinearLayout)findViewById(R.id.liearlayout);
	 relative=(RelativeLayout)findViewById(R.id.ralative);
	  edit=(EditText)findViewById(R.id.edit);
	// linearlayout.setOnTouchListener(this);
	 relative.setOnTouchListener(this);
	 btn_caiview_return=(Button)findViewById(R.id.btn_caiview_return);
 	 btn_yuding.setOnClickListener(this);
 	 btn_caiview_return.setOnClickListener(this);
 	 
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_yuding :
				btn_yuding.startAnimation(animation);
				animation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationEnd(Animation arg0) {
						str_name=tv_name.getText().toString();
						str_price=tv_price.getText().toString();
						str_number=edit.getText().toString();
						ContentValues values = new ContentValues(); 
						ContentValues pricevalues = new ContentValues(); 
						 DBHelpe helper = new DBHelpe(getApplicationContext());  
						 DB_Price price=new DB_Price(getApplicationContext());
						if(!str_number.equals("")&&isfirst==false)
						{ values.put("name",str_name );
						  values.put("price",str_price );
						  if(cup==false)
						  { values.put("number",str_number+"��" );}
						  else
						  {values.put("number",str_number+"��" );
						       cup=false;}
						 if(str_price.equals("��66Ԫ/��"))
						 {pricevalues.put("price", "66");
						 }
						 if(str_price.equals("��71Ԫ/��"))
						 {pricevalues.put("price", "72");
						 }
						 if(str_price.equals("��86Ԫ/��"))
						 {pricevalues.put("price", "86");
						 }
						 if(str_price.equals("��72Ԫ/��"))
						 {pricevalues.put("price", "72");
						 }
						 if(str_price.equals("��45Ԫ/��"))
						 {pricevalues.put("price", "45");
						 }
						 if(str_price.equals("��50Ԫ/��"))
						 {pricevalues.put("price", "50");
						 }
						 if(str_price.equals("��26Ԫ/��"))
						 {pricevalues.put("price", "26");
						 }
						 if(str_price.equals("��18Ԫ/��"))
						 {pricevalues.put("price", "18");
						 }
						 if(str_price.equals("��25Ԫ/��"))
						 {pricevalues.put("price", "25");
						 }
						 if(str_price.equals("��85Ԫ/��"))
						 {pricevalues.put("price", "85");
						 }
						 if(str_price.equals("��22Ԫ/��"))
						 {pricevalues.put("price", "22");
						 }
						 if(str_price.equals("��45Ԫ/��"))
						 {pricevalues.put("price", "45");
						 }
						 if(str_price.equals("��19Ԫ/��"))
						 {pricevalues.put("price", "19");
						 }
						 if(str_price.equals("��35Ԫ/��"))
						 {pricevalues.put("price", "35");
						 }
						 if(str_price.equals("��15Ԫ/��"))
						 {pricevalues.put("price", "15");
						 }
						 
						  pricevalues.put("number", str_number);
						  helper.insert(values);  
						  price.insert(pricevalues);
						  helper.close();
						  btn_yuding.setBackgroundResource(R.drawable.have_ordered);
						  btn_yuding.setText("�Թ���");
						  isfirst=true;
						  Caiview.number++;
  		                  Toast.makeText(Caiview.this, "�Ѿ����빺�ﳵ", Toast.LENGTH_SHORT).show();
  		                  edit.setText("");
  		                  conTrol();
  		                }
						else if(isfirst==true)
						{}
						else 
						{Toast.makeText(Caiview.this, "�������͵�����", Toast.LENGTH_SHORT).show();}// TODO Auto-generated method stub
						
					}
				});
				
				break;
			case R.id.btn_caiview_return :
				btn_caiview_return.startAnimation(animation);
				animation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationEnd(Animation arg0) {
						Intent intent=new Intent(Caiview.this,CallbyeTabActivity.class);
						startActivity(intent);// TODO Auto-generated method stub
						 finish();
					}
				});
				
				break;

			default :
				break;
		}// TODO Auto-generated method stub
		
	}



	@Override
	public boolean onTouch(View v, MotionEvent event) {
		conTrol();// TODO Auto-generated method stub
		return false;
	}
	
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode==KeyEvent.KEYCODE_BACK){
//			return true;
//		}// TODO Auto-generated method stub
//			return false;
//		}
	
	private void conTrol() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
	}

}
