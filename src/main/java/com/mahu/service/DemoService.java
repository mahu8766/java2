package com.mahu.service;


import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.mahu.dao.DemoDao;
import com.mahu.model.People;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("demoService")
public class DemoService {

    @Resource(name="demoDao")
    private DemoDao demoDao;;

    @Transactional
    public void save(People p){
        demoDao.save(p);
    }

    public People newPeople(){
        People p=new People();
        p.setName("小白");
        p.setSex("男");
        return p;
    }
}