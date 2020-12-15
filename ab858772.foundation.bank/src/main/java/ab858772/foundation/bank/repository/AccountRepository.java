package ab858772.foundation.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ab858772.foundation.bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
