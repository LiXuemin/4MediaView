package com.lxm.textconverter.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * A TaskDTO.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "text_convert_task")
public class TextConvertTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "sourceURL is mandatory")
    @Size(min = 1, max = 500)
    @Column(name = "source_url", length = 500)
    private String sourceURL;

    @Column(name = "target_url", length = 500)
    private String targetURL;

    @Size(min = 1, max = 500)
    @NotBlank(message = "callbackURL is mandatory")
    @Column(name = "callback_url", length = 500)
    private String callbackURL;

    @Column(name = "convert_state", length = 25)
    private String convertState;

    @Column(name = "convert_type", length = 25)
    private String convertType;

    @Column(name = "app_code", length = 256)
    private String appCode;
}
