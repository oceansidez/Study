package com.study.springboot.service.impl;

import com.study.springboot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Transactional
    @Override
    public String sayHi() {
        return "hi, spring boot.";
    }
}
