package com.asr.BankingApplication.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.asr.BankingApplication.Repository.AccountRepository;
import com.asr.BankingApplication.entity.Account;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository repo;

	public Account saveAcc(Account acc) {
		Account save = repo.save(acc);
		return save;
		
	}

	public Account updateAcc(Account acc, long id) {
		Account account = repo.findById(id).get();
		account.setHolderName(acc.getHolderName());
		account.setBalance(acc.getBalance());
		repo.save(account);
		return account;
		
		
	}

	public Account get(long id) {
		Account account = repo.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exist"));
		return account;
		
	}
	public Page<Account> getList(int pageNum , int pageSize)
	{
		 Page<Account> list = repo.findAll(PageRequest.of(pageNum, pageSize));
		return list;
	}

	public void delete(long id) {
		Account account = repo.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exist"));
		repo.delete(account);
		
	}
	
	public Account deposite(long id , double amt)
	{
		Account account = repo.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exist"));
		double total=account.getBalance()+ amt;
		account.setBalance(total);
		Account saveAcc = repo.save(account);
		return saveAcc;
	}
	
	public Account withdrwa(long id , double amt)
	{
		Account account = repo.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exist"));
		if(account.getBalance()<amt)
		{
			throw new RuntimeException("Insufficient amount");
		}
		double total = account.getBalance()-amt;
		account.setBalance(total);
		Account saveAcc = repo.save(account);
		return saveAcc;
		
	}

	
	
}
