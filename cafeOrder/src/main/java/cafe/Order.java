package cafe;

import javax.persistence.*;

//import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@DynamicInsert
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long productId;
    private String productName;
    private Integer qty;
    private String orderStatus;

    // 주문전 상품의 상태 확인
    @PrePersist
    public void onPrePersist() throws Exception {
    	
        String productStatus = CafeOrderApplication.applicationContext.getBean(cafe.external.ProductService.class)
                .checkProductStatus(this.getProductId());
        
        if( productStatus.equals("판매중") ) {
        	this.setOrderStatus("ordered");
        } else {
        	throw new Exception("판매중인 상품이 아닙니다.");
        }
        
        System.out.println(this.orderStatus);
    }
    
    @PostPersist
    public void onPostPersist(){
    	
    	// 주문시 결제까지 트랜잭션을 통합을 위해 결제 서비스 직접 호출
    	{
    		cafe.external.Pay pay = new cafe.external.Pay();
    		pay.setOrderId(getId());
    		pay.setProductId(getProductId());
    		pay.setProductName(getProductName());
    		pay.setQty(getQty());
    		
            // mappings goes here
            try {
            	CafeOrderApplication.applicationContext.getBean(cafe.external.PayService.class)
                        .pay(pay);
            }catch(Exception e) {
                throw new RuntimeException("결제서비스 호출 실패입니다.");
            }
            
            
    	}
    	
    	
    	OrderPlaced orderPlaced = new OrderPlaced();
        BeanUtils.copyProperties(this, orderPlaced);
        orderPlaced.setProductName(this.getProductName());
        
        System.out.println("##### orderPlaced Check [ProductName] : " + getProductName()+ "\n\n");
        System.out.println();
        
        orderPlaced.publishAfterCommit();
        


    }
    
    @PostUpdate
    public void onPostUpdate(){
        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        cafe.external.Pay pay = new cafe.external.Pay();
       
        // mappings goes here
        //Application.applicationContext.getBean(cafe.external.PayService.class).orderRefund(pay);
        
//        DeliveryStarted deliveryStarted = new DeliveryStarted();
//        BeanUtils.copyProperties(this, deliveryStarted);
//        deliveryStarted.publishAfterCommit();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }




}