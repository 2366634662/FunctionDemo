package utouu.functiondemo.bean;

/**
 * Created by Du_Li on 2016/10/31.
 * Desc:
 */

public class LocationBean {

    private String type;////获取当前定位结果来源，如网络定位结果，详见定位类型表
    private String latitude;//纬度
    private String longitude;//经度
    private String accuracy;//获取精度信息
    private String locationdate;//定位时间
    //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息
    private String address;
    private String country;//国家信息
    private String province;//省份信息
    private String city;//城市信息
    private String district;//城区信息
    private String street;//街道信息
    private String streetNum;//街道门牌号信息
    private String cityCode;//城市编码
    private String adCode;//地区编码
    private String aoiName;//获取当前定位点的AOI信息

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getLocationdate() {
        return locationdate;
    }

    public void setLocationdate(String locationdate) {
        this.locationdate = locationdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getAoiName() {
        return aoiName;
    }

    public void setAoiName(String aoiName) {
        this.aoiName = aoiName;
    }
}
