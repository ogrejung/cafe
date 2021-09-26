package cafe;

public class ProductRegistered extends AbstractEvent {

    private Long id;
    private String productName;
    private Long stock;
    private Long dealQty;
    private String productStatus;

    public ProductRegistered(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public Long getDealQty() {
        return dealQty;
    }

    public void setDealQty(Long dealQty) {
        this.dealQty = dealQty;
    }
    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}