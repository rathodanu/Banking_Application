package com.asr.BankingApplication.Controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asr.BankingApplication.Service.AccountService;
import com.asr.BankingApplication.entity.Account;

@RestController
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@PostMapping(path="/save")
	public ResponseEntity<Account> saveAcc(@RequestBody Account acc )
	{
		Account saveAcc = service.saveAcc(acc);
		return ResponseEntity.ok(saveAcc);
	}
	
	@PutMapping(path="update/{id}")
	public ResponseEntity<Account> updateAcc(@RequestBody Account acc, @PathVariable long  id)
	{
		Account updateAcc = service.updateAcc(acc, id);
		return ResponseEntity.ok(updateAcc);
	}
	
	@GetMapping(path="getdata/{id}")
	public ResponseEntity<Account> get(@PathVariable long id)
	{
		Account account = service.get(id);
		return ResponseEntity.ok(account);
	}
	
	@GetMapping(path="list")
	public ResponseEntity<Page<Account>> getList(
			@RequestParam (value="pagenum" , defaultValue = "0" , required = false ) int pageNum,
			@RequestParam (value ="pagesize" , defaultValue = "5" , required = false) int pageSize
			)
	{
		Page<Account> list = service.getList(pageNum, pageSize);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping(path="delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id)
	{
		
		service.delete(id);
		return ResponseEntity.ok("delete Successfully");
	}
	
	@PutMapping(path="deposite/{id}")
	public ResponseEntity<Account> deposite(@PathVariable long id , @RequestBody Map<String, Double> request)
	{
		Double amount = request.get("amount");
		Account deposite = service.deposite(id, amount);
		return ResponseEntity.ok(deposite);
	}
	
	@PutMapping(path="withdrwa/{id}")
	public ResponseEntity<Account> deposite(@PathVariable Long id , @RequestBody Map<String, Double> request)
	{
		Double amount = request.get("amount");
		Account withdrwa = service.withdrwa(id, amount);
		return ResponseEntity.ok(withdrwa);
	}
	
	
	


}
