package com.example.communityproducer.controller;
import com.example.communityproducer.entity.CommunityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CommunityController {
    @Autowired
    private KafkaTemplate<Integer, CommunityEntity> kafkaTemplate;
    @PostMapping("/send")
    //http://localhost:2003/send
    public ResponseEntity<String> send(@RequestBody CommunityEntity community) {
        kafkaTemplate.send("community-topic", community);  // Make sure topic name matches the consumer
        return ResponseEntity.ok("Community sent");
    }
}


