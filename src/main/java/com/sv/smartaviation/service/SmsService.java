package com.sv.smartaviation.service;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsService {
    @Value("${api.twilio.account.sid}")
    private String accountSid;

    @Value("${api.twilio.auth.token}")
    private String authToken;

    @Value("${api.twilio.from.number}")
    private String fromNumber;

    public void sendSms(String to, String message) {
        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(fromNumber),
                message
        ).create();
    }
}
