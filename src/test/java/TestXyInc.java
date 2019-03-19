import br.com.xyinc.models.Poi;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestXyInc {

    private static String URL_BASE = "http://localhost:8080/poi";
    private MockMvc mock;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mock = webAppContextSetup(webApplicationContext).build();
    }

    @org.junit.Test
    public void test01SavePoiSucesso() throws Exception {
        System.out.println("Teste01 - INICIO - Salva Ponto de Interesse com sucesso");
        Poi poi = new Poi(null, "HAMBURGUERIA", 33, 33);

        Gson gson = new Gson();
        mock.perform(post(URL_BASE).contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(poi)))
                .andExpect(status().isCreated());
        System.out.println("Teste01 - FIM");
    }

    @org.junit.Test
    public void test02SaveValorNegativo() throws Exception {
        System.out.println("Teste02 - INICIO - Salva Ponto de Interesse com falha (Valor Negativo)");
        Poi poi = new Poi(null, "Cinemark", -15, 15);

        Gson gson = new Gson();
        mock.perform(post(URL_BASE).contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(poi)))
                .andExpect(status().isBadRequest());
        System.out.println("Teste02 - FIM");
    }

    @org.junit.Test
    public void test03SaveValorNulo() throws Exception {
        System.out.println("Teste03 - INICIO - Salva Ponto de Interesse com falha (Valor Nulo)");
        Poi poi = new Poi(null, "Cinemark", null, null);

        Gson gson = new Gson();
        mock.perform(post(URL_BASE).contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(poi)))
                .andExpect(status().isBadRequest());
        System.out.println("Teste03 - FIM");
    }

    @org.junit.Test
    public void test04BuscaPontoProximo() throws Exception {
        System.out.println("Teste04 - INICIO - Busca ponto Proximo");
        mock.perform(get(URL_BASE + "/proximidade/20/10/10"))
                .andExpect(status().isOk());
        System.out.println("Teste04 - FIM");
    }

    @org.junit.Test
    public void test05BuscaPontoProximoNaoEncontrado() throws Exception {
        System.out.println("Teste05 - INICIO - Busca ponto Proximo - Not Found");
        mock.perform(get(URL_BASE + "/proximidade/99/99/1"))
                .andExpect(status().isConflict());
        System.out.println("Teste05 - FIM");
    }

    @org.junit.Test
    public void test06List() throws Exception {
        System.out.println("Teste06 - INICIO - Busca bem sucedida");
        mock.perform(get(URL_BASE))
                .andExpect(status().isOk());
        System.out.println("Teste06 - FIM");
    }


}
