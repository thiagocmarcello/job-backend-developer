package br.com.autenticacao.modules.usuario.repository;

import br.com.autenticacao.modules.usuario.model.Usuario;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer>,
        QueryDslPredicateExecutor<Usuario> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(Integer id);
}
