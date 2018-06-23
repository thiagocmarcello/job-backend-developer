package br.com.autenticacao.config;

import br.com.autenticacao.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioService
                .findByEmail(username.toUpperCase())
                .map(u -> new User(
                        u.getId().toString().concat("-").concat(u.getEmail()),
                        u.getSenha(),
                        u.obterPermissao()))
                .orElseThrow(() ->
                        new UsernameNotFoundException("Email ou senha inválido."));
    }
}
