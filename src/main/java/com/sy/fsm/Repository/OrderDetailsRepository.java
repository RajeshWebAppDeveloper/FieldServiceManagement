package com.sy.fsm.Repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.EstimationDetails;
import com.sy.fsm.Model.OrderDetails;
import com.sy.fsm.Model.OrderProductDetails;






@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,UUID>  {
	 
	@Query(value = "SELECT generate_order_sequence()",nativeQuery = true)
	String generateOrderSequence();
	
	@Query(value = "SELECT * from order_details WHERE est_no= :estNo",nativeQuery = true)
	Optional<OrderDetails> findByEstNo(String estNo);
	
	@Query(value = "SELECT * FROM getFSMUserIdsBasedOrderDetailsList(:userIds)", nativeQuery = true)
	List<OrderDetails> getFSMUserIdsBasedOrderDetailsList(String userIds);
	
	@Query(value = "SELECT count(*) FROM getFSMUserIdsBasedOrderDetailsList(:userIds)", nativeQuery = true)
	int getFSMUserIdsBasedOrderDetailsListCount(String userIds);
	
	@Query(value = "SELECT * FROM getFilterOrderDetailsList(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17, ?18)", nativeQuery = true)
	List<OrderDetails> getFilteredOrderDetails(
	    String eNo, String estNo, String orderNo, String soNo, String ddNo, String customerName,
	    Date orderPerformFromDate, Date orderPerformToDate, String repAttD, String mobileNo,
	    String demoPlan, Date demoFromDate, Date demoToDate, String itsHaveDiscount,
	    String orderStatus, Date createdFromDate, Date createdToDate, String createdBy
	);


}
