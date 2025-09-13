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
import com.sy.fsm.Model.UserDetails;






@Repository
public interface DarDetailsRepository extends JpaRepository<DarDetails,UUID>  {
	 
	@Query(value = "SELECT generate_dar_sequence()",nativeQuery = true)
	String generateDarSequence();
	
	@Query(value = "SELECT * FROM getFSMUserIdsBasedDarDetailsList(:userIds)", nativeQuery = true)
	List<DarDetails> getFSMUserIdsBasedDarDetailsList(String userIds);
	
	@Query(value = "SELECT count(*) FROM getFSMUserIdsBasedDarDetailsList(:userIds)", nativeQuery = true)
	int getFSMUserIdsBasedDarDetailsListCount(String userIds);
	
	@Query(value = "SELECT * FROM getFilterDarDetailsList(?1,?2,?3,?4,?5,?6,?7,?8,?9)", 
            nativeQuery = true)
    List<DarDetails> getFilteredDarDetails(String darNo,Date darPerformFromDate,Date darPerformToDate,String clientName,String clientMobileNo,String statusToVisit,Date createdFromDate,Date createdToDate,String createdBy);
}
