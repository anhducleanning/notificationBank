package com.anhduc.noti_bank;

import com.anhduc.noti_bank.sendmesenger.TelegramBot;
import com.anhduc.noti_bank.serivce.ServiceAccountBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotiBankApplication {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAccountBalance.class);
    public static void main(String[] args) {
        SpringApplication.run(NotiBankApplication.class, args);


    }

}
