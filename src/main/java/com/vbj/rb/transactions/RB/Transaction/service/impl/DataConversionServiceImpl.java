package com.vbj.rb.transactions.RB.Transaction.service.impl;

import java.io.IOException;
import java.io.StringReader;
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
	public List<Transaction> convertFromCSV(String str) {
		
		CSVReader csvReader = new CSVReader(new StringReader(str),
				',', '\'', 1);

		ColumnPositionMappingStrategy<Transaction> mappingStrategy = new ColumnPositionMappingStrategy<Transaction>();

		mappingStrategy.setType(Transaction.class);

		String[] columns = new String[] { "Reference", "AccountNumber",
				"Description", "Start Balance", "Mutation",
				"End Balance" };

		mappingStrategy.setColumnMapping(columns);

		CsvToBean<Transaction> csvToBean = new CsvToBean<Transaction>();
		
		return csvToBean.parse(mappingStrategy, csvReader);
	}

	@Override
	public List<Transaction> convertFromXML(String str) throws IOException {
		Transactions trans = (Transactions) xmlConverter.doUnMarshaling(str);		
		return trans.getTrans();
	}

}
