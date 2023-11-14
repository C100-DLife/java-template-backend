package py.com.code100.core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

/**
 * Interceptor for logging cURL commands of incoming HTTP requests.
 *
 * <p>This interceptor captures the details of incoming HTTP requests, including method, URL, parameters, and headers,
 * and constructs a cURL command to log the information.</p>
 *
 * <p>The constructed cURL command is then logged using a logger with the INFO level.</p>
 */
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

    /**
     * Pre-handle method to capture and log cURL command for incoming HTTP requests.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @param handler  The handler for the request.
     * @return {@code true} to allow the request to continue to the controller.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        StringBuilder curlCommand = new StringBuilder();
        curlCommand.append("curl -X ").append(request.getMethod()).append(" '")
                .append(request.getRequestURL()).append("'");

        // Add request parameters
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            curlCommand.append(" -d '").append(paramName)
                    .append("=").append(request.getParameter(paramName)).append("'");
        }

        // Add request headers
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            curlCommand.append(" -H '").append(headerName)
                    .append(": ").append(request.getHeader(headerName)).append("'");
        }

        LOGGER.info("cURL command: {}", curlCommand.toString());

        // Return true to allow the request to continue to the controller.
        return true;
    }
}
