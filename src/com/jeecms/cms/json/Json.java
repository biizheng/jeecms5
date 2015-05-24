package com.jeecms.cms.json;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * JSON实用处理类.
 * 
 * @author stworthy
 */
public class Json {
    /**
     * .
     * 
     * @param obj
     *            {@link Object}
     * @return {@link String}
     */
    public static String toJson(Object obj) {
        String s = castToJson(obj);
        if (s != null) {
            return s;
        } else {
            return toJson(getAttributes(obj));
        }
    }

    /**
     * .
     * 
     * @param map
     *            {@link Map}
     * @return {@link String}
     */
    public static String toJson(Map<String, Object> map) {
        String result = "";
        for (String name : map.keySet()) {
            Object value = map.get(name);
            String s = castToJson(value);
            if (s != null) {
                result += "\"" + name + "\":" + s + ",";
            } else if (value instanceof List<?>) {
                String v = toJson((List<?>) value);
                result += "\"" + name + "\":" + v + ",";
            } else if (value instanceof Object[]) {
                String v = toJson((Object[]) value);
                result += "\"" + name + "\":" + v + ",";
            } else if (value instanceof Map<?, ?>) {
                Map<String, Object> attr = castMap((Map<?, ?>) value);
                attr = removeListAttr(attr);
                result += "\"" + name + "\":" + Json.toJson(attr) + ",";
            } else if (!value.getClass().getName().startsWith("java")) {
                Map<String, Object> attr = getAttributes(value);
                attr = removeListAttr(attr);
                result += "\"" + name + "\":" + Json.toJson(attr) + ",";
            } else {
                result += "\"" + name + "\":" + "\"" + value.toString() + "\",";
            }
        }
        if (result.length() == 0) {
            return "{}";
        } else {
            return "{" + result.substring(0, result.length() - 1) + "}";
        }
    }

    /**
     * .
     * 
     * @param aa
     *            {@link Object}
     * @return {@link String}
     */
    public static String toJson(Object[] aa) {
        if (aa.length == 0) {
            return "[]";
        } else {
            String result = "";
            for (Object obj : aa) {
                String s = castToJson(obj);
                if (s != null) {
                    result += s + ",";
                } else if (obj instanceof Map<?, ?>) {
                    Map<String, Object> map = castMap((Map<?, ?>) obj);
                    map = removeListAttr(map);
                    result += toJson(map) + ",";
                } else {
                    Map<String, Object> attr = getAttributes(obj);
                    attr = removeListAttr(attr);
                    result += toJson(attr) + ",";
                }
            }
            return "[" + result.substring(0, result.length() - 1) + "]";
        }
    }

    /**
     * .
     * 
     * @param ll
     *            {@link List}
     * @return {@link String}
     */
    public static String toJson(List<?> ll) {
        return toJson(ll.toArray());
    }

