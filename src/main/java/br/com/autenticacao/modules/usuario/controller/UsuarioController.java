package br.com.autenticacao.modules.usuario.model;

import br.com.autenticacao.modules.usuario.dto.UsuarioResponse;
import br.com.autenticacao.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public UsuarioResponse getUsuarioById(@RequestParam Integer id) {
        return usuarioService.findById(id);
    }
}
