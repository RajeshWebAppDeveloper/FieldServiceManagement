package com.sy.fsm.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.DarExpensesDetails;








@Repository
public interface DarExpensesDetailsRepository extends JpaRepository<DarExpensesDetails,UUID>  {
	 
	@Query(value = "SELECT generate_category_sequence()",nativeQuery = true)
	String generateCategorySequence();
	
	@Query(value = "SELECT * from dar_expenses_details WHERE reference_id = :referenceId",nativeQuery = true)
	List<DarExpensesDetails> findByReferenceId(String referenceId);
}






