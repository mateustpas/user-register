package dev.mateustpas.user_register.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {

    private String estado;
    private String cidade;
    private String rua;
    private Long numero;
    private String complemento;
    private String cep;
}
