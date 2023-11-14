package py.com.code100.core.config.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;

public class LocaleChangeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String languageHeader = request.getHeader("Accept-Language");

        if (languageHeader == null || languageHeader.isEmpty()) {
            Locale locale = new Locale("es");
            LocaleContextHolder.setLocale(locale);
        } else {
            try {
                Locale locale = Locale.forLanguageTag(languageHeader);
                LocaleContextHolder.setLocale(locale);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        return true;

    }
}