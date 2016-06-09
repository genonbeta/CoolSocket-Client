package com.genonbeta.CoolSocket.test.adapter;

import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import com.genonbeta.CoolSocket.test.*;
import com.genonbeta.CoolSocket.test.helper.*;
import java.util.*;

public class MessageListAdapter extends BaseAdapter
{
    private Context mContext;
    private ArrayList<MessageItem> mList;
	
	private int receivedColor = 0;
	private int sentColor = 0;

    public MessageListAdapter(Context context, ArrayList<MessageItem> arrayList)
	{
        this.mContext = context;
        this.mList = arrayList;
		
		this.receivedColor = context.getResources().getColor(R.color.receivedMessage);
		this.sentColor = context.getResources().getColor(R.color.sentMessage);
    }

    @Override
    public int getCount()
	{
        return this.mList.size();
    }

    @Override
    public Object getItem(int i)
	{
        return this.mList.get(i);
    }

    @Override
    public long getItemId(int i)
	{
        return (long) 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
	{
        return getViewAt(LayoutInflater.from(this.mContext).inflate(R.layout.list_message, viewGroup, false), i);
    }

    public View getViewAt(View view, int i)
	{
        TextView textView1 = (TextView) view.findViewById(R.id.list_text);
        TextView textView2 = (TextView) view.findViewById(R.id.list_text2);
        MessageItem messageItem = (MessageItem) getItem(i);
		
		String str = (messageItem.client == null || messageItem.client.equals("") || messageItem.client.equals("::1")) ? "localhost" : messageItem.client;
        
		if (PairListHelper.getList().containsKey(messageItem.client))
		{
			PairListHelper.DeviceInfo device = PairListHelper.getList().get(messageItem.client);
			
			if (device.deviceName != null)
				str += " @ " + device.deviceName;
		}
		
		textView1.setTextColor((messageItem.isReceived) ? this.receivedColor : this.sentColor);
		
		textView1.setText(((messageItem.isReceived) ? "↓" : "↑") + " " + str);
        textView2.setText(messageItem.message);
	
        return view;
    }
}
