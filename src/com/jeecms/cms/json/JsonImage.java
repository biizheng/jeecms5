package com.jeecms.cms.json;

/**
 * 
 * 图片封装类.
 * 
 * @author 王根友
 * 
 */
public class JsonImage {

    /**
     * .
     */
    private String name;

    /**
     * .
     */
    private String size;

    /**
     * .
     */
    private String url;

    /**
     * 返回name的值 .
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 返回size的值 .
     * 
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * 返回url的值 .
     * 
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 name的值 .
     * 
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置 size的值 .
     * 
     * @param size
     *            size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * 设置 url的值 .
     * 
     * @param url
     *            url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
