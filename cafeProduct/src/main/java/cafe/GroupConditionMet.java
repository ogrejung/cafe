package cafe;

public class GroupConditionMet extends AbstractEvent {

    private Long productId;

    public GroupConditionMet(){
        super();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}