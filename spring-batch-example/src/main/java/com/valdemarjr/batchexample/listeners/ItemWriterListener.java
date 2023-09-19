package com.valdemarjr.batchexample.listeners;

import com.valdemarjr.batchexample.domain.Coffee;
import java.util.List;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnWriteError;

public class ItemWriterListener {

	@BeforeWrite
	public void beforeWrite(List<Coffee> items) {
		System.out.println("Before Write");
	}

	@AfterWrite
	public void afterWrite(List<Coffee> items) {
		System.out.println("After Write");
	}

	@OnWriteError
	public void onWriteError(Exception exception, List<Coffee> items) {
		System.out.println("On Write Error");
	}


}
