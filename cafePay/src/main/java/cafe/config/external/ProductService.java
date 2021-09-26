package cafe.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name = "Product", url = "${feign.client.url.productUrl}")
public interface ProductService {
    @RequestMapping(method= RequestMethod.GET, path="/products")
    public void orderRefund(@RequestBody Product product);

}