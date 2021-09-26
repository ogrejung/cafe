package cafe.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="cafeOrder", url="http://cafeOrder:8080")
public interface OrderService {
    @RequestMapping(method= RequestMethod.GET, path="/orders")
    public void cancelGroupPurchase(@RequestBody Order order);

}

