package com.rajuuu.milkdiary.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class PDFModel {
    String orderdate;
    String orderline;
    String openbalance;
    String mrngrept;
    String evngrept;
    String total;
    String closebalance;
    String Mproduct_name;
    String Mtl;
    String Mml;
    String Msl;
    String Mrate;
    String Mamount;
    String Msession;
    String Mproduct_name1;
    String Mtl1;
    String Mml1;
    String Msl1;
    String Mrate1;
    String Mamount1;
    String Msession1;
    String Eproduct_name;
    String Etl;
    String Eml;
    String Esl;
    String Erate;
    String Eamount;
    String Esession;
    String Eproduct_name1;
    String Etl1;
    String Eml1;
    String Esl1;
    String Erate1;
    String Eamount1;
    String Esession1;
    String AMTTOTAL;

    public PDFModel(String orderdate, String orderline, String openbalance, String mrngrept, String evngrept, String total, String closebalance, String mproduct_name, String mtl, String mml, String msl, String mrate, String mamount, String msession, String mproduct_name1, String mtl1, String mml1, String msl1, String mrate1, String mamount1, String msession1, String eproduct_name, String etl, String eml, String esl, String erate, String eamount, String esession, String eproduct_name1, String etl1, String eml1, String esl1, String erate1, String eamount1, String esession1, String AMTTOTAL) {
        this.orderdate = orderdate;
        this.orderline = orderline;
        this.openbalance = openbalance;
        this.mrngrept = mrngrept;
        this.evngrept = evngrept;
        this.total = total;
        this.closebalance = closebalance;
        Mproduct_name = mproduct_name;
        Mtl = mtl;
        Mml = mml;
        Msl = msl;
        Mrate = mrate;
        Mamount = mamount;
        Msession = msession;
        Mproduct_name1 = mproduct_name1;
        Mtl1 = mtl1;
        Mml1 = mml1;
        Msl1 = msl1;
        Mrate1 = mrate1;
        Mamount1 = mamount1;
        Msession1 = msession1;
        Eproduct_name = eproduct_name;
        Etl = etl;
        Eml = eml;
        Esl = esl;
        Erate = erate;
        Eamount = eamount;
        Esession = esession;
        Eproduct_name1 = eproduct_name1;
        Etl1 = etl1;
        Eml1 = eml1;
        Esl1 = esl1;
        Erate1 = erate1;
        Eamount1 = eamount1;
        Esession1 = esession1;
        this.AMTTOTAL = AMTTOTAL;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrderline() {
        return orderline;
    }

    public void setOrderline(String orderline) {
        this.orderline = orderline;
    }

    public String getOpenbalance() {
        return openbalance;
    }

    public void setOpenbalance(String openbalance) {
        this.openbalance = openbalance;
    }

    public String getMrngrept() {
        return mrngrept;
    }

    public void setMrngrept(String mrngrept) {
        this.mrngrept = mrngrept;
    }

    public String getEvngrept() {
        return evngrept;
    }

    public void setEvngrept(String evngrept) {
        this.evngrept = evngrept;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getClosebalance() {
        return closebalance;
    }

    public void setClosebalance(String closebalance) {
        this.closebalance = closebalance;
    }

    public String getMproduct_name() {
        return Mproduct_name;
    }

    public void setMproduct_name(String mproduct_name) {
        Mproduct_name = mproduct_name;
    }

    public String getMtl() {
        return Mtl;
    }

    public void setMtl(String mtl) {
        Mtl = mtl;
    }

    public String getMml() {
        return Mml;
    }

    public void setMml(String mml) {
        Mml = mml;
    }

    public String getMsl() {
        return Msl;
    }

    public void setMsl(String msl) {
        Msl = msl;
    }

    public String getMrate() {
        return Mrate;
    }

    public void setMrate(String mrate) {
        Mrate = mrate;
    }

    public String getMamount() {
        return Mamount;
    }

    public void setMamount(String mamount) {
        Mamount = mamount;
    }

    public String getMsession() {
        return Msession;
    }

    public void setMsession(String msession) {
        Msession = msession;
    }

    public String getMproduct_name1() {
        return Mproduct_name1;
    }

    public void setMproduct_name1(String mproduct_name1) {
        Mproduct_name1 = mproduct_name1;
    }

    public String getMtl1() {
        return Mtl1;
    }

    public void setMtl1(String mtl1) {
        Mtl1 = mtl1;
    }

    public String getMml1() {
        return Mml1;
    }

    public void setMml1(String mml1) {
        Mml1 = mml1;
    }

    public String getMsl1() {
        return Msl1;
    }

    public void setMsl1(String msl1) {
        Msl1 = msl1;
    }

    public String getMrate1() {
        return Mrate1;
    }

    public void setMrate1(String mrate1) {
        Mrate1 = mrate1;
    }

    public String getMamount1() {
        return Mamount1;
    }

    public void setMamount1(String mamount1) {
        Mamount1 = mamount1;
    }

    public String getMsession1() {
        return Msession1;
    }

    public void setMsession1(String msession1) {
        Msession1 = msession1;
    }

    public String getEproduct_name() {
        return Eproduct_name;
    }

    public void setEproduct_name(String eproduct_name) {
        Eproduct_name = eproduct_name;
    }

    public String getEtl() {
        return Etl;
    }

    public void setEtl(String etl) {
        Etl = etl;
    }

    public String getEml() {
        return Eml;
    }

    public void setEml(String eml) {
        Eml = eml;
    }

    public String getEsl() {
        return Esl;
    }

    public void setEsl(String esl) {
        Esl = esl;
    }

    public String getErate() {
        return Erate;
    }

    public void setErate(String erate) {
        Erate = erate;
    }

    public String getEamount() {
        return Eamount;
    }

    public void setEamount(String eamount) {
        Eamount = eamount;
    }

    public String getEsession() {
        return Esession;
    }

    public void setEsession(String esession) {
        Esession = esession;
    }

    public String getEproduct_name1() {
        return Eproduct_name1;
    }

    public void setEproduct_name1(String eproduct_name1) {
        Eproduct_name1 = eproduct_name1;
    }

    public String getEtl1() {
        return Etl1;
    }

    public void setEtl1(String etl1) {
        Etl1 = etl1;
    }

    public String getEml1() {
        return Eml1;
    }

    public void setEml1(String eml1) {
        Eml1 = eml1;
    }

    public String getEsl1() {
        return Esl1;
    }

    public void setEsl1(String esl1) {
        Esl1 = esl1;
    }

    public String getErate1() {
        return Erate1;
    }

    public void setErate1(String erate1) {
        Erate1 = erate1;
    }

    public String getEamount1() {
        return Eamount1;
    }

    public void setEamount1(String eamount1) {
        Eamount1 = eamount1;
    }

    public String getEsession1() {
        return Esession1;
    }

    public void setEsession1(String esession1) {
        Esession1 = esession1;
    }

    public String getAMTTOTAL() {
        return AMTTOTAL;
    }

    public void setAMTTOTAL(String AMTTOTAL) {
        this.AMTTOTAL = AMTTOTAL;
    }
}