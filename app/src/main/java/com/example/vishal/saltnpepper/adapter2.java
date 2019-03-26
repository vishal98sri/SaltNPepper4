package com.example.vishal.saltnpepper;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class adapter2 extends RecyclerView.Adapter<adapter2.ViewHolder> {

    private List<itemfood> listitem;
    private Context context;
    private String user;
    databasehandler mydb;

    public adapter2(List<itemfood> listitem, Context context,String user) {
        this.listitem = listitem;
        this.context = context;
        this.user=user;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_food,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final adapter2.ViewHolder holder, int position) {

        final itemfood ir=listitem.get(position);

        mydb = new databasehandler(context);

        holder.tvhead.setText(ir.getname());
        holder.tvdecs.setText(ir.getcuisine());
        holder.tvoff.setText(ir.getcost());
        holder.tvrat.setText(ir.getnum());




     /*   holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i1=new Intent(context,Items.class);
                Bundle bundle=new Bundle();
                bundle.putString("restaurant",ir.getHead());
                i1.putExtras(bundle);
                context.startActivity(i1);

            }
        });*/

     holder.pluss.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             int n=Integer.parseInt(holder.tvrat.getText().toString());
             n=n+1;

             mydb.getids(holder.tvhead.getText().toString(),n,user);
             String st=Integer.toString(n);
             holder.tvrat.setText(st);
         }
     });

     holder.minus.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             int n=Integer.parseInt(holder.tvrat.getText().toString());
             if(n==0)
                 n=0;
             else
                 n=n-1;

             mydb.getids(holder.tvhead.getText().toString(),n,user);
             String st=Integer.toString(n);
             holder.tvrat.setText(st);

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
        public RelativeLayout linear;
        public Button pluss,minus;

        public ViewHolder(View itemView) {
            super(itemView);

            tvhead=(TextView) itemView.findViewById(R.id.textviewitem);
            tvdecs=(TextView) itemView.findViewById(R.id.cuisine);
            tvoff=(TextView) itemView.findViewById(R.id.cost);
            tvrat=(TextView) itemView.findViewById(R.id.textView24);
            linear=(RelativeLayout)itemView.findViewById(R.id.linear2);
            pluss=(Button)itemView.findViewById(R.id.add);
            minus=(Button)itemView.findViewById(R.id.minus);


        }
    }
}
