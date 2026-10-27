package org.example.Service;

import org.example.Model.Order;
import org.example.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class OrderBatchProcessingService {

    @Autowired
    private OrderRepository orderRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void processOrdersInParallel() {
        int pageSize = 1000;
        int pageNumber = 0;
        Page<Order> page;

        do {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("orderId"));
            page = orderRepository.findAll(pageable);

            List<CompletableFuture<Void>> futures = page.getContent().stream()
                    .map(order -> CompletableFuture.runAsync(() -> traceOrder(order), executorService))
                    .collect(Collectors.toList());

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            pageNumber++;

        } while (page.hasNext());
    }

    private void traceOrder(Order order) {
        // High-performance multithreaded order tracing simulation
        System.out.println("Multithreaded tracing - Order ID: " + order.getOrderId() + " | UUID: " + order.getUuid());
    }
}

