package br.com.autenticacao.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("name",
                "$2a$10$e4I0XXj8o0/UVVnmPnH6qOzqgqKPASnJG5rirWV4Lnml8xkbJcQ/2",
                Arrays.asList(new SimpleGrantedAuthority("ADMIN")));// FIXME Criar models de Usuario e Permissao
    }
}
