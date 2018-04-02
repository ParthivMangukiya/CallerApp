package com.parthiv.callerapp.log;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.annotation.NonNull;

import com.parthiv.callerapp.model.CallLogs;

import java.util.ArrayList;
import java.util.List;

public class LogsViewModel extends AndroidViewModel {
    private MutableLiveData<List<CallLogs>> mLogsLiveData;

    public LogsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<CallLogs>> getContacts(){
        if(mLogsLiveData == null) {
            @SuppressLint("MissingPermission") Cursor mCursor = getApplication()
                    .getContentResolver()
                    .query(CallLog.Calls.CONTENT_URI, null, null, null, null);
            List<CallLogs> callLogsList = new ArrayList<>();
            while (mCursor.moveToNext()) {
                String number = mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.NUMBER));
                String name = mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                String callDate = mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.DATE));
                String duration = mCursor.getString(mCursor.getColumnIndex(CallLog.Calls.DURATION));
                int type = mCursor.getInt(mCursor.getColumnIndex(CallLog.Calls.TYPE));
                callLogsList.add(new CallLogs(number,type,callDate,duration,name));
            }
            mCursor.close();
            mLogsLiveData = new MutableLiveData<>();
            mLogsLiveData.setValue(callLogsList);
        }
        return mLogsLiveData;
    }

}
