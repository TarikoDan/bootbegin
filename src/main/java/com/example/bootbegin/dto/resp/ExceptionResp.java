package com.example.bootbegin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResp {
    private int status;
    private String title;
    private String message;
}
