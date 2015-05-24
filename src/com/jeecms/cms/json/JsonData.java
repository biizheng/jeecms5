package com.jeecms.cms.json;

/**
 * .
 * 
 * @author zhanghai
 * @version v1.0 2012-8-21
 */
public class JsonData {

    /**
     * .
     */
    private Object data;

    /**
     * .
     * 
     * @param data
     *            Object
     */
    public JsonData(Object data) {
        this.data = data;
    }

    /**
     * 返回data的值 .
     * 
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置 data的值 .
     * 
     * @param data
     *            data
     */
    public void setData(Object data) {
        this.data = data;
    }

}
