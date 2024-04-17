package com.timmy.health.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class UserAndRole implements java.io.Serializable {

    private String username;

    private String password;

    private Date birthday;

    private String telephone;

    private String gender;

    private Integer roleId;

    private String roleName;

    private String roleDescription;
}
