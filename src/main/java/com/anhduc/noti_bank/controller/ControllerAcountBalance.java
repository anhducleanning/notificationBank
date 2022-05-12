package com.anhduc.noti_bank.controller;

import com.anhduc.noti_bank.model.AccountBalance;
import com.anhduc.noti_bank.reponse.AddBalance;
import com.anhduc.noti_bank.reponse.ResponseJson;
import com.anhduc.noti_bank.sendmesenger.GmailBot;
import com.anhduc.noti_bank.sendmesenger.TelegramBot;
import com.anhduc.noti_bank.serivce.ServiceAccountBalance;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class ControllerAcountBalance {

//    private static Logger log = Logger.getLogger(ControllerAcountBalance.class);
    private static Logger log = Logger.getLogger(ControllerAcountBalance.class);



    @Value("${notification.channnel}")
    private String chanel ;
    @Autowired
    ServiceAccountBalance blance;

    @Autowired
    TelegramBot telegramBot;

    @Autowired
    GmailBot gmailBot;




    @GetMapping("/test")
    public String test(){
        log.info("test Log");
        sendMail();
        return "testLog" ;
    }

    public void sendMail()  {
        System.out.println("Đang  gửi mmail");
          try {
              gmailBot.sendMailTest("haanhduc1603@gmail.com");
              System.out.println("Send Mail Susses");
              log.info("Send Mail Susses");
          }catch (Exception e){
              e.printStackTrace();
          }
    }
    @GetMapping("/tele")
    public void sendTelegram(){
        List<AccountBalance> balances = blance.getNoti();
        telegramBot.sendTelegramBot(Collections.singletonList(balances.toString()));

    }

    @GetMapping("/notifi_balance")
    public ResponseEntity<?> getAllCourse(){
        ResponseJson json = new ResponseJson(0,"Thanh cong");
        System.out.println(chanel);
        List<AccountBalance> balances = blance.getNoti();
        //SEND MAIL
        if( chanel.equals("GMAIL")){
            log.info("Send Mail With Gmail");
            sendMail();
        }else if(chanel.equals("TELEGRAM")){
            log.info("Send Mail With Telegram");
            sendTelegram();
            System.out.println("Send Mail With TELEGRAM");
        } else{
            log.info("Chưa xác nhận được phương thức send Mail");
            System.out.println("Chưa xác nhận được phương thức send Mail");
        }
        //Get Datas
        if(balances != null){
            log.info("Get Data Sucsess");
            AddBalance jsonSucss = new AddBalance(0, "Thành công", LocalDate.now().toString());
            return new ResponseEntity<>(jsonSucss, HttpStatus.OK);

        }else{
            return new ResponseEntity<>(json,HttpStatus.OK);
        }


    }

    @PostMapping("/add")
   public ResponseEntity<?> addCourse(@RequestBody AccountBalance balance) {
        if (balance.getTitle()==""||balance.getCurrent_balance()==0||balance.getContten()==""
                ||balance.getPartner() ==""||balance.getType()=="") {
            System.out.println("Null Objejct");
            AddBalance json = new AddBalance(0, "Không thành công", LocalDate.now().toString());
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);

        } else {
            blance.create(balance);
            ResponseJson json = new ResponseJson(0, "Thanh cong");
            return new ResponseEntity<>(json, HttpStatus.CREATED);
        }

    }
}
