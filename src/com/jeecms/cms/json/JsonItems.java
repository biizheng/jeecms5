package com.jeecms.cms.json;

import java.util.List;

/**
 * .
 * 
 * @author zhanghai
 * @version v1.0 2012-8-21
 */
public class JsonItems {

    /**
     * .
     */
    private List<JsonItems> children;
    /**
     * .
     */
    private String cls;
    /**
     * .
     */
    private String href;
    /**
     * .
     */
    private String iconCls;
    /**
     * .
     */
    private String id;
    /**
     * .
     */
    private boolean isClass;
    /**
     * .
     */
    private boolean leaf;
    /**
     * .
     */
    private String text;

    /**
     * 返回children的值 .
     * 
     * @return children
     */
    public List<JsonItems> getChildren() {
        return children;
    }

    /**
     * 返回cls的值 .
     * 
     * @return cls
     */
    public String getCls() {
        return cls;
    }

    /**
     * 返回href的值 .
     * 
     * @return href
     */
    public String getHref() {
        return href;
    }

    /**
     * 返回iconCls的值 .
     * 
     * @return iconCls
     */
    public String getIconCls() {
        return iconCls;
    }

    /**
     * 返回id的值 .
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 返回text的值 .
     * 
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * 返回isClass的值 .
     * 
     * @return isClass
     */
    public boolean isClass() {
        return isClass;
    }

    /**
     * 返回leaf的值 .
     * 
     * @return leaf
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * 设置 children的值 .
     * 
     * @param children
     *            children
     */
    public void setChildren(List<JsonItems> children) {
        this.children = children;
    }

    /**
     * 设置 isClass的值 .
     * 
     * @param isClass
     *            isClass
     */
    public void setClass(boolean isClass) {
        this.isClass = isClass;
    }

    /**
     * 设置 cls的值 .
     * 
     * @param cls
     *            cls
     */
    public void setCls(String cls) {
        this.cls = cls;
    }

    /**
     * 设置 href的值 .
     * 
     * @param href
     *            href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * 设置 iconCls的值 .
     * 
     * @param iconCls
     *            iconCls
     */
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    /**
     * 设置 id的值 .
     * 
     * @param id
     *            id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 设置 leaf的值 .
     * 
     * @param leaf
     *            leaf
     */
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * 设置 text的值 .
     * 
     * @param text
     *            text
     */
    public void setText(String text) {
        this.text = text;
    }

}
