package com.miage.zak.meteo.data;

import org.json.JSONException;
import org.json.JSONObject;

public class Hour {
    private String ICON;
    private String CONDITION;
    private String CONDITION_KEY;
    private float TMP2m;
    private float DPT2m;
    private String WNDCHILL2m = null;
    private String HUMIDEX = null;
    private float RH2m;
    private float PRMSL;
    private float APCPsfc;
    private float WNDSPD10m;
    private float WNDGUST10m;
    private float WNDDIR10m;
    private String WNDDIRCARD10;
    private float ISSNOW;
    private String HCDC;
    private String MCDC;
    private String LCDC;
    private float HGT0C;
    private float KINDEX;
    private String CAPE180_0;
    private float CIN180_0;


    // Getter Methods

    public String getICON() {
        return ICON;
    }

    public String getCONDITION() {
        return CONDITION;
    }

    public String getCONDITION_KEY() {
        return CONDITION_KEY;
    }

    public float getTMP2m() {
        return TMP2m;
    }

    public float getDPT2m() {
        return DPT2m;
    }

    public String getWNDCHILL2m() {
        return WNDCHILL2m;
    }

    public String getHUMIDEX() {
        return HUMIDEX;
    }

    public float getRH2m() {
        return RH2m;
    }

    public float getPRMSL() {
        return PRMSL;
    }

    public float getAPCPsfc() {
        return APCPsfc;
    }

    public float getWNDSPD10m() {
        return WNDSPD10m;
    }

    public float getWNDGUST10m() {
        return WNDGUST10m;
    }

    public float getWNDDIR10m() {
        return WNDDIR10m;
    }

    public String getWNDDIRCARD10() {
        return WNDDIRCARD10;
    }

    public float getISSNOW() {
        return ISSNOW;
    }

    public String getHCDC() {
        return HCDC;
    }

    public String getMCDC() {
        return MCDC;
    }

    public String getLCDC() {
        return LCDC;
    }

    public float getHGT0C() {
        return HGT0C;
    }

    public float getKINDEX() {
        return KINDEX;
    }

    public String getCAPE180_0() {
        return CAPE180_0;
    }

    public float getCIN180_0() {
        return CIN180_0;
    }

    // Setter Methods

    public void setICON(String ICON) {
        this.ICON = ICON;
    }

    public void setCONDITION(String CONDITION) {
        this.CONDITION = CONDITION;
    }

    public void setCONDITION_KEY(String CONDITION_KEY) {
        this.CONDITION_KEY = CONDITION_KEY;
    }

    public void setTMP2m(float TMP2m) {
        this.TMP2m = TMP2m;
    }

    public void setDPT2m(float DPT2m) {
        this.DPT2m = DPT2m;
    }

    public void setWNDCHILL2m(String WNDCHILL2m) {
        this.WNDCHILL2m = WNDCHILL2m;
    }

    public void setHUMIDEX(String HUMIDEX) {
        this.HUMIDEX = HUMIDEX;
    }

    public void setRH2m(float RH2m) {
        this.RH2m = RH2m;
    }

    public void setPRMSL(float PRMSL) {
        this.PRMSL = PRMSL;
    }

    public void setAPCPsfc(float APCPsfc) {
        this.APCPsfc = APCPsfc;
    }

    public void setWNDSPD10m(float WNDSPD10m) {
        this.WNDSPD10m = WNDSPD10m;
    }

    public void setWNDGUST10m(float WNDGUST10m) {
        this.WNDGUST10m = WNDGUST10m;
    }

    public void setWNDDIR10m(float WNDDIR10m) {
        this.WNDDIR10m = WNDDIR10m;
    }

    public void setWNDDIRCARD10(String WNDDIRCARD10) {
        this.WNDDIRCARD10 = WNDDIRCARD10;
    }

    public void setISSNOW(float ISSNOW) {
        this.ISSNOW = ISSNOW;
    }

    public void setHCDC(String HCDC) {
        this.HCDC = HCDC;
    }

    public void setMCDC(String MCDC) {
        this.MCDC = MCDC;
    }

    public void setLCDC(String LCDC) {
        this.LCDC = LCDC;
    }

    public void setHGT0C(float HGT0C) {
        this.HGT0C = HGT0C;
    }

    public void setKINDEX(float KINDEX) {
        this.KINDEX = KINDEX;
    }

    public void setCAPE180_0(String CAPE180_0) {
        this.CAPE180_0 = CAPE180_0;
    }

    public void setCIN180_0(float CIN180_0) {
        this.CIN180_0 = CIN180_0;
    }

    public Hour(JSONObject object) throws JSONException {

        ICON = object.getString("ICON");
        CONDITION = object.getString("CONDITION");
        CONDITION_KEY = object.getString("CONDITION_KEY");
        TMP2m = object.getLong("TMP2m");
        DPT2m = object.getLong("DPT2m");
        WNDCHILL2m = object.getString("WNDCHILL2m");
        HUMIDEX = object.getString("HUMIDEX");
        RH2m = object.getLong("RH2m");
        PRMSL = object.getLong("PRMSL");
        APCPsfc = object.getLong("APCPsfc");
        WNDSPD10m = object.getLong("WNDSPD10m");
        WNDGUST10m = object.getLong("WNDGUST10m");
        WNDDIR10m = object.getLong("WNDDIR10m");
        WNDDIRCARD10 = object.getString("WNDDIRCARD10");
        ISSNOW = object.getLong("ISSNOW");
        HCDC = object.getString("HCDC");
        MCDC = object.getString("MCDC");
        LCDC = object.getString("LCDC");
        HGT0C = object.getLong("HGT0C");
        KINDEX = object.getLong("KINDEX");
        CAPE180_0 = object.getString("CAPE180_0");
        CIN180_0 = object.getLong("CIN180_0");

    }
}

