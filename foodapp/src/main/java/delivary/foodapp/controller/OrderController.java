package delivary.foodapp.controller;

import com.razorpay.RazorpayException;
import delivary.foodapp.io.OrderRequest;
import delivary.foodapp.io.OrderResponse;
import delivary.foodapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrderWithPayment(@RequestBody OrderRequest orderRequest) throws RazorpayException {
       OrderResponse response= orderService.createOrderWithPayment(orderRequest);
       return response;
    }

    @GetMapping("/verify")
    public void verifyOrder(@RequestBody Map<String, String> paymentData){
        orderService.verifyPayment(paymentData,"Paid");
    }
    @GetMapping
    public List<OrderResponse> getOrders(){
      return  orderService.getUserOrders();
    }
    @DeleteMapping("/{orderID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String orderId){
        orderService.removeOrder(orderId);
    }
    @GetMapping("/all")
    public List<OrderResponse> getOrdersOfAllUsers(){
        return orderService.getOrdersOfAllUsers();
    }
    @PatchMapping("/status/{orderId}")
    public void updateOrderStatus(@PathVariable String orderId, @RequestParam String status){
      orderService.updateOrderStatus(orderId, status);
    }
}
