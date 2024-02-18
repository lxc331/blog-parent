package com.example.blog.service;

import com.example.blog.vo.Result;
import com.example.blog.vo.params.RegisterParam;
import org.springframework.transaction.annotation.Transactional;

@Transactional //出现错误就进行回滚防止添加异常
public interface RegisterService {
    /**
     * 注册公式
     * @param registerParam
     * @return
     */
    Result register(RegisterParam registerParam);
}
