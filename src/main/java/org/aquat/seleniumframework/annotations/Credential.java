package org.aquat.seleniumframework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Credential annotation is used before a certain test method to setup logon username and password.</p>
 * <p>Values in the annotation will overwrite those in project.properties</p>
 * 
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Credential {
	public String username();
	public String password();
}
