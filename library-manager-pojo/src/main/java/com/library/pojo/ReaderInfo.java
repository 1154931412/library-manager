package com.library.pojo;

import java.util.Date;

public class ReaderInfo {
    private Integer readId;

    private String name;

    private String sex;

    private Date birth;

    private String address;

    private String telcode;

    public Integer getReadId() {
        return readId;
    }

    public void setReadId(Integer readId) {
        this.readId = readId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelcode() {
        return telcode;
    }

    public void setTelcode(String telcode) {
        this.telcode = telcode == null ? null : telcode.trim();
    }

	@Override
	public String toString() {
		return "ReaderInfo [readId=" + readId + ", name=" + name + ", sex=" + sex + ", birth=" + birth + ", address="
				+ address + ", telcode=" + telcode + "]";
	}
    
}