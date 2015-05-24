/**
 * 
 */
package com.jeecms.cms.json;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * @author zhouqing
 * @version v1.0 2012-12-4
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    public static String format = "yyyy-MM-dd HH:mm:ss";
    private java.text.DateFormat dateFormat;

    public DateJsonValueProcessor() {

        super();
        dateFormat = new SimpleDateFormat(format);
    }

    public DateJsonValueProcessor(String dataPattern) {
        super();
        try {
            dateFormat = new SimpleDateFormat(dataPattern);
        } catch (Exception e) {
            dateFormat = new SimpleDateFormat(format);
        }
    }

    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {

        return process(value);
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {

        return process(value);
    }

    private Object process(Object value) {
        if (value == null) {
            return "N/A";
        }
        if (value instanceof Timestamp) {
            return dateFormat.format((Timestamp) value);
        } else if (value instanceof Date) {
            return dateFormat.format((Date) value);
        } else {
            return value.toString();
        }
    }

    public String getFormat() {

        return format;
    }

    public void setFormat(String format) {

        this.format = format;
    }
}
