//TODO comment
package es.regueiro.collectionManager.utils.validator;

import java.util.regex.Matcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullOrPatternValidator implements
		ConstraintValidator<NullOrPattern, String> {

	private java.util.regex.Pattern pattern;

	public void initialize(NullOrPattern parameters) {
		pattern = java.util.regex.Pattern.compile(parameters.regexp(),
				parameters.flags());
	}


	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		if (arg0 == null)
			return true;
		if (!(arg0 instanceof String))
			return false;
		String string = (String) arg0;
		if (string.length() == 0)
			return false;
		Matcher m = pattern.matcher(string);
		return m.matches();
	}

}