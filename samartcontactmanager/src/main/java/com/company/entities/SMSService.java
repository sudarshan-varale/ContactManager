package com.company.entities;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.net.URI;

@Component
public class SMSService {

    private String ACCOUNT_SID="AC821e9f1cf7c0e8827fc8a85a50c22370";

    private String AUTH_TOKEN="76d52a39bb73f80d1f222f261c1f5dfa";

    private String FROM_NUMBER;
    

    public String getFROM_NUMBER() {
		return FROM_NUMBER;
	}

	public void setFROM_NUMBER(String fROM_NUMBER) {
		FROM_NUMBER = fROM_NUMBER;
	}

	public void send(SMS sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }
}