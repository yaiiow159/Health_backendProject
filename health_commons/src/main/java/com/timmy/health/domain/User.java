package com.timmy.health.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
public class User implements Serializable {
    private Integer id; // 主键
    private Date birthday; // 生日
    private String gender; // 性别
    private String username; // 用戶名稱，unique
    private String password; // 密码
    private String remark; // 備註
    private String station; // 狀態
    private String telephone; // 電話
    private Set<Role> roles = new HashSet<Role>(0);//對應角色
}
