package dev.mateustpas.user_register.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="estado",length=2)
    private String estado;
    @Column(name="cidade")
    private String cidade;
    @Column(name="rua", length=100)
    private String rua;
    @Column(name="numero")
    private Long numero;
    @Column(name="complemento")
    private String complemento;
    @Column(name="cep", length=9)
    private String cep;
}
