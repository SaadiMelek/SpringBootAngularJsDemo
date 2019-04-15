package com.angularjs.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angularjs.springboot.model.Enterprise;

@Repository
public interface EnterpriseDao extends JpaRepository<Enterprise, Long> {

    Enterprise findByName(String name);

}
