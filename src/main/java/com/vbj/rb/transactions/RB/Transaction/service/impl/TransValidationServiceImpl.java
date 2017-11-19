package com.vbj.rb.transactions.RB.Transaction.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.vbj.rb.transactions.RB.Transaction.RS.model.Transaction;
import com.vbj.rb.transactions.RB.Transaction.service.TransValidationService;

@Service
public class TransValidationServiceImpl implements TransValidationService {

	private static Logger log = Logger
			.getLogger(TransValidationServiceImpl.class);

	@Override
	public Map<String, List<Transaction>> vaidate(List<Transaction> trans) {

		Map<String, List<Transaction>> validationResult = new HashedMap();

		List<Transaction> duplicateTrans = duplicatesRefs(trans);

		List<Transaction> invalidBalance = invalidBalances(trans);

		validationResult.put("duplicates", duplicateTrans);
		validationResult.put("invalidBalance", invalidBalance);
		validationResult.put("allTrans", trans);

		return validationResult;
	}

	private List<Transaction> invalidBalances(List<Transaction> trans) {
		List<Transaction> invalidBalance = new ArrayList<Transaction>();

		for (Transaction tran : trans) {

			Float calBal = new Float(Float.parseFloat(tran.getStartBalance())
					+ Float.parseFloat(tran.getMutation()));
			
			if (round(calBal, 2) != Float.parseFloat(tran.getEndingBalance())) {
				invalidBalance.add(tran);
			}

		}

		return invalidBalance;
	}

	private List<Transaction> duplicatesRefs(final List<Transaction> references) {

		List<Transaction> duplicates = new ArrayList<Transaction>();
		if (references == null || references.size() < 1)
			return duplicates;

		Set<String> lump = new HashSet<String>();
		List<String> lumpDuplicate = new ArrayList<String>();

		for (Transaction tran : references) {

			if (lump.contains(tran.getReference())) {
				lumpDuplicate.add(tran.getReference());
			} else {
				lump.add(tran.getReference());
			}
		}

		Set<String> uniqueTransRefs = new HashSet<String>(lumpDuplicate);

		for (String dupe : uniqueTransRefs) {
			for (Transaction dupTrans : references) {

				if (dupe.equalsIgnoreCase(dupTrans.getReference())) {
					duplicates.add(dupTrans);
				}

			}
		}

		return duplicates;
	}

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

}
