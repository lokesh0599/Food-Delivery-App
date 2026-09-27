package delivary.foodapp.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import delivary.foodapp.Entity.OrderEntity;
import delivary.foodapp.io.OrderRequest;
import delivary.foodapp.io.OrderResponse;
import delivary.foodapp.repository.CartRepository;
import delivary.foodapp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl  implements  OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartRepository cartRepository;

    @Value("${razorpay_secret")
    private String RAZORPAY_SECRET;
    @Value("${razorpay_key")
    private String RAZORPAY_KEY;

    @Override
    public OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException {
        OrderEntity newOrder = convertToEntity(request);
       newOrder= orderRepository.save(newOrder);

       //create razorpay payment order
        RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", newOrder.getAmount());
        orderRequest.put("currency","INR");
        orderRequest.put("payment_capture",1);

        Order razorpayOrder = razorpayClient.orders.create(orderRequest);
        newOrder.setRazorpayOrderId(razorpayOrder.get("id"));
        String loggedInUserId = userService.findByUserId();
        newOrder.setUserId(loggedInUserId);
        newOrder = orderRepository.save(newOrder);
        return convertToResponse(newOrder);
    }
    @Override
    public void verifyPayment(Map<String, String> paymentData, String status){
        String razorpay =paymentData.get("razorpay_order_id");
        OrderEntity existingOrder = orderRepository.findByRazorpayOrderId(razorpay).orElseThrow(() -> new RuntimeException("Order Not found"));
        existingOrder.setPaymentStatus(status);
        existingOrder.setRazorpaySignature(paymentData.get("razorpay_signature"));
        existingOrder.setRazorpayPaymentId(paymentData.get("razorpay_payment_id"));
        orderRepository.save(existingOrder);
        if("paid".equalsIgnoreCase(status)){
            cartRepository.deleteByUserId(existingOrder.getUserId());
        }
    }
       @Override
       public List<OrderResponse> getUserOrders(){
           String loggedInUserId = userService.findByUserId();
           List<OrderEntity> list = orderRepository.findByUserId(loggedInUserId);
           return list.stream().map(orderEntity -> convertToResponse(orderEntity)).collect(Collectors.toList());
     }
      @Override
      public void removeOrder(String orderId){
        orderRepository.deleteById(orderId);

    }
      @Override
      public List<OrderResponse> getOrdersOfAllUsers(){
        List<OrderEntity> list = orderRepository.findAll();
       return list.stream().map(orderEntity -> convertToResponse(orderEntity)).collect(Collectors.toList());

      }
      @Override
      public void updateOrderStatus(String orderId, String status){
        OrderEntity entity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
        entity.setOrderStatus(status);
        orderRepository.save(entity);
    }

    private OrderResponse convertToResponse(OrderEntity newOrder) {
        return OrderResponse.builder()
                .id(newOrder.getId())
                .amount(newOrder.getAmount())
                .userAddress(newOrder.getUserAddress())
                .userId(newOrder.getUserId())
                .razorpayOrderId(newOrder.getRazorpayOrderId())
                .paymentStatus(newOrder.getPaymentStatus())
                .orderStatus(newOrder.getOrderStatus())
                .email(newOrder.getEmail())
                .phoneNumber(newOrder.getPhoneNumber())
                .orderItems(newOrder.getOrderItems())
                .build();
    }

    private OrderEntity convertToEntity(OrderRequest request){
        return OrderEntity.builder()
                .userAddress(request.getUserAddress())
                .amount(request.getAmount())
                .orderItems(request.getOrderedItems())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();

    }
}
