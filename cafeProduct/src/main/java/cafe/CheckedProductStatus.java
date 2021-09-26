package cafe;

public class CheckedProductStatus extends AbstractEvent {

    private Long id;
    private String productName;
    private Long stock;
    private Long conditionQty;
    private Long orderQty;
    private String productStatus;
    private Long sumOrderQty;
    
    public CheckedProductStatus(){
        super();
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
