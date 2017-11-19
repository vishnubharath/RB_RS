package com.vbj.rb.transactions.RB.Transaction.RS.model;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class HttpResponse {

	Map<String, List<Transaction>> results;
	HttpStatus status;

	public Map<String, List<Transaction>> getResults() {
		return results;
	}

	public void setResults(Map<String, List<Transaction>> results) {
		this.results = results;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
