package cn.l.x.common.config;

import cn.l.x.course.controller.CourseController;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestLogFilter extends OncePerRequestFilter {

    private static Logger logger = Logger.getLogger(RequestLogFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 包装请求，缓存输入流
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        byte[] requestBody = wrappedRequest.getContentAsByteArray();

        // 记录请求日志（可在此处添加自定义逻辑）
        logger.info("Received request body: " +new String(requestBody));

        // 传递包装后的请求，确保后续组件能重复读取
        filterChain.doFilter(wrappedRequest, response);

    }
}
