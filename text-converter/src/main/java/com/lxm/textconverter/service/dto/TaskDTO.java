package com.lxm.textconverter.service.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * A TaskDTO.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "sourceURL is mandatory")
    @Size(min = 1, max = 500)
    private String sourceURL;

    @NotBlank(message = "callbackURL is mandatory")
    @Size(min = 1, max = 500)
    private String callbackURL;

    private String convertState;

    @NotBlank
    @Size(min = 1, max = 50)
    private String createdBy;
}
