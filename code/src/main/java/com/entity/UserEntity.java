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

@TableName("user")
public class UserEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public UserEntity() { }
    public UserEntity(T t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }

    @TableId(type = IdType.AUTO)
    private Long id;
    private String account;
    private String password;
    private String fullName;
    private String gender;
    private String phone;
    private String idCard;
    private String avatar;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }

    public String getZhanghao() { return account; }
    public void setZhanghao(String zhanghao) { this.account = zhanghao; }
    public String getMima() { return password; }
    public void setMima(String mima) { this.password = mima; }
    public String getXingming() { return fullName; }
    public void setXingming(String xingming) { this.fullName = xingming; }
    public String getXingbie() { return gender; }
    public void setXingbie(String xingbie) { this.gender = xingbie; }
    public String getShouji() { return phone; }
    public void setShouji(String shouji) { this.phone = shouji; }
    public String getShenfenzheng() { return idCard; }
    public void setShenfenzheng(String shenfenzheng) { this.idCard = shenfenzheng; }
    public String getTouxiang() { return avatar; }
    public void setTouxiang(String touxiang) { this.avatar = touxiang; }
}
