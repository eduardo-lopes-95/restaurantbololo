package restaurant.models;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.restaurante.controller.ClientController;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import spark.Route;

public class ClientTest {

    private ClientController clientController;
    private Request request;
    private Response response;

    @Before
    public void setUp() {
        clientController = new ClientController();
        request = mock(Request.class);
        response = mock(Response.class);
    }

    @Test
    public void testPost() throws Exception {
        // Mock do método AddClient
        when(clientController.AddClient(request, response)).thenReturn("Cliente adicionado com sucesso");

        // Chamar o método post e verificar se o resultado está correto
        Route postRoute = ClientController.post;
        Object result = postRoute.handle(request, response);
        assertEquals("Cliente adicionado com sucesso", result);

        // Verificar se o método AddClient foi chamado corretamente
        verify(clientController).AddClient(request, response);
    }

    @Test
    public void testGetById() throws Exception {
        // Mock do método GetClientById
        when(clientController.GetClientById(request, response)).thenReturn("Cliente com ID específico");

        // Chamar o método getById e verificar se o resultado está correto
        Route getByIdRoute = ClientController.getById;
        Object result = getByIdRoute.handle(request, response);
        assertEquals("Cliente com ID específico", result);

        // Verificar se o método GetClientById foi chamado corretamente
        verify(clientController).GetClientById(request, response);
    }

    @Test
    public void testGetAll() throws Exception {
        // Mock do método GetAllClients
        when(clientController.GetAllClients(response)).thenReturn("Lista de clientes");

        // Chamar o método getAll e verificar se o resultado está correto
        Route getAllRoute = ClientController.getAll;
        Object result = getAllRoute.handle(request, response);
        assertEquals("Lista de clientes", result);

        // Verificar se o método GetAllClients foi chamado corretamente
        verify(clientController).GetAllClients(response);
    }

    @Test
    public void testDelete() throws Exception {
        // Mock do método DeleteClientById
        when(clientController.DeleteClientById(request)).thenReturn("Cliente excluído com sucesso");

        // Chamar o método delete e verificar se o resultado está correto
        Route deleteRoute = ClientController.delete;
        Object result = deleteRoute.handle(request, response);
        assertEquals("Cliente excluído com sucesso", result);

        // Verificar se o método DeleteClientById foi chamado corretamente
        verify(clientController).DeleteClientById(request);
    }
}

