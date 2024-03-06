package com.example.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.admin.mapper.SysUserMapper;
import com.example.blog.admin.model.params.PageParam;
import com.example.blog.admin.pojo.SysUser;
import com.example.blog.admin.vo.PageResult;
import com.example.blog.admin.vo.Result;
import com.example.blog.admin.vo.SysUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    SysUserMapper sysUserMapper;
    //注意后端的数据传递展示到前端的时候要进行copy，否则无法精确展示大数(long)
    private SysUserVo copy(SysUser sysUser) {
        SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setId(String.valueOf(sysUser.getId()));
        BeanUtils.copyProperties(sysUser,sysUserVo);
        return sysUserVo;
    }
    //注意后端的数据传递展示到前端的时候要进行copyList，否则无法精确展示大数(long)，从而使得不仅前端展示错误，并且从前端获取数据时也会出错
    private Page<SysUserVo> copyList(Page<SysUser> sysUserPage) {
        Page<SysUserVo> sysUserVoPage = new Page<>();
        List<SysUser> sysUserList = sysUserPage.getRecords();
        List<SysUserVo> sysUserVoList = new ArrayList<>();
        for (SysUser sysUser : sysUserList) {
            sysUserVoList.add(copy(sysUser));
        }
        sysUserVoPage.setRecords(sysUserVoList);

        return sysUserVoPage;
    }

    public Result listUser(PageParam pageParam) {
        Page<SysUser> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(pageParam.getQueryString())) {
            queryWrapper.eq(SysUser::getAccount,pageParam.getQueryString());
        }
        Page<SysUser> sysUserPage = sysUserMapper.selectPage(page, queryWrapper);

        PageResult<SysUserVo> pageResult = new PageResult<>();
        pageResult.setList(copyList(sysUserPage).getRecords());//注意copyList
        pageResult.setTotal(sysUserPage.getTotal());
        return Result.success(pageResult);
    }


    public Result update(SysUser sysUser) {
        sysUserMapper.updateById(sysUser);
        return Result.success(null);
    }

    public Result delete(Long id) {
        sysUserMapper.deleteById(id);
        return Result.success(null);
    }
}
