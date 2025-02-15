package net.bounceme.chronos.springboot.backend.apirest.utils;

import org.slf4j.Logger;

import lombok.experimental.UtilityClass;

/**
 * @author federico
 *
 */
@UtilityClass
public class LogWrapper {
	
	/**
	 * @param log
	 * @param msg
	 * @param args
	 */
	public void debug(Logger log, String msg, Object... args) {
		log.debug(String.format(msg, args));
	}
	
	/**
	 * @param log
	 * @param msg
	 */
	public void debug(Logger log, String msg) {
		log.debug(msg);
	}
	
	/**
	 * @param log
	 * @param msg
	 * @param cause
	 */
	public void error(Logger log, String msg, Throwable cause) {
		log.error(msg, cause);
	}
	
	/**
	 * @param log
	 * @param msg
	 * @param cause
	 */
	public void error(Logger log, String msg, Object... args) {
		log.error(String.format(msg, args));
	}
	
	/**
	 * @param log
	 * @param msg
	 */
	public void error(Logger log, String msg) {
		log.error(msg);
	}
}
