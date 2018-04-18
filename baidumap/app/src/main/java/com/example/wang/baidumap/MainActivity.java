package com.example.wang.baidumap;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

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
                break;
            case R.id.id_maker_overlay:
                initMark();

                break;
            case R.id.id_duobianxing:
                LatLng pt1 = new LatLng(40.115,  116.404);
                LatLng pt2 = new LatLng(39.915,  116.104);
                LatLng pt3 = new LatLng(39.715,  116.304);
                LatLng pt4 = new LatLng(39.715,  116.504);
                LatLng pt5 = new LatLng(39.915,  116.704);
                List<LatLng> pts = new ArrayList<LatLng>();
                pts.add(pt1);  pts.add(pt2);  pts.add(pt3);
                pts.add(pt4);  pts.add(pt5);
                // 构建用户绘制多边形的Option对象
                OverlayOptions polygonOption = new PolygonOptions()
                        .points(pts).stroke(new Stroke(5, 0xAA00FF00))
                        .fillColor(0xAAFFFF00);
                // 在地图上添加多边形Option，用于显示
                baiduMap.addOverlay(polygonOption);

                break;
            case R.id.id_zhexian:
                // 构造折线点坐标
                List<LatLng> points = new ArrayList<LatLng>();
                points.add(new LatLng(39.965,116.404));
                points.add(new LatLng(39.925,116.454));
                points.add(new LatLng(39.955,116.494));
                points.add(new LatLng(39.905,116.554));
                points.add(new LatLng(39.965,116.604));
// 构建分段颜色索引数组
                List<Integer> colors = new ArrayList<>();
                colors.add(Integer.valueOf(Color.BLUE));
                colors.add(Integer.valueOf(Color.RED));
                colors.add(Integer.valueOf(Color.YELLOW));
                colors.add(Integer.valueOf(Color.GREEN));
                OverlayOptions polyline = new PolylineOptions().width(20)
                        .colorsValues(colors).points(points);
// 添加在地图中
                baiduMap.addOverlay(polyline);

                break;

            case R.id.id_ground:
                LatLng ptt1 = new LatLng(39.919404, 116.398035);
                LatLng ptt2 = new LatLng(39.92964, 116.408527);

                LatLngBounds bounds = new LatLngBounds.Builder().include(ptt1)
                        .include(ptt2).build();
                // 定义Ground显示的图片
                BitmapDescriptor bdGround = BitmapDescriptorFactory
                        .fromResource(R.drawable.gugong);
// 定义Ground覆盖物选项
                OverlayOptions ooGround = new GroundOverlayOptions()
                        .positionFromBounds(bounds)
                        .image(bdGround)
                        .transparency(0.8f);

// 在地图中添加Ground覆盖物
                baiduMap.addOverlay(ooGround);
                break;
            case R.id.id_info:

                break;

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

    private Marker marker;

    //地图标注
    private void initMark() {
        //定义Maker坐标点
        LatLng point = new LatLng(39.915, 116.404);
//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.marker);
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions options = new MarkerOptions()
                .position(point)  //设置marker的位置
                .icon(bitmap)  //设置marker图标
                .zIndex(9)  //设置marker所在层级
                .draggable(true);  //设置手势拖拽
        //在地图上添加Marker，并显示

        marker = (Marker) (baiduMap.addOverlay(options));
        marker.setTitle("天安门");
        //调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听

        baiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
                //拖拽中
                Log.e("MAKER","拖拽中");
            }
            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
                final LatLng latLng = marker.getPosition();
                        Toast.makeText(MainActivity.this,
                                latLng.toString(),
                                Toast.LENGTH_SHORT)
                                .show();
                Log.e("MAKER","拖拽结束");
            }
            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
                Log.e("MAKER","开始拖拽");
            }
        });
                        //设置标志物的监听
                baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        final LatLng latLng = marker.getPosition();
                        Toast.makeText(MainActivity.this,
                                latLng.toString(),
                                Toast.LENGTH_SHORT)
                                .show();
                        TextView textView = new TextView(MainActivity.this);
                        textView.setText(marker.getTitle());
                        textView.setTextColor(Color.WHITE);
                        textView.setTextSize(30);
                        textView.setBackgroundResource(R.drawable.popup);
                        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
                                .fromView(textView);
                        LatLng poi = new LatLng(marker.getPosition().latitude
                                , marker.getPosition().longitude);
                        InfoWindow infoWindow = new InfoWindow(bitmapDescriptor
                                , poi, -80, new InfoWindow.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick() {

                                baiduMap.hideInfoWindow();
                            }
                        });
                        baiduMap.showInfoWindow(infoWindow);
                        return false;

                    }
                });


    }

}
