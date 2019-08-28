package com.gamazing.util;

import com.gamazing.exception.PublicException;
import org.springframework.context.MessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class LangUtil {
    public static Locale lang() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String lang = request.getHeader("lang");
        if (lang == null) {
            return Locale.SIMPLIFIED_CHINESE;
        }

        switch(lang) {
            case "en_US":
                return Locale.US;
            default:
                return Locale.SIMPLIFIED_CHINESE;
        }
    }

    public static void exc(final String status, final MessageSource messageSource) {
        throw new PublicException(status, messageSource.getMessage(status, null, LangUtil.lang()));
    }

}
