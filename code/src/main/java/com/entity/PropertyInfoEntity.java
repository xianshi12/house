package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;
import java.lang.reflect.InvocationTargetException;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("property_info")
public class PropertyInfoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public PropertyInfoEntity() { }
    public PropertyInfoEntity(T t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }

    @TableId(type = IdType.AUTO)
    private Long id;
    private String propertyName;
    private String houseType;
    private String propertyImage;
    private String floor;
    private String rooms;
    private String district;
    private Integer salePrice;
    private String details;
    private String address;
    private String floorArea;
    private String developerCode;
    private String developerName;
    private String saleContract;
    private String contactPhone;
    private Date clicktime;
    private Integer discussnum;
    private Integer storeupnum;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }
    public String getHouseType() { return houseType; }
    public void setHouseType(String houseType) { this.houseType = houseType; }
    public String getPropertyImage() { return propertyImage; }
    public void setPropertyImage(String propertyImage) { this.propertyImage = propertyImage; }
    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }
    public String getRooms() { return rooms; }
    public void setRooms(String rooms) { this.rooms = rooms; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public Integer getSalePrice() { return salePrice; }
    public void setSalePrice(Integer salePrice) { this.salePrice = salePrice; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getFloorArea() { return floorArea; }
    public void setFloorArea(String floorArea) { this.floorArea = floorArea; }
    public String getDeveloperCode() { return developerCode; }
    public void setDeveloperCode(String developerCode) { this.developerCode = developerCode; }
    public String getDeveloperName() { return developerName; }
    public void setDeveloperName(String developerName) { this.developerName = developerName; }
    public String getSaleContract() { return saleContract; }
    public void setSaleContract(String saleContract) { this.saleContract = saleContract; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public Date getClicktime() { return clicktime; }
    public void setClicktime(Date clicktime) { this.clicktime = clicktime; }
    public Integer getDiscussnum() { return discussnum; }
    public void setDiscussnum(Integer discussnum) { this.discussnum = discussnum; }
    public Integer getStoreupnum() { return storeupnum; }
    public void setStoreupnum(Integer storeupnum) { this.storeupnum = storeupnum; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }

    public String getFangwumingcheng() { return propertyName; }
    public void setFangwumingcheng(String fangwumingcheng) { this.propertyName = fangwumingcheng; }
    public String getFangwuleixing() { return houseType; }
    public void setFangwuleixing(String fangwuleixing) { this.houseType = fangwuleixing; }
    public String getFangwutupian() { return propertyImage; }
    public void setFangwutupian(String fangwutupian) { this.propertyImage = fangwutupian; }
    public String getLouceng() { return floor; }
    public void setLouceng(String louceng) { this.floor = louceng; }
    public String getTingshi() { return rooms; }
    public void setTingshi(String tingshi) { this.rooms = tingshi; }
    public String getDiduan() { return district; }
    public void setDiduan(String diduan) { this.district = diduan; }
    public Integer getChushoujiage() { return salePrice; }
    public void setChushoujiage(Integer chushoujiage) { this.salePrice = chushoujiage; }
    public String getXiangqing() { return details; }
    public void setXiangqing(String xiangqing) { this.details = xiangqing; }
    public String getWeizhi() { return address; }
    public void setWeizhi(String weizhi) { this.address = weizhi; }
    public String getMianji() { return floorArea; }
    public void setMianji(String mianji) { this.floorArea = mianji; }
    public String getKaifashanghao() { return developerCode; }
    public void setKaifashanghao(String kaifashanghao) { this.developerCode = kaifashanghao; }
    public String getKaifashangming() { return developerName; }
    public void setKaifashangming(String kaifashangming) { this.developerName = kaifashangming; }
    public String getChushouhetong() { return saleContract; }
    public void setChushouhetong(String chushouhetong) { this.saleContract = chushouhetong; }
    public String getLianxidianhua() { return contactPhone; }
    public void setLianxidianhua(String lianxidianhua) { this.contactPhone = lianxidianhua; }
    public Integer getClicknum() { return discussnum; }
    public void setClicknum(Integer clicknum) { this.discussnum = clicknum; }
}
