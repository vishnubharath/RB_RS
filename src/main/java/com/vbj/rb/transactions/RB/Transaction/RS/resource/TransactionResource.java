package com.vbj.rb.transactions.RB.Transaction.RS.resource;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.vbj.rb.transactions.RB.Transaction.RS.exceptions.DuplicateReferenceIDException;
import com.vbj.rb.transactions.RB.Transaction.RS.model.Transaction;
import com.vbj.rb.transactions.RB.Transaction.service.DataConversionService;
import com.vbj.rb.transactions.RB.Transaction.service.TransValidationService;

@RestController
public class TransactionResource {

	@Autowired
	private TransValidationService transValidationService;
	
	@Autowired
	private DataConversionService conversionService;

	@GET
	@RequestMapping("/transactionTest")
	public String getTransactionHealth() {
		return "OK";
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@RequestMapping(value = "/uploadCSV")
	public String uploadCSV(@RequestParam("files") MultipartFile[] files) {
		
		List<Transaction> trans = new ArrayList<Transaction>();

		for (MultipartFile file : files) {
			try {
				String content = new String(file.getBytes());
				trans = conversionService.convertFromCSV(content);

			} catch (IOException e) {
				return HttpStatus.UNSUPPORTED_MEDIA_TYPE.name();
			}

			try {
				transValidationService.vaidate(trans);
			} catch (DuplicateReferenceIDException e) {
				return HttpStatus.NOT_ACCEPTABLE.name();
			}

		}

		return HttpStatus.OK.name();
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@RequestMapping(value = "/uploadXML")
	public String uploadXML(@RequestParam("files") MultipartFile[] files) {
		
		List<Transaction> trans = new ArrayList<Transaction>();

		for (MultipartFile file : files) {
			try {
				String content = new String(file.getBytes());
				trans = conversionService.convertFromXML(content);

			} catch (IOException e) {
				return HttpStatus.UNSUPPORTED_MEDIA_TYPE.name();
			}

			try {
				transValidationService.vaidate(trans);
			} catch (DuplicateReferenceIDException e) {
				return HttpStatus.NOT_ACCEPTABLE.name();
			}

		}

		return HttpStatus.OK.name();
	}
}