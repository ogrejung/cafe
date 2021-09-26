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
        ProductDeleted productDeleted = new ProductDeleted();
        BeanUtils.copyProperties(this, productDeleted);
        productDeleted.publishAfterCommit();

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




}