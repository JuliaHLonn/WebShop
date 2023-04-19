package se.iths.webshop.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.annotation.SessionScope;
import se.iths.webshop.storage.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@SessionScope
public class ShopService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    Customer customer;

    ArrayList<OrderLine> shoppingCart = new ArrayList<>();


    public List<CustomerOrder> showOrders(boolean delivered) {
         return orderRepository.findByDelivered(delivered);
    }

    public String loginOrCreateUser(String username, String password) {
        if (username.equalsIgnoreCase("admin") && password.equals("root")) {
            return "shopAdmin";
        } else if (customerRepository.findByName(username).isEmpty() && !username.equals("admin")) {
            createCustomer(username, password);
            return "shop";
        }

        for (Customer c : customerRepository.findByName(username)) {
            if (username.equalsIgnoreCase(c.getName()) && password.equals(c.getPassword()))
            { customer = c;
            return "shop";}
        }

        return "login";
    }

    public void createCustomer(String name, String password) {
        customer = new Customer(name, password);
        customer = customerRepository.save(customer);
    }

    public String chooseCategory(Model m, String choice, String product) {
        if (choice.equalsIgnoreCase("search")) {
            m.addAttribute("product", showProduct(product));
            return "productpage";
        } else {
            m.addAttribute("category", showCategory(choice));
            return "productcategory";
        }
    }


    public String adminSelect(String choice, Model m) {
        switch (choice) {
            case "Delivered Orders" -> {
                m.addAttribute("deliveredorders", showOrders(true));
                return "deliveredOrders";
            }
            case "Undelivered Orders" -> {
                m.addAttribute("undeliveredorders", showOrders(false));
                return "undeliveredOrders";
            }
            case "Add product" -> {
                m.addAttribute("product", new Product());
                return "newproduct";
            }
            default -> {
                return "shopAdmin";
            }
        }
    }

    public void markOrderAsDelivered(Long id, Model m) {
        CustomerOrder customerOrder = orderRepository.findById(id).get();
        customerOrder.setDelivered(true);
        customerOrder = orderRepository.save(customerOrder);
        m.addAttribute("undeliveredorders", showOrders(false));
        System.out.println("Is delivered : "+ customerOrder.delivered);
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public List<Product> showCategory(String choice) {
        ProductCategory category = null;
        if (choice.equalsIgnoreCase("Flowers"))
            category = ProductCategory.FLOWER;
        else if (choice.equalsIgnoreCase("Tools"))
            category = ProductCategory.TOOL;
        else if (choice.equalsIgnoreCase("Trees"))
            category = ProductCategory.TREE;
        else if (choice.equalsIgnoreCase("Seeds"))
            category = ProductCategory.SEED;

        return productRepository.findByCategory(category);
    }

    public List<Product> showProduct(String product) {
        return productRepository.findByName(product);
    }


    public void putInCart(Long id, int quantity) {
        Product p = productRepository.findById(id).get();
        OrderLine orderline = new OrderLine(p, quantity);
        shoppingCart.add(orderline);
    }

    public double calculateTotal(List<OrderLine> orderLines) {
        double total = 0;
        for (OrderLine orderLine : orderLines) {
            double lineTotal = orderLine.getQuantity() * orderLine.getProduct().getPrice();
            orderLine.setSubTotal(lineTotal);
            total += lineTotal;
        }
        return total;
    }


    public void makeOrder(String email) {
        CustomerOrder customerOrder = new CustomerOrder(LocalDateTime.now(), false, customer, shoppingCart);
        customerOrder = orderRepository.save(customerOrder);
        customer.addOrder(customerOrder);
        customer.setEmail(email);
        customer = customerRepository.save(customer);
        shoppingCart.clear();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).get();
    }


    public ArrayList<OrderLine> getShoppingCart() {
        return shoppingCart;
    }

    public void decreaseQuantityInCart(OrderLine orderline) {
        orderline.quantity--;
    }

    public void increaseQuantityInCart(OrderLine orderLine) {
        orderLine.quantity++;
    }

    public void removeFromCart(OrderLine orderLine) {
        shoppingCart.remove(orderLine);
    }

    public OrderLine getOrderLineById(Long id) {
        return orderLineRepository.findById(id).get();
    }

    public String showShoppingCart(Model m) {
        m.addAttribute("shoppingCart", getShoppingCart());
        m.addAttribute("totalPrice", calculateTotal(getShoppingCart()));
        return "shoppingcartpage";
    }

    public String modifyShoppingCart(Model m, String action, int orderlineindex) {
        OrderLine orderline = getShoppingCart().get(orderlineindex);
        switch (action) {
            case "decrease" -> decreaseQuantityInCart(orderline);
            case "increase" -> increaseQuantityInCart(orderline);
            case "remove" -> removeFromCart(orderline);
        }
        m.addAttribute("shoppingCart", getShoppingCart());
        m.addAttribute("totalPrice", calculateTotal(getShoppingCart()));
        return "shoppingcartpage";
    }

    public String doCheckout(Model m, String email) {
        List<OrderLine> currentOrder = new ArrayList<>(getShoppingCart());
        double total = calculateTotal(currentOrder);
        emailSenderService.sendemail(email);
        makeOrder(email);
        m.addAttribute("customerorder", currentOrder);
        m.addAttribute("totalPrice", total);
        return "orderConfirmationPage";
    }


}
