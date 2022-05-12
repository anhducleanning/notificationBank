package com.anhduc.noti_bank.sendmesenger;

import com.anhduc.noti_bank.model.AccountBalance;
import com.anhduc.noti_bank.serivce.ServiceAccountBalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.time.LocalTime;
import java.util.List;


@Component
public class TelegramBot extends TelegramLongPollingBot {

//    private static Logger logger = Logger.getLogger(TelegramBot.class);

    private static Logger logger = LoggerFactory.getLogger(TelegramBot.class);
    @Autowired
    ServiceAccountBalance blance;
    @Value("${telegram.bot.username}")
    private String meocon_bot;
    @Value("${telegram.bot.token}")
    private String token;



    @Override
    public String getBotUsername() {
        return meocon_bot;
    }

    @Override
    public String getBotToken() {
        return token;
    }


    public void sendTelegramBot(List<?> list){
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId("655374855");
        message.setText("Time: "+ list.toString());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
    }
}
