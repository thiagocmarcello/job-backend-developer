package br.com.autenticacao.modules.usuario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_USUARIO", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Email
    @NotNull
    @Size(max = 80)
    @Column(name = "EMAIL", nullable = false, length = 80, unique = true)
    private String email;

    @CPF
    @NotNull
    @Column(name = "CPF", length = 14, unique = true)
    private String cpf;

    @JsonIgnore
    @Column(name = "SENHA", nullable = false, updatable = false, length = 80)
    private String senha;

    @NotNull
    @JoinColumn(name = "FK_PERMISSAO", referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_USUARIO_PERMISSAO"))
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Permissao permissao;

    public List<SimpleGrantedAuthority> obterPermissao() {
        return Arrays.asList(new SimpleGrantedAuthority(this.permissao.obterRoleConfigurada()));
    }
}
