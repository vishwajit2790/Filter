package org.example.Scheduler;

import org.example.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StatusEmailScheduler {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OrderRepository orderRepository;

    // Fixed rate of 2 hours in milliseconds (7,200,000 ms)
    @Scheduled(fixedRate = 7200000)
    public void sendSystemStatusUpdate() {
        try {
            long totalOrders = orderRepository.count();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("logistics-ops@company.com");
            message.setSubject("Automated Logistics System Status Update");
            message.setText("System operational. Total orders currently tracked in pool: " + totalOrders);

            mailSender.send(message);
        } catch (Exception e) {
            // Gracefully handle mail server offline scenarios during testing
        }
    }
}

