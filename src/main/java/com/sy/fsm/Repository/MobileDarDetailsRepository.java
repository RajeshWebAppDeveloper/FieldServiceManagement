package com.sy.fsm.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.CategoryDetails;
import com.sy.fsm.Model.DarDetails;
import com.sy.fsm.Model.MobileDarDetails;
import com.sy.fsm.Model.UserDetails;






@Repository
public interface MobileDarDetailsRepository extends JpaRepository<MobileDarDetails,UUID>  {
	 
	@Query(value = "SELECT generate_dar_sequence()",nativeQuery = true)
	String generateDarSequence();
	
	@Query(value = "SELECT * FROM getFSMUserIdsBasedDarDetailsList(:userIds)", nativeQuery = true)
	List<MobileDarDetails> getFSMUserIdsBasedDarDetailsList(String userIds);
	
	@Query(value = "SELECT count(*) FROM getFSMUserIdsBasedDarDetailsList(:userIds)", nativeQuery = true)
	int getFSMUserIdsBasedDarDetailsListCount(String userIds);

}
