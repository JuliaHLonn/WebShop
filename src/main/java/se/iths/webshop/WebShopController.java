package se.iths.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.webshop.business.*;



@Controller
public class WebShopController {


    @Autowired
    ShopService service;

    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(Model m, @RequestParam String username, @RequestParam String password) {
        m.addAttribute("errorstring", "Wrong username or password");
        return service.loginOrCreateUser(username, password);

    }

    @GetMapping("/shopAdmin")
    public String getAdminAlternatives(){
        return "shopAdmin";
    }
    @PostMapping("/shopAdmin")
    public String adminSelection(Model m, @RequestParam("choice") String choice) {
        m.addAttribute("enumvalues", ProductCategory.values());
        return service.adminSelect(choice, m);
    }

    @PostMapping("/undelivered-orders")
    public String handleUndeliveredOrders(Model m, @RequestParam("delivered") String delivered, @RequestParam("id") Long id) {
        if (delivered != null) {
            service.markOrderAsDelivered(id, m);
        }
        return "undeliveredOrders";
    }

    @PostMapping("/new")
    String addNewProduct(Product product, Model m) {
        service.add(product);
        m.addAttribute("enumvalues", ProductCategory.values());
        m.addAttribute("product", new Product());
        return "newproduct";
    }

    @GetMapping("/productpage")
    public String showProductPage(@RequestParam long id, Model m) {
        Product p = service.getProductById(id);
        m.addAttribute("product", p);
        return "productpage";
    }

    @GetMapping("/shop")
    public String getShopPage(){
        return "shop";
    }

    @PostMapping("/shop")
    public String chooseCategory(Model m, @RequestParam("choice") String choice, @RequestParam String product) {
       return service.chooseCategory(m, choice, product);

    }

    @PostMapping("/product")
    public String addToCart(@RequestParam Long id, @RequestParam int amount) {
        service.putInCart(id, amount);
        return "shop";
    }

    @GetMapping("/shoppingcart")
    public String showShoppingCart(Model m) {
       return service.showShoppingCart(m);
    }

    @PostMapping("/shoppingcart")
    public String modifyShoppingCart(Model m, @RequestParam String action, @RequestParam int orderlineindex) {
        return service.modifyShoppingCart(m, action, orderlineindex);
    }

    @PostMapping("/checkout")
    public String doCheckout(Model m, @RequestParam String email){
        return service.doCheckout(m, email);
    }

}



