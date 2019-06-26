package com.ox.bank.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ox.bank.Exception.LoanException;
import com.ox.bank.entity.Loan;
import com.ox.bank.service.LoanService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private LoanService loanSerive;

	@MockBean
	private LoanController loanController;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void createLoanTest() throws Exception {

		// Officer officer = new Officer(10); //Customer customer = new Customer(10);

		// Positive case
		Loan loan = new Loan(10, "pending", 500, 10, 10);
		ObjectMapper om = new ObjectMapper();
		String expected = om.writeValueAsString(loan);
		Mockito.when(loanSerive.createLoan(loan)).thenReturn("Loan Created!!Pending for Approval");
		mvc.perform(
				MockMvcRequestBuilders.post("/createLoan").contentType(MediaType.APPLICATION_JSON).content(expected))
				.andExpect(status().isOk()).andDo(print());

		// Negative case
		loan = null;
		ObjectMapper omForNegative = new ObjectMapper();
		String expectedForNegative = omForNegative.writeValueAsString(loan);

		Mockito.when(loanSerive.createLoan(loan)).thenReturn("Loan not created!! Please contact support team");
		mvc.perform(MockMvcRequestBuilders.post("/createLoan").contentType(MediaType.APPLICATION_JSON)
				.content(expectedForNegative)).andExpect(status().isBadRequest()).andDo(print());

	}

	@Test
	public void loanApproval() throws Exception {

		// positive case
		long loanId = 7L;
		ObjectMapper om = new ObjectMapper();
		String expected = om.writeValueAsString(loanId);

		Mockito.when(loanSerive.loanApproval(loanId)).thenReturn("Loan Approved!!");

		mvc.perform(
				MockMvcRequestBuilders.put("/loanApproval/{loanId}",loanId).contentType(MediaType.APPLICATION_JSON).
				content(expected))
				.andExpect(status().isOk()).andDo(print());
		
		/*
		 * ResponseEntity<String> actual = loanController.loanApproval(loanId);
		 * 
		 * System.out.println(actual);
		 * 
		 * assertEquals("Loan Approved!!", actual.getBody());
		 */

	}
}
