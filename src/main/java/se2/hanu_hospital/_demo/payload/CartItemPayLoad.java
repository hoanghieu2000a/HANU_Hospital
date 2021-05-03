package sqa.hanu_minimart.payload;

public class CartItemPayLoad {
    private Long cartId;
    private String productName;
    private Integer quantity;
    private String content;

    public CartItemPayLoad(){

    }

    public CartItemPayLoad(Long cartId, String productName, Integer quantity) {
        this.cartId = cartId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public CartItemPayLoad(Long cartId, String productName, Integer quantity, String content) {
        this.cartId = cartId;
        this.productName = productName;
        this.quantity = quantity;
        this.content = content;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CartItemPayLoad{" +
                "cartId=" + cartId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", content='" + content + '\'' +
                '}';
    }
}
