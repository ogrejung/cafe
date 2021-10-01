package cafe;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.Optional;
import java.util.List;
import java.util.Date;


@Entity
@Table(name="Pay_table")
public class Pay {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer qty;
    private String payStatus;
    private String orderStatus;

    // 주문 상태 지정
    @PrePersist
    public void onPrePersist() throws Exception {
    	
        System.out.println(this.getClass().getName()+" : onPrePersist   -----------------start------------------");
        System.out.println("주문상태 : "+orderStatus);	
        
    	if("주문취소".equals(orderStatus)) {
            OrderRefund orderRefund = new OrderRefund();
            BeanUtils.copyProperties(this, orderRefund);
            orderRefund.publishAfterCommit();
            
    	} else {
    		this.setPayStatus("orderPaid");
    	}
    	
    }
    
    @PostPersist
    public void onPostPersist(){
        OrderPaid orderPaid = new OrderPaid();
        BeanUtils.copyProperties(this, orderPaid);
        orderPaid.setPayStatus(payStatus);
        orderPaid.publishAfterCommit();
        
    }
    
//    @PostUpdate
//    public void onPostUpdate(){
//    	// 환불인 경우 실행 
//
//
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}