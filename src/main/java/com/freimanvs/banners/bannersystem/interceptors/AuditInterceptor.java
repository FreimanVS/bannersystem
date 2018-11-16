package com.freimanvs.banners.bannersystem.interceptors;

import com.freimanvs.banners.bannersystem.dao.AuditDAO;
import com.freimanvs.banners.bannersystem.data.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Calendar;

@Component
public class AuditInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    AuditDAO auditDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        if (uri.startsWith("/api/v1/banners") && "DELETE".equals(method)) {
            Long id = Long.valueOf(request.getRequestURI().split("/")[4]);
            Timestamp time = Timestamp.from(Calendar.getInstance().toInstant());
            String username = getUsername(request);

            Audit audit = new Audit();
            audit.setBanner_id(id);
            audit.setAction("DELETE");
            audit.setTime(time);
            audit.setUsername(username);
            auditDAO.add(audit);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();

        if (("/api/v1/banners".equals(uri) && "POST".equals(method)) ||
                (uri.startsWith("/api/v1/banners") && "PUT".equals(method))) {
            Timestamp time = Timestamp.from(Calendar.getInstance().toInstant());
            String username = getUsername(request);

            Audit audit = new Audit();
            audit.setTime(time);
            audit.setUsername(username);

            String location = response.getHeader("location");
            Long id = Long.valueOf(location.split("/")[6]);

            if ("POST".equals(method)) {
                audit.setAction("ADD");
            } else {
                audit.setAction("UPDATE");
            }


            audit.setBanner_id(id);
            auditDAO.add(audit);
        }
    }

    private String getUsername(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return  principal.getName();
    }
}
