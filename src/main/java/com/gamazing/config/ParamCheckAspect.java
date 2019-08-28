package com.gamazing.config;

import com.gamazing.util.LangUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import javax.validation.constraints.*;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Arrays;

@Aspect
@Component
public class ParamCheckAspect {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private Validator validator;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {
    }

    @Before("requestMapping() ||getMapping()||postMapping()")
    public void paramCheck(JoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Parameter[] parameters = signature.getMethod().getParameters();
        for (Parameter parameter : parameters) {
            Class<?> paramClazz = parameter.getType();
            Field[] fields = paramClazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getAnnotation(Pattern.class) != null ||
                        field.getAnnotation(NotNull.class) != null ||
                        field.getAnnotation(Digits.class) != null ||
                        field.getAnnotation(Size.class) != null ||
                        field.getAnnotation(Max.class) != null ||
                        field.getAnnotation(Min.class) != null) {
                    Object arg = Arrays.stream(joinPoint.getArgs())
                            .filter(ar -> paramClazz.isAssignableFrom(ar.getClass())).findFirst().get();
                    BindingResult bindingResult;
                    for(Object _arg : joinPoint.getArgs()) {
                        if(_arg instanceof BindingResult) {
                            bindingResult = (BindingResult) _arg;
                            validator.validate(arg, bindingResult);
                            if (bindingResult.hasErrors()) {
                                for (ObjectError e : bindingResult.getAllErrors()) {
                                    LangUtil.exc(e.getDefaultMessage(), messageSource);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
