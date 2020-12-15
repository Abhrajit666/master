package ab858772.foundation.bank.dto;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import ab858772.foundation.bank.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class AccountRequest implements Serializable {
	@Autowired
	private Customer customer;

}
