package com.example.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.admin.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
     /*@Update("update ms_sys_user set account = #{name} WHERE id = #{id}")
     void updateById(@Param("id")Long id, @Param("name")String name);*/
}
