package com.uniovi.sdi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ServletShoppingCart", value = "/AddToShoppingCart")
public class ServletShoppingCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<String, Integer> cart = (HashMap<String, Integer>) request.getSession().getAttribute("cart"); // No hay carrito, creamos uno y lo insertamos en sesión
        if (cart == null) {
            cart = new HashMap<String, Integer>();
            request.getSession().setAttribute("cart", cart);
        }
        String product = request.getParameter("product");
        if (product != null) {
            addToShoppingCart(cart, product);
        }
        // Retornar la vista con parámetro "selectedItems"
        request.setAttribute("selectedItems", cart);
        getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    private void addToShoppingCart(HashMap<String, Integer> cart, String productKey) {
        if (cart.get(productKey) == null) {
            cart.put(productKey, Integer.valueOf(1));
        } else {
            int productCount = (Integer) cart.get(productKey).intValue();
            cart.put(productKey, Integer.valueOf(productCount + 1));
        }
    }

    private String shoppingCartToHtml(Map<String, Integer> cart) {
        String shoppingCartToHtml = "";
        for (String key : cart.keySet()) {
            shoppingCartToHtml += "<p>[" + key + "]" + cart.get(key) + "unidades</p>";
        }
        return shoppingCartToHtml;
    }

}