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

import com.yotabytes.huntill.talentpool.domain.TalentPointManagement;
import com.yotabytes.huntill.talentpool.service.TalentPoolPaymentService;
import com.yotabytes.huntill.talentpool.service.TalentPoolService;

@RestController
@RequestMapping("/api/v2/")
public class TalentPoolPaymentController {

    final static Logger logger = Logger.getLogger(TalentPoolPaymentController.class);

    @Autowired
    private TalentPoolPaymentService paymentService;

    // @Operation(description = "add point", responses = {
    // @ApiResponse(content = @Content(schema = @Schema(implementation =
    // TalentPointManagement.class)), responseCode = "200"), })
    @RequestMapping(value = "/addPoint", method = RequestMethod.POST)
    public ResponseEntity<Object> addPoint(@RequestBody TalentPointManagement pointManagement, HttpSession session) {
        logger.info("inside addPoint mapping ==>> ");
        try {
            TalentPointManagement talentPointManagement = paymentService.savePoint(pointManagement);
            if (Objects.nonNull(talentPointManagement)) {
                logger.info("inside addPoint mapping save generatedPoint ==>>successful ");
                return ResponseEntity.ok("Add point Save");
            }
            return new ResponseEntity("Add point save Failed", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity("Add point save Failed", HttpStatus.BAD_REQUEST);
    }

    // @Operation(description = "update point", responses = {
    // @ApiResponse(content = @Content(schema = @Schema(implementation =
    // TalentPointManagement.class)), responseCode = "200"), })
    @RequestMapping(value = "/updatePoint", method = RequestMethod.POST)
    public ResponseEntity<Object> updatePoint(@RequestBody TalentPointManagement pointManagement, HttpSession session) {
        logger.info("inside updatePoint mapping ==>> ");

        try {
            TalentPointManagement talentPointManagement1 = paymentService
                    .findByCandidateUniqueId(pointManagement.getCandidateUniqueId());

            if (Objects.nonNull(talentPointManagement1)) {
                talentPointManagement1.setDollar(pointManagement.getDollar());
                talentPointManagement1.setTransactionType(pointManagement.getTransactionType());
                pointManagement = paymentService.updatePoint(talentPointManagement1);
                if (Objects.nonNull(pointManagement)) {
                    logger.info("inside updatePoint mapping save generatedPoint ==>>successful ");
                    return ResponseEntity.ok("update point Save");
                }
                return new ResponseEntity("update point  Failed", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("CandidateUniqueId not present..", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity("update point  Failed", HttpStatus.BAD_REQUEST);
    }

    // @Operation(description = "Remove point", responses = {
    // @ApiResponse(content = @Content(schema = @Schema(implementation =
    // TalentPointManagement.class)), responseCode = "200"), })
    @RequestMapping(value = "/removePoint", method = RequestMethod.POST)
    public ResponseEntity<Object> removePoint(@RequestBody TalentPointManagement pointManagement, HttpSession session) {
        logger.info("inside removePoint mapping ==>> ");

        try {
            TalentPointManagement talentPointManagement1 = paymentService
                    .findByCandidateUniqueId(pointManagement.getCandidateUniqueId());
            logger.info(talentPointManagement1.getCurrentAvailablePoint() + talentPointManagement1.getCandidateUniqueId()
                    + talentPointManagement1.getResume() + talentPointManagement1.getTransactionType());

            if (Objects.nonNull(talentPointManagement1)) {
                talentPointManagement1.setResume(pointManagement.getResume());
                talentPointManagement1.setTransactionType(pointManagement.getTransactionType());

                pointManagement = paymentService.removePoint(talentPointManagement1);
                if (Objects.nonNull(pointManagement)) {
                    logger.info("inside removePoint mapping remove generatedPoint ==>>successful ");
                    return ResponseEntity.ok("resume download successfully");
                }
                return new ResponseEntity("resume download   Failed", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("CandidateUniqueId not present..", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity("resume download successfully  Failed", HttpStatus.BAD_REQUEST);
    }
}
