package ag.bpc.validation;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailHolder {

    @NotNull
    @ValueValid
    private Email email;

}
