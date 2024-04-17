package com.timmy.health.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String title;

    private String message;
}
