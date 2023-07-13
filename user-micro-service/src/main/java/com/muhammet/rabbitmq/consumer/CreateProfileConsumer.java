package com.muhammet.rabbitmq.consumer;

import com.muhammet.rabbitmq.model.CreateProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProfileConsumer {

    @RabbitListener(queues = "queue-auth-create-profile")
    public void consumerCreateProfile(CreateProfile createProfile) {
        System.out.println("Create Profile: " + createProfile.toString());
    }
}
