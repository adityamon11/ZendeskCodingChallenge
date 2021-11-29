package com.zcc.aditya.ZccAditya;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.zcc.aditya.ZccAditya.Controller.ZccController;

@SpringBootTest
class ZccAdityaApplicationTests {

	private MockMvc mockMvc;

	private static ZccController zccController;

	public void mockBuilder() {
		zccController = new ZccController();
		mockMvc = MockMvcBuilders.standaloneSetup(zccController).build();
	}

	@Test
	public void testTickets() throws Exception {

		mockBuilder();
		mockMvc.perform(get("/tickets/info")).andExpect(status().isOk()).andExpect(view().name("tickets"));
	}

	@Test
	public void testInvalidUrl() throws Exception {

		mockBuilder();
		mockMvc.perform(get("/tickets/inf")).andExpect(status().isNotFound());

	}

	@Test
	public void testViewTicket() throws Exception {
		mockBuilder();
		mockMvc.perform(get("/tickets/view/1")).andExpect(status().isOk()).andExpect(view().name("viewTicket"));
	}

	@Test
	void contextLoads() {
	}

}
