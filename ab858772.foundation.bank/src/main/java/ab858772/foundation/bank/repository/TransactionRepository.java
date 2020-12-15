package ab858772.foundation.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ab858772.foundation.bank.model.Account;
import ab858772.foundation.bank.model.TransferDetails;

public interface TransactionRepository extends JpaRepository<TransferDetails, Integer> {

}
