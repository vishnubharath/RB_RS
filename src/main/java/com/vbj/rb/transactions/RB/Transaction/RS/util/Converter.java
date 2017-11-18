package com.vbj.rb.transactions.RB.Transaction.RS.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

public class Converter {
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	// Converts Object to XML file
	public void doMarshaling(Object graph) throws IOException {
			marshaller.marshal(graph, new StreamResult(System.out));
			
	}

	// Converts XML to Java Object
	public Object doUnMarshaling(String xmlString) throws IOException {
		Source source=new StreamSource(new StringReader(xmlString));
		return unmarshaller.unmarshal(source);
		
	}
}