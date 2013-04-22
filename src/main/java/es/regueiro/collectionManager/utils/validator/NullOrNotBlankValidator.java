//TODO comment
package es.regueiro.collectionManager.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class NullOrNotBlankValidator implements
		ConstraintValidator<NullOrNotBlank, String> {

	@Override
	public void initialize(NullOrNotBlank arg0) {
		
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		if (arg0 == null)
			return true;
		if (!(arg0 instanceof String))
			return false;
		String string = (String) arg0;
		return !StringUtils.isBlank(string);
	}

	
}