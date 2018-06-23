package oauth;

import br.com.autenticacao.Application;
import helpers.OAuthToken;
import helpers.TestsHelper;
import helpers.Usuarios;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = Application.class)
@Transactional
@Sql(scripts = {"classpath:/tests_database.sql"})
public class AutenticacaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void deveAutenticar() {
        OAuthToken token = TestsHelper.getAccessTokenObject(mvc, Usuarios.ADMIN);
        assertNotNull(token.getAccessToken());
    }

    @Test
    public void deveNaoAutenticar() {
        OAuthToken token = TestsHelper.getAccessTokenObject(mvc, Usuarios.USUARIO_NAO_EXISTENTE);
        assertNull(token.getAccessToken());
    }

    @Test
    public void deveChecarATokenComoInvalida() throws Exception {
        mvc.perform(
                post("/oauth/check_token")
                        .param("token", "teste"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveAutenticarUsandoClientCredentials() {
        assertNotNull(TestsHelper
                .getAccessTokenClientCredentials(mvc, "external-api:4p1").getAccessToken());
    }
}
