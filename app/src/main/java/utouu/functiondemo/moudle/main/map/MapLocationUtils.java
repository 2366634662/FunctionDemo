package utouu.functiondemo.moudle.main.map;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;

import utouu.functiondemo.bean.LocationBean;

/**
 * Created by Du_Li on 2016/10/31.
 * Desc:
 */
public class MapLocationUtils {
    private static volatile MapLocationUtils mMapLocationUtils;

    private AMapLocationClientOption mLocationOption = null;

    private AMapLocationClient mlocationClient = null;

    private GetLocationDatas getLocationDatas;

    private LocationBean locationBean;

    public void setGetLocationDatas(GetLocationDatas getLocationDatas) {
        this.getLocationDatas = getLocationDatas;
    }

    private MapLocationUtils() {
    }

    public static MapLocationUtils getInstance() {
        if (mMapLocationUtils == null) {
            synchronized (MapLocationUtils.class) {
                if (mMapLocationUtils == null) {
                    mMapLocationUtils = new MapLocationUtils();
                }
            }
        }
        return mMapLocationUtils;
    }

    public MapLocationUtils regAndStartLocation(Context context) {
        mlocationClient = new AMapLocationClient(context);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
//        mlocationClient.setLocationListener(this);

        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationMode.Battery_Saving);
        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
        mLocationOption.setLocationMode(AMapLocationMode.Device_Sensors);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);

        //获取一次定位结果：
        //该方法默认为false。
//        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocation(true);


        //设置是否返回地址信息（默认返回地址信息）
//        mLocationOption.setNeedAddress(true);


        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        startLocation();
        return this;
    }

    public void startLocation() {
        mlocationClient.startLocation();
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
//                        String type = "" + aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                        String latitude = "" + aMapLocation.getLatitude();//获取纬度
//                        String longitude = "" + aMapLocation.getLongitude();//获取经度
//                        String accuracy = "" + aMapLocation.getAccuracy();//获取精度信息
//                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date date = new Date(aMapLocation.getTime());
//                        String locationdate = df.format(date);//定位时间
//
//                        String address = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                        String country = aMapLocation.getCountry();//国家信息
//                        String province = aMapLocation.getProvince();//省信息
//                        String city = aMapLocation.getCity();//城市信息
//                        String district = aMapLocation.getDistrict();//城区信息
//                        String street = aMapLocation.getStreet();//街道信息
//                        String streeNum = aMapLocation.getStreetNum();//街道门牌号信息
//                        String cityCode = aMapLocation.getCityCode();//城市编码
//                        String adCode = aMapLocation.getAdCode();//地区编码
//                        String aoiNamse = aMapLocation.getAoiName();//获取当前定位点的AOI信息
                        if (getLocationDatas != null) {
                            getLocationDatas.getLocationData(aMapLocation);
                        }

                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                        if (getLocationDatas != null) {
                            getLocationDatas.getErrorMsg("" + aMapLocation.getErrorCode(), aMapLocation.getErrorInfo());
                        }
                    }
                }
            }
        });
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        mlocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mlocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    public interface GetLocationDatas {
        void getLocationData(AMapLocation aMapLocation);

        void getErrorMsg(String errCode, String errorInfo);

    }


}
