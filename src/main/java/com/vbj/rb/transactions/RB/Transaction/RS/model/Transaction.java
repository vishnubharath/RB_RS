package com.vbj.rb.transactions.RB.Transaction.RS.model;

import com.opencsv.bean.CsvBindByName;

public class Transaction {

	//Reference,AccountNumber,Description,Start Balance,Mutation,End Balance
	@CsvBindByName(column = "Reference", required = true)
    private String reference;
	
	@CsvBindByName(column = "AccountNumber", required = true)
    private String accountNumber;
	
	@CsvBindByName(column = "Description", required = true)
    private String description;
	
	@CsvBindByName(column = "Start Balance", required = true)
    private String startBalance;
	
	@CsvBindByName(column = "Mutation", required = true)
    private String mutation;
	
	@CsvBindByName(column = "End Balance", required = true)
    private String endingBalance;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(String startBalance) {
		this.startBalance = startBalance;
	}

	public String getMutation() {
		return mutation;
	}

	public void setMutation(String mutation) {
		this.mutation = mutation;
	}

	public String getEndingBalance() {
		return endingBalance;
	}

	public void setEndingBalance(String endingBalance) {
		this.endingBalance = endingBalance;
	}
	

}
