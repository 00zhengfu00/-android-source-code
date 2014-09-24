package com.example.backstagecamera;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera; 
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback; 
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class FloatView {
	private static WindowManager wm;  
	private static WindowManager.LayoutParams params;  
	private Camera mCamera;  
    private CameraPreview mPreview ;
    private Context context;
    private ToneGenerator mTone;  
    public FloatView(Context context) {
		super();
		this.context = context;
	}

	public void mFloatView(){
    wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);   
           params = new WindowManager.LayoutParams();  
             System.out.println("����������!!!!!!!!");
             /* 
              * �������Ϊparams.type = WindowManager.LayoutParams.TYPE_PHONE; 
              * ��ô���ȼ��ή��һЩ, ������֪ͨ�����ɼ� 
              */  
           params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;  
           params.format = PixelFormat.RGBA_8888; // ����ͼƬ��ʽ��Ч��Ϊ����͸��   
             
           /* 
            * �����flags���Ե�Ч����ͬ���������� 
            * ���������ɴ������������κ��¼�,ͬʱ��Ӱ�������¼���Ӧ�� 
            * */  
           params.flags=WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL 
                                  | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE 
                                  | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE; 
//           params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL  
//                                 | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;  
           
           // �����������ĳ��ÿ�   
           params.width = 100;  
           params.height = 100;  
           mPreview = new CameraPreview(context); 
           wm.addView(mPreview, params);  
           Timer timer = new Timer(); 
           timer.schedule(new TimerTask() {
        	   int index = 0;
			@Override    
			public void run() { 
				//��һ����������Ϊnull,�Ϳ��Ծ���������
				mCamera.takePicture(shutterCallback, null, jpegCallback);  
			}
		}, 1000);		//�ӳ�1������
    } 
	 
	//������Ŀ��ż���,һ������������������ 
	private ShutterCallback shutterCallback = new ShutterCallback(){  
       public void onShutter() {    
           if(mTone == null){  
               mTone = new ToneGenerator(AudioManager.STREAM_MUSIC,ToneGenerator.MAX_VOLUME);  
           }  
           mTone.startTone(ToneGenerator.TONE_PROP_BEEP2);  
       }  
   };
   
	//�õ�jpegͼƬ���ұ���
	 private PictureCallback jpegCallback = new PictureCallback(){  
		  
	        public void onPictureTaken(byte[] data, Camera camera) {  
	            Parameters ps = camera.getParameters();  
	            if(ps.getPictureFormat() == PixelFormat.JPEG){  
	                String path = save(data);  
	                Uri uri = Uri.fromFile(new File(path));  
//	                Intent intent = new Intent();    
//	                intent.setAction("android.intent.action.VIEW");  
//	                intent.setDataAndType(uri, "image/jpeg");  
//	                startActivity(intent);  
	            }  
	        }  
	    };  
    
	    //���ñ����ַ 
	    private String save(byte[] data){  
	    	String saveDir = Environment.getExternalStorageDirectory().toString()+"/backstageCamera/image/";
	        String fileName = System.currentTimeMillis()+".jpg";  
	        try {  
	    			File dirFile = new File(saveDir);
	    			if (!dirFile.exists()) {
	    				dirFile.mkdirs(); 
	    			} 
	    			File file = new File(saveDir, fileName);
	    			if (!file.exists()) {
	    				file.createNewFile();
	    			}
	    			FileOutputStream fos = new FileOutputStream(file,true);
	    			fos.write(data);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return null;  
	        }  
	        return saveDir+fileName;  
	    }  
    
	    
	//Ԥ������CameraPreview
	@TargetApi(9)
    class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {  
  
        SurfaceHolder mHolder;  
  
        public CameraPreview(Context context) {  
            super(context);  
            System.out.println("����CameraPreview!!!!!!!!");
            mHolder = getHolder();  
            mHolder.addCallback(this);  
            //mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // 4.0+ auto   
        }  
        int cameraCount = 0; 
        public void surfaceCreated(SurfaceHolder holder) {  
        	    try {              
    	        	mCamera = Camera.open(1);  	//�ڶ��δ���ʧ��
    	        } catch (RuntimeException e) {  
    	            e.printStackTrace();  
    	            mCamera = null;  
    	        }   
        }  
  
        public void surfaceDestroyed(SurfaceHolder holder) {  
            mCamera.stopPreview();  
            mCamera.release();  
            mCamera = null;
        }   
  
        public void surfaceChanged(SurfaceHolder holder, int format, int w,  
                int h) {  
        	//�Ѿ����Surface��width��height������Camera�Ĳ���
        		System.out.println("mCamera = "+ mCamera);	//�ڶ�����null,���Ǹ�bug
        		Camera.Parameters parameters = mCamera.getParameters();
        		parameters.setPreviewSize(w, h);
        		mCamera.setParameters(parameters);
        		//��ʼԤ��
        		mCamera.startPreview();
        }     
    }  
}
