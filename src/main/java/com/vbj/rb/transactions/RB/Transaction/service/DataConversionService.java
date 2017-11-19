package com.vbj.rb.transactions.RB.Transaction.service;

import java.io.IOException;
import java.util.List;

import com.vbj.rb.transactions.RB.Transaction.RS.model.Transaction;

public interface DataConversionService {

	List<Transaction> convertFromXML(String str) throws IOException;

	List<Transaction> convertFromCSV(String str) throws IOException;
}
