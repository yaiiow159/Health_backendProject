package com.timmy.health.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class Member implements Serializable{

    private Integer id;//主鍵
    private String fileNumber;//檔案號碼
    private String name;//姓名
    private String sex;//性别
    private String idCard;//身份證號碼
    private String phoneNumber;//手機號碼
    private Date regTime;//注冊时间
    private String password;//登錄密碼
    private String email;//郵相
    private Date birthday;//出生日期
    private String remark;//備註
}
