package com.example.yonko.contacts.contacts.dialogs;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yonko.contacts.R;
import com.example.yonko.contacts.model.Contact;
import com.example.yonko.contacts.util.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactDialogFragment extends DialogFragment {
    private static final String ARG_CONTACT = "arg-contact";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    private Contact mContact;

    @BindView(R.id.iv_dialog_photo)
    ImageView mImageViewPhoto;
    @BindView(R.id.tv_dialog_name)
    TextView mTextViewName;
    @BindView(R.id.tv_dialog_phone)
    TextView mTextViewPhone;

    public ContactDialogFragment() {
    }

    public static ContactDialogFragment newInstance(Contact contact) {
        ContactDialogFragment fragment = new ContactDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContact = getArguments().getParcelable(ARG_CONTACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_dialog, container, false);
        ButterKnife.bind(this, view);

        GlideApp
                .with(getContext())
                .load(mContact.getPicUrl())
                .centerCrop()
                .placeholder(R.drawable.default_photo_placeholder)
                .error(R.drawable.default_photo_placeholder)
                .into(mImageViewPhoto);

        mTextViewName.setText(mContact.getName());

        SpannableString content = new SpannableString(mContact.getPhoneNumber());
        content.setSpan(new UnderlineSpan(), 0, mContact.getPhoneNumber().length(), 0);
        mTextViewPhone.setText(content);

        mTextViewPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCallPermission();
            }
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callNumber();
                } else {
                    checkCallPermission();
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void checkCallPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            callNumber();
        }
    }

    private void callNumber() {
        Intent sIntent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:" + mTextViewPhone.getText()));
        sIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(sIntent);
    }
}
