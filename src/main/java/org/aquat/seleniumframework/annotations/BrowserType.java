package org.aquat.seleniumframework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aquat.seleniumframework.browser.Browser;

/**
 * <p>BrowserType annotation is used to setup which type of browser will be used for the method to run</p>
 * <p>Value in the annotation will overwrite 'browser.type' in project.properties</p>
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BrowserType {
	public Browser value() default Browser.CHROME;
}
