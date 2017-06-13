package com.example.yonko.contacts.data;

import com.example.yonko.contacts.model.Contact;

import java.util.List;

import io.reactivex.Single;

public interface ContactsSource {
    public Single<List<Contact>> getContacts();
}
