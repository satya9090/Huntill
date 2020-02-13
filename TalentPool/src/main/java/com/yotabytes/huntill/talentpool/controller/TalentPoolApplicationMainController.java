package com.yotabytes.huntill.talentpool.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.yotabytes.huntill.talentpool.domain.PasswordResetToken;
import com.yotabytes.huntill.talentpool.domain.ResetPasswordDTO;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateAddress;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateProjectDetails;
import com.yotabytes.huntill.talentpool.domain.TalentEducationDetails;
import com.yotabytes.huntill.talentpool.domain.TalentQuestion;
import com.yotabytes.huntill.talentpool.domain.TalentQuestionAnswer;
import com.yotabytes.huntill.talentpool.repository.TokenRepository;
import com.yotabytes.huntill.talentpool.service.PdfService;
import com.yotabytes.huntill.talentpool.service.TalentPoolService;
//import com.yotabytes.huntill.talentpool.service.impl.TalentPoolServiceImpl;
import com.yotabytes.huntill.talentpool.utils.MailUtil;
import com.yotabytes.huntill.talentpool.utils.PasswordEncryptionUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;






//@Api(value = "TalentPool Application API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@Api(tags = "Talent Pool Application", description = "the All Talent information")

@Validated
public class TalentPoolApplicationMainController {

	final static Logger logger = Logger.getLogger(TalentPoolApplicationMainController.class);
	@Autowired
	private TalentPoolService talentPoolService;
	
	
	  @Autowired 
	  private TokenRepository tokenRepository;
	 
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PasswordEncryptionUtil encoder;
	@Autowired
	private PdfService pdfService;
	
//	@RequestMapping("/")
//	@ResponseBody
//	public String welcome() {
//		return "Welcome to Huntill Rest API ";
//	}


