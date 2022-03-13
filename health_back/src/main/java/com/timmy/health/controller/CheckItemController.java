package com.timmy.health.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.CheckItem;
import com.timmy.health.entity.PageResult;
import com.timmy.health.entity.QueryPageBean;
import com.timmy.health.entity.Result;
import com.timmy.health.service.CheckItemService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

@RestController
@RequestMapping("/checkitems")
public class CheckItemController {

    @DubboReference(interfaceClass = CheckItemService.class)
    private CheckItemService checkItemService;

    //添加檢查項目
    @PostMapping
    public Result add(@RequestBody CheckItem checkItem) {
        if (checkItemService.save(checkItem)){
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        } else {
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    //分頁查詢功能
    @GetMapping("{currentPage}/{pageSize}")
    public Result findPage(@PathVariable int currentPage,@PathVariable int pageSize,CheckItem checkItem){
        IPage<CheckItem> page = checkItemService.findPage(currentPage, pageSize, checkItem);
        if (currentPage > page.getPages()){
            page = checkItemService.findPage((int)page.getPages(),pageSize,checkItem);
        }
        return new Result(null != page,page);
    }

    //刪除檢查項目
    @DeleteMapping("{id}")
    public Result deleteCheck(@PathVariable Integer id){
        if (checkItemService.removeById(id)){
            return new Result(checkItemService.removeById(id),MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } else {
            return new Result(checkItemService.removeById(id),MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }
}
