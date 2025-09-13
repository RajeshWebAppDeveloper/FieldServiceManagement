package com.sy.fsm.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.MobileProductDetails;
import com.sy.fsm.Model.ProductDetails;








@Repository
public interface MobileProductDetailsRepository extends JpaRepository<MobileProductDetails,UUID>  {
	 
	@Query(value = "SELECT generate_product_sequence()",nativeQuery = true)
	String generateProductSequence();
	

	@Query(value = "SELECT * from product_details WHERE product_name = :productName",nativeQuery = true)
	Optional<MobileProductDetails> findByProductName(String productName);
	
	@Query(value = "SELECT * from product_details WHERE product_id = :productId",nativeQuery = true)
	Optional<MobileProductDetails> findByProductId(String productId);
	
	@Query(value = "SELECT count(*) from product_details",nativeQuery = true)
	int getProductDetailsCount();
	
	@Query(value = "SELECT * from product_details WHERE product_category = :categoryName",nativeQuery = true)
	List<MobileProductDetails> findByCategory(String categoryName);

}













