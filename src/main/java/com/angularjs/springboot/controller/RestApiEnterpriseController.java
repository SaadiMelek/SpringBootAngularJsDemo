package com.angularjs.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.angularjs.springboot.model.Enterprise;
import com.angularjs.springboot.service.EnterpriseService;
import com.angularjs.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiEnterpriseController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiEnterpriseController.class);

	@Autowired
	EnterpriseService enterpriseService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/enterprise/")
	public ResponseEntity<List<Enterprise>> listAllEnterprises() {
		List<Enterprise> enterprises = enterpriseService.findAllEnterprises();
		if (enterprises.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Enterprise>>(enterprises, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/enterprise/{id}")
	public ResponseEntity<?> getEnterprise(@PathVariable("id") long id) {
		logger.info("Fetching Enterprise with id {}", id);
		Enterprise enterprise = enterpriseService.findById(id);
		if (enterprise == null) {
			logger.error("Enterprise with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Enterprise with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Enterprise>(enterprise, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value = "/enterprise/")
	public ResponseEntity<?> createEnterprise(@RequestBody Enterprise enterprise, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Enterprise : {}", enterprise);
		if (enterpriseService.isEnterpriseExist(enterprise)) {
			logger.error("Unable to create. A Enterprise with name {} already exist", enterprise.getName());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A Enterprise with name " + enterprise.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		enterpriseService.saveEnterprise(enterprise);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/enterprise/{id}").buildAndExpand(enterprise.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping(value = "/enterprise/{id}")
	public ResponseEntity<?> updateEnterprise(@PathVariable("id") long id, @RequestBody Enterprise enterprise) {
		logger.info("Updating Enterprise with id {}", id);
		Enterprise currentEnterprise = enterpriseService.findById(id);
		if (currentEnterprise == null) {
			logger.error("Unable to update. Enterprise with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Enterprise with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentEnterprise.setName(enterprise.getName());
		currentEnterprise.setSerial(enterprise.getSerial());
		currentEnterprise.setCapital(enterprise.getCapital());
		enterpriseService.updateEnterprise(currentEnterprise);
		return new ResponseEntity<Enterprise>(currentEnterprise, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(value = "/enterprise/{id}")
	public ResponseEntity<?> deleteEnterprise(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Enterprise with id {}", id);
		Enterprise enterprise = enterpriseService.findById(id);
		if (enterprise == null) {
			logger.error("Unable to delete. Enterprise with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Enterprise with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		enterpriseService.deleteEnterpriseById(id);
		return new ResponseEntity<Enterprise>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/enterprise/")
	public ResponseEntity<Enterprise> deleteAllEnterprises() {
		logger.info("Deleting All Enterprises");
		enterpriseService.deleteAllEnterprises();
		return new ResponseEntity<Enterprise>(HttpStatus.NO_CONTENT);
	}

}