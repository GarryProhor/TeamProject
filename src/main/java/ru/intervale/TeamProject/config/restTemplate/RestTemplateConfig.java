
package ru.intervale.TeamProject.config.restTemplate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import javax.annotation.Resource;

/**
 * The type Rest template config.
 */
@Configuration
@EnableAutoConfiguration
@Getter
@Setter
public class RestTemplateConfig {

    @Value("${rest.template.alfabank.url}")
    private String urlAlfaBank;

    @Value("${rest.template.bookinfo.url}")
    private String urlBook;
    /**
     * RestTemplate with default URL "https://ibapi.alfabank.by:8273"
     *
     * @param builder the builder
     * @return the rest template
     */
    @Bean("alfaBank")
    @Resource
    public RestTemplate restTemplateAlfaBank(RestTemplateBuilder builder) {
        return builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(urlAlfaBank))
                .additionalInterceptors(new RequestLoggerInterceptor())
                .build();
    }

    @Bean("bookInfo")
    @Resource
    public RestTemplate restTemplateBook(RestTemplateBuilder builder) {
        return builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(urlBook))
                .additionalInterceptors(new RequestLoggerInterceptor())
                .build();
    }

}
