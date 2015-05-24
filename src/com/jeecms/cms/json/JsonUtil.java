package com.jeecms.cms.json;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

/**
 * json工具.
 * 
 * @author zhanghai
 * @version v1.0 2012-8-20
 */
@SuppressWarnings( { "unchecked", "rawtypes" })
public class JsonUtil {

    /**
     * .
     * 
     * @param str
     *            {@link String}
     * @return {@link Map}
     */
    private static Map<String, Object> parseStr(String str) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (String strPart : str.split(",")) {
            String[] ss = strPart.split(":");
            if (ss == null || ss.length != 2) {
                continue;
            }
            String key = ss[0];
            String value = ss[1].trim();
            if (value.startsWith("'") && value.endsWith("'")) {
                map.put(key, value.substring(1, value.length() - 1));
            } else if (value.startsWith("\"") && value.endsWith("\"")) {
                map.put(key, value.substring(1, value.length() - 1));
            } else if (value.equals("true") || value.equals("false")) {
                map.put(key, Boolean.valueOf(value));
            } else if (value.indexOf(".") == -1) {
                try {
                    int val = Integer.parseInt(value);
                    map.put(key, val);
                } catch (Exception e) {
                    map.put(key, value);
                }
            } else {
                try {
                    BigDecimal val = new BigDecimal(value);
                    map.put(key, val);
                } catch (Exception e) {
                    map.put(key, value);
                }
            }
        }
        return map;
    }

    /**
     * .
     * 
     * @param str
     *            {@link String}
     * @return {@link String}
     */
    public static String JsonView(String str) {
        Map<String, Object> map = parseStr(str);
        return Json.toJson(map);
    }

    /**
     * .
     * 
     * @return {@link String}
     */
    public static String JsonView() {
        return "{}";
    }

    /**
     * .
     * 
     * @param map
     *            {@link Map}
     * @return {@link String}
     */
    public static String JsonView(Map<String, Object> map) {
        return Json.toJson(map);
    }

    /**
     * .
     * 
     * @param list
     *            {@link List}
     * @return {@link String}
     */
    public static String JsonView(List<?> list) {
        return Json.toJson(list);
    }

    /**
     * .
     * 
     * @param objects
     *            {@link Object}
     * @return {@link String}
     */
    public static String JsonView(Object[] objects) {
        return Json.toJson(objects);
    }

    /**
     * .
     * 
     * @param obj
     *            {@link Object}
     * @return {@link String}
     */
    public static String JsonView(Object obj) {
        return Json.toJson(obj);
    }

    /**
     * .
     * 
     * @param map
     *            {@link Map}
     * @return {@link String}
     */
    public static String JsonView_w(Map<String, Object> map) {
        return Json.toJson_w(map);
    }

    /**
     * .
     * 
     * @param obj
     *            {@link Object}
     * @return {@link String}
     */
    public static String object2json(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Short
                || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal
                || obj instanceof BigInteger || obj instanceof Byte) {
            json.append("\"").append(string2json(obj.toString())).append("\"");
        } else if (obj instanceof Boolean) {
            json.append(string2json(obj.toString()));
        } else if (obj instanceof Object[]) {
            json.append(array2json((Object[]) obj));
        } else if (obj instanceof List) {
            json.append(list2json((List<?>) obj));
        } else if (obj instanceof Map) {
            json.append(map2json((Map<?, ?>) obj));
        } else if (obj instanceof Set) {
            json.append(set2json((Set<?>) obj));
        } else if (obj instanceof Date) {
            if (obj.toString().contains("00:00:00")) {
                json.append("\"").append(DateFormat.shortDate((Date) obj)).append("\"");
            } else {
                json.append("\"").append(DateFormat.longDate((Date) obj)).append("\"");
            }

        } else {
            json.append(bean2json(obj));
        }
        return json.toString();
    }

    /**
     * .
     * 
     * @param bean
     *            {@link Object}
     * @return {@link String}
     */
    public static String bean2json(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        if (props != null) {
            for (int i = 0; i < props.length; i++) {
                try {
                    String name = object2json(props[i].getName());
                    if (!JsonFilter(name)) {
                        continue;
                    }

                    String value = object2json(props[i].getReadMethod().invoke(bean));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    /**
     * 过虑掉用不到的关联表数据避免json死循环(解决hibernate one to many many to many).
     * 
     * @param name
     *            {@link String}
     * @return author:liye
     */
    public static Boolean JsonFilter(String name) {
        boolean flag = true;
        if (!StringUtils.isBlank(name)) {
            if (name.contains("hand")) {
                flag = false;
            } else if (name.contains("hibernate")) {
                flag = false;
            } else if (name.contains("roles")) { //暂时这么写，以后要去掉
                flag = false;
            }
        }
        return flag;
    }

    /**
     * .
     * 
     * @param list
     *            {@link List}
     * @return {@link String}
     */
    public static String list2json(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * .
     * 
     * @param array
     *            {@link Object}
     * @return {@link String}
     */
    public static String array2json(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * .
     * 
     * @param map
     *            {@link Map}
     * @return {@link String}
     */
    public static String map2json(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(object2json(key));
                json.append(":");
                json.append(object2json(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    /**
     * .
     * 
     * @param set
     *            {@link Set}
     * @return {@link String}
     */
    public static String set2json(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * .
     * 
     * @param s .
     * @return String
     */
    public static String string2json(String s) {
        if (s == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
            case '\'':
                sb.append("\\'");
                break;
            case '"':
                sb.append("\\\"");
                break;
            case '\\':
                sb.append("\\\\");
                break;
            case '\b':
                sb.append("\\\b");
                break;
            case '\f':
                sb.append("\\\f");
                break;
            case '\n':
                sb.append("\\\n");
                break;
            case '\r':
                sb.append("\\\r");
                break;
            case '\t':
                sb.append("\\\t");
                break;
            case '/':
                sb.append("\\/");
                break;
            default:
                if (ch >= '\u0000' && ch <= '\u001F') {
                    String ss = Integer.toHexString(ch);
                    sb.append("\\u");
                    for (int k = 0; k < 4 - ss.length(); k++) {
                        sb.append('0');
                    }
                    sb.append(ss.toUpperCase());
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象.
     * 
     * @param jsonString
     *            {@link String}
     * @param pojoCalss
     *            {@link Class}
     * @return {@link Object}
     */
    public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        pojo = JSONObject.toBean(jsonObject, pojoCalss);
        return pojo;
    }

    /**
     * 从json HASH表达式中获取一个map，改map支持嵌套功能.
     * 
     * @param jsonString
     *            {@link String}
     * @return {@link Map}
     */
    public static Map getMap4Json(String jsonString) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator keyIter = jsonObject.keys();
        String key;
        Object value;
        Map valueMap = new HashMap();

        while (keyIter.hasNext()) {
            key = (String) keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }

        return valueMap;
    }

    /** */
    /**
     * 从json数组中得到相应java数组.
     * 
     * @param jsonString
     *            {@link String}
     * @return {@link Object}
     */
    public static Object[] getObjectArray4Json(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();

    }

    /**
     * 
     * 从json对象集合表达式中得到一个java对象列表.
     * 
     * @param jsonString
     *            {@link String}
     * @param pojoClass
     *            {@link Class}
     * @return {@link List}
     */
    public static List getList4Json(String jsonString, Class pojoClass) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;

        List list = new ArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {

            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject, pojoClass);
            list.add(pojoValue);

        }
        return list;

    }

    /**
     * 从json对象集合表达式中得到一个java对象列表 过滤不要的属性.
     * 
     * @param jsonString
     *            {@link String}
     * @param pojoClass
     *            {@link Class}
     * @param config
     *            {@link JsonConfig}
     * @return {@link List}
     */
    public static List getList4Json(String jsonString, Class pojoClass, JsonConfig config) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString, config);
        JSONObject jsonObject;
        Object pojoValue;

        List list = new ArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {

            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject, pojoClass);
            list.add(pojoValue);

        }
        return list;

    }

    /**
     * 
     * 从json数组中解析出java字符串数组.
     * 
     * @param jsonString
     *            {@link String}
     * @return {@link String}
     */
    public static String[] getStringArray4Json(String jsonString) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            stringArray[i] = jsonArray.getString(i);

        }

        return stringArray;
    }

    /**
     * 
     * 从json数组中解析出javaLong型对象数组.
     * 
     * @param jsonString
     *            {@link String}
     * @return {@link Long}
     */
    public static Long[] getLongArray4Json(String jsonString) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            longArray[i] = jsonArray.getLong(i);

        }
        return longArray;
    }

    /**
     * 
     * 从json数组中解析出java Integer型对象数组.
     * 
     * @param jsonString
     *            {@link String}
     * @return {@link Integer}
     */
    public static Integer[] getIntegerArray4Json(String jsonString) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            integerArray[i] = jsonArray.getInt(i);
        }
        return integerArray;
    }

}