package com.vbj.rb.transactions.RB.Transaction.service;

import java.util.List;
import java.util.Map;

import com.vbj.rb.transactions.RB.Transaction.RS.model.Transaction;


public interface TransValidationService {

	Map<String, List<Transaction>> vaidate(List<Transaction> trans);

}
