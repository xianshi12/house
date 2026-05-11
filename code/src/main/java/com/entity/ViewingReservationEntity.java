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
 * 看房预约
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * 
 */
@TableName("viewing_reservation")
public class ViewingReservationEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ViewingReservationEntity() {
		
	}
	
	public ViewingReservationEntity(T t) {
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
	 * 出售价格
	 */
					
	private Integer salePrice;
	
	/**
	 * 面积
	 */
					
	private String floorArea;
	
	/**
	 * 联系电话
	 */
					
	private String contactPhone;
	
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
	 * 预约时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = DateJsonDeserializer.class)
	@DateTimeFormat 		
	private Date appointmentTime;
	
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
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@JsonDeserialize(using = DateJsonDeserializer.class)
	@DateTimeFormat 		
	private Date registerTime;
	
	/**
	 * 是否审核
	 */
					
	private String auditStatus;
	
	/**
	 * 审核回复
	 */
					
	private String auditReply;
	
	
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
	 * 设置：联系电话
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getContactPhone() {
		return contactPhone;
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
	 * 设置：预约时间
	 */
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	/**
	 * 获取：预约时间
	 */
	public Date getAppointmentTime() {
		return appointmentTime;
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
	 * 设置：是否审核
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：是否审核
	 */
	public String getAuditStatus() {
		return auditStatus;
	}
	/**
	 * 设置：审核回复
	 */
	public void setAuditReply(String auditReply) {
		this.auditReply = auditReply;
	}
	/**
	 * 获取：审核回复
	 */
	public String getAuditReply() {
		return auditReply;
	}

	public String getFangwumingcheng() { return propertyName; }
	public void setFangwumingcheng(String fangwumingcheng) { this.propertyName = fangwumingcheng; }
	public String getFangwuleixing() { return houseType; }
	public void setFangwuleixing(String fangwuleixing) { this.houseType = fangwuleixing; }
	public String getFangwutupian() { return propertyImage; }
	public void setFangwutupian(String fangwutupian) { this.propertyImage = fangwutupian; }
	public Integer getChushoujiage() { return salePrice; }
	public void setChushoujiage(Integer chushoujiage) { this.salePrice = chushoujiage; }
	public String getMianji() { return floorArea; }
	public void setMianji(String mianji) { this.floorArea = mianji; }
	public String getLianxidianhua() { return contactPhone; }
	public void setLianxidianhua(String lianxidianhua) { this.contactPhone = lianxidianhua; }
	public String getWeizhi() { return address; }
	public void setWeizhi(String weizhi) { this.address = weizhi; }
	public String getKaifashanghao() { return developerCode; }
	public void setKaifashanghao(String kaifashanghao) { this.developerCode = kaifashanghao; }
	public String getKaifashangming() { return developerName; }
	public void setKaifashangming(String kaifashangming) { this.developerName = kaifashangming; }
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = DateJsonDeserializer.class)
	public Date getYuyueshijian() { return appointmentTime; }
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = DateJsonDeserializer.class)
	public void setYuyueshijian(Date yuyueshijian) { this.appointmentTime = yuyueshijian; }
	public String getZhanghao() { return account; }
	public void setZhanghao(String zhanghao) { this.account = zhanghao; }
	public String getXingming() { return fullName; }
	public void setXingming(String xingming) { this.fullName = xingming; }
	public String getShouji() { return phone; }
	public void setShouji(String shouji) { this.phone = shouji; }
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@JsonDeserialize(using = DateJsonDeserializer.class)
	public Date getDengjishijian() { return registerTime; }
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@JsonDeserialize(using = DateJsonDeserializer.class)
	public void setDengjishijian(Date dengjishijian) { this.registerTime = dengjishijian; }
	public String getSfsh() { return auditStatus; }
	public void setSfsh(String sfsh) { this.auditStatus = sfsh; }
	public String getShhf() { return auditReply; }
	public void setShhf(String shhf) { this.auditReply = shhf; }

}
