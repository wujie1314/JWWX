package org.jiaowei.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alex on 15-4-28.
 *
 *
 */
public class RequestParameterWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> params = new HashMap<String, String[]>();
    private Map<String, String[]> paramsTemp;

    public RequestParameterWrapper(HttpServletRequest request,
                                   Map<String, String[]> newParams) {
        super(request);
//        this.params = newParams;
        this.paramsTemp = newParams;
        filterParameterMap(request);
    }

    @Override
    public String getParameter(String name) {
        String result = "";
        Object v = params.get(name);
        if (v == null) {
            result = null;
        } else if (v instanceof String[]) {
            String[] strArr = (String[]) v;
            if (strArr.length > 0) {
                result = strArr[0];
            } else {
                result = null;
            }
        } else if (v instanceof String) {
            result = (String) v;
        } else {
            result = v.toString();
        }
        return result;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return new Vector<String>(params.keySet()).elements();
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] result = null;

        Object v = params.get(name);
        if (v == null) {
            result = null;
        } else if (v instanceof String[]) {
            result = (String[]) v;
        } else if (v instanceof String) {
            result = new String[]{(String) v};
        } else {
            result = new String[]{v.toString()};
        }
        return result;
    }

    private void filterParameterMap(HttpServletRequest req) {
        if (null != this.paramsTemp && this.paramsTemp.size() > 0) {
            Iterator<String> it = paramsTemp.keySet().iterator();
            while (it.hasNext()) {
                String kay = it.next();
                String[] values = paramsTemp.get(kay);
                if (null != values && values.length > 0) {
                    String[] newValues = new String[values.length];
                    for (int i = 0; i < values.length; i++) {
//                        newValues[i] = values[i].replace("<script", "&lt;script").replace("</script>", "&lt;/script&gt;").replace("\\", "\\\\");
                        Pattern pattern=Pattern.compile("<[/]*s\\s*c\\s*r\\s*i\\s*p\\s*t\\s*\\S*");
                        Matcher matcher=pattern.matcher(values[i]);
                        while (matcher.find()){
                            String temp = matcher.group();
                            values[i] = (values[i]).replace(temp, (temp.replace("<","&lt;").replace(">","&gt;")));
                        }
                        newValues[i] = values[i].replace("\\", "\\\\");
                    }
//                  params.remove(kay);
                    params.put(kay, newValues);
                }
            }
        }
    }
}
