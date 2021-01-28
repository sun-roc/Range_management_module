package cn.edu.sdu.ise.labs.filter;

import org.apache.catalina.util.ParameterMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;

/**
 * @author 李洪文
 * @description
 * @date 2019/11/14 11:54
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private ParameterMap<String, String[]> params;

    @SuppressWarnings("all")
    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        params = (ParameterMap) request.getParameterMap();
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    public void addParameter(String name, Object value) {
        if (value != null) {
            params.setLocked(false);
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
            params.setLocked(true);
        }
    }
}