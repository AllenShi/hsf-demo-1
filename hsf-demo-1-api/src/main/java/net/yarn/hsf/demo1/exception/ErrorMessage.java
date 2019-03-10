package net.yarn.hsf.demo1.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6575153460885100647L;
	
	
	private int errorCode;
	private String description;
	private String rootCause;
	private String resolution;

}
