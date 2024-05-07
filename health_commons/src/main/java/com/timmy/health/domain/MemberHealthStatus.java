package com.timmy.health.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_member_health_status")
public class MemberHealthStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String HEALTH_STATUS_VERY_OBESE = "非常肥胖";
    public static final String HEALTH_STATUS_STANDARD = "健康";
    public static final String HEALTH_STATUS_NORMAL = "正常";
    public static final String HEALTH_STATUS_EXCESS = "過重";
    public static final String HEALTH_STATUS_OBESE = "肥胖";
    public static final String HEALTH_STATUS_VERY_THIN = "過輕";


    @TableId
    private Integer id;

    @TableField(value = "member_id")
    // 會員編號
    private Integer memberId;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "height")
    // 身高
    private Double height;

    @TableField(value = "weight")
    // 體重
    private Double weight;

    @TableField(value = "body_fat")
    // 體脂肪
    private Double bodyFat;

    @TableField(value = "BMR")
    // 基礎代謝率
    private Double BMR;

    // 體重指數
    @TableField(value = "BMI")
    private Double BMI;

    @TableField(value = "healthStatus")
    // 健康狀態
    private String healthStatus;

    // 建議卡路里攝取量
    @TableField(value = "calorie_intake")
    private Double calorieIntake;

}
