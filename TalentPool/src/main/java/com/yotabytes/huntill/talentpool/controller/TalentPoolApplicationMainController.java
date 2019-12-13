package com.yotabytes.huntill.talentpool.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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

import com.yotabytes.huntill.talentpool.domain.TalentCandidateAddress;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateExperience;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateProjectDetails;
import com.yotabytes.huntill.talentpool.domain.TalentEducationDetails;
import com.yotabytes.huntill.talentpool.domain.TalentQuestion;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionAnswer;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionOption;

import com.yotabytes.huntill.talentpool.service.TalentPoolService;
import com.yotabytes.huntill.talentpool.service.impl.TalentPoolServiceImpl;
import com.yotabytes.huntill.talentpool.utils.MailUtil;
import com.yotabytes.huntill.talentpool.utils.PasswordEncryptionUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//@Api(value = "TalentPool Application API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
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
	public @ResponseBody TalentCandidateInformation login(@RequestBody TalentCandidateInformation information,
			HttpSession session) {
		information.setIsActive("y");
		TalentCandidateInformation candidateInformation = talentPoolService.findByUserNameAndPasswordAndIsActive(
				information.getUserName(), encoder.getEncriptedPassword(information.getPassword()),
				information.getIsActive());
		if (Objects.nonNull(candidateInformation)) {

			return candidateInformation;
		} else {

			return null;
		}

	}
	// this method use to store candidateInformation in talent_candidate_information

	@RequestMapping(value = "/candidateRegistration", method = RequestMethod.POST)
	public @ResponseBody String saveCandidateRegistration(@RequestBody TalentCandidateInformation information,
			HttpSession session, HttpServletRequest request) {

		TalentCandidateInformation checkUserName = new TalentCandidateInformation();
		TalentCandidateInformation checkEmailId = new TalentCandidateInformation();

		checkUserName = talentPoolService.findByUserName(information.getUserName());
		checkEmailId = talentPoolService.findByEmailId(information.getEmailId());
		if (Objects.nonNull(checkUserName)) {

			return "username already Exit";
		} else {

			if (Objects.nonNull(checkEmailId)) {

				return "EmailId already Exit ,plese enter anather mailId..";
			} else {

				session.setAttribute("uniqueId", information.getCandidateUniqeId());
				information.setPassword(encoder.getEncriptedPassword(information.getPassword()));

				information.setCreatedDate(new Date());
				information.setUpdateDate(new Date());
				information = talentPoolService.saveCandidateInformation(information);
				session.setAttribute("information", information);
				if (Objects.nonNull(information)) {

					MailUtil.mailSendUtil(session, request);
					return "candidate Registretion sussecful..";
				}
				return "candidate Registretion fail.";
			}

		}

	}

