package ab858772.foundation.bank.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ab858772.foundation.bank.dto.AccountRequest;
import ab858772.foundation.bank.model.Account;
import ab858772.foundation.bank.model.Customer;
import ab858772.foundation.bank.model.TransferDetails;
import ab858772.foundation.bank.repository.AccountRepository;
import ab858772.foundation.bank.repository.CustomerRepository;
import ab858772.foundation.bank.repository.TransactionRepository;

@Service
public class CustomerService {

	
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private TransactionRepository transactionRepo;
	

	public String createAccount(Customer customer) {

		String accountNumber;
		String userId;
		
			LocalDateTime now=LocalDateTime.now();
			userId="USER"+now.getNano();
			//Customer customer=request.getCustomer();
			customer.setUserId(userId);
			/*foreach ( Account account : customer.getAccounts()) {
				accountNumber="ACC"+now.getDayOfYear();
				account.setAccountNumber(accountNumber);
				}*/	
		
		customerRepo.save(customer);

		return "Account created successfully: \n\nUserId: "+customer.getUserId()+" \nAccount number(s): "+customer.getAccounts();
	}

	public Customer viewCustomerById(String userId) {

		return customerRepo.findById(userId).get();
	}

	public List<Customer> viewAll() {
		ArrayList<Customer> customers=new ArrayList<>();

		customerRepo.findAll().forEach(customer->customers.add(customer));
		return customers;
	}

	public String removeCustomer(String userId) {

		customerRepo.deleteById(userId);
		return "Customer ID "+userId+" has been deleted.";
	}
	
	public String updateCustomer(Customer customer) {
		if(viewCustomerById(customer.getUserId())!=null) {
			customerRepo.save(customer);
			}
		return "Customer details updated susseccfully. \n\nUpdated customer details:\n"+customer;
		}
	

	public String transfer(TransferDetails transfer) {

		/*Customer sender=viewCustomerById(transfer.getFromAccount());
		Customer receiver=viewCustomerById(transfer.getToAccount());
		float amount=transfer.getAmount();
		if(sender!=null) {
			System.out.println("Sender exists");
			if(receiver!=null) {
				System.out.println("Receiver exists");
				Iterator<Account> accountIter=sender.getAccounts().iterator();
				while(accountIter.hasNext())
				{
					Account account=accountIter.next();
					String accountNumber=account.getAccountNumber();
					if(accountNumber.equals(transfer.getFromAccount()))
					{
						if(account.getBalance()>amount) 
						{
							float senderNewBalance=account.getBalance()-amount;
							System.out.println("Sender's new balance: "+senderNewBalance);
							account.setBalance(senderNewBalance);
						}
						else
							return "Insufficient balance";
					}
					else
						return "Invalid Account";
				}
				
				Iterator<Account> accountIter2=receiver.getAccounts().iterator();
				while(accountIter2.hasNext())
				{
					Account account=accountIter2.next();
					String accountNumber=account.getAccountNumber();
					if(accountNumber.equals(transfer.getToAccount()))
					{
							float receiverNewBalance=account.getBalance()+amount;
							System.out.println("Receiver's new balance: "+receiverNewBalance);
							account.setBalance(receiverNewBalance);
						
					}
					else
						return "Invalid Account";
				}
					
					updateCustomer(sender);
					updateCustomer(receiver);
					
				}
			}

		
		return "Transfered Successfully";*/
		
		Account fromAccount=accountRepo.findById(transfer.getFromAccount()).get();
		Account toAccount=accountRepo.findById(transfer.getToAccount()).get();
		if(fromAccount==null || toAccount==null)
			return "Account invalid";
		if(fromAccount.getBalance()>=transfer.getAmount())
		{
			float newBalance=fromAccount.getBalance()-transfer.getAmount();
			fromAccount.setBalance(newBalance);
			newBalance=toAccount.getBalance()+transfer.getAmount();
			toAccount.setBalance(newBalance);
			accountRepo.save(fromAccount);
			accountRepo.save(toAccount);
			transactionRepo.save(transfer);
		}
		else
			return "Insufficient balance";
		
		return 
				"Rs. "
		+transfer.getAmount()
		+" Transferred successfully from account "
		+fromAccount.getAccountNumber()
		+" to "
		+toAccount.getAccountNumber();
	}






}



















