package com.example.mapdemo;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKLocationManager;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.OverlayItem;

public class Near_MapActivity extends MapActivity {

	static MapView mMapView = null;
	static View mPopView = null; // ���markʱ����������View

	MyLocationOverlay mLocationOverlay = null; // ��λͼ��

	private LocationListener loc_listener;
	private MapController mMapController;
	private double longitude;
	private double latitude;
	private MKLocationManager mLocationManager;//locationģ��
	
	private List<LocationInfo> infos;
	
	List<Overlay> mapOverlays;
	SimpleItemizedOverlay itemizedOverlay;
    Drawable marker;
    
    GeoPoint myPoint;
    GeoPoint myLastPoint;//��¼λ���Ƿ��б仯
    int i=0;
    BaseApplication app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.near_map);
		// ��ʼ����ͼ
		app = (BaseApplication) this.getApplication();

		if (app.mBMapMan == null) {
			app.mBMapMan = new BMapManager(getApplication());
			app.mBMapMan.init(app.mStrKey,
					new BaseApplication.MyGeneralListener());
		}
		app.mBMapMan.start();
		// ���ʹ�õ�ͼSDK�����ʼ����ͼActivity
		super.initMapActivity(app.mBMapMan);

		// ��ʼ��Locationģ��
		mLocationManager = app.mBMapMan.getLocationManager();
		// ͨ��enableProvider��disableProvider������ѡ��λ��Provider
		mLocationManager.enableProvider(MKLocationManager.MK_NETWORK_PROVIDER);
		mLocationManager.disableProvider(MKLocationManager.MK_GPS_PROVIDER);
		mLocationManager.setNoitifyInternal(5, 2);
		
		// ����mapView���������
		mMapView = (MapView) findViewById(R.id.bmapView);
		mMapView.setBuiltInZoomControls(true);// ��ʾ�����������Ź���
		// ���������Ŷ���������Ҳ��ʾoverlay,Ĭ��Ϊ������
		mMapView.setDrawOverlayWhenZooming(true);
		mMapView.setAlwaysDrawnWithCacheEnabled(false);
		mMapView.setAnimationCacheEnabled(false);
		
		//��ȡ��ͼ������
	    mMapController = mMapView.getController(); // �õ�mMapView�Ŀ���Ȩ,�����������ƺ�����ƽ�ƺ�����
	    mMapController.setZoom(15); // ���õ�ͼzoom����   
	    //��ȡ������ͼ�� 
	    mapOverlays = mMapView.getOverlays();
	    
	    marker = getResources().getDrawable(R.drawable.icon_marka);
	    //��ʼ��
	    itemizedOverlay = new SimpleItemizedOverlay(marker, mMapView);
		// ��Ӷ�λͼ��
		mLocationOverlay = new MyLocationOverlay(Near_MapActivity.this,mMapView);
		// ע��GPSλ�ø��µ��¼�,�õ�ͼ��ʵʱ��ʾ��ǰλ��    
		mLocationOverlay.enableMyLocation();    
		
		//��ӵ�ǰλ�õĶ�λͼ�㸲����
		mapOverlays.add(mLocationOverlay);
        
		// ע�ᶨλ�¼�
		loc_listener = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				if (location != null) {
					i++;
					longitude = location.getLongitude();
					latitude = location.getLatitude();
					Log.i("life", "onLocationChanged=longitude:"+longitude+",latitude:"+latitude+",i="+i);
					myLastPoint = new GeoPoint((int) (latitude * 1e6),
							(int) (longitude * 1e6));
					if(i==1){
						myPoint = myLastPoint;
						mMapController.animateTo(myPoint);
					}else{
						if(myLastPoint!=myPoint){
							myPoint = myLastPoint;
							mMapController.animateTo(myPoint);
						}
					}
				   //����ҵ�λ��
					CustomOverlayItem item = new CustomOverlayItem(marker,
							Near_MapActivity.this, myLastPoint, "�ҵ�λ��", "", false);
					
					mapOverlays.add(item);
				}
			}
			
		};

		
		setLocationInfo();
		
		//������������ﵽͼ����
		GeoPoint p1 = new GeoPoint((int) (infos.get(0).getLatitude() * 1E6), (int) (infos.get(0).getLongtitude() * 1E6));
		OverlayItem overlayItem = new OverlayItem(p1, "������������", "(����λ����Խ����ӭѡ����)");
		itemizedOverlay.addOverlay(overlayItem);
		
		GeoPoint p2 = new GeoPoint((int) (infos.get(1).getLatitude() * 1E6), (int) (infos.get(0).getLongtitude() * 1E6));
		OverlayItem overlayItem1 = new OverlayItem(p2, "˳�±̹�԰��Է", "(����λ����Խ����ӭѡ����)");
		itemizedOverlay.addOverlay(overlayItem1);
		
		GeoPoint p3 = new GeoPoint((int) (infos.get(2).getLatitude() * 1E6), (int) (infos.get(0).getLongtitude() * 1E6));
		OverlayItem overlayItem2 = new OverlayItem(p3, "�����ž��ֻ�԰", "(����λ����Խ����ӭѡ����)");
		itemizedOverlay.addOverlay(overlayItem2);
		
		mapOverlays.add(itemizedOverlay);
		
       if (savedInstanceState == null) {
			final MapController mc = mMapView.getController();
			if(myPoint!=null){
				mc.animateTo(myPoint);
			}
			mc.setZoom(15);
			
		} else {
			int focused;
			focused = savedInstanceState.getInt("focused_1", -1);
			if (focused >= 0) {
				itemizedOverlay.setFocus(itemizedOverlay.getItem(focused));
			}
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
		if (itemizedOverlay.getFocus() != null) outState.putInt("focused_1", itemizedOverlay.getLastFocusedIndex());
		super.onSaveInstanceState(outState);
	
	}
	
	private void setLocationInfo() {
		infos = new ArrayList<LocationInfo>();

		LocationInfo info1 = new LocationInfo();
		info1.setLatitude(23.12);
		info1.setLongtitude(113.38333);
		infos.add(info1);

		LocationInfo info2 = new LocationInfo();
		info2.setLatitude(23.13);
		info2.setLongtitude(113.38333);
		infos.add(info2);

		LocationInfo info3 = new LocationInfo();
		info3.setLatitude(23.14);
		info3.setLongtitude(113.38333);
		infos.add(info3);

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ����MyLocationOverlay���õ�����ȷ���Ƿ��ڵ�ͼ����ʾ��ǰλ��
	 */
	@Override
	protected boolean isLocationDisplayed() {
		return mLocationOverlay.isMyLocationEnabled();
//		return false;
	}

	@Override
	protected void onPause() {
		if (null == app){
			app = (BaseApplication) this.getApplication();
		}
		if (app.mBMapMan != null) {
			mLocationManager.removeUpdates(loc_listener);
			mLocationOverlay.disableMyLocation();
			mLocationOverlay.disableCompass(); // �ر�ָ����
			app.mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (null == app){
			app = (BaseApplication) this.getApplication();
		}
		if (app.mBMapMan != null) {
			mLocationManager.requestLocationUpdates(loc_listener);
			app.mBMapMan.start();
			mLocationOverlay.enableMyLocation();//���ö�λ
			mLocationOverlay.enableCompass(); // ��ָ����
		}
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (app.mBMapMan != null) {
			app.mBMapMan.destroy();
			app.mBMapMan = null;
		}
		super.onDestroy();
	}

}
