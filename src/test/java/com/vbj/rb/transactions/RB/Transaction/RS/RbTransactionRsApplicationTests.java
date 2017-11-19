package com.vbj.rb.transactions.RB.Transaction.RS;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import com.vbj.rb.transactions.RB.Transaction.RS.model.HttpResponse;
import com.vbj.rb.transactions.RB.Transaction.RS.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RbTransactionRsApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Ignore
	public void transactionTest() {
		String body = this.restTemplate.getForObject("/transactionTest",
				String.class);
		assertThat(body).isEqualTo(HttpStatus.OK.name());
	}

	@Test
	public void postCSVMP() throws IOException {

		System.out.println("\nTest 1"); // Using sysout instead of logging
										// framework to make manual verification convenient

		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		Resource resource = new ClassPathResource("records.csv");
		File uploadFile1 = new File(resource.getFile().getAbsolutePath());

		map.add("files", new FileSystemResource(uploadFile1));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(
				map, headers);
		HttpResponse response = restTemplate.postForObject("/uploadCSV",
				requestEntity, HttpResponse.class);

		System.out.println("Duplicate Transactions : "
				+ (response.getResults().get("duplicates").size() > 0 ? ""
						: "Empty"));
		for (Transaction tran : response.getResults().get("duplicates")) {
			System.out.println(tran);
		}

		System.out.println("Invalid Transactions : "
				+ (response.getResults().get("invalidBalance").size() > 0 ? ""
						: "Empty"));
		for (Transaction tran : response.getResults().get("invalidBalance")) {
			System.out.println(tran);
		}

		System.out.println("All Transactions : "
				+ (response.getResults().get("allTrans").size() > 0 ? ""
						: "Empty"));
		for (Transaction tran : response.getResults().get("allTrans")) {
			System.out.println(tran);
		}

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void postXMLMP() throws IOException {

		System.out.println("\nTest 2");

		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		Resource resource = new ClassPathResource("records.xml");
		File uploadFile1 = new File(resource.getFile().getAbsolutePath());

		map.add("files", new FileSystemResource(uploadFile1));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(
				map, headers);
		HttpResponse response = restTemplate.postForObject("/uploadXML",
				requestEntity, HttpResponse.class);

		System.out.println("Duplicate Transactions : "
				+ (response.getResults().get("duplicates").size() > 0 ? ""
						: "Empty"));
		for (Transaction tran : response.getResults().get("duplicates")) {
			System.out.println(tran);
		}

		System.out.println("Invalid Transactions : "
				+ (response.getResults().get("invalidBalance").size() > 0 ? ""
						: "Empty"));
		for (Transaction tran : response.getResults().get("invalidBalance")) {
			System.out.println(tran);
		}

		System.out.println("All Transactions : "
				+ (response.getResults().get("allTrans").size() > 0 ? ""
						: "Empty"));
		for (Transaction tran : response.getResults().get("allTrans")) {
			System.out.println(tran);
		}

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
	}
}
