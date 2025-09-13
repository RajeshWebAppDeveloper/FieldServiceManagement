package com.sy.fsm.Repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.sy.fsm.Model.ProductDetails;








@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails,UUID>  {
	 
	@Query(value = "SELECT generate_product_sequence()",nativeQuery = true)
	String generateProductSequence();
	

	@Query(value = "SELECT * from product_details WHERE product_name = :productName",nativeQuery = true)
	Optional<ProductDetails> findByProductName(String productName);
	
	@Query(value = "SELECT * from product_details WHERE product_id = :productId",nativeQuery = true)
	Optional<ProductDetails> findByProductId(String productId);
	
	@Query(value = "SELECT count(*) from product_details",nativeQuery = true)
	int getProductDetailsCount();
	
	@Query(value = "SELECT * FROM getFilterProductDetailsList(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    List<ProductDetails> getFilteredProductDetails(String productId,String productName,String productCategory,Date createdFromDate,Date createdToDate,String createdBy);

}













