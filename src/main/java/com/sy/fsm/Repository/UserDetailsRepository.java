package com.sy.fsm.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sy.fsm.Model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, UUID>  {
	
	@Query(value = "SELECT * FROM user_details WHERE role_name= :roleName", nativeQuery = true)
	Optional<UserDetails> findByRoleName(String roleName);
	
	@Query(value = "SELECT count(*) FROM user_details",nativeQuery = true)
	int getNumberOfuserDetails();
	
	@Query(value = "SELECT * FROM user_details WHERE user_name= :userName", nativeQuery = true)
	Optional<UserDetails> findByUserName(String userName);
	
	@Query(value = "SELECT generate_user_id_sequence()",nativeQuery = true)
	String generateUserIdSequence();
	
	@Query(value = "SELECT reset_user_id_sequence()",nativeQuery = true)
	String resetUserIdSequence();
	
	@Query(value = "SELECT getUserBasedTeamMemberList(:userId)",nativeQuery = true)
	String getUserBasedTeamMemberUserIds(String userId);
	
	@Query(value = "SELECT * FROM user_details WHERE leader_user_id= :leaderUserId", nativeQuery = true)
	List<UserDetails> findByLeadUserId(String leaderUserId);
	
	@Query(value = "SELECT * FROM user_details WHERE mobile_no= :mobileNo AND password= :password", nativeQuery = true)
	Optional<UserDetails> findByMoblieNoAndPassword(String mobileNo,String password);
	
	 @Query(value = "SELECT * FROM getFilterUsersDetailsList(?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
	    List<UserDetails> getFilteredUserDetails(String userId,String userName,String mobileNo,String roleName,String branchName,String repCode,String repAccount,String leaderUserId);
}
