package com.vbj.rb.transactions.RB.Transaction.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.vbj.rb.transactions.RB.Transaction.RS.exceptions.DuplicateReferenceIDException;
import com.vbj.rb.transactions.RB.Transaction.RS.model.Transaction;
import com.vbj.rb.transactions.RB.Transaction.service.TransValidationService;

@Service
public class TransValidationServiceImpl implements TransValidationService {

	@Override
	public String vaidate(List<Transaction> trans) throws DuplicateReferenceIDException {

		if(trans == null) return null;
		
		List<String> transRef = new ArrayList<String>();

		for (Transaction tran : trans) {
			transRef.add(tran.getReference());
		}

		if (duplicates(transRef)) throw new DuplicateReferenceIDException();

		return "";
	}

	
	private boolean duplicates(final List<String> references) {
		Set<String> lump = new HashSet<String>();
		for (String i : references) {
			if (lump.contains(i))
				return true;
			lump.add(i);
		}
		return false;
	}

}
