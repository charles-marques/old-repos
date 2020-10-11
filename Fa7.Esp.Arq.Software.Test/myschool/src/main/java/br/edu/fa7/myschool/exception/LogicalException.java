package br.edu.fa7.myschool.exception;

import javax.ejb.ApplicationException;
@ApplicationException(rollback=true)
public class LogicalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9087810523243391869L;

}
