package ag.bpc.validation;

import org.junit.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ValueValidValidatorTest {

    private final Configuration configuration = Validation.byDefaultProvider().configure();

    @Test
    public void testIsValid() throws Exception {
        ValidatorFactory factory = this.configuration.buildValidatorFactory();
        Validator validator = factory.getValidator();

        EmailHolder emailHolder = new EmailHolder();

        Set<ConstraintViolation<EmailHolder>> violations = validator.validate(emailHolder);
        assertEquals(1, violations.size());
        ConstraintViolation<EmailHolder> violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("kann nicht null sein", violation.getMessage());

        emailHolder.setEmail(new Email(null));

        violations = validator.validate(emailHolder);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("kann nicht null sein", violation.getMessage());

        emailHolder.setEmail(new Email("a"));

        violations = validator.validate(emailHolder);
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("keine gültige E-Mail-Adresse", violation.getMessage());

        emailHolder.setEmail(new Email("a@be.de"));

        violations = validator.validate(emailHolder);
        assertEquals(0, violations.size());
    }

}
