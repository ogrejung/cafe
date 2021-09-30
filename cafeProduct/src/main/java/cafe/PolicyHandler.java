package cafe;

import cafe.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired ProductRepository productRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderPaid_RegisterGroupOrder(@Payload OrderPaid orderPaid){

        if(orderPaid.validate()) {
            System.out.println("##### listener OrderPaid : " + orderPaid.toJson()  + "\n\n");
            System.out.println();
            
            Optional<Product> productOptional = productRepository.findById(orderPaid.getProductId());
            Product product = productOptional.get();
            
            // 주문 수량 반영
            product.setSumOrderQty( product.getSumOrderQty() + orderPaid.getQty() ) ;
            product.setOrderId( orderPaid.getOrderId() );
            product.setOrderQty(Long.valueOf( orderPaid.getQty() ) );
            
            // 배송 조건 수량 체크 
            if ( product.getConditionQty().equals(product.getSumOrderQty() ) ) {
            	
                System.out.println("##### listener condition Check [product.getSumOrderQty()] : " + product.getSumOrderQty()+ "\n\n");
                System.out.println();
            	
            	product.setProductStatus("판매완료");
            	
            } else {
                System.out.println("##### AAAAAAAAAAAAAAAA  [product.getSumOrderQty()] : " + product.getSumOrderQty()+ "\n\n");
                System.out.println();
            }
            
            productRepository.save(product);
            
        }

 //       System.out.println("\n\n##### listener RegisterGroupOrder : " + orderPaid.toJson() + "\n\n");



        // Sample Logic //
        // Product product = new Product();
        // productRepository.save(product);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderRefund_CancelGroupOrder(@Payload OrderRefund orderRefund){

        if(!orderRefund.validate()) return;

        System.out.println("\n\n##### listener CancelGroupOrder : " + orderRefund.toJson() + "\n\n");



        // Sample Logic //
        // Product product = new Product();
        // productRepository.save(product);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}