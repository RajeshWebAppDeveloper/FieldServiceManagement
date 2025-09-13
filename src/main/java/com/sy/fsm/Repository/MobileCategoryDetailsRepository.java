package com.sy.fsm.Repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.CategoryDetails;
import com.sy.fsm.Model.MobileCategoryDetails;






@Repository
public interface MobileCategoryDetailsRepository extends JpaRepository<MobileCategoryDetails,UUID>  {
	 
	@Query(value = "SELECT generate_category_sequence()",nativeQuery = true)
	String generateCategorySequence();

}
