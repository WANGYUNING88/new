package com.example.wang.baidumap;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.UiSettings;

public class MainActivity extends AppCompatActivity {
    private MapView aMapView = null;
    private BaiduMap baiduMap;
    private UiSettings uiSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);



        // 获取地图控件引用
        aMapView = (MapView) findViewById(R.id.bmapView);
        //获取地图控制器
        baiduMap = aMapView.getMap();

        //设置比例尺
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo((float) 15.0);
        baiduMap.setMapStatus(mapStatusUpdate);

        //设置手势操作
        uiSettings = baiduMap.getUiSettings();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_putong:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.id_weixing:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.id_kongbai:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
                break;
            case R.id.id_on_jiaotong:
                if (baiduMap.isTrafficEnabled()){
                    baiduMap.setTrafficEnabled(false);
                    item.setTitle("打开交通图层");
                }else  {
                    baiduMap.setTrafficEnabled(true);
                    item.setTitle("关闭交通图层");
                }
                break;
            case R.id.id_on_reli:
                if (baiduMap.isBaiduHeatMapEnabled()){
                    baiduMap.setBaiduHeatMapEnabled(false);
                    item.setTitle("打开热力图层");
                }else {
                    baiduMap.setBaiduHeatMapEnabled(true);
                    item.setTitle("关闭热力图层");
                }
                break;
            case R.id.id_on_shoushi:
                if (uiSettings.isScrollGesturesEnabled()){
                    uiSettings.setAllGesturesEnabled(false);
                    item.setTitle("打开全部手势");
                }else {
                    uiSettings.setAllGesturesEnabled(true);
                    item.setTitle("关闭全部手势");
                }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        aMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        aMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        aMapView.onPause();
    }

}
