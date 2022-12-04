package net.bounceme.chronos.springboot.backend.apirest.aspects;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author federico
 *
 */
public class CommonJoinPointConfig {
	
	/**
	 * 
	 */
	@Pointcut("@annotation(net.bounceme.chronos.springboot.backend.apirest.aspects.TrackTime)")
	public void trackTimeAnnotation(){}

}
