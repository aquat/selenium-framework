package org.aquat.seleniumframework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>LogIn annotation is used to define the Login page object and the method to invoke login operation</p>
 * <p>Values in the annotation will overwrite those in project.properties</p>
 * 
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogIn {
	public String className();
	public String methodName();
}
