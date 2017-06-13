package com.example.yonko.contacts.contacts;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.yonko.contacts.R;
import com.example.yonko.contacts.contacts.dialogs.ContactDialogFragment;
import com.example.yonko.contacts.data.ContactsLocalRepository;
import com.example.yonko.contacts.model.Contact;
import com.example.yonko.contacts.util.ActivityUtils;
import com.example.yonko.contacts.util.schedulers.SchedulerProvider;

public class ContactsActivity extends AppCompatActivity implements ContactsFragment.OnListFragmentInteractionListener {

    private ContactsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ContactsFragment contactsFragment = (ContactsFragment) getSupportFragmentManager().findFragmentById(R.id.contacts_container);

        if (contactsFragment == null) {
            contactsFragment = ContactsFragment.newInstance(1);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), contactsFragment, R.id.contacts_container);
        }

        mPresenter = new ContactsPresenter(
                contactsFragment,
                ContactsLocalRepository.getInstance(),
                SchedulerProvider.getInstance());
    }

    @Override
    public void onListFragmentInteraction(Contact contact) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        ContactDialogFragment newFragment = ContactDialogFragment.newInstance(contact);
        newFragment.show(ft, "dialog");
    }
}
