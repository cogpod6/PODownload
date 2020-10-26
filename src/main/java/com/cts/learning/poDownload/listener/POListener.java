package com.cts.learning.poDownload.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cts.learning.poDownload.model.PO;
import com.cts.learning.poDownload.service.POService;

@Service
public class POListener {
	
	@Autowired
	POService poService;
	
	@KafkaListener(topics = "po_download",groupId = "truck-sch", containerFactory = "kafkaListenerContainerFactory")
    public void listenToKafkaTopic(PO po){
        System.out.println("Message received from Kafka topic is  ::::  " + po);
        poService.createPO(po);
    }
	
}
