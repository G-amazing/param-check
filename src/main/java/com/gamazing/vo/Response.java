package com.gamazing.vo;

import com.gamazing.util.LangUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 8423910092437237925L;
    private String status;

    private String message;

    private T data;

    public Response(final String status, final String message) {
        this.status = status;
        this.message = message;
    }

    public Response(final String status, final MessageSource messageSource) {
        this.status = status;
        this.message = messageSource.getMessage(status, null, LangUtil.lang());
    }

    public Response(final String status, final MessageSource messageSource, T data) {
        this.status = status;
        this.message = messageSource.getMessage(status, null, LangUtil.lang());
        this.data = data;
    }

    public static <T> Response<T> success(final MessageSource messageSource, T data) {
        return new Response<>("0", messageSource, data);
    }

    public static <T> Response<T> success(final MessageSource messageSource) {
        return new Response<>("0", messageSource);
    }

    public static <T> Response<T> fail(final String status, final MessageSource messageSource) {
        return new Response<>(status, messageSource);
    }

    public static <T> Response<T> fail(final String status, final String message) {
        return new Response<>(status, message);
    }

    public static <T> Response<T> fail(final MessageSource messageSource) {
        return new Response<>("1", messageSource);
    }

}
