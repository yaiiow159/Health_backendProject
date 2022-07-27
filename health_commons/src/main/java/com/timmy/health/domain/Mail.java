package com.timmy.health.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String title;

    private String message;
}
