package com.cloudvendor.thinkconstructive.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cloudvendor.thinkconstructive.model.CloudVendor;

@DataJpaTest
class CloudVendorRepositoryTest {

	@Autowired
	private CloudVendorRepository cloudVendorRepository;
	CloudVendor cloudVendor;

	@BeforeEach
	void setUp() throws Exception {

		cloudVendor = new CloudVendor("1", "Amazon", "USA", "xxxxx");
		cloudVendorRepository.save(cloudVendor);
	}

	@AfterEach
	void tearDown() throws Exception {

		cloudVendor = null;
		cloudVendorRepository.deleteAll();
	}

	// Test case SUCCESS
	@Test
	void testFindByVendorName_Found() {
		List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("Amazon");
		assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
		assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
	}

	// Test case FAILURE
	@Test
	void testFindByVendorName_NotFound() {
		List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("GCP");
		assertThat(cloudVendorList.isEmpty()).isTrue();
	}

}
