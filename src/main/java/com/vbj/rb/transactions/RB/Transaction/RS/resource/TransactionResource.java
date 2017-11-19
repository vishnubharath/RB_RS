package com.vbj.rb.transactions.RB.Transaction.RS.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vbj.rb.transactions.RB.Transaction.RS.model.HttpResponse;
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
	public HttpResponse uploadCSV(@RequestParam("files") MultipartFile[] files) {

		HttpResponse resp = new HttpResponse();

		List<Transaction> trans = new ArrayList<Transaction>();

		for (MultipartFile file : files) {
			try {
				String content = new String(file.getBytes());
				trans = conversionService.convertFromCSV(content);

			} catch (IOException e) {
				resp.setStatus(HttpStatus.BAD_REQUEST);
				return resp;
			}
		}

		Map<String, List<Transaction>> result = transValidationService
				.vaidate(trans);

		resp.setResults(result);
		resp.setStatus(HttpStatus.OK);

		return resp;
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@RequestMapping(value = "/uploadXML")
	public HttpResponse uploadXML(@RequestParam("files") MultipartFile[] files) {

		HttpResponse resp = new HttpResponse();
		List<Transaction> trans = new ArrayList<Transaction>();

		for (MultipartFile file : files) {
			try {
				String content = new String(file.getBytes());
				trans = conversionService.convertFromXML(content);

			} catch (IOException e) {
				resp.setStatus(HttpStatus.BAD_REQUEST);
				return resp;
			}

		}

		Map<String, List<Transaction>> result = transValidationService.vaidate(trans);
		resp.setResults(result);
		resp.setStatus(HttpStatus.OK);

		return resp;
	}
}