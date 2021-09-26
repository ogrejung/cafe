package cafe;

import cafe.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired ProductRepository productRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderPaid_RegisterGroupOrder(@Payload OrderPaid orderPaid){

        if(!orderPaid.validate()) return;

        System.out.println("\n\n##### listener RegisterGroupOrder : " + orderPaid.toJson() + "\n\n");



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