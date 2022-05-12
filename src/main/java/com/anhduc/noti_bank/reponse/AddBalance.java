package com.anhduc.noti_bank.reponse;

public class AddBalance extends ResponseJson{
    private String timeAdd;

    public String getTimeAdd() {
        return timeAdd;
    }

    public void setTimeAdd(String timeAdd) {
        this.timeAdd = timeAdd;
    }

    public AddBalance(int code, String message, String timeAdd) {
        super(code, message);
        this.timeAdd = timeAdd;
    }

    public AddBalance(int code, String message) {
        super(code, message);
    }
}
