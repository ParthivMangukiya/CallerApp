package com.parthiv.callerapp.contact;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.parthiv.callerapp.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsViewModel extends AndroidViewModel {
    private MutableLiveData<List<Contacts>> mContactsLiveData;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Contacts>> getContacts(){
        if(mContactsLiveData == null) {
            Cursor mCursor = getApplication()
                    .getContentResolver()
                    .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            List<Contacts> contacts = new ArrayList<>();
            while (mCursor.moveToNext()) {
                String number = mCursor.getString(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String name = mCursor.getString(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                contacts.add(new Contacts(name, number));
            }
            mCursor.close();
            mContactsLiveData = new MutableLiveData<>();
            mContactsLiveData.setValue(contacts);
        }
        return mContactsLiveData;
    }

}
