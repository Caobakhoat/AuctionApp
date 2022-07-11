package com.example.demo_web.service;

import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.model.Bids;
import com.example.demo_web.repository.BidsRepository;
import com.example.demo_web.repository.UserRepository;
import com.example.demo_web.request.AddBidsRequest;
import com.example.demo_web.response.AddBidsResponse;
import com.example.demo_web.tokenAuthen.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BidsServiceImpl implements BidsService {
    @Autowired
    BidsRepository bidsRepository;
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    AuctionRepository auctionRepository;
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    MessageConfig messageConfig;
    @Override
    public AddBidsResponse addBids(AddBidsRequest req) {
        AddBidsResponse res= new AddBidsResponse();
        Bids bids = new Bids();
        bids.setBid_price(req.getBid_price());
//        bids.setAuctionBids(auctionRepository.findById(req.getIdAuction()).get());
        String username = jwtTokenUtil.getUsername(req.getToken());
        bids.setUserBids( userRepository.findByUsername(username));
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSAGE_ADDBIDS);
        res.setResult(bidsRepository.save(bids));
        return res;
    }
}
