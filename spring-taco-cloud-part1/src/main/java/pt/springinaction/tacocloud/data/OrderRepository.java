package pt.springinaction.tacocloud.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pt.springinaction.tacocloud.Order;
import pt.springinaction.tacocloud.User;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    //CrudRepository first parameter entity type the repository is to persist
    //second parameter being the type of entity id property

    List<Order> findByDeliveryZip(String deliveryZip);

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

    //another example of methods to be understood by spring data
    //List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    //or countOrders

    //if query is too specific feel free to do like this
    //@Query("Order o where o.deliveryCity='Seattle'")
    //List<Order> readOrdersDeliveredInSeattle();
}