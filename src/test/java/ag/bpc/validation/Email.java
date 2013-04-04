package ag.bpc.validation;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Email {
    @org.hibernate.validator.constraints.Email
    @NotNull
    private final String value;
}
