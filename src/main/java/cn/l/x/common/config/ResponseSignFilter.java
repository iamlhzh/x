package cn.l.x.common.config;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
public class ResponseSignFilter extends OncePerRequestFilter {

    private static Logger logger = Logger.getLogger(ResponseSignFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        // 包装响应，缓存输出流
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        // 执行后续处理（控制器逻辑）
        filterChain.doFilter(request, wrappedResponse);

        // 响应后处理：添加签名
        byte[] responseBody = wrappedResponse.getContentAsByteArray();
        String signature = generateSignature(responseBody);
        wrappedResponse.setHeader("X-Response-Signature", signature);

        // 必须调用此方法将缓存内容写入原始响应
        wrappedResponse.copyBodyToResponse();
    }

    private String generateSignature(byte[] body) {
        // 自定义签名逻辑
        return Base64.getEncoder().encodeToString(body);
    }
}
