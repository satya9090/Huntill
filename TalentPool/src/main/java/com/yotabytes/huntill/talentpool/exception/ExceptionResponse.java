package com.yotabytes.huntill.talentpool.exception;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ExceptionResponse {

  private Date timestamp;
  private String message;
  private String details;
  
  public ExceptionResponse(Date timestamp, String message, String details) {
	  
  }
}