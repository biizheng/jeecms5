package com.jeecms.cms.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * struts2 返回 JSON 数据格式 工具类.
 * 
 * @author 王根友
 */
public class JsonResult {

    /**
     * 表单提交返回数据格式化方法.
     * 
     * @param success
     *            Boolean
     * @param errors
     *            String
     * @return String
     */
    public static String submit(Boolean success, String errors) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        if (!success) {
            result.put("failure", !success);
            result.put("errors", errors);
        } else {
            result.put("id", errors);
        }
        return JsonUtil.JsonView(result);
    }

    public static String tip(Boolean success) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        return JsonUtil.JsonView(result);
    }
    /**
     * 文件上传.
     * 
     * @param msg
     *            String
     * @param error
     *            String
     * @return String
     */
    public static String upload(String error, String msg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", error);
        result.put("msg", msg);
        return JsonUtil.JsonView(result);
    }

    /**
     * 多文件上传.
     * 
     * @param oldName
     *            String
     * @param success
     *            String
     * @param newName
     *            String
     * @param msg
     *            String.
     * @return String
     */
    public static String upload(boolean success, String msg, String oldName, String newName) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("error", msg);
        result.put("oldname", oldName);
        result.put("newname", newName);
        return JsonUtil.JsonView(result);
    }

    /**
     * AJAX提交返回数据格式化方法(String).
     * 
     * @param success
     *            Boolean
     * @param msg
     *            String
     * @return String
     */
    public static String ajax(Boolean success, String msg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("msg", msg);
        return JsonUtil.JsonView(result);
    }

    
    public static String ajax(Boolean success, String url, String name) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("url", url);
        result.put("name", name);
        return JsonUtil.JsonView(result);
    }
    /**
     * AJAX提交返回数据格式化方法(Object).
     * 
     * @param success
     *            Booelan
     * @param obj
     *            object
     * @return String
     */
    public static String ajax_data(Boolean success, Object obj) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("msg", obj);
        return JsonUtil.map2json(result);
    }

    /**
     * AJAX提交返回数据格式化方法(Bean).
     * 
     * @param success
     *            Boolean
     * @param obj
     *            Object
     * @return String
     */
    public static String ajax_bean(Boolean success, Object obj) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("data", obj);
        return JsonUtil.map2json(result);
    }

    /**
     * AJAX提交返回数据格式化方法(List).
     * 
     * @param obj
     *            Object.
     * @return String
     */
    public static String ajax_list(Object obj) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", obj);
        return JsonUtil.map2json(result);
    }

    /**
     * 返回grid数据格式化方法.
     * 
     * @param list
     *            List
     * @param count
     *            Object
     * @return String
     */
    @SuppressWarnings("rawtypes")
    public static String list(List list, Object count) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", count == null ? 0 : count);
        result.put("datas", list.isEmpty() ? new ArrayList() : list);
        return JsonUtil.map2json(result);
    }

    /**
     * 返回grid数据格式化方法.
     * 带过滤器的方法
     * 
     * @param list
     *            List
     * @param count
     *            Object
     * @param filter
     *            {@link JsonFilterProperty}
     * 
     * @return String
     */
    @SuppressWarnings("rawtypes")
    public static String list(List list, Object count, JsonFilterProperty filter) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", count == null ? 0 : count);
        result.put("datas", list.isEmpty() ? new ArrayList() : list);
        return JsonUtil.map2json(result);
    }

    /**
     * 返回grid数据格式化方法.
     * 
     * @param list
     *            List
     * @return String
     */
    @SuppressWarnings("rawtypes")
    public static String list(List list) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("datas", list.isEmpty() ? new ArrayList() : list);
        return JsonUtil.map2json(result);
    }

    /**
     * 返回对象数据格式化方法 .
     * 
     * @param obj
     *            Object
     * @return String
     */
    public static String bean(Object obj) {
        return JsonUtil.JsonView(obj);
    }

    /**
     * 返回对象集合数据格式化方法 .
     * 
     * @param list
     *            List
     * @return String
     */
    @SuppressWarnings("rawtypes")
    public static String beans(List list) {
        return JsonUtil.JsonView(list);
    }
}
