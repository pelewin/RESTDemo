package org.pelevin.controllers;

import org.pelevin.exceptions.EntityNotFoundException;
import org.pelevin.model.CustomerVO;
import org.pelevin.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

/**
 * Created by dmitry on 27.08.15.
 */
@RestController
@RequestMapping("/customers")
public class CustomerRestController {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerRestController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> addCustomer(@RequestBody CustomerVO input) {

		CustomerVO result = customerRepository.save(input);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getId()).toUri());
		return new ResponseEntity<>(input, httpHeaders, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	CustomerVO readCustomer(@PathVariable String customerId) {
		return findById(customerId);
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<CustomerVO> readCustomers() {
		return this.customerRepository.findAll();
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.PUT)
	ResponseEntity<?>  updateCustomer(@PathVariable String customerId, @RequestBody CustomerVO input) {

		CustomerVO customerVO = findById(customerId);
		customerVO.setName(input.getName());
		customerVO.setAddress(input.getAddress());
		customerVO.setTelephoneNumber(input.getTelephoneNumber());
		customerRepository.save(input);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customerVO.getId()).toUri());
		return new ResponseEntity<>(customerVO, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
	void deleteCustomer(@PathVariable String customerId) {
		findById(customerId);
		this.customerRepository.delete(customerId);
	}

	private CustomerVO findById(String customerId) {
		return this.customerRepository.findById(customerId).orElseThrow(
				() -> new EntityNotFoundException(CustomerVO.class, customerId));
	}

}
