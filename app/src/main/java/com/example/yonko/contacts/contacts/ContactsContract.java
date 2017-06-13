package com.example.yonko.contacts.contacts;

import com.example.yonko.contacts.BasePresenter;
import com.example.yonko.contacts.BaseView;
import com.example.yonko.contacts.login.LoginContract;
import com.example.yonko.contacts.model.Contact;

import java.util.List;

public interface ContactsContract {

    interface View extends BaseView<ContactsContract.Presenter> {
        void setLoadingBar(boolean isLoading);
        void addItems(List<Contact> contactList);
        void makeToast(String msg);
    }

    interface Presenter extends BasePresenter {
    }
}
