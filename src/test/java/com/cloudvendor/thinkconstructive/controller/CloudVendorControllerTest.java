package com.cloudvendor.thinkconstructive.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cloudvendor.thinkconstructive.model.CloudVendor;
import com.cloudvendor.thinkconstructive.service.CloudVendorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith({ SpringExtension.class, MockitoExtension.class })
@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {

	@MockBean
	private CloudVendorService cloudVendorService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetCloudVendorDetails() throws Exception {
		String vendorId = "1";
		CloudVendor mockCloudVendor = new CloudVendor(vendorId, "Amazon Web Services", "Cloud Street", "123-456-7890");

		when(cloudVendorService.getCloudVendor(vendorId)).thenReturn(mockCloudVendor);

		mockMvc.perform(get("/cloudvendor/{vendorId}", vendorId)).andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.data.vendorId").value(vendorId))
				.andExpect(jsonPath("$.data.vendorName").value("Amazon Web Services"))
				.andExpect(jsonPath("$.data.vendorAddress").value("Cloud Street"))
				.andExpect(jsonPath("$.data.vendorPhoneNumber").value("123-456-7890"));

		verify(cloudVendorService, times(1)).getCloudVendor(vendorId);

	}

	@Test
	void testGetAllCloudVendorDetails() throws Exception {

		List<CloudVendor> mockCloudVendors = Arrays.asList(
				new CloudVendor("aws", "Amazon Web Services", "Cloud Street", "123-456-7890"),
				new CloudVendor("azure", "Microsoft Azure", "Cloud Avenue", "987-654-3210"));

		when(cloudVendorService.getAllCloudVendors()).thenReturn(mockCloudVendors);

		mockMvc.perform(get("/cloudvendor/")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray());

		verify(cloudVendorService, times(1)).getAllCloudVendors();
	}

	@Test
	void testCreateCloudVendorDetails() throws Exception {
		CloudVendor newCloudVendor = new CloudVendor("gcp", "Google Cloud Platform", "Cloud Road", "111-222-3333");
		String requestBody = new ObjectMapper().writeValueAsString(newCloudVendor);

		mockMvc.perform(post("/cloudvendor/").content(requestBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string("Cloud Vendor Created Successfully"));

		// verify(cloudVendorService, times(1)).createCloudVendor(newCloudVendor);
	}

	@Test
	void testUpdateCloudVendorDetails() throws Exception {
		CloudVendor updatedCloudVendor = new CloudVendor("aws", "Updated AWS", "Updated Address", "555-555-5555");
		String requestBody = new ObjectMapper().writeValueAsString(updatedCloudVendor);

		mockMvc.perform(put("/cloudvendor/").content(requestBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string("Cloud Vendor Updated Successfully"));

		//verify(cloudVendorService, times(1)).updateCloudVendor(updatedCloudVendor);
	}

	@Test
	void testDeleteCloudVendorDetails() throws Exception {
		String vendorId = "aws";

		mockMvc.perform(delete("/cloudvendor/{vendorId}", vendorId)).andExpect(status().isOk())
				.andExpect(content().string("Cloud Vendor Deleted Successfully"));

		verify(cloudVendorService, times(1)).deleteCloudVendor(vendorId);
	}

}
