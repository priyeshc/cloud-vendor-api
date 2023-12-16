package com.cloudvendor.thinkconstructive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudvendor.thinkconstructive.exception.CloudVendorNotFoundException;
import com.cloudvendor.thinkconstructive.model.CloudVendor;
import com.cloudvendor.thinkconstructive.repository.CloudVendorRepository;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

	@Autowired
	CloudVendorRepository cloudVendorRepository;

	public String createCloudVendor(CloudVendor cloudVendor) {
		// More Business Logic
		cloudVendorRepository.save(cloudVendor);
		return "Success";
	}

	@Override
	public String updateCloudVendor(CloudVendor cloudVendor) {
		// More Business Logic
		cloudVendorRepository.save(cloudVendor);
		return "Success";
	}

	@Override
	public String deleteCloudVendor(String cloudVendorId) {
		// More Business Logic
		cloudVendorRepository.deleteById(cloudVendorId);
		return "Success";
	}

	@Override
	public CloudVendor getCloudVendor(String cloudVendorId) {
		// More Business Logic
		if (cloudVendorRepository.findById(cloudVendorId).isEmpty())
			throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");

			return cloudVendorRepository.findById(cloudVendorId).get();
	}

	@Override
	public List<CloudVendor> getAllCloudVendors() {
		// More Business Logic
		return cloudVendorRepository.findAll();
	}

}
