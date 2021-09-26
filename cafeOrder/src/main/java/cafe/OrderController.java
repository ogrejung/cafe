package cafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class OrderController {

//     @Autowired
//     OrderRepository orderRepository;
//
//     @RequestMapping(value = "/order/checkOrderStatus", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//     public String checkOrderStatus(@RequestParam("orderId") Long orderId) throws Exception {
//             System.out.println("##### /order/checkorderStatus  called #####");
//
//             Optional<Order> orderOptional = orderRepository.findById(orderId);
//             Order order = orderOptional.get();
//
////             if (product.getPrice() > 0) {
////                     price = product.getPrice();
////             }
//
//             //임의의 부하를 위한 강제 설정
////             try {
////                     Thread.currentThread().sleep((long) (400 + Math.random() * 220));
////             } catch (InterruptedException e) {
////                     e.printStackTrace();
////             }
//
//             return order.getOrderStatus();
//     }

 }