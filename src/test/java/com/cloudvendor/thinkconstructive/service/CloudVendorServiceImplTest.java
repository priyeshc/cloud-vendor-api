package com.cloudvendor.thinkconstructive.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import com.cloudvendor.thinkconstructive.model.CloudVendor;
import com.cloudvendor.thinkconstructive.repository.CloudVendorRepository;

@Service
class CloudVendorServiceImplTest {

	@Mock
	CloudVendorRepository cloudVendorRepository;

	@InjectMocks
	CloudVendorServiceImpl cloudVendorService;

	AutoCloseable autoCloseable;

	@Mock
	CloudVendor cloudVendor;

	@BeforeEach
	void setUp() throws Exception {

		autoCloseable = MockitoAnnotations.openMocks(this);
		// cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
		cloudVendor = new CloudVendor("1", "Amazon", "USA", "xxxxx");

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateCloudVendor() {
		
		
        
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        // Act
        String result = cloudVendorService.createCloudVendor(cloudVendor);

        // Assert
        assertEquals("Success", result);

	}

	@Test
	void testUpdateCloudVendor() {
		
		when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        // Act
        String result = cloudVendorService.updateCloudVendor(cloudVendor);

        // Assert
        assertEquals("Success", result); 

	}

	@Test
	void testDeleteCloudVendor() {
		
		String cloudVendorId = "1";
		
		doNothing().when(cloudVendorRepository).deleteById(cloudVendorId);

        // Act
        String result = cloudVendorService.deleteCloudVendor(cloudVendorId);

        // Assert
        assertEquals("Success", result);  // Adjust this based on your actual service logic

	}

	@Test
	void testGetCloudVendor() {

		// String cloudVendorId = "aws";
	       // CloudVendor expectedCloudVendor = new CloudVendor(cloudVendorId, "Amazon Web Services");

	        // Mock the behavior of the JpaRepository findById method
	        when(cloudVendorRepository.findById(cloudVendor.getVendorId())).thenReturn(Optional.of(cloudVendor));

	        // Act
	        CloudVendor actualCloudVendor = cloudVendorService.getCloudVendor("1");

	        // Assert
	        assertEquals(cloudVendor, actualCloudVendor);
	       // verify(cloudVendorRepository, times(1)).findById("1");
	}

	@Test
	void testGetAllCloudVendors() {
		
		List<CloudVendor> expectedCloudVendors = Arrays.asList(
				new CloudVendor("1", "Amazon", "USA", "XXXX"),
				new CloudVendor("2", "GCP", "USA", "XXXX"),
				new CloudVendor("3", "Microsoft", "USA", "XXXX")
				);
		
		when(cloudVendorRepository.findAll()).thenReturn(expectedCloudVendors);
		
		List<CloudVendor> actualCloudVendors = cloudVendorService.getAllCloudVendors();
		
		assertEquals(expectedCloudVendors, actualCloudVendors);
	}

}
