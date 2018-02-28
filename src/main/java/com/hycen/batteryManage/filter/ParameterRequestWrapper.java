package com.hycen.batteryManage.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author hshao
 * @date 2018/1/28
 * @since 3.0
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return trim(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            String[] newValues = new String[values.length];

            for (int i = 0; i < values.length; i++) {
                newValues[i] = trim(values[i]);
            }
            return newValues;
        }
        return super.getParameterValues(name);
    }

    private String trim(String value) {
        if (value != null) {
            return value.trim();
        }
        return value;
    }
}
