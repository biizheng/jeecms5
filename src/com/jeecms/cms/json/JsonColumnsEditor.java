package com.jeecms.cms.json;

/**
 * .
 * 
 * @author zhanghai
 * @version v1.0 2012-8-20
 */
public class JsonColumnsEditor {

    /**
     * .
     */
    private String header;
    /**
     * .
     */
    private String dataIndex;
    /**
     * .
     */
    private Integer width;
    /**
     * .
     */
    private Boolean sortable;
    /**
     * .
     */
    private String editor;

    /**
     * 返回header的值 .
     * 
     * @return header
     */
    public String getHeader() {
        return header;
    }

    /**
     * 设置 header的值 .
     * 
     * @param header
     *            header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * 返回dataIndex的值 .
     * 
     * @return dataIndex
     */
    public String getDataIndex() {
        return dataIndex;
    }

    /**
     * 设置 dataIndex的值 .
     * 
     * @param dataIndex
     *            dataIndex
     */
    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }

    /**
     * 返回width的值 .
     * 
     * @return width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 设置 width的值 .
     * 
     * @param width
     *            width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 返回sortable的值 .
     * 
     * @return sortable
     */
    public Boolean getSortable() {
        return sortable;
    }

    /**
     * 设置 sortable的值 .
     * 
     * @param sortable
     *            sortable
     */
    public void setSortable(Boolean sortable) {
        this.sortable = sortable;
    }

    /**
     * 返回editor的值 .
     * 
     * @return editor
     */
    public String getEditor() {
        return editor;
    }

    /**
     * 设置 editor的值 .
     * 
     * @param editor
     *            editor
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

}
