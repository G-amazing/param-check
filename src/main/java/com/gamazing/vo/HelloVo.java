package com.gamazing.vo;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class HelloVo {
    @Data
    public static class ReqHello {
        @Pattern(regexp="^[1-9]{1}[0-9]{0,8}$",message="2")
        @NotNull(message = "2")
        private String num;
    }
}
