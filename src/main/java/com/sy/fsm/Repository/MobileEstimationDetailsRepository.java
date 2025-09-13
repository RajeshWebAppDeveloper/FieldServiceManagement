package com.sy.fsm.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.CategoryDetails;
import com.sy.fsm.Model.DarDetails;
import com.sy.fsm.Model.EstimationDetails;
import com.sy.fsm.Model.MobileEstimationDetails;






@Repository
public interface MobileEstimationDetailsRepository extends JpaRepository<MobileEstimationDetails,UUID>  {
	 
	@Query(value = "SELECT generate_estimation_sequence()",nativeQuery = true)
	String generateEstimationSequence();
	
	@Query(value = "SELECT * FROM getFSMUserIdsBasedEstimationDetailsList(:userIds)", nativeQuery = true)
	List<MobileEstimationDetails> getFSMUserIdsBasedEstimationDetailsList(String userIds);
	
	@Query(value = "SELECT count(*) FROM getFSMUserIdsBasedEstimationDetailsList(:userIds)", nativeQuery = true)
	int getFSMUserIdsBasedEstimationDetailsListCount(String userIds);
	
	@Query(value = "SELECT * FROM estimation_details Where est_no= :estNo",nativeQuery = true)
	 Optional<MobileEstimationDetails> findByEstNo(String estNo);

}
