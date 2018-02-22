package br.com.xyinc;

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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Testes {

    private static String URL_BASE = "http://localhost:8080/poi";
    private MockMvc mock;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mock = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test01BaseVazia() throws Exception {
        System.out.println("Teste01 - INICIO - Busca na base vazia");
        mock.perform(get(URL_BASE + "/list"))
                .andExpect(status().isConflict());
        System.out.println("Teste01 - FIM");
    }

    @Test
    public void test02SavePoiSucesso() throws Exception {
        System.out.println("Teste02 - INICIO - Salva Ponto de Interesse com sucesso");
        Poi poi = new Poi(null, "Lanchonete", 27, 12);

        Gson gson = new Gson();
        mock.perform(post(URL_BASE + "/add").contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(poi)))
                .andExpect(status().isCreated());
        System.out.println("Teste02 - FIM");
    }

    @Test
    public void test03PopulaBase() throws Exception {
        System.out.println("Teste03 - INICIO - Popula Base");
        List<Poi> pois = new ArrayList<>();
        pois.add(new Poi(null, "Posto", 31, 18));
        pois.add(new Poi(null, "Joalheria", 15, 12));
        pois.add(new Poi(null, "Floricultura", 19, 21));
        pois.add(new Poi(null, "Pub", 12, 8));
        pois.add(new Poi(null, "Supermercado", 23, 6));
        pois.add(new Poi(null, "Churrascaria", 28, 2));

        Gson gson = new Gson();
        for (Poi poi : pois) {
            mock.perform(post(URL_BASE + "/add").contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(poi)));
        }
        System.out.println("Teste03 - FIM");
    }

    @Test
    public void test04SaveValorNegativo() throws Exception {
        System.out.println("Teste04 - INICIO - Salva Ponto de Interesse com falha (Valor Negativo)");
        Poi poi = new Poi(null, "Cinemark", -15, 15);

        Gson gson = new Gson();
        mock.perform(post(URL_BASE + "/add").contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(poi)))
                .andExpect(status().isBadRequest());
        System.out.println("Teste04 - FIM");
    }

    @Test
    public void test05SaveValorNulo() throws Exception {
        System.out.println("Teste05 - INICIO - Salva Ponto de Interesse com falha (Valor Nulo)");
        Poi poi = new Poi(null, "Cinemark", null, null);

        Gson gson = new Gson();
        mock.perform(post(URL_BASE + "/add").contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(poi)))
                .andExpect(status().isBadRequest());
        System.out.println("Teste05 - FIM");
    }

    @Test
    public void test06BuscaPontoProximo() throws Exception {
        System.out.println("Teste06 - INICIO - Busca ponto Proximo");
        mock.perform(get(URL_BASE + "/buscaProximos/20/10/10"))
                .andExpect(status().isOk());
        System.out.println("Teste06 - FIM");
    }

    @Test
    public void test07BuscaPontoProximoNaoEncontrado() throws Exception {
        System.out.println("Teste07 - INICIO - Busca ponto Proximo - Not Found");
        mock.perform(get(URL_BASE + "/buscaProximos/99/99/1"))
                .andExpect(status().isConflict());
        System.out.println("Teste07 - FIM");
    }

    @Test
    public void test08List() throws Exception {
        System.out.println("Teste08 - INICIO - Busca bem sucedida");
        mock.perform(get(URL_BASE + "/list"))
                .andExpect(status().isOk());
        System.out.println("Teste08 - FIM");
    }


}
