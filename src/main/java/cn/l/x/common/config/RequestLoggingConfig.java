package cn.l.x.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingConfig {

    public CommonsRequestLoggingFilter loggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(1024);
        loggingFilter.setAfterMessagePrefix("[REQUEST DATA]");
        return loggingFilter;
    }
}


