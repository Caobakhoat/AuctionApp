package com.example.demo_web.controller;
import com.example.demo_web.request.BidMessage;
import com.example.demo_web.service.BidsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import static java.lang.String.format;

@Controller
public class BidsController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @Autowired
    private BidsServiceImpl bidsService;

    @MessageMapping("/bids/{auctionId}/bidAuction")
    public void bids(@DestinationVariable int auctionId, @Payload BidMessage bidMessage) {
        bidsService.addBids(bidMessage,auctionId);
        messagingTemplate.convertAndSend(format("/auction/%s", auctionId), bidMessage);
    }
}
