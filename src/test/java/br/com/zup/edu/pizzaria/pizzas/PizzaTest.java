package br.com.zup.edu.pizzaria.pizzas;

import br.com.zup.edu.pizzaria.ingredientes.Ingrediente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PizzaTest {

    private final static BigDecimal PRECO_MASSA = new BigDecimal("15.0");
    private final static BigDecimal PRECO_MAO_DE_OBRA = new BigDecimal("5.0");

    @Test
    void deveCalcularPrecoPizza(){
        Pizza pizza = new Pizza("Mussarela",
                Arrays.asList(new Ingrediente("Alho", 10, new BigDecimal("0.40")),
                        new Ingrediente("Pimenta", 100, new BigDecimal("0.10")),
                        new Ingrediente("Oregano", 10, new BigDecimal("0.20"))));

        BigDecimal soma = PRECO_MASSA;
        soma = soma.add(PRECO_MAO_DE_OBRA);
        soma = soma.add(new BigDecimal("0.40"));
        soma = soma.add(new BigDecimal("0.10"));
        soma = soma.add(new BigDecimal("0.20"));

        assertEquals(soma, pizza.getPreco());
    }

}