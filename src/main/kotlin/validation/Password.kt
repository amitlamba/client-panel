/*
package com.und.validation

import java.lang.annotation.Documented;
import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

import kotlin.annotation.AnnotationRetention.RUNTIME


*/
/**
 * The string has to be a well-formed email address.
 *
 * @author shiv pratap singh
 *//*

@MustBeDocumented
@Constraint(validatedBy = arrayOf())
@Target(AnnotationTarget.FIELD)
@Retention(RUNTIME)
@ReportAsSingleViolation
@Pattern(regexp = "")
annotation class Password {
	String message() default "{org.hibernate.validator.constraints.Email.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	*/
/**
	 * @return an additional regular expression the annotated string must match. The default is any string ('.*')
	 *//*

	@OverridesAttribute(constraint = Pattern.class, name = "regexp") String regexp() default ".*";

	*/
/**
	 * @return used in combination with {@link #regexp()} in order to specify a regular expression option
	 *//*

	@OverridesAttribute(constraint = Pattern.class, name = "flags") Pattern.Flag[] flags() default { };

	*/
/**
	 * Defines several {@code @Email} annotations on the same element.
	 *//*

    @Target(AnnotationTarget.FIELD)
    @Retention(RUNTIME)
    @MustBeDocumented
	annotation class List {
        Password[] value();
	}
}*/
