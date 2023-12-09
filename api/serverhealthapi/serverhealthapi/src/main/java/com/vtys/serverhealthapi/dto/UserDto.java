package com.vtys.serverhealthapi.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer userid;
    private String username;
    private String userpassword;
    private String useremail;
    private String userregistrationdate;
    private String userlastlogin;
}
