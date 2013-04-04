package ag.bpc.validation;

import javax.validation.*;
import java.util.Set;

public class ValueValidValidator implements ConstraintValidator<ValueValid, Object> {

    private final Validator validator = Validation.byDefaultProvider().configure()
            .buildValidatorFactory().getValidator();

    @Override
    public void initialize(ValueValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (value != null) {
            context.disableDefaultConstraintViolation();
            Set<ConstraintViolation<Object>> violations = validator.validate(value);
            for (ConstraintViolation<Object> violation : violations) {
                context.buildConstraintViolationWithTemplate(violation.getMessage()).addConstraintViolation();
            }
            isValid = violations.isEmpty();
        }
        return isValid;
    }

}
