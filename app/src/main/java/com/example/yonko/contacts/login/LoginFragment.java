package com.example.yonko.contacts.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.yonko.contacts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements LoginContract.View {

    private OnFragmentInteractionListener mListener;
    private LoginContract.Presenter mPresenter;

    @BindView(R.id.et_username)
    EditText mEditTextUsername;
    @BindView(R.id.et_password)
    EditText mEditTextPassword;

    @BindView(R.id.input_layout_username)
    TextInputLayout mInputLayoutUsername;
    @BindView(R.id.input_layout_password)
    TextInputLayout mInputLayoutPassword;

    @BindView(R.id.btn_login)
    Button mButtonLogin;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        mEditTextUsername.addTextChangedListener(new MyTextWatcher(mEditTextUsername));
        mEditTextPassword.addTextChangedListener(new MyTextWatcher(mEditTextPassword));

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onLoginPressed();
            }
        });

        return view;
    }

    @Override
    public void onSuccessfulAuth() {
        if (mListener != null) {
            mListener.onFragmentInteraction(null);
        }
    }

    @Override
    public void setUsernameError(boolean showError) {
        if (showError) {
            mInputLayoutUsername.setError(getString(R.string.username_error));
        } else {
            mInputLayoutUsername.setErrorEnabled(false);
        }
    }

    @Override
    public void setPasswordError(boolean showError) {
        if (showError) {
            mInputLayoutPassword.setError(getString(R.string.password_error));
        } else {
            mInputLayoutPassword.setErrorEnabled(false);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public String getUsername() {
        return mEditTextUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEditTextPassword.getText().toString();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_username:
                    setUsernameError(false);
                    break;
                case R.id.et_password:
                    setPasswordError(false);
                    break;
            }
        }
    }
}
