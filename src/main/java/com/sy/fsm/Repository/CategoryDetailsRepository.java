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






@Repository
public interface CategoryDetailsRepository extends JpaRepository<CategoryDetails,UUID>  {
	 
	@Query(value = "SELECT generate_category_sequence()",nativeQuery = true)
	String generateCategorySequence();
	
	 @Query(value = "SELECT * FROM getFilterCategoryDetailsList(?1,?2,?3,?4,?5)", nativeQuery = true)
	    List<CategoryDetails> getFilteredCategoryDetails(String categoryId,String categoryName,Date createdFromDate,Date createdToDate,String createdBy);

}
