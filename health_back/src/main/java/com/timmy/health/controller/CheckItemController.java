package com.timmy.health.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.CheckItem;
import com.timmy.health.entity.Result;
import com.timmy.health.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/checkitems")
@Slf4j
public class CheckItemController {

    @DubboReference(interfaceClass = CheckItemService.class)
    private CheckItemService checkItemService;


    @PutMapping
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")
    public Result editCheckItem(@RequestBody CheckItem checkItem) {
        if (checkItemService.edit(checkItem) > -1) {
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } else {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    //add checkItem
    @PostMapping
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")
    public Result add(@RequestBody CheckItem checkItem) {
        if (checkItemService.save(checkItem)) {
            return new Result(true, Optional.of(MessageConstant.ADD_CHECKITEM_SUCCESS));
        } else {
            return new Result(false, Optional.of(MessageConstant.ADD_CHECKITEM_FAIL));
        }
    }

    //search pages
    @GetMapping("{currentPage}/{pageSize}")
    public Result findPage(@PathVariable int currentPage, @PathVariable int pageSize, CheckItem checkItem) {
        IPage<CheckItem> page = checkItemService.findPage(currentPage, pageSize, checkItem);
        if (currentPage > page.getPages()) {
            page = checkItemService.findPage((int) page.getPages(), pageSize, checkItem);
        }
        return new Result(null != page, page);
    }

    //delete checkItem
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')") //if the user have the premisson the user can delete the checkitem
    public Result deleteCheck(@PathVariable Integer id) {
        if (checkItemService.removeById(id)) {
            return new Result(checkItemService.removeById(id), Optional.of(MessageConstant.DELETE_CHECKITEM_SUCCESS));
        } else {
            return new Result(checkItemService.removeById(id), Optional.of(MessageConstant.DELETE_CHECKITEM_FAIL));
        }
    }

    // get checkItem
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result getCheckItem(@PathVariable Integer id) {
        if (checkItemService.getById(id) != null) {
            return new Result(true, checkItemService.getById(id));
        } else {
            return new Result(false, Optional.of("查無此結果"));
        }
    }

    //get all checkItems
    @GetMapping()
    public Result getAllCheckItems() {
        List<CheckItem> checkItemList = checkItemService.getAllCheckItems();
        try {
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
