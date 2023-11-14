package py.com.code100.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import py.com.code100.core.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "config")
public class AppConfig {

    @NotNull
    private Integer defaultPaginated;


    /**
     * Configures and provides a {@link LocaleResolver} using {@link AcceptHeaderLocaleResolver}.
     *
     * <p>This bean creates an instance of {@code AcceptHeaderLocaleResolver} as the implementation
     * of {@code LocaleResolver}. It sets the default locale to Spanish ("es").
     *
     * @return A configured {@code LocaleResolver} using {@code AcceptHeaderLocaleResolver}.
     */
    @Bean
    public LocaleResolver localeResolver() {
        // Create an instance of AcceptHeaderLocaleResolver.
        AcceptHeaderLocaleResolver ahlr = new AcceptHeaderLocaleResolver();

        // Set the default locale to Spanish ("es").
        ahlr.setDefaultLocale(Locale.forLanguageTag("es"));

        // Return the configured LocaleResolver.
        return ahlr;
    }


    /**
     * Replace the default ObjectMapper when injected with Autowired
     *
     * @return new ObjectMapper object
     */
    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        return JsonUtils.getObjectMapper();
    }
    /**
     * Configures and provides a {@link FilterRegistrationBean} for a {@link CharacterEncodingFilter}.
     *
     * <p>This bean creates a {@code FilterRegistrationBean} for a {@code CharacterEncodingFilter}
     * with UTF-8 encoding. The filter is configured to force encoding and set UTF-8 as the character encoding.
     *
     * @return A {@code FilterRegistrationBean} for the configured {@code CharacterEncodingFilter}.
     */
    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean() {
        // Create a FilterRegistrationBean for a CharacterEncodingFilter.
        FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<>();

        // Create an instance of CharacterEncodingFilter and configure it.
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");

        // Set the filter on the FilterRegistrationBean.
        registrationBean.setFilter(characterEncodingFilter);

        // Return the configured FilterRegistrationBean.
        return registrationBean;
    }


    /**
     * Configures and provides a custom instance of {@link MappingJackson2HttpMessageConverter}.
     *
     * <p>This bean creates a {@code MappingJackson2HttpMessageConverter} with a custom {@code ObjectMapper}
     * obtained from {@link JsonUtils#getObjectMapper()}.
     * It also extends the list of supported media types by adding "text/plain" and "text/html".
     * Additionally, it sets the "pretty print" option to true for improved readability of the JSON output.
     *
     * @return An instance of {@code MappingJackson2HttpMessageConverter} configured with custom settings.
     */
    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        // Create an instance of MappingJackson2HttpMessageConverter with a custom ObjectMapper.
        MappingJackson2HttpMessageConverter messageConverter =
                new MappingJackson2HttpMessageConverter(JsonUtils.getObjectMapper());

        // Get the current list of supported media types from the converter.
        messageConverter.getSupportedMediaTypes();
        List<MediaType> mediaTypes = new ArrayList<>(messageConverter.getSupportedMediaTypes());

        // Add additional media types that the converter should support.
        mediaTypes.add(new MediaType("text", "plain"));
        mediaTypes.add(new MediaType("text", "html"));

        // Set the updated list of supported media types for the converter.
        messageConverter.setSupportedMediaTypes(mediaTypes);

        // Enable "pretty print" for better JSON readability.
        messageConverter.setPrettyPrint(true);

        // Return the configured instance of MappingJackson2HttpMessageConverter.
        return messageConverter;
    }

}
