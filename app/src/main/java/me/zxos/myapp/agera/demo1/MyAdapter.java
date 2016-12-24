package me.zxos.myapp.agera.demo1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.zxos.myapp.R;

/**
 * Created by zxos on 16/12/24.
 */

public class MyAdapter extends BaseAdapter {

    List<String> data = new ArrayList<>();
    Context mContext;

    public MyAdapter(Context context) {
        mContext = context;
    }


    public void setData(List<String> data){

        this.data = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null){

            holder = new ViewHolder(mContext);
            view   = holder.itemView;
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.setData(data.get(i));

        return view;
    }


    class ViewHolder {

        TextView textView;
        View itemView;

        public ViewHolder(Context context) {

            itemView = View.inflate(context, R.layout.item_agera, null);
            textView = (TextView) itemView.findViewById(R.id.textView);

            itemView.setTag(ViewHolder.this);
        }

        public void setData(String str){
            textView.setText(str);
        }
    }
}
