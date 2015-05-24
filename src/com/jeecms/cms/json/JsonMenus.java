package com.jeecms.cms.json;

import java.util.List;

/**
 * .
 * 
 * @author zhanghai
 * @version v1.0 2012-8-21
 */
public class JsonMenus {

    /**
     * .
     */
    private Long id;

    /**
     * .
     */
    private String image;

    /**
     * .
     */
    private List<JsonItems> items;

    /**
     * .
     */
    private String title;

    /**
     * 返回id的值 .
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 返回image的值 .
     * 
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * 返回items的值 .
     * 
     * @return items
     */
    public List<JsonItems> getItems() {
        return items;
    }

    /**
     * 返回title的值 .
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 id的值 .
     * 
     * @param id
     *            id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 设置 image的值 .
     * 
     * @param image
     *            image
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * 设置 items的值 .
     * 
     * @param items
     *            items
     */
    public void setItems(List<JsonItems> items) {
        this.items = items;
    }
    /**
     * 设置 title的值 .
     * 
     * @param title
     *            title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
