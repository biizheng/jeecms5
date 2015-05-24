package com.jeecms.cms.json;

/**
 * 过滤不用的属性 主要是集合类.
 * application 2011-8-10
 * 
 * @author: liye
 */

public class JsonFilterProperty {
    /**
     * .
     */
    private String[] excludes;

    /**
     * 返回excludes的值 .
     * 
     * @return excludes
     */
    public String[] getExcludes() {
        return excludes;
    }

    /**
     * 设置 excludes的值 .
     * 
     * @param excludes
     *            excludes
     */
    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

}
