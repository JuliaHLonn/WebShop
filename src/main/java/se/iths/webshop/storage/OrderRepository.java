package se.iths.webshop.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.business.CustomerOrder;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder,Long> {
List<CustomerOrder> findAll();
List<CustomerOrder> findByDelivered(boolean delivered);

}
