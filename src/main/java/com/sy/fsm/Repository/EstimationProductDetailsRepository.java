package com.sy.fsm.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.DarExpensesDetails;
import com.sy.fsm.Model.EstimationProductDetails;








@Repository
public interface EstimationProductDetailsRepository extends JpaRepository<EstimationProductDetails,UUID>  {
	 
	
	@Query(value = "SELECT * from estimation_product_details WHERE reference_id = :referenceId",nativeQuery = true)
	List<EstimationProductDetails> findByReferenceId(String referenceId);
}
