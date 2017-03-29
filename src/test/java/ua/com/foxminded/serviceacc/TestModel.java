package ua.com.foxminded.serviceacc;

import org.junit.Test;

import ua.com.foxminded.serviceacc.model.Client;

public class TestModel {

	@Test
	public void TestLevelClient() {
		Client client = new Client();
		System.out.println("client level is = " + client.getLevel());
		client.increaseLevel();
		System.out.println("client level is = " + client.getLevel());
	}

}
