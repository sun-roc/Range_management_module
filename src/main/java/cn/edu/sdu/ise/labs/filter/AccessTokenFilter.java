package cn.edu.sdu.ise.labs.filter;

import cn.edu.sdu.ise.labs.model.ResultContext;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.TokenService;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 描述：token过滤器
 *
 * @author 李洪文
 * @date 2019-03-11
 */
@Component
public class AccessTokenFilter implements Filter {

    @Autowired
    private TokenService tokenService;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ParameterRequestWrapper parameterRequestWrapper = new ParameterRequestWrapper(request);
        String accessToken = FormatUtils.trimToEmpty(request.getHeader("accessToken"));
        if (request.getRequestURI().startsWith("/system/login")) {
            accessToken = "";
        }

        Token token = null;
        if (!accessToken.isEmpty()) {
            token = tokenService.getToken(accessToken);
            if (token == null) {
                returnAuthFail(servletResponse);
                return;
            }

            token.setLastAction(new Date());
            TokenContextHolder.setToken(token);
        }

        filterChain.doFilter(parameterRequestWrapper, servletResponse);
    }

    private void returnAuthFail(ServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter printWriter = response.getWriter();
            response.reset();
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Access-Control-Allow-Methods", "*");
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            resp.addHeader("Content-Type", "application/json; charset=utf-8");
            printWriter.write(JSONObject.toJSONString(ResultContext.returnFail(ResultContext.SESSION_TIMEOUT, "用户不存在或登陆信息失效，请重新登陆！")));
        } catch (IOException e) {
        }
    }

    @Override
    public void destroy() {

    }
}
