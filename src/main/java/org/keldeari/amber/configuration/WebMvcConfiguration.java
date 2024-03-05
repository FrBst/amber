package org.keldeari.amber.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

// Slightly modified solution from Willem Cheizoo
// https://blog.jdriven.com/2016/11/handling-yaml-format-rest-spring-boot/
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_YAML = MediaType.valueOf("text/yaml");
    private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("text/yml");

    @Qualifier("yamlObjectMapper")
    @NonNull
    @Autowired
    private ObjectMapper yamlObjectMapper;

    @Override
    public void configureContentNegotiation(@NonNull ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false).ignoreAcceptHeader(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType(MediaType.APPLICATION_JSON.getSubtype(), MediaType.APPLICATION_JSON)
                .mediaType(MEDIA_TYPE_YML.getSubtype(), MEDIA_TYPE_YML)
                .mediaType(MEDIA_TYPE_YAML.getSubtype(), MEDIA_TYPE_YAML);
    }

    @Override
    public void extendMessageConverters(@NonNull List<HttpMessageConverter<?>> converters) {
        var yamlConverter = new MappingJackson2HttpMessageConverter(yamlObjectMapper);
        yamlConverter.setSupportedMediaTypes(List.of(MEDIA_TYPE_YAML, MEDIA_TYPE_YML));
        converters.add(yamlConverter);
    }
}
