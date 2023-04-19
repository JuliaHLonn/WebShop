package se.iths.webshop.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.business.Product;
import se.iths.webshop.business.ProductCategory;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findByCategory(ProductCategory category);

}
