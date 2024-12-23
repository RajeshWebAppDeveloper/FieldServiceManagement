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
import com.sy.fsm.Model.PaymentDetails;






@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails,UUID>  {
	 
	
	
	@Query(value = "SELECT * from payment_details WHERE est_no= :estNo",nativeQuery = true)
	Optional<PaymentDetails> findByEstNo(String estNo);
	
	@Query(value = "SELECT * FROM getFSMUserIdsBasedPaymentDetailsList(:userIds)", nativeQuery = true)
	List<PaymentDetails> getFSMUserIdsBasedPaymentDetailsList(String userIds);
	
	@Query(value = "SELECT count(*) FROM getFSMUserIdsBasedPaymentDetailsList(:userIds)", nativeQuery = true)
	int getFSMUserIdsBasedPaymentDetailsListCount(String userIds);
	
	@Query(value = "SELECT * FROM getFilterPaymentsDetailsList(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17, ?18)", nativeQuery = true)
	List<PaymentDetails> getFilteredPaymentDetails(
	    String eNo, String estNo, String orderNo, String soNo, String ddNo, String customerName,
	    Date orderPerformFromDate, Date orderPerformToDate, String repAttD, String mobileNo,
	    String demoPlan, Date demoFromDate, Date demoToDate, String itsHaveDiscount,
	    String paymentStatus, Date createdFromDate, Date createdToDate, String createdBy
	);


}
