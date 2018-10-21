package com.ivo.demo.rest.entity;

/**
 * @author wangjian
 * @date 2018/10/15
 */
public class Code {

    private String VENDOR_ID;

    private String PROD_TYPE;

    private String PROD_ID;

    private Long MAX_PRODUCTION;

    private String CODE_CATE;

    public String getVENDOR_ID() {
        return VENDOR_ID;
    }

    public void setVENDOR_ID(String VENDOR_ID) {
        this.VENDOR_ID = VENDOR_ID;
    }

    public String getPROD_TYPE() {
        return PROD_TYPE;
    }

    public void setPROD_TYPE(String PROD_TYPE) {
        this.PROD_TYPE = PROD_TYPE;
    }

    public String getPROD_ID() {
        return PROD_ID;
    }

    public void setPROD_ID(String PROD_ID) {
        this.PROD_ID = PROD_ID;
    }

    public Long getMAX_PRODUCTION() {
        return MAX_PRODUCTION;
    }

    public void setMAX_PRODUCTION(Long MAX_PRODUCTION) {
        this.MAX_PRODUCTION = MAX_PRODUCTION;
    }

    public String getCODE_CATE() {
        return CODE_CATE;
    }

    public void setCODE_CATE(String CODE_CATE) {
        this.CODE_CATE = CODE_CATE;
    }
}
