package cafe;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Product_table")
public class Product {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String productName;
    private Long stock;
    private Long conditionQty;
    private Long orderQty;
    private String productStatus;
    private Long sumOrderQty;
    private Long orderId;
    

    @PostPersist
    public void onPostPersist(){
        ProductRegistered productRegistered = new ProductRegistered();
        BeanUtils.copyProperties(this, productRegistered);
        productRegistered.publishAfterCommit();

    }

    @PrePersist
    public void onPrePersist() {
        CheckedProductStatus checkedProductStatus = new CheckedProductStatus();
        BeanUtils.copyProperties(this, checkedProductStatus);
        checkedProductStatus.publishAfterCommit();

    }    
    
    @PostUpdate
    public void onPostUpdate(){
                
    	if (this.getProductStatus().equals("판매중")) {
    		
	    	// 배송쪽 Kafka 메시지 전달 
	        DeliveryStarted deliveryStarted = new DeliveryStarted();
	        
	        System.out.println("##### Product 입력받은 값  [this.getId()]: "+ this.getId()    );     
	        System.out.println("##### Product 입력받은 값  [this.getOrderId()]: "+ this.getOrderId()    );     
	        System.out.println("##### Product 입력받은 값  [this.getOrderQty()]: "+ this.getOrderQty()    );   	        
	        
	        BeanUtils.copyProperties(this, deliveryStarted);
	        deliveryStarted.setProductId(this.getId());
	        deliveryStarted.setOrderStatus("orderPaid");
	        deliveryStarted.setQty(Integer.parseInt(String.valueOf(this.getOrderQty())));
	        deliveryStarted.publishAfterCommit();

	        System.out.println("##### Kafka 입력 완료 ~~~ [deliveryStarted]");        
	    }
        
    	
//    	ProductDeleted productDeleted = new ProductDeleted();
//        BeanUtils.copyProperties(this, productDeleted);
//        productDeleted.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

//        cafe.external.Order order = new cafe.external.Order();
        // mappings goes here
//        CafeProductApplication.applicationContext.getBean(cafe.external.OrderService.class)
//            .cancelGroupPurchase(order);

//        GroupConditionMet groupConditionMet = new GroupConditionMet();
//        BeanUtils.copyProperties(this, groupConditionMet);
//        groupConditionMet.publishAfterCommit();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
    public Long getConditionQty() {
        return conditionQty;
    }

    public void setConditionQty(Long conditionQty) {
        this.conditionQty = conditionQty;
    }
    public Long getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Long orderQty) {
        this.orderQty = orderQty;
    }
    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
    public Long getSumOrderQty() {
        return sumOrderQty;
    }

    public void setSumOrderQty(Long sumOrderQty) {
        this.sumOrderQty = sumOrderQty;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


}