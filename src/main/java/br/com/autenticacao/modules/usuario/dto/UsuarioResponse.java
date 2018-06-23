package br.com.autenticacao.modules.usuario.dto;

import br.com.autenticacao.modules.usuario.model.Usuario;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UsuarioResponse {

    private Integer id;
    private String nome;
    private String email;

    public static UsuarioResponse parse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        BeanUtils.copyProperties(usuario, response);
        return response;
    }
}
