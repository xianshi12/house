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

@TableName("developer")
public class DeveloperEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public DeveloperEntity() { }
    public DeveloperEntity(T t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }

    @TableId(type = IdType.AUTO)
    private Long id;
    private String developerCode;
    private String password;
    private String developerName;
    private String gender;
    private String contactPhone;
    private String avatar;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDeveloperCode() { return developerCode; }
    public void setDeveloperCode(String developerCode) { this.developerCode = developerCode; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDeveloperName() { return developerName; }
    public void setDeveloperName(String developerName) { this.developerName = developerName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }

    public String getKaifashanghao() { return developerCode; }
    public void setKaifashanghao(String kaifashanghao) { this.developerCode = kaifashanghao; }
    public String getMima() { return password; }
    public void setMima(String mima) { this.password = mima; }
    public String getKaifashangming() { return developerName; }
    public void setKaifashangming(String kaifashangming) { this.developerName = kaifashangming; }
    public String getXingbie() { return gender; }
    public void setXingbie(String xingbie) { this.gender = xingbie; }
    public String getLianxidianhua() { return contactPhone; }
    public void setLianxidianhua(String lianxidianhua) { this.contactPhone = lianxidianhua; }
    public String getTouxiang() { return avatar; }
    public void setTouxiang(String touxiang) { this.avatar = touxiang; }
}
