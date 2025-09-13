package com.sy.fsm.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.DarExpensesDetails;
import com.sy.fsm.Model.EstimationProductDetails;
import com.sy.fsm.Model.MobileOrderProductDetails;
import com.sy.fsm.Model.OrderProductDetails;








@Repository
public interface MobileOrderProductDetailsRepository extends JpaRepository<MobileOrderProductDetails,UUID>  {
	 
	
	@Query(value = "SELECT * from order_product_details WHERE reference_id = :referenceId",nativeQuery = true)
	List<MobileOrderProductDetails> findByReferenceId(String referenceId);
	
	@Query(value = "SELECT sum(total) from order_product_details WHERE reference_id = :referenceId",nativeQuery = true)
	int findByReferenceIdAndResetTotalProduct(String referenceId);
}
