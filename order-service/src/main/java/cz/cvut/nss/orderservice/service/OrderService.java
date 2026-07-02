package cz.cvut.nss.orderservice.service;

import cz.cvut.nss.orderservice.client.CatalogServiceClient;
import cz.cvut.nss.orderservice.dto.CreateOrderRequest;
import cz.cvut.nss.orderservice.dto.MenuItemDto;
import cz.cvut.nss.orderservice.dto.OrderItemRequest;
import cz.cvut.nss.orderservice.entity.Order;
import cz.cvut.nss.orderservice.entity.OrderItem;
import cz.cvut.nss.orderservice.entity.OrderStatus;
import cz.cvut.nss.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CatalogServiceClient catalogServiceClient;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public Order createOrder(cz.cvut.nss.orderservice.dto.CreateOrderRequest request) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        // 1. Loop through requested items and verify with CatalogService
        for (OrderItemRequest itemRequest : request.items()) {
            MenuItemDto menuItem = catalogServiceClient.getMenuItem(itemRequest.menuItemId());

            if (!menuItem.available() || !menuItem.restaurantId().equals(request.restaurantId())) {
                throw new IllegalArgumentException("Menu item " + menuItem.name() + " is unavailable or invalid.");
            }

            // 2. Build the OrderItem
            OrderItem orderItem = OrderItem.builder()
                    .menuItemId(menuItem.id())
                    .quantity(itemRequest.quantity())
                    .unitPrice(menuItem.price())
                    .build();

            orderItems.add(orderItem);

            // Calculate price: (unitPrice * quantity) and add to total
            BigDecimal itemTotal = menuItem.price().multiply(BigDecimal.valueOf(itemRequest.quantity()));
            totalPrice = totalPrice.add(itemTotal);
        }

        // 3. Build and save the Order
        Order order = Order.builder()
                .customerId(request.customerId())
                .restaurantId(request.restaurantId())
                .items(orderItems)
                .totalPrice(totalPrice)
                .status(OrderStatus.PLACED)
                .build();

        Order savedOrder = orderRepository.save(order);

        // 4. Asynchronously notify the rest of the system via Kafka
        kafkaTemplate.send("order-events", "New order created: " + savedOrder.getId());

        return savedOrder;
    }

    @Transactional
    public Order updateOrderStatus(String orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(newStatus);
        Order savedOrder = orderRepository.save(order);

        // Відправляємо івент в Kafka про зміну статусу
        kafkaTemplate.send("order-events", "Order " + orderId + " status changed to " + newStatus);

        return savedOrder;
    }
}