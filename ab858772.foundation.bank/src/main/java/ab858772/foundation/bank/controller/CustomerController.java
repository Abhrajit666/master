package ab858772.foundation.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ab858772.foundation.bank.dto.AccountRequest;
import ab858772.foundation.bank.exception.ResourceNotFoundException;
import ab858772.foundation.bank.model.Customer;
import ab858772.foundation.bank.model.TransferDetails;
import ab858772.foundation.bank.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/newaccount")
	public ResponseEntity<String> accountRequest(@RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.createAccount(customer),HttpStatus.CREATED);
	}
	@GetMapping("/customer/{userId}")
	public ResponseEntity<Customer> searchById(@PathVariable String userId) throws ResourceNotFoundException {
		Customer customer=customerService.viewCustomerById(userId);
		if(customer==null)
			throw new ResourceNotFoundException("Customer does not exists");
		else
			return new ResponseEntity<Customer>(customer,HttpStatus.NOT_FOUND);
	}
	@GetMapping("/customers")
	public ResponseEntity<List> fetchAll() throws ResourceNotFoundException{
		List<Customer> customers=customerService.viewAll();
		if(customers.isEmpty())
			throw new ResourceNotFoundException("Customer does not exists");
			
		else
			return new ResponseEntity<List>(customers,HttpStatus.FOUND);
			
	}
	@DeleteMapping("/customer/{userId}")
	public ResponseEntity<String> remove(@PathVariable String userId) {
		return new ResponseEntity<String>(customerService.removeCustomer(userId),HttpStatus.OK);
	}
	@PutMapping("/customer")
	public ResponseEntity<String> update(@RequestBody Customer customer) {
		return new ResponseEntity<String>(customerService.updateCustomer(customer),HttpStatus.OK);
	}
	@PostMapping("/customer/transfer")
	public String transfer(@RequestBody TransferDetails transfer) {
		System.out.println("Transferring from "+transfer.getFromAccount()+" to "+transfer.getToAccount());
		return customerService.transfer(transfer);
	}
	

}