    /**
     * 取得对象的属性.
     * 
     * @param obj
     *            {@link Object}
     * @return 对象属性表.
     */
    public static Map<String, Object> getAttributes(Object obj) {
        Class<?> c = obj.getClass();
        try {
            Method method = c.getMethod("isProxy");
            Boolean result = (Boolean) method.invoke(obj);
            if (result) {
                c = c.getSuperclass();
            }
        } catch (Exception e) {
            //  e.printStackTrace();
        }
        Map<String, Object> attr = new HashMap<String, Object>();

        // 取得所有公共字段
        for (Field f : c.getFields()) {
            try {
                Object value = f.get(obj);
                attr.put(f.getName(), value);
            } catch (Exception e) {
                //  e.printStackTrace();
            }
        }

        // 取得所有本类方法
        for (Method m : c.getDeclaredMethods()) {
            String mname = m.getName();
            if (mname.equals("getClass")) {
                continue;
            } else if (mname.startsWith("get")) {
                String pname = mname.substring(3);
                if (pname.length() == 1) {
                    pname = pname.toLowerCase();
                } else {
                    pname = pname.substring(0, 1).toLowerCase() + pname.substring(1);
                }

                try {
                    Object value = m.invoke(obj);
                    attr.put(pname, value);
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            } else if (mname.startsWith("is")) {
                String pname = mname.substring(2);
                if (pname.length() == 1) {
                    pname = pname.toLowerCase();
                } else {
                    pname = pname.substring(0, 1).toLowerCase() + pname.substring(1);
                }

                try {
                    Object value = m.invoke(obj);
                    attr.put(pname, value);
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        }
        return attr;
    }

    /**
     * 将简单对象转换成JSON串.
     * 
     * @param obj
     *            {@link Object}
     * @return 如果是简单对象则返回JSON，如果是复杂对象则返回null
     */
    private static String castToJson(Object obj) {
        if (obj == null) {
            return "null";
        } else if (obj instanceof Boolean) {
            return obj.toString();
        } else if (obj instanceof Integer || obj instanceof Long || obj instanceof Float || obj instanceof Double
                || obj instanceof Short || obj instanceof java.math.BigInteger || obj instanceof java.math.BigDecimal) {
            return obj.toString();
        } else if (obj instanceof String) {
            String v = (String) obj;
            v = v.replaceAll("\\\\", "\\\\\\\\");
            v = v.replaceAll("\n", "\\\\n");
            v = v.replaceAll("\r", "\\\\r");
            v = v.replaceAll("\"", "\\\\\"");
            v = v.replaceAll("'", "\\\\\'");
            return "\"" + v + "\"";
        } else if (obj instanceof java.sql.Date) {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date v = (java.sql.Date) obj;
            String s = df.format(new java.util.Date(v.getTime()));
            return "\"" + s + "\"";
        } else if (obj instanceof java.util.Date) {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date v = (java.util.Date) obj;
            String s = df.format(v);
            return "\"" + s + "\"";
        } else if (obj instanceof java.sql.Timestamp) {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.sql.Timestamp v = (java.sql.Timestamp) obj;
            String s = df.format(new java.util.Date(v.getTime()));
            return "\"" + s + "\"";
        } else {
            return null;
        }

    }

    /**
     * .
     * 
     * @param map
     *            {@link Map}
     * @return {@link Map}
     */
    public static Map<String, Object> castMap(Map<?, ?> map) {
        Map<String, Object> newMap = new HashMap<String, Object>();
        for (Object key : map.keySet()) {
            newMap.put(key.toString(), map.get(key));
        }
        return newMap;
    }

    /**
     * 删除属性中类型是List的属性.
     * 
     * @param map
     *            {@link Map}
     * @return {@link Map}
     */
    private static Map<String, Object> removeListAttr(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<String, Object>();
        for (Entry<String, Object> en : map.entrySet()) {
            if (!(en.getValue() instanceof List<?>)) {
                newMap.put((String) en.getKey(), en.getValue());
            }
        }
        return newMap;
    }

    /**
     * .
     * 
     * @param map
     *            {@link Map}
     * @return {@link String}
     */
    public static String toJson_w(Map<String, Object> map) {
        String result = "";
        for (String name : map.keySet()) {
            Object value = map.get(name);
            String s = castToJson_w(value);
            if (s != null) {
                result += "'" + name + "':" + s + ",";
            } else if (value instanceof List<?>) {
                String v = toJson_w((List<?>) value);
                result += "'" + name + "':" + v + ",";
            } else if (value instanceof Object[]) {
                String v = toJson_w((Object[]) value);
                result += "'" + name + "':" + v + ",";
            } else if (value instanceof Map<?, ?>) {
                Map<String, Object> attr = castMap((Map<?, ?>) value);
                attr = removeListAttr(attr);
                result += "'" + name + "':" + Json.toJson_w(attr) + ",";
            } else if (!value.getClass().getName().startsWith("java")) {
                Map<String, Object> attr = getAttributes(value);
                attr = removeListAttr(attr);
                result += "'" + name + "':" + Json.toJson_w(attr) + ",";
            } else {
                result += "'" + name + "':" + "'" + value.toString() + "',";
            }
        }
        if (result.length() == 0) {
            return "";
        } else {
            return result.substring(0, result.length() - 1);
        }
    }

    /**
     * .
     * 
     * @param aa
     *            {@link Object}
     * @return {@link String}
     */
    public static String toJson_w(Object[] aa) {
        if (aa.length == 0) {
            return "[]";
        } else {
            String result = "";
            for (Object obj : aa) {
                String s = castToJson_w(obj);
                if (s != null) {
                    result += s + ",";
                } else if (obj instanceof Map<?, ?>) {
                    Map<String, Object> map = castMap((Map<?, ?>) obj);
                    map = removeListAttr(map);
                    result += toJson_w(map) + ",";
                } else {
                    Map<String, Object> attr = getAttributes(obj);
                    attr = removeListAttr(attr);
                    result += toJson_w(attr) + ",";
                }
            }
            return "[" + result.substring(0, result.length() - 1) + "]";
        }
    }

    /**
     * .
     * 
     * @param obj
     *            {@link Object}
     * @return {@link String}
     */
    private static String castToJson_w(Object obj) {
        if (obj == null) {
            return "null";
        } else if (obj instanceof Boolean) {
            return obj.toString();
        } else if (obj instanceof Integer || obj instanceof Long || obj instanceof Float || obj instanceof Double
                || obj instanceof Short || obj instanceof java.math.BigInteger || obj instanceof java.math.BigDecimal) {
            return obj.toString();
        } else if (obj instanceof String) {
            String v = (String) obj;
            v = v.replaceAll("\\\\", "\\\\\\\\");
            v = v.replaceAll("\n", "\\\\n");
            v = v.replaceAll("\r", "\\\\r");
            v = v.replaceAll("\"", "\\\\\"");
            v = v.replaceAll("'", "\\\\\'");
            return "'" + v + "'";
        } else if (obj instanceof java.sql.Date) {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date v = (java.sql.Date) obj;
            String s = df.format(new java.util.Date(v.getTime()));
            return "'" + s + "'";
        } else if (obj instanceof java.util.Date) {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date v = (java.util.Date) obj;
            String s = df.format(v);
            return "'" + s + "'";
        } else if (obj instanceof java.sql.Timestamp) {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.sql.Timestamp v = (java.sql.Timestamp) obj;
            String s = df.format(new java.util.Date(v.getTime()));
            return "'" + s + "'";
        } else {
            return null;
        }
    }

    /**
     * .
     * 
     * @param ll
     *            {@link List}
     * @return {@link String}
     */
    public static String toJson_w(List<?> ll) {
        return toJson_w(ll.toArray());
    }
}
