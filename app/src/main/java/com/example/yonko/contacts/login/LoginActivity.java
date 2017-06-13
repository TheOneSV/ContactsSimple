package com.example.yonko.contacts.login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yonko.contacts.R;
import com.example.yonko.contacts.contacts.ContactsActivity;
import com.example.yonko.contacts.util.ActivityUtils;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment homeFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.login_container);

        if (homeFragment == null) {
            homeFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homeFragment, R.id.login_container);
        }

        mPresenter = new LoginPresenter(homeFragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }
}
