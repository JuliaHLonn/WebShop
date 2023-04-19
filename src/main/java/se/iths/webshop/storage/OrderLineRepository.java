package se.iths.webshop.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.webshop.business.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
}