//this method use to save candidate basic information in to database...
	@RequestMapping(value = "/saveCandidateProfileDetails", method = RequestMethod.POST)
	public @ResponseBody String saveCandidateInformation(@RequestBody TalentCandidateInformation information,
			HttpSession session, HttpServletRequest request) {

		TalentCandidateInformation information1 = talentPoolService
				.findByCandidateUniqeId(information.getCandidateUniqeId());
		try {
			information1.setGender(information.getGender());
			information1.setMaritalStatus(information.getMaritalStatus());
			information1.setBloodGroup(information.getBloodGroup());
			information1.setAlternateEmailId(information.getAlternateEmailId());
			information1.setNationality(information.getNationality());
			information1.setDateOfBirth(information.getDateOfBirth());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		information1 = talentPoolService.saveCandidateInformation(information1);
		if (Objects.nonNull(information1)) {

			return "save candidateBasicInformation sussecful..";
		}
		return "candidate candidateBasicInformation fail to save.";

	}

	// this method use to store candidateExperience in TalentCandidateProjectDetails
	// TABLE..

	@RequestMapping(value = "/saveCandidateProjectDetails", method = RequestMethod.POST)
	public @ResponseBody String saveCandidateExperience(@RequestBody List<TalentCandidateProjectDetails> projectDetails,
			HttpSession session, HttpServletRequest request) {
		/* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); */

		try {
			/*
			 * projectDetails.setCandidateUniqueId(session.getAttribute("uniqueId").toString
			 * ());
			 */
			TalentCandidateProjectDetails talentCandidateProjectDetails=new TalentCandidateProjectDetails();
			for(int i=0;i<projectDetails.size();i++) {
				talentCandidateProjectDetails.setCreatedDate(new Date());
				talentCandidateProjectDetails.setUpdateDate(new Date());
				talentCandidateProjectDetails.setCandidateUniqeId(projectDetails.get(i).getCandidateUniqeId());
				talentCandidateProjectDetails.setCompanyName(projectDetails.get(i).getCompanyName());
				talentCandidateProjectDetails.setProjectName(projectDetails.get(i).getProjectName());
				talentCandidateProjectDetails.setProjectDetails(projectDetails.get(i).getProjectDetails());
				talentCandidateProjectDetails.setRole(projectDetails.get(i).getRole());
				talentCandidateProjectDetails.setStartDate(projectDetails.get(i).getStartDate());
				talentCandidateProjectDetails.setEndDate(projectDetails.get(i).getEndDate());
				talentCandidateProjectDetails.setTechnologyUsed(projectDetails.get(i).getTechnologyUsed());
				talentCandidateProjectDetails.setRoleDescription(projectDetails.get(i).getRoleDescription());
				talentPoolService.saveCandidateExperience(talentCandidateProjectDetails);
			
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//projectDetails = talentPoolService.saveCandidateExperience(projectDetails);
		if (Objects.nonNull(projectDetails)) {

			return "save candidateBasicInformation sussecful..";
		}
		return "candidate candidateBasicInformation fail to save.";
	}

	@RequestMapping(value = "/saveCandidateEducationDetails", method = RequestMethod.POST)
	public @ResponseBody String saveCandidateEducationDetails(@RequestBody TalentEducationDetails educationDetails,
			HttpSession session) {

		try {
			educationDetails.setCreatedDate(new Date());
			educationDetails.setUpdateDate(new Date());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		educationDetails = talentPoolService.saveEducationDetails(educationDetails);
		if (Objects.nonNull(educationDetails)) {

			return "save candidateeducationDetails sussecfully..";
		}
		return "candidate candidateeducationDetails fail to save.";

	}

	@RequestMapping(value = "/saveCandidateAddressDetails", method = RequestMethod.POST)
	public @ResponseBody String saveCandidateAddressDetails(@RequestBody List<TalentCandidateAddress> address,
			HttpSession session, HttpServletRequest request) {

		try {
			TalentCandidateAddress  talentCandidateAddress =new TalentCandidateAddress();
		for(int i=0;i<address.size();i++) {
			talentCandidateAddress.setAddressId(address.get(i).getAddressId());
			talentCandidateAddress.setCreatedDate(new Date());
			talentCandidateAddress.setUpdateDate(new Date());
			talentCandidateAddress.setAddressType(address.get(i).getAddressType());
			talentCandidateAddress.setCandidateUniqeId(address.get(i).getCandidateUniqeId());
			talentCandidateAddress.setCity(address.get(i).getCity());
			talentCandidateAddress.setCoutry(address.get(i).getCoutry());
			talentCandidateAddress.setState(address.get(i).getState());
			talentCandidateAddress.setPincode(address.get(i).getPincode());
			talentPoolService.saveCandidateAddress(talentCandidateAddress);
		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//address = talentPoolService.saveCandidateAddress(address);
		if (Objects.nonNull(address)) {

			return "save candidateAddress sussecful..";
		}
		return "candidate candidateAddress fail to save.";

	}

	// This method use to check this mailId is valid od not.

	@RequestMapping(value = "/VerifyEmail", method = RequestMethod.GET)
	public TalentCandidateInformation checkValidEmail(@RequestParam("uniqeId") String candidateUniqeId) {

		TalentCandidateInformation information = talentPoolService.findByCandidateUniqeId(candidateUniqeId);
		if (Objects.nonNull(information)) {

			information.setIsVerify("Y");

			return talentPoolService.saveCandidateInformation(information);
		} else {
			return null;
		}

	}

	@RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
	public String checkPassword(@RequestParam("email") String emailId, HttpSession session,
			HttpServletRequest request) {
		TalentCandidateInformation information = null;
		information = talentPoolService.findByEmailId(emailId);
		session.setAttribute("information", information);
		if (Objects.nonNull(information)) {

			MailUtil.mailSendToResetPassword(session, request);
			return "mail send to your mainId check your mail ..";

		} else {
			return "mail not send ";
		}

	}

	@RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST)
	public String updatePassword(@RequestParam("candidateUniqeId") String candidateUniqeId,
			@RequestParam("password") String password, HttpServletRequest request) {

		TalentCandidateInformation information = talentPoolService.findByCandidateUniqeId(candidateUniqeId);
		if (Objects.nonNull(information)) {

			information.setPassword(encoder.getEncriptedPassword(password));
			information.setUpdateDate(new Date());
			information = talentPoolService.saveCandidateInformation(information);
			if (Objects.nonNull(information)) {
				return "update record sucessfully ";
			} else {
				return "update record fail ";
			}

		} else {
			return "return null values";
		}

	}

	@RequestMapping(value = "/UpdateRegistrationPageById", method = RequestMethod.GET)
	public ArrayList UpdateRegistrationPage(@RequestParam("candidateUniqeId") String candidateUniqeId,
			HttpSession session) {
		session.setAttribute("candidateUniqeId", candidateUniqeId);

		ArrayList list = new ArrayList();
		TalentCandidateInformation information = talentPoolService.findByCandidateUniqeId(candidateUniqeId);
		TalentCandidateProjectDetails project = talentPoolService.findByCandidateUniqeid(candidateUniqeId);
		TalentCandidateAddress address = talentPoolService.findByCandidateUniqeIdFromCandidateAddress(candidateUniqeId);
		TalentEducationDetails education = talentPoolService.findByCandidateEducationDetails(candidateUniqeId);
		list.add(information);
		list.add(project);
		list.add(address);
		list.add(education);
		session.setAttribute("CnadidateInfo", list);
		return list;
	}

	@RequestMapping(value = "/candidateInformationUpdate", method = RequestMethod.PUT)
	public @ResponseBody String updateCandidateInformation(@ModelAttribute TalentCandidateInformation information,
			HttpSession session) {
		TalentCandidateInformation information1 = talentPoolService
				.findByCandidateUniqeId(information.getCandidateUniqeId());
		if (Objects.nonNull(information1)) {
			information1.setFirstName(information.getFirstName());
			information1.setMiddleName(information.getMiddleName());
			information1.setLastName(information.getLastName());
			information1.setEmailId(information.getEmailId());
			information1.setAlternateEmailId(information.getAlternateEmailId());
			information1.setContactNumber(information.getContactNumber());
			information1.setGender(information.getGender());

			information1.setIsActive(information.getIsActive());
			information1.setIsEmployer(information.getIsEmployer());
			information1.setIsVerify(information.getIsVerify());

			information1.setPassword(information.getPassword());
			information1.setUserId(information.getUserName());
			information1.setCandidateId(information.getCandidateId());
			information1.setUpdateDate(new Date());
			information1 = talentPoolService.saveCandidateInformation(information1);
			if (Objects.nonNull(information1)) {
				return "candidate information update sussesfully";
			} else {
				return " not updated candidate information";
			}
		} else {
			return "return null";
		}
	}

	@RequestMapping(value = "/CandidateProjectDetailsUpdate", method = RequestMethod.PUT)
	public @ResponseBody String updateCandidateExperience(@ModelAttribute TalentCandidateProjectDetails project,
			HttpSession session) {
		TalentCandidateProjectDetails experience1 = talentPoolService
				.findByCandidateUniqeid(project.getCandidateUniqueId());
		/* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); */
		if (Objects.nonNull(experience1)) {
			experience1.setProjectName(project.getProjectName());
			experience1.setStartDate(project.getStartDate());
			experience1.setEndDate(project.getEndDate());
			experience1.setTechnologyUsed(project.getTechnologyUsed());
			experience1.setRoleDescription(project.getRoleDescription());
			experience1.setRole(project.getRole());
			experience1.setProjectDetails(project.getProjectDetails());
			experience1.setCompanyName(project.getCompanyName());
			experience1.setUpdateDate(new Date());
			experience1 = talentPoolService.saveCandidateExperience(experience1);
			if (Objects.nonNull(experience1)) {
				return "candidate information update sussesfully";
			} else {
				return " not updated candidate information";
			}
		} else {
			return "return null";
		}
	}

	@RequestMapping(value = "/CandidateAddressUpdate", method = RequestMethod.PUT)
	public @ResponseBody String updateCandidateAddress(@ModelAttribute TalentCandidateAddress address,
			HttpSession session) {
		TalentCandidateAddress addressNew = talentPoolService
				.findByCandidateUniqeIdFromCandidateAddress(address.getCandidateUniqeId());
		/* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); */
		if (Objects.nonNull(addressNew)) {
			addressNew.setAddressType(address.getAddressType());
			addressNew.setCoutry(address.getCoutry());
			addressNew.setCity(address.getCity());
			addressNew.setState(address.getState());
			addressNew.setPincode(address.getPincode());
			addressNew.setUpdateDate(new Date());
			addressNew = talentPoolService.saveCandidateAddress(addressNew);
			if (Objects.nonNull(addressNew)) {
				return "candidate Address update sussesfully";
			} else {
				return " not updated CandidateAddress information";
			}
		} else {
			return "return null";
		}
	}

	@RequestMapping(value = "/CandidateEducationUpdate", method = RequestMethod.PUT)
	public @ResponseBody String updateCandidateEducation(@ModelAttribute TalentEducationDetails education,
			HttpSession session) {
		TalentEducationDetails educationNew = talentPoolService
				.findByCandidateEducationDetails(education.getCandidateUniqeId());
		/* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); */
		if (Objects.nonNull(educationNew)) {
			educationNew.setCourseType(education.getCourseType());
			educationNew.setInstituteName(education.getInstituteName());
			educationNew.setPassingYear(education.getPassingYear());
			educationNew.setPercentage(education.getPercentage());
			educationNew.setSpecification(education.getSpecification());
			educationNew.setQualification(education.getQualification());

			educationNew = talentPoolService.saveEducationDetails(educationNew);
			if (Objects.nonNull(educationNew)) {
				return "candidate EducationDetails update sussesfully";
			} else {
				return " not updated EducationDetails information";
			}
		} else {
			return "return null";
		}
	}

	@RequestMapping(value = "/candidateDeleteById", method = RequestMethod.POST)
	public @ResponseBody TalentCandidateInformation deleteById(
			@RequestParam("candidateUniqeId") String candidateUniqeId, HttpSession session,
			HttpServletRequest request) {
		TalentCandidateInformation information = talentPoolService.findByCandidateUniqeId(candidateUniqeId);
		if (Objects.nonNull(information)) {
			information.setIsActive("N");

			return talentPoolService.saveCandidateInformation(information);
		} else {
			return null;
		}

	}

	@RequestMapping(value = "/saveTalentQuestionAnswer", method = RequestMethod.POST)
	public String saveQuestionAnswer(@RequestBody TalentQuestionAnswer answer, HttpSession session,
			HttpServletRequest request) {
		answer = talentPoolService.saveQuestionAnswer(answer);
		if (Objects.nonNull(answer)) {
			return "saveTalentQuestionAnswer save sussesfully";
		} else {
			return null;
		}

	}

	/*
	 * @RequestMapping(value = "/getCandidateAll", method = RequestMethod.POST)
	 * public List getCandidateAll(@RequestBody TalentCandidateInformation
	 * information,HttpSession session){
	 * 
	 * System.out.println("DATA::"+information.getPassingYear());
	 * List<TalentCandidateExperience>
	 * exe=information.getTalentCandidateExperience(); TalentCandidateExperience
	 * ta=new TalentCandidateExperience(); try {
	 * //System.out.println("size:"+exe.size()); if(Objects.requireNonNull(exe) !=
	 * null) { if(exe.size()>0) { for(int i=0;i<exe.size();i++) {
	 * ta.setTechnologyUsed(exe.get(i).getTechnologyUsed());
	 * System.out.println(exe.get(i).getTechnologyUsed()); } } }
	 * catch(NullPointerException ne) { System.out.println(ne.getMessage()); }
	 * //session.setAttribute("candidateUniqeId", candidateUniqeId);
	 * ArrayList<CandidateInformation>
	 * canf=talentPoolService.findByCriteria(information.getPassingYear(),
	 * information.getInstituteName(),ta.getTechnologyUsed());
	 * //CandidateInformation
	 * informat=talentPoolService.findByCandidateUniqeId(candidateUniqeId); return
	 * canf; }
	 */

}
