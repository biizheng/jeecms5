package com.jeecms.cms.json;

import java.util.List;

/**
 * .
 * 
 * @author zhanghai
 * @version v1.0 2012-8-21
 */
public class JsonMenuCheck {

    /**
     * .
     */
    private boolean checked;

    /**
     * .
     */
    private List<Object> children;

    /**
     * .
     */
    private String id;

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
    public List<Object> getChildren() {
        return children;
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
     * 返回checked的值 .
     * 
     * @return checked
     */
    public boolean isChecked() {
        return checked;
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
     * 设置 checked的值 .
     * 
     * @param checked
     *            checked
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * 设置 children的值 .
     * 
     * @param children
     *            children
     */
    public void setChildren(List<Object> children) {
        this.children = children;
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
