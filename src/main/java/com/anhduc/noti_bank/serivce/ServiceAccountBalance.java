package com.anhduc.noti_bank.serivce;

import com.anhduc.noti_bank.controller.ControllerAcountBalance;
import com.anhduc.noti_bank.model.AccountBalance;


import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceAccountBalance implements SerivceAccountBalanceDAO<AccountBalance>{

//    private static Logger logger = Logger.getLogger(ServiceAccountBalance.class);
    private static Logger log = Logger.getLogger(ControllerAcountBalance.class);
    private JdbcTemplate jdbcTemplate;

    RowMapper<AccountBalance> rowMapper = (rs, rowNum) -> {
        AccountBalance balance = new AccountBalance();
        balance.setTitle(rs.getString("title"));
        balance.setContten(rs.getString("content"));
        balance.setCurrent_balance(rs.getLong("current_balance"));
        balance.setType(rs.getString("type"));
        balance.setPartner(rs.getString("partner"));
        return balance;
    };



    public ServiceAccountBalance(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AccountBalance> getNoti() {
        String sql = "SELECT  title, content, current_balance,type, partner FROM account_balance ";
        log.info("Serivce Get Sucsess");
        return jdbcTemplate.query(sql,rowMapper);

    }

    @Override
    public AccountBalance create(AccountBalance accountBalance) {
        String sql = "insert into account_balance(title, content,current_balance, type,partner) values (?,?,?,?,?)";

        int insert =  jdbcTemplate.update(sql,
                accountBalance.getTitle(),
                accountBalance.getContten(),
                accountBalance.getCurrent_balance(),
                accountBalance.getType(),
                accountBalance.getPartner());
        if(insert == 1){
            log.info("Create Susess" );
        }
        return accountBalance;
    }

}
