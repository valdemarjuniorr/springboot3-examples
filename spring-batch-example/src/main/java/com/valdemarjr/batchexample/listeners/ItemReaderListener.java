package com.valdemarjr.batchexample.listeners;

import com.valdemarjr.batchexample.domain.Coffee;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.OnReadError;

public class ItemReaderListener  {

	@BeforeRead
	public void beforeRead() {
		System.out.println("Before Read");
	}

	@AfterRead
	public void afterRead(Coffee coffee) {
		System.out.println("After Read");
	}

	@OnReadError
	public void onReadError(Exception e) {
		System.out.println("On Read Error");
	}

}