	@ApiOperation(value = "Find All Talent Questions ")
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message  = "successful operation") })

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@RequestMapping(value = "/getTalentQuestions", method = RequestMethod.GET)
	public ResponseEntity<List<TalentQuestion>> findAll() {

		List<TalentQuestion> talentQuestions = talentPoolService.findAll();
		System.out.println("Controller..." + new ResponseEntity<>(talentQuestions, HttpStatus.FOUND));
		return new ResponseEntity<>(talentQuestions, HttpStatus.FOUND);

	}
         /*Token validation  */
	 @ApiOperation(value = "candidate Details")
			 @ApiResponse(code = 200, message = "Successfully retrieved list")
	@RequestMapping(value="/getUser",method = RequestMethod.GET)
	    public ResponseEntity<Object> user(Principal principal) {
		 System.out.println(principal.getName());
		     //TalentCandidateInformation candidateInformation = null;
		 TalentCandidateInformation candidateInformation= talentPoolService.findByUserName(principal.getName());
	        return ResponseEntity.ok(candidateInformation);
	    }

	/*
	 * @Operation(description = "coustomer login", responses = {
	 * 
	 * @ApiResponse(content = @Content(schema = @Schema(implementation =
	 * TalentCandidateInformation.class)), responseCode = "200"), })
	 */
	 @ApiOperation(value = "coustomer login")
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	 
	public ResponseEntity<Object> login(@RequestBody TalentCandidateInformation information) {
		TalentCandidateInformation candidateInformation1 = null;
		try {
			logger.info("Candidate pass userId and Password for login");
			TalentCandidateInformation candidateInformation2=talentPoolService.findByUserNameAndIsActive(information.getUserName(),information.getIsActive());
			System.out.println("password:"+candidateInformation2.getPassword());
			boolean data=passwordEncoder.matches(information.getPassword(), candidateInformation2.getPassword());
			System.out.println(data+"return");
			
			/*
			 * candidateInformation1 =
			 * talentPoolService.findByUserNameAndPasswordAndIsActive(information.
			 * getUserName(), passwordEncoder.encode(information.getPassword()),
			 * information.getIsActive());
			 */

			if (data==true) {
				logger.info("return TalentCandidateInformation object when login successful ");
				return ResponseEntity.ok(candidateInformation2);

			} else {
				logger.info("return Error message  when login Fail ");
				return new ResponseEntity("login fail ,incorrect UserId and password", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity("login fail ,incorrect UserId and password", HttpStatus.CONFLICT);
		}

	}
//
//	@Operation(description = "Create new person", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"),
//			@ApiResponse(responseCode = "409", description = "Person with such e-mail already exists") })
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCandidateRegistration(@RequestBody @Valid TalentCandidateInformation information) {

		TalentCandidateInformation checkUserName = new TalentCandidateInformation();
		TalentCandidateInformation checkEmailId = new TalentCandidateInformation();
		information.setCandidateUniqueId(UUID.randomUUID().toString().toUpperCase());
		try {
			checkUserName = talentPoolService.findByUserId(information.getUserName());
			checkEmailId = talentPoolService.findByEmailId(information.getEmailId());
			logger.info("check UserName and EmailId ");
			if (Objects.nonNull(checkUserName)) {
				return new ResponseEntity("User already exists", HttpStatus.BAD_REQUEST);

			} else {
				if (Objects.nonNull(checkEmailId)) {
					return new ResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);

				} else {
					//session.setAttribute("uniqueId", information.getCandidateUniqueId());
					information.setPassword(passwordEncoder.encode(information.getPassword()));
					information.setCreatedDate(new Date());
					information.setUpdateDate(new Date());
					information = talentPoolService.saveCandidateInformation(information);
					//session.setAttribute("information", information);
					if (Objects.nonNull(information)) {
						//MailUtil.mailSendUtil(session, request);
						logger.info("user Registration==> sucessful ");
						List<TalentCandidateInformation> alentCandidateInformation=talentPoolService.findAllUser();
						return ResponseEntity.ok(alentCandidateInformation);

					}
					logger.info("user Registration==> fail ");
					return new ResponseEntity("Registration Failed", HttpStatus.BAD_REQUEST);

				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity("Registration Failed", HttpStatus.BAD_REQUEST);
		}

	}

//	@Operation(description = "Save Candidate BasicInformation", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"),
//			@ApiResponse(responseCode = "409", description = "Person with such e-mail already exists") })
	@RequestMapping(value = "/saveCandidateProfileDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCandidateInformation(@RequestBody TalentCandidateInformation information
			) {
		TalentCandidateInformation information1 = null;
		logger.info("inside saveCandidateInformation mapping ==>> ");
		try {
			information1 = talentPoolService.saveCandidateInformationProfiles(information);
			if (Objects.nonNull(information1)) {
				logger.info("inside saveCandidateInformation mapping save profileDetails ==>>sucssesful ");
				return ResponseEntity.ok(information1);
			}
			return new ResponseEntity("CandidateBesicInformation save Failed", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.info("inside saveCandidateInformation mapping save profileDetails ==>>fail ");
			return new ResponseEntity("CandidateBesicInformation save Failedssss", HttpStatus.BAD_REQUEST);
		}

	}

//	@Operation(description = "Save Candidate ProjectDetails", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"),
//			@ApiResponse(responseCode = "409", description = "Person with such e-mail already exists") })
	@RequestMapping(value = "/saveCandidateProjectDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCandidateProjectDetails(
			@RequestBody @Valid List<TalentCandidateProjectDetails> projectDetails
			) {
		logger.info("inside saveCandidateExperience mapping save projectDetails==>> ");
		try {
			Iterable<TalentCandidateProjectDetails> projectDetail = talentPoolService
					.saveCandidateProjectDetails(projectDetails); 
			if (Objects.nonNull(projectDetail)) {
				logger.info("inside saveCandidateExperience mapping ==>> sucssesful");
				
				return ResponseEntity.ok(projectDetail);
			}
			logger.info("inside saveCandidateExperience mapping ==>> Fail");
			return new ResponseEntity("CandidateBesicInformation save Failed", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.info("inside saveCandidateExperience mapping ==>> Fail");
			return new ResponseEntity("CandidateBesicInformation save Failed", HttpStatus.BAD_REQUEST);
		}

	}

//	@Operation(description = "Save Candidate EducationDetails", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"),
//			@ApiResponse(responseCode = "409", description = "Person with such e-mail already exists") })
	@RequestMapping(value = "/saveCandidateEducationDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCandidateEducationDetails(
			@RequestBody @Valid List<TalentEducationDetails> educationDetails) {
		logger.info("inside saveCandidateEducationDetails mapping save projectDetails==>> ");
		try {
			Iterable<TalentEducationDetails> educationDetail = talentPoolService.saveEducationDetails(educationDetails);
			if (Objects.nonNull(educationDetail)) {
				logger.info(
						"inside saveCandidateEducationDetails mapping educationDetails save susscesful ==>> sucssesful");
				return ResponseEntity.ok(educationDetail);
			}
			logger.info("inside saveCandidateEducationDetails mapping educationDetails save fail ==>> fail");
			return new ResponseEntity("CandidateEducationDetails save Failed", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.info("inside saveCandidateEducationDetails mapping educationDetails save fail ==>> fail");
			return new ResponseEntity("CandidateEducationDetails save Failed", HttpStatus.BAD_REQUEST);
		}

	}

//	@Operation(description = "Save Candidate AddressDetails", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"),
//			@ApiResponse(responseCode = "409", description = "Person with such e-mail already exists") })
	@RequestMapping(value = "/saveCandidateAddressDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCandidateAddressDetails(@RequestBody @Valid List<TalentCandidateAddress> address) {
		logger.info("inside saveCandidateAddressDetails mapping save AddressDetails==>> ");
		try {
			Iterable<TalentCandidateAddress> educationDetail = talentPoolService.saveCandidateAddress(address);
			if (Objects.nonNull(address)) {
				logger.info(
						"inside saveCandidateAddressDetails mapping saveCandidateAddressDetails save susscesful ==>> sucssesful");
				return ResponseEntity.ok(educationDetail);
			}
			logger.info("inside saveCandidateAddressDetails mapping educationDetails save fail ==>> fail");
			return new ResponseEntity("CandidateAddressDetails save Failed", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.info("inside saveCandidateAddressDetails mapping educationDetails save fail ==>> fail");
			return new ResponseEntity("CandidateAddressDetails save Failed", HttpStatus.BAD_REQUEST);
		}

	}

//	@Operation(description = "VerifyEmail for candidate", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"),
//			@ApiResponse(responseCode = "409", description = "Person with such e-mail already exists") })
	@GetMapping(value = "/verifyEmail")
	public ResponseEntity<Object> checkValidEmail(@RequestParam("uniqeId") String candidateUniqeId) {

		try {
			TalentCandidateInformation information = talentPoolService.findByCandidateUniqeId(candidateUniqeId);
			if (Objects.nonNull(information)) {

				information.setIsVerify("Y");

				information = talentPoolService.saveCandidateInformation(information);
				logger.info("Mail verifyed ==>>");
				if (Objects.nonNull(information)) {
					return ResponseEntity.ok("mail verify sussesfull");
				} else {
					return new ResponseEntity("maill not verify ", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity("maill not verify", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity("maill not verify", HttpStatus.BAD_REQUEST);
		}

	}

//	@Operation(description = "Forgot Password Mail send to candidate", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"), })
	
	 @ApiOperation(value = "forgot Password")
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<Object> checkPassword(@RequestBody TalentCandidateInformation talentCandidateInformation, HttpSession session,
			HttpServletRequest request) {
		TalentCandidateInformation information = null;
		try {
			logger.info("userName  ==>>" + talentCandidateInformation.getUserName());
			TalentCandidateInformation	information1 = talentPoolService.findByUserName(talentCandidateInformation.getUserName());
			information=talentPoolService.findByEmailId(information1.getEmailId());
			//System.out.println("Token::"+information.getPasswordResetToken().getToken());
			PasswordResetToken token = new PasswordResetToken();
			
			 
			if (Objects.nonNull(information)) {
				 
			        token.setToken(UUID.randomUUID().toString());
			        token.setTalentCandidateInformation(information);
			        token.setCandidateUniqueId(information.getCandidateUniqueId());
			        token.setExpiryDate(30);
			        token.setIsUpdate("N");
			      PasswordResetToken passwordResetToken= tokenRepository.findByCandidateUniqueIdAndIsUpdate(token.getTalentCandidateInformation().getCandidateUniqueId(),token.getIsUpdate());
				
				if (Objects.nonNull(passwordResetToken)) {
				 if(passwordResetToken.isExpired()) {
					//tokenRepository.delete(passwordResetToken);
					passwordResetToken.setExpiryDate(30);
					passwordResetToken.setToken(UUID.randomUUID().toString());
					tokenRepository.save(passwordResetToken);
					session.setAttribute("information", passwordResetToken);
					MailUtil.mailSendToResetPassword(session, request);
					logger.info("Mail send to this mail ==>>" + talentCandidateInformation.getUserName()+information.getEmailId());
					return ResponseEntity.ok("mail send to your mailId check your mail and reset your password ..");
				}
				else {
					return ResponseEntity.ok("mail Already send to your mailId check your mail and reset your password ..");
				}
					
				 
				}
				else {
					tokenRepository.save(token);
					session.setAttribute("information", token);
					MailUtil.mailSendToResetPassword(session, request);
					logger.info("Mail send to this mail ==>>" + talentCandidateInformation.getUserName()+information.getEmailId());
					return ResponseEntity.ok("mail send to your mailId check your mail and reset your password ..");
				}
				 
			       
				

			} else {
				return new ResponseEntity("", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity("", HttpStatus.BAD_REQUEST);
		}

	}

	
//	@Operation(description = "updatePassword for candidate", responses = {
//
//	@ApiResponse(content = @Content(schema = @Schema(implementation = ResetPasswordDTO.class)), responseCode = "200"), })
	 @ApiOperation(value = "RestPasswword ")
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<Object> updatePassword(@RequestBody ResetPasswordDTO resetPasswordDTO
			) {

		try {
			logger.info("updatePassword Method ==>>");
			PasswordResetToken passwordResetToken = new PasswordResetToken();
			passwordResetToken.setToken(resetPasswordDTO.getResetPasswordToken());

			PasswordResetToken pass = tokenRepository.findByToken(passwordResetToken.getToken());

			if (Objects.nonNull(pass)) {
				if (pass.isExpired()) {
					return ResponseEntity.ok("your token has expired..");
				} else {
					TalentCandidateInformation information = talentPoolService
							.findByCandidateUniqeId(pass.getCandidateUniqueId());
					logger.info("updatePassword Method check candidateUniqeId ==>>");
					if (Objects.nonNull(information)) {
						information.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));
						information.setUpdateDate(new Date());
						information = talentPoolService.saveCandidateInformation(information);
						if (Objects.nonNull(information)) {
							pass.setIsUpdate("Y");
							tokenRepository.save(pass);
							return ResponseEntity.ok("UpdatePassword sussesfull");
						} else {
							return new ResponseEntity("password not Update4", HttpStatus.BAD_REQUEST);
						}

					} else {
						return new ResponseEntity("password not Update3", HttpStatus.BAD_REQUEST);
					}

				}
			}
			else {
				return new ResponseEntity("password not Update1", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity("password not Update2", HttpStatus.CONFLICT);
		}
		

	}
	 @ApiOperation(value = "Candidate Profile Details")
	@RequestMapping(value = "/getCandidateProfileById", method = RequestMethod.GET)
	public ArrayList getCandidateProfileById(@RequestParam("candidateUniqeId") String candidateUniqeId,
			HttpSession session) {
		session.setAttribute("candidateUniqeId", candidateUniqeId);
		logger.info("getCandidateProfileById Method call ==>>");
		try {
			ArrayList list = new ArrayList();
			TalentCandidateInformation information = talentPoolService.findByCandidateUniqeId(candidateUniqeId);
			TalentCandidateProjectDetails project = talentPoolService.findByCandidateUniqeid(candidateUniqeId);
			TalentCandidateAddress address = talentPoolService
					.findByCandidateUniqeIdFromCandidateAddress(candidateUniqeId);
			TalentEducationDetails education = talentPoolService.findByCandidateEducationDetails(candidateUniqeId);
			list.add(information);
			list.add(project);
			list.add(address);
			list.add(education);
			session.setAttribute("CnadidateInfo", list);
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

//	@Operation(description = "candidate besic Information Update", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"), })
	 @ApiOperation(value = "Candidate Profile Update")
	 @RequestMapping(value = "/candidateInformationUpdate", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCandidateInformation(@RequestBody TalentCandidateInformation information
			) {
		TalentCandidateInformation information1 = null;
		logger.info("updateCandidateInformation Method call ==>>");
		try {
			information1 = talentPoolService.findByCandidateUniqeId(information.getCandidateUniqueId());
			logger.info("updateCandidateInformation Method call check candidateUniquId ==>>");
			if (Objects.nonNull(information1)) {
				information1.setFirstName(information.getFirstName());
				information1.setMiddleName(information.getMiddleName());
				information1.setLastName(information.getLastName());
				information1.setEmailId(information.getEmailId());
				information1.setAlternateEmailId(information.getAlternateEmailId());
				information1.setContactNumber(information.getContactNumber());
				information1.setGender(information.getGender());
				information1.setIsActive(information.getIsActive());
				information1.setRole(information.getRole());
				information1.setIsVerify(information.getIsVerify());
				information1.setPassword(information.getPassword());
				information1.setUserName(information.getUserName());
				/*information1.setCandidateId(information.getCandidateId());*/
				information1.setUpdateDate(new Date());
				information1 = talentPoolService.saveCandidateInformation(information1);
				if (Objects.nonNull(information1)) {
					return ResponseEntity.ok("candidate besic Information Update sussesfull");
				} else {
					return new ResponseEntity("candidate besic Information not Update", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity("candidate besic Information not Update", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity("candidate besic Information not Update", HttpStatus.CONFLICT);
	}

//	@Operation(description = "Candidate Address Update", responses = {
//	@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"), })
	
	 @ApiOperation(value = "Candidate Address Update")
	 @RequestMapping(value = "/candidateAddressUpdate", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCandidateAddress(@RequestBody List<TalentCandidateAddress> address
			) {
		try {
			logger.info("updateCandidateAddress Method call ==>>");
			TalentCandidateAddress addressNew = talentPoolService
					.findByCandidateAddressId(address.get(0).getAddressId());
			addressNew.setType(address.get(0).getType());
			addressNew.setCoutry(address.get(0).getCountry());
			addressNew.setCity(address.get(0).getCity());
			addressNew.setState(address.get(0).getState());
			addressNew.setPincode(address.get(0).getPincode());
			addressNew = talentPoolService.saveCandidateUpdateAddress(addressNew);

			TalentCandidateAddress addressNew2 = talentPoolService
					.findByCandidateAddressId(address.get(1).getAddressId());
			addressNew2.setType(address.get(1).getType());
			addressNew2.setCoutry(address.get(1).getCountry());
			addressNew2.setCity(address.get(1).getCity());
			addressNew2.setState(address.get(1).getState());
			addressNew2.setPincode(address.get(1).getPincode());
			addressNew2 = talentPoolService.saveCandidateUpdateAddress(addressNew2);

			return ResponseEntity.ok("candidate Address Update sussesfull");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity("candidate Address Update not Update", HttpStatus.BAD_REQUEST);

	}

//	@Operation(description = "Candidate ProjectDetailsUpdate ", responses = {
//	@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"), })
	@RequestMapping(value = "/candidateProjectDetailsUpdate", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCandidateProjectDetails(@RequestBody TalentCandidateProjectDetails project
			) {
		logger.info("updateCandidateAddress Method call ==>>");
		try {
			TalentCandidateProjectDetails project1 = talentPoolService.findByCandidateProjectId(project.getProjectId());
			logger.info("return CandidateProjectDetails  using candidateUniueId  ==>>");
			if (Objects.nonNull(project1)) {
				project1.setProjectName(project.getProjectName());
				project1.setStartDate(project.getStartDate());
				project1.setEndDate(project.getEndDate());
				project1.setTechnologyUsed(project.getTechnologyUsed());
				project1.setRoleDescription(project.getRoleDescription());
				project1.setRole(project.getRole());
				project1.setProjectDetails(project.getProjectDetails());
				project1.setCompanyName(project.getCompanyName());
				project1.setUpdateDate(new Date());
				project1 = talentPoolService.saveCandidateProject(project1);
				if (Objects.nonNull(project1)) {
					logger.info("Update candidateProjectDetail   sussesfull ==>>");
					return ResponseEntity.ok("candidate ProjectDetails Update sussesfull");
				} else {
					return new ResponseEntity("candidate ProjectDetails Information not Update",
					HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity("candidate ProjectDetails Information not Update", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity("candidate ProjectDetails Information not Update", HttpStatus.CONFLICT);

	}

//	@Operation(description = "Candidate EducationDetailsUpdate ", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"), })
	@RequestMapping(value = "/candidateEducationUpdate", method = RequestMethod.PUT)
	public ResponseEntity<Object> candidateEducationUpdate(@RequestBody TalentEducationDetails education
			) {
		try {
			TalentEducationDetails educationNew = talentPoolService.findEducationId(education.getEducationId());
			logger.info("return candidateEducationDetails using candidateUniueId  ==>>");
			if (Objects.nonNull(educationNew)) {
				//educationNew.setCourseType(education.getCourseType());
				educationNew.setInstitution(education.getInstitution());
				educationNew.setStartYear(education.getStartYear());
				educationNew.setPercentage(education.getPercentage());
				educationNew.setSubject(education.getSubject());
				educationNew.setEndYear(education.getEndYear());
				educationNew = talentPoolService.saveEducationDetail(educationNew);
				
				if (Objects.nonNull(educationNew)) {
					logger.info("Update CanddateEducationdetails    ==>>");
					return ResponseEntity.ok("candidate EducationDetails Update sussesfull");
				} else {
					return new ResponseEntity("candidate EducationDetails not Update", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity("candidate EducationDetails not Update", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity("candidate EducationDetails not Update", HttpStatus.BAD_REQUEST);
	}

//	@Operation(description = "User Delete ", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"), })
	@DeleteMapping(value = "/userDelete/{candidateUniqeId}")
	public ResponseEntity<Object> deleteById(@RequestParam("candidateUniqeId") String candidateUniqeId) {
		try {
			logger.info("candidateId  ==>>" + candidateUniqeId);
			TalentCandidateInformation information = talentPoolService.findByCandidateUniqeId(candidateUniqeId);
			if (Objects.nonNull(information)) {
				information.setIsActive("N");
				information = talentPoolService.saveCandidateInformation(information);
				logger.info("delete user using candidateUniquId  ==>>");
				return ResponseEntity.ok("user deleted");
			} else {
				return new ResponseEntity("User not deleted", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity("User not deleted", HttpStatus.BAD_REQUEST);

	}

//	@Operation(description = "searchCandidate ", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentCandidateInformation.class)), responseCode = "200"), })
	@RequestMapping(value = "/searchCandidate", method = RequestMethod.POST)
	public ResponseEntity<Object> searchCandidate(@RequestBody TalentCandidateInformation candidateInformation,
			HttpSession session) {
		List<TalentCandidateInformation> listCandidateInformationSearch = null;
		logger.info("Search candidate controller..");
		try {
			System.out.println(candidateInformation.getCurrentLocation()+candidateInformation.getExperience());
			listCandidateInformationSearch = talentPoolService.searchCandidateInfomation(candidateInformation);
			System.out.println(listCandidateInformationSearch);
			if (listCandidateInformationSearch.size() != 0) {
				logger.info("Return search candidate infornation ");
				return ResponseEntity.ok(listCandidateInformationSearch);

			} else {
				logger.info("No search found1.. ");
				return new ResponseEntity("no search found..", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		logger.info("No search found2.. ");
		return new ResponseEntity("no search found..", HttpStatus.CONFLICT);
	}

	/*
	 * @RequestMapping(value = "/searchCandidate", method = RequestMethod.POST)
	 * public ResponseEntity<Object> searchCandidate(@RequestBody
	 * TalentCandidateSearch candidateSearch, HttpSession session) {
	 * List<TalentCandidateSearch> listcandidateSearch1 = null; try {
	 * logger.info("Search candidate controller.."); listcandidateSearch1 =
	 * talentPoolService.findBySkillsAndCurrentLocationAndExperience(candidateSearch
	 * .getSkills(),
	 * candidateSearch.getCurrentLocation(),candidateSearch.getExperience() );
	 * System.out.println(listcandidateSearch1); if (listcandidateSearch1.size()!=0)
	 * { logger.info("Return search candidate infornation "); return
	 * ResponseEntity.ok("Return matching candidate Information");
	 * 
	 * } else { logger.info("No search found.. "); return new
	 * ResponseEntity("no search found..", HttpStatus.BAD_REQUEST); } } catch
	 * (Exception e) { System.out.println(e.getMessage()); return new
	 * ResponseEntity("no search found..", HttpStatus.CONFLICT); }
	 * 
	 * }
	 */
	@RequestMapping(value = "/saveTalentQuestionAnswer", method = RequestMethod.POST)
	public String saveQuestionAnswer(@RequestBody TalentQuestionAnswer answer
			) {
		answer = talentPoolService.saveQuestionAnswer(answer);
		if (Objects.nonNull(answer)) {
			return "saveTalentQuestionAnswer save sussesfully";
		} else {
			return null;
		}

	}
	
	
		@RequestMapping(value = "/createPdf", method = RequestMethod.POST)
		public String createPdf(@RequestParam("candidateUniqueId") String candidateUniqueId) throws FileNotFoundException, DocumentException {
			TalentCandidateInformation talentCandidateInformation=talentPoolService.findByCandidateUniqueId(candidateUniqueId);
			 java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH_mm_ss");
			  //private static long time = sdf.getCalendar();
			 System.out.println(talentCandidateInformation.getFirstName());
			 String date=sdf.format(new Date());
			 System.out.println(date);
			//private static Timestamp ts = new Timestamp(time);
			 String FILE = "c:/temp/Resume"+date+".pdf";
		        try {
		        	
		            Document document = new Document();
		            System.out.println("Date::"+date);
		            System.out.println("file:"+FILE);
		           
		            
		            PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(FILE));
		            document.open();
		           
			
		            pdfService .addMetaData(document,talentCandidateInformation);
		            pdfService .addTitlePage(document,talentCandidateInformation,writer); 
		           
			 
		            document.close();
		        } 
		        catch (NullPointerException ne) {
					System.out.println(ne.getMessage());
				}
		    
			return"Creatred";
		}
		
		
	
		

}
