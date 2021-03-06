package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class TestsHelper {

    public static String getAccessToken(MockMvc mvc, String usuario) throws Exception {
        return "Bearer " + getAccessTokenObject(mvc, usuario).getAccessToken();
    }

    public static OAuthToken getAccessTokenObject(MockMvc mvc, String usuario) {
        try {
            MockHttpServletResponse response = mvc
                    .perform(
                            post("/oauth/token")
                                    .header("Authorization", "Basic "
                                            + new String(Base64Utils.encode(("app-client:4pp").getBytes())))
                                    .param("username", usuario)
                                    .param("password", "123456")
                                    .param("grant_type", "password"))
                    .andReturn().getResponse();

            return new ObjectMapper()
                    .readValue(response.getContentAsByteArray(), OAuthToken.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new OAuthToken();
        }
    }

    public static OAuthToken getAccessTokenClientCredentials(MockMvc mvc, String app) {
        try {
            MockHttpServletResponse response = mvc
                    .perform(
                            post("/oauth/token")
                                    .header("Authorization", "Basic "
                                            + new String(Base64Utils.encode((app).getBytes())))
                                    .param("grant_type", "client_credentials"))
                    .andReturn().getResponse();

            return new ObjectMapper()
                    .readValue(response.getContentAsByteArray(), OAuthToken.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new OAuthToken();
        }
    }
}
