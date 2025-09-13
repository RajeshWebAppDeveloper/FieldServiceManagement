package com.sy.fsm.Repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.DefaultPropertiesDetails;






@Repository
public interface DefaultPropertiesRepository extends JpaRepository<DefaultPropertiesDetails, UUID>  {

	 @Query(value = "SELECT * FROM default_properties WHERE property_name= :propertyName", nativeQuery = true)
	 Optional<DefaultPropertiesDetails> findByPropertyName(String propertyName);	

}
