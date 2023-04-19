package se.iths.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.iths.webshop.business.*;
import se.iths.webshop.storage.OrderRepository;
import se.iths.webshop.storage.ProductRepository;


@SpringBootApplication
public class WebShopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }


    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

//        productRepository.save(new Product("Spade", 200, ProductCategory.TOOL));
//               productRepository.save(new Product("Rake", 300, ProductCategory.TOOL));
//             productRepository.save(new Product("Watering can", 150, ProductCategory.TOOL));
//           productRepository.save(new Product("AppleTree", 800, ProductCategory.TREE));
//        productRepository.save(new Product("CherryTree", 700, ProductCategory.TREE));
//              productRepository.save(new Product("Tomato", 35, ProductCategory.SEED));
//        productRepository.save(new Product("Cucumber", 30, ProductCategory.SEED));
//              productRepository.save(new Product("Dill", 40, ProductCategory.SEED));
//            productRepository.save(new Product("Amaryllis", 100, ProductCategory.FLOWER));
//          productRepository.save(new Product("Tulip", 175, ProductCategory.FLOWER));


    }
}


