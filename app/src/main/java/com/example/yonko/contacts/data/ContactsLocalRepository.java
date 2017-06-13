package com.example.yonko.contacts.data;

import com.example.yonko.contacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;

public class ContactsLocalRepository implements ContactsSource {
    private List<Contact> items;

    private static ContactsLocalRepository INSTANCE = null;

    private ContactsLocalRepository() {}

    public static ContactsLocalRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContactsLocalRepository();
            INSTANCE.items = new ArrayList<>();
            INSTANCE.items.add(new Contact(
                    "https://content-static.upwork.com/uploads/2014/10/02123010/profile-photo_friendly.jpg",
                    "Bill",
                    "+359884220030"));
            INSTANCE.items.add(new Contact(
                    "http://cdn.business2community.com/wp-content/uploads/2014/04/profile-picture.jpg",
                    "Richard",
                    "+359884220031"));
            INSTANCE.items.add(new Contact(
                    "http://www.miamidish.net/wp-content/uploads/2017/02/bb1.jpg",
                    "Laura",
                    "+359884220032"));
        }

        return INSTANCE;
    }

    @Override
    public Single<List<Contact>> getContacts() {
        return Single.create(new SingleOnSubscribe<List<Contact>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<Contact>> e) throws Exception {
                e.onSuccess(items);
            }
        });
    }
}
