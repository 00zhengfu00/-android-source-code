package com.javen.share;

import java.io.UnsupportedEncodingException;
import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * ��demo�Ƿ�΢�ŵĶ�ά����Ƭ ����google�Ķ�ά����һ����Դ����Ŀ����Ҫ����һ����ά��ܼ� �����������ǽ�ͼƬ���ά���ϣ���ȻͼƬ����̫��
 * ��Ҫ��Ȼ��ά�����������
 */
public class QRCodeActivity extends Activity {
    // ͼƬ��ȵ�һ��
	private static final int IMAGE_HALFWIDTH = 20;
	// ��ʾ��ά��ͼƬ
	private ImageView imageview;
	// ���뵽��ά�������ͼƬ����
	private Bitmap mBitmap;
	// ��Ҫ��ͼͼƬ�Ĵ�С �����趨Ϊ40*40
	int[] pixels = new int[2*IMAGE_HALFWIDTH * 2*IMAGE_HALFWIDTH];
	
	private static final String CACHE_FILE = "/MOGUCache";
	private static final String filename ="erweima.png";
	private String s = "����Javen  QQ��572839485 ";
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
        // �������
		imageview = new ImageView(this);
        // ������Ҫ�����ͼƬ����
		mBitmap = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.ic_launcher)).getBitmap();
		// ����ͼƬ
		Matrix m = new Matrix();
		float sx = (float) 2*IMAGE_HALFWIDTH / mBitmap.getWidth();
		float sy = (float) 2*IMAGE_HALFWIDTH / mBitmap.getHeight();
		m.setScale(sx, sy);
		// ���¹���һ��40*40��ͼƬ
		mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
				mBitmap.getHeight(), m, false);

		try {
			
			Bitmap bitmap=cretaeBitmap(new String(s.getBytes(),
					"ISO-8859-1"));
			
			//����ͼƬ �����������һ�ź�
			//saveBmpToSd(bitmap);  
			imageview.setImageBitmap(bitmap);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setContentView(imageview);
		
		
	}

	/**
	 * ���ɶ�ά��
	 * 
	 * @param �ַ���
	 * @return Bitmap
	 * @throws WriterException
	 */
	public Bitmap cretaeBitmap(String str) throws WriterException {
		// ���ɶ�ά����,����ʱָ����С,��Ҫ������ͼƬ�Ժ��ٽ�������,������ģ������ʶ��ʧ��
		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, 300, 300);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		// ��ά����תΪһά��������,Ҳ����һֱ��������
		int halfW = width / 2;
		int halfH = height / 2;
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH && y > halfH - IMAGE_HALFWIDTH
						&& y < halfH + IMAGE_HALFWIDTH) {
					pixels[y * width + x] = mBitmap.getPixel(x - halfW + IMAGE_HALFWIDTH, y
							- halfH + IMAGE_HALFWIDTH);
				} else {
					if (matrix.get(x, y)) {
						pixels[y * width + x] = 0xff000000;
					}
				}

			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		// ͨ��������������bitmap
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

		return bitmap;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 1, "����ͼƬ");
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			// ��ӵ�sdcard   ���봥���¼��ſ������   ����Ҳ��һ�ź�
			imageview.setDrawingCacheEnabled(true);
						Bitmap bitmap = imageview.getDrawingCache();
						Log.e("aaa", (bitmap == null) + "");
						ContentResolver cr = this.getContentResolver();
						MediaStore.Images.Media.insertImage(cr, bitmap, "myPhotoesWall",
								"this is a PhotoesWall");
						Toast.makeText(this, "ͼƬ����ɹ�", 1).show();
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
    
   /* private void saveBmpToSd(Bitmap bm) {
        if (bm == null) {
            Log.w(TAG, " trying to savenull bitmap");
            return;
        }
         //�ж�sdcard�ϵĿռ�
        if (FREE_SD_SPACE_NEEDED_TO_CACHE >freeSpaceOnSd()) {
            Log.w(TAG, "Low free space onsd, do not cache");
            return;
        }
       
        
        String dir =isCacheFileIsExit();
        File file = new File(dir +"/" + filename);
        try {
            file.createNewFile();
            OutputStream outStream = new FileOutputStream(file);
           bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            Log.i(TAG, "Image saved tosd");
        } catch (FileNotFoundException e) {
            Log.w(TAG,"FileNotFoundException");
        } catch (IOException e) {
            Log.w(TAG,"IOException");
        }
    }*/
    
   /* 
	 * �жϻ����ļ����Ƿ������������������ļ���·��������������½��ļ��в������ļ���·��
	 
	private String isCacheFileIsExit() {
		String filePath="";
		String rootpath = "";
		
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			rootpath = Environment.getExternalStorageDirectory().toString();
		}
		filePath = rootpath + CACHE_FILE;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return filePath;
	}*/
	
	
		//���������ά�����
	  @Override
	    public void onWindowFocusChanged(boolean hasFocus) {
	        // TODO Auto-generated method stub
	        super.onWindowFocusChanged(hasFocus);
	        ScreenShot.shoot(this);
	    }
}
