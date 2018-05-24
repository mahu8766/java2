package com.mahu.dao;


import javax.annotation.Resource;

import com.mahu.model.People;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository("demoDao")
public class DemoDao    {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * 保存对象
     * @param p
     * @return
     */
    public People save(People p){
        return (People) sessionFactory.getCurrentSession().save(p);
    }

}