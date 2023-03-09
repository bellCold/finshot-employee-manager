package com.example.finshot.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeCsvDownloadRequestDto {
    private String id;
    private String name;
    private String position;
    private String email;
    private String phone;
}
