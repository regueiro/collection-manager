//TODO comment
package es.regueiro.collectionManager.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NullOrPatternValidator.class)
@Documented

public @interface NullOrPattern {
	/** regular expression */
	String regexp();

	/** regular expression processing flags */
	int flags() default 0;
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[]  payload() default {};

	String message() default "{validator.pattern}";
}