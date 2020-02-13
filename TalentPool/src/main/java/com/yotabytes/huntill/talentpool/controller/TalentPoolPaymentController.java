package com.yotabytes.huntill.talentpool.controller;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.yotabytes.huntill.talentpool.domain.TalentPointManagment;
import com.yotabytes.huntill.talentpool.service.TalentPoolPaymentService;
import com.yotabytes.huntill.talentpool.service.TalentPoolService;




@RestController
@RequestMapping("/api/v2/")
public class TalentPoolPaymentController {
	
	final static Logger logger = Logger.getLogger(TalentPoolPaymentController.class);
	
	@Autowired
	private TalentPoolPaymentService paymentService; 
	
//	@Operation(description = "add point", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentPointManagment.class)), responseCode = "200"), })
	@RequestMapping(value = "/addPoint", method = RequestMethod.POST)
	public ResponseEntity<Object>  addPoint(@RequestBody TalentPointManagment pointManagment, HttpSession session) {
		logger.info("inside addPoint mapping ==>> ");
		try
		{
			TalentPointManagment talentPointManagment=paymentService.savePoint(pointManagment);
			if (Objects.nonNull(talentPointManagment)) {
				logger.info("inside addPoint mapping save generatedPoint ==>>sucssesful ");
				return ResponseEntity.ok("Add point Save");
			}
			return new ResponseEntity("Add point save Failed", HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity("Add point save Failed", HttpStatus.BAD_REQUEST);
	}
	
//	@Operation(description = "update point", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentPointManagment.class)), responseCode = "200"), })
	@RequestMapping(value = "/updatePoint", method = RequestMethod.POST)
	public ResponseEntity<Object>  updatePoint(@RequestBody TalentPointManagment pointManagment, HttpSession session) {
		logger.info("inside updatePoint mapping ==>> ");
		
		try
		{ 
			TalentPointManagment talentPointManagment1=paymentService.findByCandidateUniqueId(pointManagment.getCandidateUniqueId());
			
			if (Objects.nonNull(talentPointManagment1)) {
				talentPointManagment1.setDollar(pointManagment.getDollar());
				talentPointManagment1.setTransactionType(pointManagment.getTransactionType());
				pointManagment=paymentService.updatePoint(talentPointManagment1);
				 if (Objects.nonNull(pointManagment)) {
					 logger.info("inside updatePoint mapping save generatedPoint ==>>sucssesful ");
						return ResponseEntity.ok("update point Save");
				 }
				 return new ResponseEntity("update point  Failed", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity("CandidateUniqueId not present..", HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity("update point  Failed", HttpStatus.BAD_REQUEST);
	}
	
	
//	@Operation(description = "Remove point", responses = {
//			@ApiResponse(content = @Content(schema = @Schema(implementation = TalentPointManagment.class)), responseCode = "200"), })
	@RequestMapping(value = "/removePoint", method = RequestMethod.POST)
	public ResponseEntity<Object>  removePoint(@RequestBody TalentPointManagment pointManagment, HttpSession session) {
		logger.info("inside removePoint mapping ==>> ");
		
		try
		{ 
			TalentPointManagment talentPointManagment1=paymentService.findByCandidateUniqueId(pointManagment.getCandidateUniqueId());
			logger.info(talentPointManagment1.getCurrentAvailablePoint()+talentPointManagment1.getCandidateUniqueId()+talentPointManagment1.getResume()+talentPointManagment1.getTransactionType());
			
			if (Objects.nonNull(talentPointManagment1)) {
				talentPointManagment1.setResume(pointManagment.getResume());
				talentPointManagment1.setTransactionType(pointManagment.getTransactionType());
				
				pointManagment=paymentService.removePoint(talentPointManagment1);
				 if (Objects.nonNull(pointManagment)) {
					 logger.info("inside removePoint mapping remove generatedPoint ==>>sucssesful ");
						return ResponseEntity.ok("resume downlode sussesfully");
				 }
				 return new ResponseEntity("resume downlode   Failed", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity("CandidateUniqueId not present..", HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity("resume downlode sussesfully  Failed", HttpStatus.BAD_REQUEST);
	}
}
