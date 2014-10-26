package com.maestrano.account;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.maestrano.Maestrano;
import com.maestrano.helpers.MnoDateHelper;

public class MnoUserIntegrationTest {
	private Properties props = new Properties();

	@Before
	public void beforeEach() {
		props.setProperty("app.environment", "test");
		props.setProperty("api.id", "app-1");
		props.setProperty("api.key", "gfcmbu8269wyi0hjazk4t7o1sndpvrqxl53e1");
		Maestrano.configure(props);
	}

	@Test
	public void all_itRetrievesAllUsers() throws Exception {
		List<MnoUser> list = MnoUser.all();
		MnoUser entity = null;
		for (MnoUser elem : list) {
			if (elem.getId().equals("usr-1")) entity = elem;
		}
		
		assertEquals("usr-1",entity.getId());
		assertEquals("John",entity.getName());
		assertEquals("Doe",entity.getSurname());
		assertEquals("2014-05-21T00:32:35Z",MnoDateHelper.toIso8601(entity.getCreatedAt()));
	}

	@Test 
	public void retrieve_itRetrievesASingleUser() throws Exception {
		MnoUser entity = MnoUser.retrieve("usr-1");

		assertEquals("usr-1",entity.getId());
		assertEquals("John",entity.getName());
		assertEquals("Doe",entity.getSurname());
		assertEquals("2014-05-21T00:32:35Z",MnoDateHelper.toIso8601(entity.getCreatedAt()));
	}
}