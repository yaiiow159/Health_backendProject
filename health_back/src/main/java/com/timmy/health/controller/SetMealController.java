package com.timmy.health.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.constant.MessageConstant;
import com.timmy.health.constant.RedisConstant;
import com.timmy.health.domain.Setmeal;
import com.timmy.health.entity.Result;
import com.timmy.health.service.SetMealService;
import com.timmy.health.utils.GoogleFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@RestController
@RequestMapping("/setMeals")
@Slf4j
public class SetMealController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @DubboReference(interfaceClass = SetMealService.class)
    private SetMealService setMealService;

    @PostMapping(value = "/upload")
    public Result upload(@RequestParam("imgFile") @NotNull MultipartFile imgFile) {
        String extension = null;
        String originalFilename = imgFile.getOriginalFilename();

        if (Objects.requireNonNull(originalFilename).lastIndexOf(".") > -1) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID() + extension;

        try {
            GoogleFileUtil.uploadFile(imgFile.getBytes(), fileName);
            redisTemplate.opsForSet().add(RedisConstant.SETMEAL_PIC_RESOURCE, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
    }

    @PostMapping("{checkgroupIds}")
    @PreAuthorize("hasAuthority('SETMEAL_ADD')")
    public Result add(@RequestBody Setmeal setmeal, @PathVariable Integer[] checkgroupIds) {
        try {
            setMealService.add(setmeal, checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @GetMapping("{currenPage}/{pageSize}")
    public Result findPages(@PathVariable("currenPage") Integer currenPage,
                            @PathVariable("pageSize") Integer pageSize,
                            Setmeal setmeal) {
        IPage<Setmeal> setmealIPage = setMealService.getPages(currenPage, pageSize, setmeal);
        if (currenPage > setmealIPage.getPages()) {
            log.warn("當前頁面超過取得頁面數");
            setmealIPage = setMealService.getPages((int) setmealIPage.getPages(), pageSize, setmeal);
        }
        return new Result(null != setmealIPage.getRecords(), setmealIPage);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('SETMEAL_QUERY')")
    public Result getMealById(@PathVariable Integer id) {
        try {
            Setmeal setmeal = setMealService.getMealById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @RequestMapping(value = "/findcheckGroupsByMealId", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('CHECKGROUP_QUERY')")
    public Result findcheckGroupsByMealId(Integer id) {
        try {
            List<Integer> checkGroups = setMealService.findCheckGroupById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, checkGroups);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('SETMEAL_QUERY')")
    public Result findAll() {
        List<Setmeal> setmealList = setMealService.findAll();
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmealList);
    }

}
