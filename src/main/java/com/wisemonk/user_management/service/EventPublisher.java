package com.wisemonk.user_management.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.wisemonk.user_management.dto.LoginEvent;
import com.wisemonk.user_management.dto.RegistrationEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishRegistrationEvent(RegistrationEvent event) {
        kafkaTemplate.send("user-registration-topic", event);
    }
    
    public void publishLoginEvent(LoginEvent event) {
        kafkaTemplate.send("user-login-topic", event);
    }
}
