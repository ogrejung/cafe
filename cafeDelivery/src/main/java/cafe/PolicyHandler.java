package cafe;

import cafe.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

@Service
public class PolicyHandler{
    @Autowired DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryStarted_StartDelivery(@Payload DeliveryStarted deliveryStarted){

        if(deliveryStarted.validate()) {
        	System.out.println("\n\n##### listener StartDelivery : " + deliveryStarted.toJson() + "\n\n");
        	
            Delivery delivery = new Delivery();
//            BeanUtils.copyProperties(delivery, deliveryStarted);
            
            delivery.setOrderId( deliveryStarted.getOrderId() );
            delivery.setProductId( deliveryStarted.getProductId() );
            delivery.setProductName( deliveryStarted.getProductName() );
            delivery.setQty( deliveryStarted.getQty() );
            delivery.setDeliveryStatus("배송완료");
     
            deliveryRepository.save(delivery);
        	
        }

        // Sample Logic //
        // Delivery delivery = new Delivery();
        // deliveryRepository.save(delivery);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}