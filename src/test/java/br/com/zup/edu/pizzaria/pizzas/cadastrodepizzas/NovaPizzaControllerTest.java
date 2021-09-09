package br.com.zup.edu.pizzaria.pizzas.cadastrodepizzas;

import br.com.zup.edu.pizzaria.ingredientes.Ingrediente;
import br.com.zup.edu.pizzaria.ingredientes.IngredienteRepository;
import br.com.zup.edu.pizzaria.pizzas.cadastropizza.NovaPizzaRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class NovaPizzaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Test
    void deveCadastrarNovaPizza() throws Exception {

        Ingrediente ingrediente1 = new Ingrediente(
                "cenoura",
                12,
                new BigDecimal("20.00"));

        Ingrediente ingrediente2 = new Ingrediente(
                "frango",
                13,
                new BigDecimal("17.00"));

        ingredienteRepository.save(ingrediente1);
        ingredienteRepository.save(ingrediente2);

        NovaPizzaRequest body = new NovaPizzaRequest(
                "Mussarela",
                Arrays.asList(ingrediente1.getId(), ingrediente2.getId())
        );

        MockHttpServletRequestBuilder request = postRequest("/api/pizzas", body);
        mvc.perform(request)
                .andExpect(status().isCreated());
    }

    public static MockHttpServletRequestBuilder postRequest(String url,NovaPizzaRequest body) throws JsonProcessingException {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(body));
    }
}
