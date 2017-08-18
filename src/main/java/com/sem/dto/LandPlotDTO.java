package com.sem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LandPlotDTO implements Serializable {
    private static final long serialVersionUID = -1100119556903358022L;

    private long koatuu;
    private int zona;
    private int kvartal;
    private int parcel;
    @JsonProperty("cadnum")
    private String cadNum;
    @JsonProperty("ownershipcode")
    private int ownershipCode;
    private String purpose;
    private String use;
    private String area;
    @JsonProperty("unit_area")
    private String unitArea;
    @JsonProperty("ownershipvalue")
    private String ownershipValue;
    @JsonProperty("id_office")
    private int officeId;
    
    public long getKoatuu() {
        return koatuu;
    }

    public void setKoatuu(long koatuu) {
        this.koatuu = koatuu;
    }

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public int getKvartal() {
        return kvartal;
    }

    public void setKvartal(int kvartal) {
        this.kvartal = kvartal;
    }

    public int getParcel() {
        return parcel;
    }

    public void setParcel(int parcel) {
        this.parcel = parcel;
    }

    public String getCadNum() {
        return cadNum;
    }

    public void setCadNum(String cadNum) {
        this.cadNum = cadNum;
    }

    public int getOwnershipCode() {
        return ownershipCode;
    }

    public void setOwnershipCode(int ownershipCode) {
        this.ownershipCode = ownershipCode;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUnitArea() {
        return unitArea;
    }

    public void setUnitArea(String unitArea) {
        this.unitArea = unitArea;
    }

    public String getOwnershipValue() {
        return ownershipValue;
    }

    public void setOwnershipValue(String ownershipValue) {
        this.ownershipValue = ownershipValue;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
}
