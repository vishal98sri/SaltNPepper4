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

public class adapter3 extends RecyclerView.Adapter<adapter3.ViewHolder> {

    private List<itemcart> listitem;
    private Context context;


    public adapter3(List<itemcart> listitem, Context context) {
        this.listitem = listitem;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(adapter3.ViewHolder holder, int position) {

        final itemcart ir=listitem.get(position);

        holder.tvhead.setText(ir.getname());
        holder.tvdecs.setText(ir.getnum());
        holder.tvoff.setText(ir.getcost());

      /*  int n=Integer.parseInt(holder.amt.getText().toString());

        n+=Integer.parseInt(ir.getcost())*Integer.parseInt(ir.getnum());

        holder.amt.setText(n);*/

    }

    @Override
    public int getItemCount() {
        return listitem.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvhead;
        public TextView tvdecs;
        public TextView tvoff;
        public TextView amt;
        public LinearLayout linear;

        public ViewHolder(View itemView) {
            super(itemView);

            tvhead=(TextView) itemView.findViewById(R.id.itemname);
            tvdecs=(TextView) itemView.findViewById(R.id.number);
            tvoff=(TextView) itemView.findViewById(R.id.cost);
            amt=(TextView)itemView.findViewById(R.id.amount);



        }
    }
}
