package org.aquat.seleniumframework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>TestData annotation is used to define test data</p>
 * <p>Values in the annotation will overwrite those in testdata.json</p>
 * 
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestData {
	public String[] value() default "";
}
