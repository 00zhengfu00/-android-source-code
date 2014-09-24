package com.zhg.client;

import com.zhg.client.service.MusicPlayerService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//���ñ��������ɼ�
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//����ȫ����ʾ
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		//ʵ����imageview
		ImageView ivSplash = (ImageView)findViewById(R.id.ivSplash);
		//ʵ������������
		Animation anim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
		//��imageview���������붯��
		ivSplash.startAnimation(anim);
		
		//�����ļ�����
		anim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				Intent intent = new Intent(SplashActivity.this,MusicPlayerService.class);
				SplashActivity.this.startService(intent);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				//���������ʱ������סactivity���ر�����
				Intent intent = new Intent(SplashActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
