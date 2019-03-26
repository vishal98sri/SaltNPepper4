package com.example.vishal.saltnpepper;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private List<itemrest> listitem;
    private Context context;
    private String user;

    public adapter(List<itemrest> listitem, Context context,String user) {
        this.listitem = listitem;
        this.context = context;
        this.user=user;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(adapter.ViewHolder holder, int position) {

        final itemrest ir=listitem.get(position);

        holder.tvhead.setText(ir.getHead());
        holder.tvdecs.setText(ir.getDesc());
        holder.tvoff.setText(ir.getOff());
        holder.tvrat.setText(ir.getRat());


        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i1=new Intent(context,Items.class);
                Bundle bundle=new Bundle();
                bundle.putString("restaurant",ir.getHead());
                bundle.putString("numb",user);
                i1.putExtras(bundle);
                context.startActivity(i1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listitem.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvhead;
        public TextView tvdecs;
        public TextView tvoff;
        public TextView tvrat;
        public LinearLayout linear;

        public ViewHolder(View itemView) {
            super(itemView);

            tvhead=(TextView) itemView.findViewById(R.id.textviewitem);
            tvdecs=(TextView) itemView.findViewById(R.id.desc);
            tvoff=(TextView) itemView.findViewById(R.id.off);
            tvrat=(TextView) itemView.findViewById(R.id.rati);
            linear=(LinearLayout)itemView.findViewById(R.id.linear);


        }
    }
}
