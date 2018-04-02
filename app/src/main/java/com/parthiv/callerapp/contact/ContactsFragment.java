package com.parthiv.callerapp.contact;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parthiv.callerapp.R;
import com.parthiv.callerapp.model.Contacts;

import java.util.ArrayList;


public class ContactsFragment extends Fragment {

    RecyclerView contactsRecycler;
    ContactsViewModel mContactsViewModel;
    ContactsAdapter mContactsAdapter;

    public ContactsFragment() {
    }

    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        mContactsAdapter = new ContactsAdapter(new ArrayList<Contacts>());
        contactsRecycler = rootView.findViewById(R.id.contactsRecycler);
        contactsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactsRecycler.setItemAnimator(new DefaultItemAnimator());
        contactsRecycler.setAdapter(mContactsAdapter);
        mContactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        mContactsViewModel.getContacts().observe(this,mContactsAdapter::updateList);
        return rootView;
    }
}
