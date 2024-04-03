package restaurant.models;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.restaurante.controller.OrderController;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import spark.Route;

public class OrderTest {

    private OrderController orderController;
    private Request request;
    private Response response;

    @Before
    public void setUp() {
        orderController = new OrderController();
        request = mock(Request.class);
        response = mock(Response.class);
    }

    @Test
    public void testPost() throws Exception {
        // Mock do método AddOrder
        when(orderController.AddOrder(request, response)).thenReturn("Pedido adicionado com sucesso");

        // Chamar o método post e verificar se o resultado está correto
        Route postRoute = OrderController.post;
        Object result = postRoute.handle(request, response);
        assertEquals("Pedido adicionado com sucesso", result);

        // Verificar se o método AddOrder foi chamado corretamente
        verify(orderController).AddOrder(request, response);
    }

    @Test
    public void testGetAll() throws Exception {
        // Mock do método GetAllOrders
        when(orderController.GetAllOrders()).thenReturn("Lista de pedidos");

        // Chamar o método getAll e verificar se o resultado está correto
        Route getAllRoute = OrderController.getAll;
        Object result = getAllRoute.handle(request, response);
        assertEquals("Lista de pedidos", result);

        // Verificar se o método GetAllOrders foi chamado corretamente
        verify(orderController).GetAllOrders();
    }

    @Test
    public void testGetById() throws Exception {
        // Mock do método GetOrderById
        when(orderController.GetOrderById(request)).thenReturn("Pedido com ID específico");

        // Chamar o método getById e verificar se o resultado está correto
        Route getByIdRoute = OrderController.getById;
        Object result = getByIdRoute.handle(request, response);
        assertEquals("Pedido com ID específico", result);

        // Verificar se o método GetOrderById foi chamado corretamente
        verify(orderController).GetOrderById(request);
    }

    @Test
    public void testDelete() throws Exception {
        // Mock do método DeleteOrderById
        when(orderController.DeleteOrderById(request, response)).thenReturn("Pedido excluído com sucesso");

        // Chamar o método delete e verificar se o resultado está correto
        Route deleteRoute = OrderController.delete;
        Object result = deleteRoute.handle(request, response);
        assertEquals("Pedido excluído com sucesso", result);

        // Verificar se o método DeleteOrderById foi chamado corretamente
        verify(orderController).DeleteOrderById(request, response);
    }
}
