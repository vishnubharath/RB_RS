package com.vbj.rb.transactions.RB.Transaction.service;

import java.util.List;

import com.vbj.rb.transactions.RB.Transaction.RS.exceptions.DuplicateReferenceIDException;
import com.vbj.rb.transactions.RB.Transaction.RS.model.Transaction;


public interface TransValidationService {

	String vaidate(List<Transaction> trans) throws DuplicateReferenceIDException;

}
