package com.example.yonko.contacts.contacts;

import com.example.yonko.contacts.data.ContactsSource;
import com.example.yonko.contacts.model.Contact;
import com.example.yonko.contacts.util.schedulers.BaseSchedulerProvider;
import com.example.yonko.contacts.util.schedulers.SchedulerProvider;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class ContactsPresenter implements ContactsContract.Presenter {

    private WeakReference<ContactsContract.View> view;
    private ContactsSource contactsSource;
    private BaseSchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean mFirstLoad = true;

    public ContactsPresenter(ContactsContract.View view, ContactsSource contactsSource, BaseSchedulerProvider schedulerProvider) {
        this.view = new WeakReference<>(view);
        this.view.get().setPresenter(this);
        this.contactsSource = contactsSource;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe() {
        if(mFirstLoad) {
            loadContacts();
            mFirstLoad = false;
        }
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    private void loadContacts() {
        view.get().setLoadingBar(true);
        Disposable disposable = contactsSource
                .getContacts()
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<List<Contact>>() {
                    @Override
                    public void onSuccess(@NonNull List<Contact> contactList) {
                        view.get().setLoadingBar(false);
                        view.get().addItems(contactList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
        compositeDisposable.add(disposable);
    }
}
