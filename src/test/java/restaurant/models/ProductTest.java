package restaurant.models;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.restaurante.controller.ProductController;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import spark.Route;

public class ProductTest {
    private ProductController productController;
    private Request request;
    private Response response;

    @Before
    public void setUp() {
        productController = new ProductController();
        request = mock(Request.class);
        response = mock(Response.class);
    }

    @Test
    public void testPost() throws Exception {
        // Mock do método AddProduct
        when(productController.AddProduct(request, response)).thenReturn("Produto adicionado com sucesso");

        // Chamar o método post e verificar se o resultado está correto
        Route postRoute = ProductController.post;
        Object result = postRoute.handle(request, response);
        assertEquals("Produto adicionado com sucesso", result);

        // Verificar se o método AddProduct foi chamado corretamente
        verify(productController).AddProduct(request, response);
    }

    @Test
    public void testGetAll() throws Exception {
        // Mock do método GetAllProduct
        when(productController.GetAllProduct()).thenReturn("Lista de produtos");

        // Chamar o método getAll e verificar se o resultado está correto
        Route getAllRoute = ProductController.getAll;
        Object result = getAllRoute.handle(request, response);
        assertEquals("Lista de produtos", result);

        // Verificar se o método GetAllProduct foi chamado corretamente
        verify(productController).GetAllProduct();
    }

    @Test
    public void testDelete() throws Exception {
        // Mock do método DeleteOrderById
        when(productController.DeleteOrderById(request, response)).thenReturn("Produto excluído com sucesso");

        // Chamar o método delete e verificar se o resultado está correto
        Route deleteRoute = ProductController.delete;
        Object result = deleteRoute.handle(request, response);
        assertEquals("Produto excluído com sucesso", result);

        // Verificar se o método DeleteOrderById foi chamado corretamente
        verify(productController).DeleteOrderById(request, response);
    }
}
