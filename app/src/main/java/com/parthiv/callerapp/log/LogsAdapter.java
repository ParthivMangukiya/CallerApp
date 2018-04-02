package com.parthiv.callerapp.log;

import android.content.Context;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parthiv.callerapp.R;
import com.parthiv.callerapp.model.CallLogs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.LogsViewHolder> {

    private List<CallLogs> logsList;
    private Context mContext;

    public LogsAdapter(Context context,List<CallLogs> logsList) {
        this.mContext = context;
        this.logsList = logsList;
    }

    @NonNull
    @Override
    public LogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_calllog, parent, false);
        return new LogsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LogsViewHolder holder, int position) {
        CallLogs callLogs = logsList.get(position);
        if(callLogs.getName() != null && callLogs.getName().length() > 0)
            holder.nameTextView.setText(callLogs.getName());
        else
            holder.nameTextView.setText(callLogs.getNumber());
        int resId = R.drawable.ic_import_export_black_24dp;
        switch (callLogs.getCallType()){
            case CallLog.Calls.INCOMING_TYPE :
                resId = R.drawable.ic_phone_incoming;
                break;
            case CallLog.Calls.OUTGOING_TYPE :
                resId = R.drawable.ic_phone_outgoing;
                break;
            case CallLog.Calls.MISSED_TYPE :
                resId = R.drawable.ic_phone_missed_call;
                break;

        }
        holder.callTypeImageView.setImageDrawable(mContext.getResources().getDrawable(resId));
        holder.dateTextView.setText(normalizeTime(callLogs.getCallDate()));
        holder.durationTextView.setText(getDuration(callLogs.getCallDuration()));
    }

    private String normalizeTime(String time){
        Date dt = new Date(Long.parseLong(time));
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("d MMM h:mm", Locale.getDefault());
        return format.format(dt);
    }

    private String getDuration(String duration){
        int time = Integer.parseInt(duration.trim());
        int hour = time/3600;
        int min = (time-hour*3600)/60;
        int sec = time - hour*3600 - min*60;
        if(hour == 0)
            return String.format("%02d:%02d",min,sec);
        else
            return String.format("%02d%02d:%02d",hour,min,sec);
    }

    @Override
    public int getItemCount() {
        return logsList.size();
    }

    public class LogsViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView durationTextView;
        TextView dateTextView;
        ImageView callTypeImageView;
        public LogsViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            dateTextView = itemView.findViewById(R.id.date);
            durationTextView = itemView.findViewById(R.id.duration);
            callTypeImageView = itemView.findViewById(R.id.call_type_imageview);
        }
    }

    public void updateList(List<CallLogs> callLogsList){
        logsList.clear();
        logsList.addAll(callLogsList);
        notifyDataSetChanged();
    }

}
