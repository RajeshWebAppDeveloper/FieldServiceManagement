package com.sy.fsm.WebController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sy.fsm.Model.UserDetails;
import com.sy.fsm.ErrorException.SessionExpiredException;

import com.sy.fsm.Repository.UserDetailsRepository;


@Controller
public class WebController {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	
	@RequestMapping(value = "/fsm/login", method = RequestMethod.GET)
    public String getLoginForm(@RequestParam(value = "status", required = false, defaultValue ="") String status,@RequestParam(value = "validationMessage", required = false, defaultValue = "") String validationMessage,Model model) {
		System.out.println("/fsm/Login :: Rendering...");
		if (status == null || validationMessage == null) {		
            return "fsmLogin";
        }
		model.addAttribute("status",status);
		model.addAttribute("validationMessage",validationMessage);
        return "fsmLogin";
    }
	
	@ExceptionHandler(SessionExpiredException.class)
    public String handleSessionExpired() {
		System.out.println("Session Out::");
        return "fsmLogin";
    }
	
	@GetMapping("/fsm/Admin")
    public String getAdminIndexForm(@RequestParam(value = "userId", required = false, defaultValue ="") String userId
    								,@RequestParam(value = "userName", required = false, defaultValue ="") String userName
    								,@RequestParam(value = "roleName", required = false, defaultValue ="") String roleName
    								,@RequestParam(value = "profileImageName", required = false, defaultValue ="") String profileImageName
    								,@RequestParam(value = "userRights", required = false, defaultValue ="") String userRights
    								,Model model) {
		
		System.out.println("/fsm/admin :: Rendering..."+userRights+"<<>>"+userId+"<<>>"+userName+"<<>>"+roleName+"<<>>"+profileImageName+"<<>>");		
		if (userName == null) {
            return "fsmIndex";
        }
		model.addAttribute("userId",userId);
		model.addAttribute("userName",userName);		
		model.addAttribute("roleName",roleName);
		model.addAttribute("profileImageName",profileImageName);
		model.addAttribute("userRights",userRights);		
		return "fsmIndex";
    }
	
	@PostMapping(value = "/fsm/LoginDetailsValidate")
	public String loginDetailsValidate(@RequestParam("User Name") String userName,@RequestParam("Password") String password,Model model) {
		String vResponse =  "fsmLogin";
		
		try {	
			System.out.println("/fsm/LoginDetailsValidate---Entering");
			
			if (userName != null && password != null ) {
				Optional<UserDetails> userDetailsRecord = userDetailsRepository.findByUserName(userName);
				if(userDetailsRecord.isPresent()) {
					System.out.println("Record is present");
					UserDetails userDetails = userDetailsRecord.get();
					String recordPassword = userDetails.getPassword();
					if(recordPassword.equalsIgnoreCase(password)) {
						System.out.println("password also success");
						model.addAttribute("userId",userDetails.getUserId());
						model.addAttribute("userName",userDetails.getUserName());
						model.addAttribute("roleName",userDetails.getRoleName());						
						model.addAttribute("profileImageName",userDetails.getFileName());
						model.addAttribute("userRights",userDetails.getUserRights());		    	        
						System.out.println(userName+"<<>>"+password+"<<>>"+userDetails.getRoleName()+"<<>>"+userDetails.getFileName()+"<<>>"+userDetails.getUserRights());
						return "fsmIndex";
					}else {
						System.out.println("password fail");					 
						model.addAttribute("status","false");
						model.addAttribute("validateMessage","Invalid password");
						return "fsmLogin";
					}
				}else {	
					System.out.println("userName wrong");	
					model.addAttribute("status","false");
					model.addAttribute("validateMessage","Invalid User Id");
					return "fsmLogin";
				}	
	        }
								
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
}
