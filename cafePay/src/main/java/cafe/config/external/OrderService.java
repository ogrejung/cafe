package cafe.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(name = "order", url = "${feign.client.url.orderUrl}")
public interface OrderService {
	
    @RequestMapping(method= RequestMethod.GET, path="/orders")
    public Void order(@RequestBody Order order);

}

