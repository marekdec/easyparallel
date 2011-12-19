package com.googlecode.easyparallel;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class GoogleDatastoreTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
				new LocalDatastoreServiceTestConfig());

	public GoogleDatastoreTest() {
		super();
	}

	@BeforeTest
	public void setUp() {
		helper.setUp();
	}

	@AfterTest
	public void tearDown() {
		helper.tearDown();
	}

}