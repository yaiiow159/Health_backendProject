package com.timmy.health.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timmy.health.constant.MessageConstant;
import com.timmy.health.domain.CheckGroup;
import com.timmy.health.entity.Result;
import com.timmy.health.service.CheckgroupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author TimmyChung
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/checkgroups")
@Slf4j
public class CheckgroupController {

    @DubboReference(interfaceClass = CheckgroupService.class)
    private CheckgroupService checkgroupService;

    @PutMapping("{checkitemIds}")
    @PreAuthorize("hasAuthority('CHECKGROUP_EDIT')")
    public Result editCheckGroup(@RequestBody CheckGroup checkGroup,@PathVariable Integer[] checkitemIds) {
        try {
            checkgroupService.editCheckGroup(checkGroup,checkitemIds);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @PostMapping("{checkitemIds}")
    @PreAuthorize("hasAuthority('CHECKGROUP_ADD')")
    public Result add(@RequestBody CheckGroup checkGroup,@PathVariable Integer[] checkitemIds) {
        try {
            checkgroupService.add(checkGroup, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @GetMapping("{currenPage}/{pageSize}")
    public Result findPages(@PathVariable("currenPage") Integer currenPage,
                            @PathVariable("pageSize") Integer pageSize,
                            CheckGroup checkGroup) {
        IPage<CheckGroup> groupIPage = checkgroupService.getPages(currenPage, pageSize, checkGroup);
        if (currenPage > groupIPage.getPages()) {
            log.info("當前頁面超過取得頁面數");
            groupIPage = checkgroupService.getPages((int) groupIPage.getPages(), pageSize, checkGroup);
        }
        return new Result(null != groupIPage.getRecords(), groupIPage);
    }

    @RequestMapping(value = "/findCheckItemByGroupId",method = RequestMethod.GET)
    public Result findCheckItemByGroupId(Integer id) {
        try {
            List<Integer> checkItemsId = checkgroupService.findCheckItemIds(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @GetMapping("/getCheckGroupById")
    @PreAuthorize("hasAuthority('CHECKGROUP_QUERY')")
    public Result getCheckGroupById(Integer id) {
        try {
            CheckGroup checkGroup = checkgroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('CHECKGROUP_DELETE')")
    public Result deleteCheckGroupById(@PathVariable Integer id) {
        if (checkgroupService.deleteById(id)) {
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } else {
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @DeleteMapping(value = "/deleteGroupItemsIdByGroupId/" + "{id}")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")
    public Result deleteGroupItemsIdByGroupId(@PathVariable Integer id) {
          try {
              checkgroupService.deleteGroupItemByGroupId(id);
          } catch (Exception e) {
              e.printStackTrace();
              return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
          }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    //find all checkgroups
    @GetMapping
    public Result getAllCheckgroups(){
        try {
            List<CheckGroup> checkGroupList = checkgroupService.getAll();
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
}
