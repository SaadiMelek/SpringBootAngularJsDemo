package com.angularjs.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.angularjs.springboot.dao.EnterpriseDao;
import com.angularjs.springboot.model.Enterprise;
import com.angularjs.springboot.service.EnterpriseService;

@Service
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	private EnterpriseDao enterpriseDao;

	public Enterprise findById(Long id) {
		return enterpriseDao.findById(id).orElse(null);
	}

	public Enterprise findByName(String name) {
		return enterpriseDao.findByName(name);
	}

	public void saveEnterprise(Enterprise enterprise) {
		enterpriseDao.save(enterprise);
	}

	public void updateEnterprise(Enterprise enterprise) {
		saveEnterprise(enterprise);
	}

	public void deleteEnterpriseById(Long id) {
		enterpriseDao.deleteById(id);
	}

	public void deleteAllEnterprises() {
		enterpriseDao.deleteAll();
	}

	public List<Enterprise> findAllEnterprises() {
		return enterpriseDao.findAll();
	}

	public boolean isEnterpriseExist(Enterprise enterprise) {
		return findByName(enterprise.getName()) != null;
	}

}
