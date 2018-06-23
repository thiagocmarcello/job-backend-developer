package br.com.autenticacao.modules.usuario.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "PERMISSAO")
@EqualsAndHashCode(of = {"id"})
public class Permissao {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_PERMISSAO", sequenceName = "SEQ_PERMISSAO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_PERMISSAO", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "ROLE")
    private String role;

    public String obterRoleConfigurada() {
        return "ROLE_".concat(this.role);
    }
}
