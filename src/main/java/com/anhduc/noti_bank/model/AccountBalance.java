package com.anhduc.noti_bank.model;

public class AccountBalance {
        private String title;
        private String contten;
        private Long current_balance;
        private String type;
        private String partner;

        public AccountBalance() {
        }

        public AccountBalance(String title, String contten, Long current_balance, String type, String partner) {
                this.title = title;
                this.contten = contten;
                this.current_balance = current_balance;
                this.type = type;
                this.partner = partner;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getContten() {
                return contten;
        }

        public void setContten(String contten) {
                this.contten = contten;
        }

        public Long getCurrent_balance() {
                return current_balance;
        }

        public void setCurrent_balance(Long current_balance) {
                this.current_balance = current_balance;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getPartner() {
                return partner;
        }

        public void setPartner(String partner) {
                this.partner = partner;
        }

        @Override
        public String toString() {
                return "AccountBalance{" +
                        "title='" + title + '\'' +
                        ", contten='" + contten + '\'' +
                        ", current_balance=" + current_balance +
                        ", type='" + type + '\'' +
                        ", partner='" + partner + '\'' +
                        '}';
        }
}
