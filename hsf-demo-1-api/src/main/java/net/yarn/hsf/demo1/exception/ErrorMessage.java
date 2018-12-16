package net.yarn.hsf.demo1.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
	
	private int errorCode;
	private String description;
	private String rootCause;
	private String resolution;

}
