package delivary.foodapp.service;

import delivary.foodapp.io.CartRequest;
import delivary.foodapp.io.CartResponse;

public interface CartService {
    CartResponse addToCart(CartRequest request);
    CartResponse getCart();

    void clearCart();

    CartResponse removeFromCart(CartRequest cartRequest);
}
