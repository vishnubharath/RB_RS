package com.vbj.rb.transactions.RB.Transaction.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.vbj.rb.transactions.RB.Transaction.RS.model.Transaction;
import com.vbj.rb.transactions.RB.Transaction.RS.model.Transactions;
import com.vbj.rb.transactions.RB.Transaction.RS.util.Converter;
import com.vbj.rb.transactions.RB.Transaction.service.DataConversionService;

@Service
public class DataConversionServiceImpl implements DataConversionService {

	@Resource
	Converter xmlConverter;

	@Override
	public List<Transaction> convertFromCSV(String str) throws IOException {

		List<Transaction> trans = new ArrayList<Transaction>();
		
		if(str == null || str.isEmpty()  ) return trans;
		CSVReader csvReader = new CSVReader(new StringReader(str), ',', '\'', 1);

		String[] nextLine;

		try {
			while ((nextLine = csvReader.readNext()) != null) {

				Transaction tran = new Transaction();
				
				tran.setReference(nextLine[0].trim());
				tran.setAccountNumber(nextLine[1].trim());
				tran.setDescription(nextLine[2].trim());
				tran.setStartBalance(nextLine[3].trim());
				tran.setMutation(nextLine[4].trim());
				tran.setEndingBalance(nextLine[5].trim());
				
				trans.add(tran);

			}
		} catch (IOException e) {
			throw e;
		}
		
		return trans;

	}

	@Override
	public List<Transaction> convertFromXML(String str) throws IOException {
		Transactions trans = (Transactions) xmlConverter.doUnMarshaling(str);
		return trans.getTrans();
	}

}
