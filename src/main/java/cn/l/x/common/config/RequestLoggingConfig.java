package cn.l.x.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingConfig {

    /**
     * Springboot 使用 CommonsRequestLoggingFilter 记录接口访问日志
     * https://www.cnblogs.com/zhen-jiao-ren-tou-da/p/14748500.html
     * @return
     */
    @Bean
    public CommonsRequestLoggingFilter loggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeQueryString(true);//包含查询参数
        loggingFilter.setIncludePayload(true);//包含请求体
        loggingFilter.setMaxPayloadLength(1024); // 限制请求体日志长度（避免大字段溢出）
        loggingFilter.setAfterMessagePrefix("[REQUEST DATA]");
        loggingFilter.setAfterMessageSuffix("[END]");
        return loggingFilter;
    }
}


