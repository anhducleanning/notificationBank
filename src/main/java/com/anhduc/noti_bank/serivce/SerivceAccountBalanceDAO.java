package com.anhduc.noti_bank.serivce;

import com.anhduc.noti_bank.model.AccountBalance;

import java.util.List;

public interface SerivceAccountBalanceDAO<T> {
     List<AccountBalance> getNoti();

     AccountBalance create(T t);
}
