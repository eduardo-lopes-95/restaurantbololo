package restaurant.models;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.restaurante.controller.AddressController;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddressTest {

    private AddressController addressController;
    private Request request;
    private Response response;

    @Before
    public void setUp() {
        addressController = new AddressController();
        request = mock(Request.class);
        response = mock(Response.class);
    }

    @Test
    public void testPost() throws Exception {
        // Mock do método AddNewAddress
        when(addressController.AddNewAddress(request, response)).thenReturn("Endereço adicionado com sucesso");

        // Chamar o método post e verificar se o resultado está correto
        Route postRoute = AddressController.post;
        Object result = postRoute.handle(request, response);
        assertEquals("Endereço adicionado com sucesso", result);

        // Verificar se o método AddNewAddress foi chamado corretamente
        verify(addressController).AddNewAddress(request, response);
    }

    @Test
    public void testGetById() throws Exception {
        // Mock do método GetAddressById
        when(addressController.GetAddressById(request, response)).thenReturn("Endereço com ID específico");

        // Chamar o método getById e verificar se o resultado está correto
        Route getByIdRoute = AddressController.getById;
        Object result = getByIdRoute.handle(request, response);
        assertEquals("Endereço com ID específico", result);

        // Verificar se o método GetAddressById foi chamado corretamente
        verify(addressController).GetAddressById(request, response);
    }

    @Test
    public void testGetAll() throws Exception {
        // Mock do método GetAllAddresses
        when(addressController.GetAllAddresses(response)).thenReturn("Lista de endereços");

        // Chamar o método getAll e verificar se o resultado está correto
        Route getAllRoute = AddressController.getAll;
        Object result = getAllRoute.handle(request, response);
        assertEquals("Lista de endereços", result);

        // Verificar se o método GetAllAddresses foi chamado corretamente
        verify(addressController).GetAllAddresses(response);
    }

    @Test
    public void testDeletes() throws Exception {
        // Mock do método DeleteAddress
        when(addressController.DeleteAddress(request)).thenReturn("Endereço excluído com sucesso");

        // Chamar o método deletes e verificar se o resultado está correto
        Route deletesRoute = AddressController.deletes;
        Object result = deletesRoute.handle(request, response);
        assertEquals("Endereço excluído com sucesso", result);

        // Verificar se o método DeleteAddress foi chamado corretamente
        verify(addressController).DeleteAddress(request);
    }
}

