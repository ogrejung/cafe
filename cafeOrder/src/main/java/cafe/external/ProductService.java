package cafe.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(name = "product", url = "${feign.client.url.productUrl}")
// @FeignClient(name="cafeProduct", url="http://cafeProduct:8080")
public interface ProductService {
	
    @RequestMapping(method= RequestMethod.GET, path="/products/checkProductStatus")
    public String checkProductStatus(@RequestParam("productId") Long productId);

    @RequestMapping(method= RequestMethod.PATCH, path="/products")
    public void product(@RequestBody Product product);
    
}
