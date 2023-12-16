package com.cloudvendor.thinkconstructive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudvendor.thinkconstructive.model.CloudVendor;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, String>{
	
	List<CloudVendor> findByVendorName(String name);

}
