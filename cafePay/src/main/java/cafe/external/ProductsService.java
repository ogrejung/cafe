package cafe.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(name = "product", url = "${feign.client.url.productUrl}")
public interface ProductsService {
    @RequestMapping(method= RequestMethod.PATCH, path="/products/updateProductOrderQty")
    public Long updateProductOrderQty(@RequestParam("productId") Long productId, Integer orderQty);
    
}
