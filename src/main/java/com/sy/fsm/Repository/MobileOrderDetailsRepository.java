package com.sy.fsm.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.EstimationDetails;
import com.sy.fsm.Model.MobileOrderDetails;
import com.sy.fsm.Model.OrderDetails;
import com.sy.fsm.Model.OrderProductDetails;






@Repository
public interface MobileOrderDetailsRepository extends JpaRepository<MobileOrderDetails,UUID>  {
	 
	@Query(value = "SELECT generate_order_sequence()",nativeQuery = true)
	String generateOrderSequence();
	
	@Query(value = "SELECT * from order_details WHERE est_no= :estNo",nativeQuery = true)
	Optional<MobileOrderDetails> findByEstNo(String estNo);
	
	@Query(value = "SELECT * FROM getFSMUserIdsBasedOrderDetailsList(:userIds)", nativeQuery = true)
	List<MobileOrderDetails> getFSMUserIdsBasedOrderDetailsList(String userIds);
	
	@Query(value = "SELECT count(*) FROM getFSMUserIdsBasedOrderDetailsList(:userIds)", nativeQuery = true)
	int getFSMUserIdsBasedOrderDetailsListCount(String userIds);

}
