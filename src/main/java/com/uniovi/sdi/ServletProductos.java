package com.uniovi.sdi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletProductos", value = "/products")
public class ServletProductos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductsService productsService = new ProductsService();
        List<Product> products = productsService.getProducts();

        request.setAttribute("storeProducts", products);
        getServletContext().getRequestDispatcher("/products-view.jsp").forward(request, response);
    }

}
