package com.sy.fsm.Repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.CategoryDetails;
import com.sy.fsm.Model.DarDetails;
import com.sy.fsm.Model.EstimationDetails;






@Repository
public interface EstimationDetailsRepository extends JpaRepository<EstimationDetails,UUID>  {
	 
	@Query(value = "SELECT generate_estimation_sequence()",nativeQuery = true)
	String generateEstimationSequence();
	
	@Query(value = "SELECT * FROM getFSMUserIdsBasedEstimationDetailsList(:userIds)", nativeQuery = true)
	List<EstimationDetails> getFSMUserIdsBasedEstimationDetailsList(String userIds);
	
	@Query(value = "SELECT count(*) FROM getFSMUserIdsBasedEstimationDetailsList(:userIds)", nativeQuery = true)
	int getFSMUserIdsBasedEstimationDetailsListCount(String userIds);
	
	 @Query(value = "SELECT * FROM getFilterEstimationDetailsList(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12)",nativeQuery = true)
	 List<EstimationDetails> getFilteredEstimationDetails(String estNo,String customerName,Date estimationPerformFromDate,Date estimationPerformToDate,String repAttD,String repAccount,String mobileNo,String itsHaveDiscount,String estimationStatus,Date createdFromDate,Date createdToDate,String createdBy);
	 
	 @Query(value = "SELECT * FROM estimation_details Where est_no= :estNo",nativeQuery = true)
	 Optional<EstimationDetails> findByEstNo(String estNo);
	 
}
