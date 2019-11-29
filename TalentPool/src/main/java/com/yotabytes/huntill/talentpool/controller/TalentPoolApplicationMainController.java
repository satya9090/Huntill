 package com.yotabytes.huntill.talentpool.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import com.yotabytes.huntill.talentpool.domain.CandidateInformation;
import com.yotabytes.huntill.talentpool.domain.TalentQuestion;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionOption;
import com.yotabytes.huntill.talentpool.domain.Talent_candidate_experience;
import com.yotabytes.huntill.talentpool.service.TalentPoolService;
import com.yotabytes.huntill.talentpool.service.impl.TalentPoolServiceImpl;
import com.yotabytes.huntill.talentpool.utils.MailUtil;
import com.yotabytes.huntill.talentpool.utils.PasswordEncryptionUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//@Api(value = "TalentPool Application API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@Tag(name = "Talent Pool Application", description = "the All Talent information")

public class TalentPoolApplicationMainController {

	@Autowired
	private TalentPoolService talentPoolService;

	@Autowired
	private PasswordEncryptionUtil encoder;

	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Welcome to Huntill Rest API ";
	}

	@Operation(summary = "Find All Talent Questions ", description = "Get All talent queations", tags = { "All" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@RequestMapping(value = "/getTalentQuestions", method = RequestMethod.GET)
	public ResponseEntity<List<TalentQuestion>> findAll() {

		List<TalentQuestion> talentQuestions = talentPoolService.findAll();
		System.out.println("Controller..." + new ResponseEntity<>(talentQuestions, HttpStatus.FOUND));
		return new ResponseEntity<>(talentQuestions, HttpStatus.FOUND);

	}

	@RequestMapping(value = "/Registration", method = RequestMethod.GET)
	public ModelAndView getRegistrationPage(Map<String, Object> model, HttpSession session) {

		return new ModelAndView("Registration");
	}
	
	@RequestMapping(value = "/Forgotpassword", method = RequestMethod.GET)
	public ModelAndView getResetPassword(Map<String, Object> model, HttpSession session) {

		return new ModelAndView("ResetPassword");
	}
	
	@RequestMapping(value = "/ResetPasswordPage", method = RequestMethod.GET)
	public ModelAndView getResetPasswordPage(Map<String, Object> model, HttpSession session) {

		return new ModelAndView("ResetPasswordPage");
	}
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView getLoginPage(Map<String, Object> model, HttpSession session) {

		return new ModelAndView("Login");   
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public  @ResponseBody CandidateInformation login(@RequestBody CandidateInformation information,
			HttpSession session) {
		information.setIsActive("y");
		CandidateInformation candidateInformation=talentPoolService.findByUserIdAndPasswordAndIsActive(information.getUserId(),encoder.getEncriptedPassword(information.getPassword()),information.getIsActive());
		if(Objects.nonNull(candidateInformation)) {
			System.out.println("SUCCESFULLY");
			return candidateInformation;
		}else { 
			
			return null;
		} 
		
	}
	// this method use to store candidateInformation in talent_candidate_information

	@RequestMapping(value = "/candidateInformation", method = RequestMethod.POST)
	public @ResponseBody CandidateInformation saveCandidateInformation(@ModelAttribute CandidateInformation information,
			HttpSession session,HttpServletRequest request) {
		
		CandidateInformation checkUserName=new CandidateInformation();
		CandidateInformation checkEmailId=new CandidateInformation();
		
		checkUserName=talentPoolService.findByUserId(information.getUserId());
		checkEmailId=talentPoolService.findByEmailId(information.getEmailId());
		if(Objects.nonNull(checkUserName))
		{
			return null;
		}else {
			
			if(Objects.nonNull(checkEmailId))
			{
				return null;	
			}else {
				session.setAttribute("uniqueId",information.getCandidateUniqeId());
				
				  information.setPassword(encoder.getEncriptedPassword(information.getPassword())); 
				/*
				 * String pass=information.getPassword(); byte[] bytes = pass.getBytes(); String
				 * password=PasswordEncryptionUtil.bytesToHex(bytes);
				 * System.out.println(password);
				 */
				 
				information = talentPoolService.saveCandidateInformation(information);
				session.setAttribute("information",information);
				if (Objects.nonNull(information)) {
					
					MailUtil.mailSendUtil(session,request);
					
				}
				return information;
			}
			
		}
		
		
	}

	// this method use to store candidateExperience in talent_candidate_experience

	@RequestMapping(value = "/candidateExperience", method = RequestMethod.POST)
	public @ResponseBody Talent_candidate_experience saveCandidateExperience(HttpSession session,
			HttpServletRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Talent_candidate_experience experience = new Talent_candidate_experience();

		try {

			experience.setCandidateUniqueId(session.getAttribute("uniqueId").toString());
			experience.setProject_name(request.getParameter("project_name"));
			experience.setStart_date(df.parse(request.getParameter("start_date")));
			experience.setEnd_date(df.parse(request.getParameter("end_date")));
			experience.setTechnology_used(request.getParameter("Technology_used"));
			experience.setDescription(request.getParameter("description"));

		} catch (Exception e) {
			e.getMessage();
		}

		return talentPoolService.saveCandidateExperience(experience);

	}
	
	// This method use to check this mailId is valid od not.
	
	@RequestMapping(value = "/VerifyEmail", method = RequestMethod.GET)
	public CandidateInformation checkValidEmail(@RequestParam("uniqeId") String candidateUniqeId) {

		CandidateInformation information=talentPoolService.findByCandidateUniqeId(candidateUniqeId);
		if(Objects.nonNull(information))
		{
			information.setIsVerify("Y");
			
			return talentPoolService.saveCandidateInformation(information);	
		}else {
			return null;
		}
		
		
	}
	
	@RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
	public CandidateInformation checkPassword(@RequestParam("email") String emailId,HttpSession session,HttpServletRequest request) {
		CandidateInformation information=null;
		information=talentPoolService.findByEmailId(emailId);
		session.setAttribute("information",information);
		if(Objects.nonNull(information))
		{
			MailUtil.mailSendToResetPassword(session,request);
			return information;
		}else {
			return null;
		}

		
	}
	
	@RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST) 
	public CandidateInformation updatePassword(@RequestParam("candidateUniqeId") String candidateUniqeId ,@RequestParam("password") String password,HttpServletRequest request) {
		
		CandidateInformation information=talentPoolService.findByCandidateUniqeId(candidateUniqeId);
		if(Objects.nonNull(information))
		{
			information.setPassword(encoder.getEncriptedPassword(password));
			return talentPoolService.saveCandidateInformation(information);
		}
		else {
			return null;
		}
		
	}
	
	@RequestMapping(value = "/UpdateRegistrationPageById", method = RequestMethod.GET) 
	public List UpdateRegistrationPage(@RequestParam("candidateUniqeId") String candidateUniqeId,HttpSession session) {
		session.setAttribute("candidateUniqeId", candidateUniqeId);
		
		ArrayList list=new ArrayList();
		CandidateInformation information=talentPoolService.findByCandidateUniqeId(candidateUniqeId);
		Talent_candidate_experience experience=talentPoolService.findByCandidateUniqeid(candidateUniqeId);
		list.add(information);
		list.add(experience);
		session.setAttribute("CnadidateInfo", list);
		return list;
	}
	
	@RequestMapping(value = "/candidateInformationUpdate", method = RequestMethod.PUT)
	public @ResponseBody CandidateInformation updateCandidateInformation(@RequestBody CandidateInformation information,HttpSession session,HttpServletRequest request) {
		CandidateInformation information1=talentPoolService.findByCandidateUniqeId(session.getAttribute("candidateUniqeId").toString());
		if(Objects.nonNull(information1))
		{
			information1.setFirst_name(information.getFirst_name());
			information1.setMiddle_name(information.getMiddle_name());
			information1.setLast_name(information.getLast_name());
			information1.setEmailId(information.getEmailId());
			information1.setAlternateEmail_id(information.getAlternateEmail_id());
			information1.setContact_number(information.getContact_number());
			information1.setGender(information.getGender());
			information1.setGrade(information.getGrade());
			information1.setInstitute_name(information.getInstitute_name());
			information1.setIsActive(information.getIsActive());
			information1.setIsEmployer(information.getIsEmployer());
			information1.setIsVerify(information.getIsVerify());  
			information1.setLocation(information.getLocation());
			information1.setPassing_year(information.getPassing_year());
			information1.setPassword(information.getPassword());
			information1.setUserId(information.getUserId());
			information1.setCandidate_id(information.getCandidate_id());
			return talentPoolService.saveCandidateInformation(information1); 
		}else {
			return null;
		}
	}
	@RequestMapping(value = "/candidateDeleteById", method = RequestMethod.POST)
	public @ResponseBody CandidateInformation deleteById(@RequestParam("candidateUniqeId") String candidateUniqeId,
			HttpSession session,HttpServletRequest request) {
		CandidateInformation information=talentPoolService.findByCandidateUniqeId(candidateUniqeId);
		if(Objects.nonNull(information))
		{
			information.setIsActive("N");
			
			return talentPoolService.saveCandidateInformation(information);	
		}else {
			return null;
		}
		
	}
	
	}

