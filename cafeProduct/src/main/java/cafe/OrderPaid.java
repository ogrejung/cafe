package cafe;

public class OrderPaid extends AbstractEvent {

    private Long payId;
    private Long orderId;
    private Long productId;
    private Integer qty;

    public Long getId() {
        return payId;
    }

    public void setId(Long payId) {
        this.payId = payId;
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
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}