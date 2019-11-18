package com.yotabytes.huntill.talentpool.controller;

import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.yotabytes.huntill.talentpool.domain.TalentQuestionAnswers;
import com.yotabytes.huntill.talentpool.domain.Talent_candidate_experience;
import com.yotabytes.huntill.talentpool.service.TalentPoolService;
import com.yotabytes.huntill.talentpool.service.impl.TalentPoolServiceImpl;

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
	public ResponseEntity<List<TalentQuestionAnswers>> findAll() {

		List<TalentQuestionAnswers> talentQuestions = talentPoolService.findAll();
		System.out.println("Controller..." + new ResponseEntity<>(talentQuestions, HttpStatus.FOUND));
		return new ResponseEntity<>(talentQuestions, HttpStatus.FOUND);

	}

	@RequestMapping(value = "/Registration", method = RequestMethod.GET)
	public ModelAndView getRegistrationPage(Map<String, Object> model, HttpSession session) {

		return new ModelAndView("Registration");
	}

	// this method use to store candidateInformation in talent_candidate_information

	@RequestMapping(value = "/candidateInformation", method = RequestMethod.POST)
	public @ResponseBody CandidateInformation saveCandidateInformation(@ModelAttribute CandidateInformation information,
			HttpSession session) {

		// create random unique id using randomUUID() method..
		UUID uniqueKey = UUID.randomUUID();
		String uniqueId = uniqueKey.toString();
		session.setAttribute("uniqueId", uniqueId);

		information.setCandidate_uniqeId(uniqueId);

		return talentPoolService.saveCandidateInformation(information);
	}

	// this method use to store candidateExperience in talent_candidate_experience

	@RequestMapping(value = "/candidateExperience", method = RequestMethod.POST)
	public @ResponseBody Talent_candidate_experience saveCandidateExperience(HttpSession session,
			HttpServletRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Talent_candidate_experience experience = new Talent_candidate_experience();

		try {

			experience.setCandidate_uniqueId(session.getAttribute("uniqueId").toString());
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

}
