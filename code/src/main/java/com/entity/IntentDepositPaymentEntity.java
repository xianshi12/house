package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.utils.DateJsonDeserializer;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 意向金支付
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * 
 */
@TableName("intent_deposit_payment")
public class IntentDepositPaymentEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public IntentDepositPaymentEntity() {
		
	}
	
	public IntentDepositPaymentEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 订单编号
	 */
					
	private String orderNo;
	
	/**
	 * 房屋名称
	 */
					
	private String propertyName;
	
	/**
	 * 房屋类型
	 */
					
	private String houseType;
	
	/**
	 * 房屋图片
	 */
					
	private String propertyImage;
	
	/**
	 * 楼层
	 */
					
	private String floor;
	
	/**
	 * 出售价格
	 */
					
	private Integer salePrice;
	
	/**
	 * 意向金占比
	 */
					
	private Double intentDepositRatio;
	
	/**
	 * 意向定金
	 */
					
	private Double intentDeposit;
	
	/**
	 * 面积
	 */
					
	private String floorArea;
	
	/**
	 * 厅室
	 */
					
	private String rooms;
	
	/**
	 * 地段
	 */
					
	private String district;
	
	/**
	 * 位置
	 */
					
	private String address;
	
	/**
	 * 开发商号
	 */
					
	private String developerCode;
	
	/**
	 * 开发商名
	 */
					
	private String developerName;
	
	/**
	 * 账号
	 */
					
	private String account;
	
	/**
	 * 姓名
	 */
					
	private String fullName;
	
	/**
	 * 手机
	 */
					
	private String phone;
	
	/**
	 * 登记时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = DateJsonDeserializer.class)
	@DateTimeFormat 		
	private Date registerTime;
	
	/**
	 * 是否支付
	 */
					
	private String isPaid;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：房屋名称
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	/**
	 * 获取：房屋名称
	 */
	public String getPropertyName() {
		return propertyName;
	}
	/**
	 * 设置：房屋类型
	 */
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	/**
	 * 获取：房屋类型
	 */
	public String getHouseType() {
		return houseType;
	}
	/**
	 * 设置：房屋图片
	 */
	public void setPropertyImage(String propertyImage) {
		this.propertyImage = propertyImage;
	}
	/**
	 * 获取：房屋图片
	 */
	public String getPropertyImage() {
		return propertyImage;
	}
	/**
	 * 设置：楼层
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}
	/**
	 * 获取：楼层
	 */
	public String getFloor() {
		return floor;
	}
	/**
	 * 设置：出售价格
	 */
	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 获取：出售价格
	 */
	public Integer getSalePrice() {
		return salePrice;
	}
	/**
	 * 设置：意向金占比
	 */
	public void setIntentDepositRatio(Double intentDepositRatio) {
		this.intentDepositRatio = intentDepositRatio;
	}
	/**
	 * 获取：意向金占比
	 */
	public Double getIntentDepositRatio() {
		return intentDepositRatio;
	}
	/**
	 * 设置：意向定金
	 */
	public void setIntentDeposit(Double intentDeposit) {
		this.intentDeposit = intentDeposit;
	}
	/**
	 * 获取：意向定金
	 */
	public Double getIntentDeposit() {
		return intentDeposit;
	}
	/**
	 * 设置：面积
	 */
	public void setFloorArea(String floorArea) {
		this.floorArea = floorArea;
	}
	/**
	 * 获取：面积
	 */
	public String getFloorArea() {
		return floorArea;
	}
	/**
	 * 设置：厅室
	 */
	public void setRooms(String rooms) {
		this.rooms = rooms;
	}
	/**
	 * 获取：厅室
	 */
	public String getRooms() {
		return rooms;
	}
	/**
	 * 设置：地段
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * 获取：地段
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * 设置：位置
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：位置
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：开发商号
	 */
	public void setDeveloperCode(String developerCode) {
		this.developerCode = developerCode;
	}
	/**
	 * 获取：开发商号
	 */
	public String getDeveloperCode() {
		return developerCode;
	}
	/**
	 * 设置：开发商名
	 */
	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	/**
	 * 获取：开发商名
	 */
	public String getDeveloperName() {
		return developerName;
	}
	/**
	 * 设置：账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：账号
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置：姓名
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * 获取：姓名
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * 设置：手机
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：登记时间
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * 获取：登记时间
	 */
	public Date getRegisterTime() {
		return registerTime;
	}
	/**
	 * 设置：是否支付
	 */
	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}
	/**
	 * 获取：是否支付
	 */
	public String getIsPaid() {
		return isPaid;
	}

	public String getDingdanbianhao() { return orderNo; }
	public void setDingdanbianhao(String dingdanbianhao) { this.orderNo = dingdanbianhao; }
	public String getFangwumingcheng() { return propertyName; }
	public void setFangwumingcheng(String fangwumingcheng) { this.propertyName = fangwumingcheng; }
	public String getFangwuleixing() { return houseType; }
	public void setFangwuleixing(String fangwuleixing) { this.houseType = fangwuleixing; }
	public String getFangwutupian() { return propertyImage; }
	public void setFangwutupian(String fangwutupian) { this.propertyImage = fangwutupian; }
	public String getLouceng() { return floor; }
	public void setLouceng(String louceng) { this.floor = louceng; }
	public Integer getChushoujiage() { return salePrice; }
	public void setChushoujiage(Integer chushoujiage) { this.salePrice = chushoujiage; }
	public Double getYixiangjinzhanbi() { return intentDepositRatio; }
	public void setYixiangjinzhanbi(Double yixiangjinzhanbi) { this.intentDepositRatio = yixiangjinzhanbi; }
	public Double getYixiangdingjin() { return intentDeposit; }
	public void setYixiangdingjin(Double yixiangdingjin) { this.intentDeposit = yixiangdingjin; }
	public String getMianji() { return floorArea; }
	public void setMianji(String mianji) { this.floorArea = mianji; }
	public String getTingshi() { return rooms; }
	public void setTingshi(String tingshi) { this.rooms = tingshi; }
	public String getDiduan() { return district; }
	public void setDiduan(String diduan) { this.district = diduan; }
	public String getWeizhi() { return address; }
	public void setWeizhi(String weizhi) { this.address = weizhi; }
	public String getKaifashanghao() { return developerCode; }
	public void setKaifashanghao(String kaifashanghao) { this.developerCode = kaifashanghao; }
	public String getKaifashangming() { return developerName; }
	public void setKaifashangming(String kaifashangming) { this.developerName = kaifashangming; }
	public String getZhanghao() { return account; }
	public void setZhanghao(String zhanghao) { this.account = zhanghao; }
	public String getXingming() { return fullName; }
	public void setXingming(String xingming) { this.fullName = xingming; }
	public String getShouji() { return phone; }
	public void setShouji(String shouji) { this.phone = shouji; }
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = DateJsonDeserializer.class)
	public Date getDengjishijian() { return registerTime; }
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = DateJsonDeserializer.class)
	public void setDengjishijian(Date dengjishijian) { this.registerTime = dengjishijian; }
	public String getIspay() { return isPaid; }
	public void setIspay(String ispay) { this.isPaid = ispay; }

}
