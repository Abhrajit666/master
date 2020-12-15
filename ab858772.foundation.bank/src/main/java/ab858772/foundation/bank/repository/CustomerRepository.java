package ab858772.foundation.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ab858772.foundation.bank.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
