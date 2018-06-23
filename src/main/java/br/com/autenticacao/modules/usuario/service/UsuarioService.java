package br.com.autenticacao.modules.usuario.service;

import br.com.autenticacao.modules.comum.exception.ValidacaoException;
import br.com.autenticacao.modules.usuario.dto.UsuarioResponse;
import br.com.autenticacao.modules.usuario.model.Usuario;
import br.com.autenticacao.modules.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private static final ValidacaoException EX_NAO_ENCONTRADO = new ValidacaoException("Usuário não encontrado.");

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email.toUpperCase());
    }

    public UsuarioResponse findById(Integer id) {
        return UsuarioResponse.parse(usuarioRepository.findById(id).orElseThrow(() -> EX_NAO_ENCONTRADO));
    }
}
