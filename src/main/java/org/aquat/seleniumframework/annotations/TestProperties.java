package org.aquat.seleniumframework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>TestProperties annotation is used to define the custom test properties</p>
 * <p>Values in the annotation will overwrite those in project.properties</p>
 * 
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestProperties {
	public String[] value() default "";
}
