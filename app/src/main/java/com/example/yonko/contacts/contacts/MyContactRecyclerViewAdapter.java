package com.example.yonko.contacts.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yonko.contacts.R;
import com.example.yonko.contacts.contacts.ContactsFragment.OnListFragmentInteractionListener;
import com.example.yonko.contacts.model.Contact;
import com.example.yonko.contacts.util.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mData;
    private final OnListFragmentInteractionListener mListener;
    private final Context mContext;

    public MyContactRecyclerViewAdapter(Context context, List<Contact> items, OnListFragmentInteractionListener listener) {

        mContext = context;

        if (items == null) {
            mData = new ArrayList<>();
        } else {
            mData = items;
        }

        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mData.get(position);
        holder.mTextViewName.setText(mData.get(position).getName());
        holder.mTextViewPhone.setText(mData.get(position).getPhoneNumber());

        GlideApp
                .with(mContext)
                .load(mData.get(position).getPicUrl())
                .circleCrop()
                .placeholder(R.drawable.default_photo_placeholder)
                .error(R.drawable.default_photo_placeholder)
                .into(holder.mImageViewPhoto);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addAll(List<Contact> items) {
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.iv_photo)
        public ImageView mImageViewPhoto;
        @BindView(R.id.tv_name)
        public TextView mTextViewName;
        @BindView(R.id.tv_phone)
        public TextView mTextViewPhone;
        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextViewName.getText() + "'";
        }
    }
}
