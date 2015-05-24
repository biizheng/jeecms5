/**
 * 
 */
package com.jeecms.cms.json;

import java.util.Date;

import net.sf.json.JSONNull;
import net.sf.json.processors.DefaultValueProcessor;

/**
 * 
 * @author Administrator
 * @version v1.0 2012-12-4
 */
public class MyDefaultValueProcessor implements DefaultValueProcessor {

    public Object getDefaultValue(Class type) {
        if (type != null && Date.class.isAssignableFrom(type)) {
            return "";
        }
        return JSONNull.getInstance();
    }

}
