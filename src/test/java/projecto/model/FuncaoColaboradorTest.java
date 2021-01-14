package projecto.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FuncaoColaboradorTest {
    @Test
    void FuncaoColaboradorTeste() {
        FuncaoColaborador novofuncaoColaborador = new FuncaoColaborador(1, "Programador", 25.0);
        assertEquals("Programador", novofuncaoColaborador.getNomeFuncao());
        assertNotEquals(20.0, novofuncaoColaborador.getValorHora());

        novofuncaoColaborador.setNomeFuncao("Analista");
        novofuncaoColaborador.setValorHora(30.0);

        assertEquals("Analista", novofuncaoColaborador.getNomeFuncao());
        assertEquals(30.0, novofuncaoColaborador.getValorHora());
    }
}