package com.example.vishal.saltnpepper;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class cartfrag extends Fragment {

    TextView cart;
    Button b1,b2;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter3;
    private List<itemcart> listitem;
    databasehandler mydb;

    public cartfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv= inflater.inflate(R.layout.fragment_cartfrag, container, false);


        cart=(TextView)vv.findViewById(R.id.amount);
        b1=(Button)vv.findViewById(R.id.button7);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),couponapp.class);
                startActivity(i);
            }
        });
        recyclerView=(RecyclerView)vv.findViewById(R.id.recycler3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listitem=new ArrayList<>();
        mydb=new databasehandler(getActivity());

        Cursor res=mydb.getcart();

        if(res.getCount()==0)
        {
            Toast.makeText(getActivity(),"Cart empty..... enjoy ordering !!",Toast.LENGTH_SHORT).show();
        }

        else {

            int costt=0;

            StringBuffer buffer=new StringBuffer();
            while(res.moveToNext()) {
                buffer.append(res.getString(0) + res.getString(1) + res.getString(2) + res.getString(3) + "\n");
                Cursor resi=mydb.getnamecost(Integer.parseInt(res.getString(2)));

                String name="",cost="";
                while(resi.moveToNext())
                {
                    name=resi.getString(2);
                    cost=resi.getString(4);

                }
                costt+=Integer.parseInt(cost.substring(4))*Integer.parseInt(res.getString(3));
                itemcart irr = new itemcart(name,cost,res.getString(3));
                listitem.add(irr);

            }

            cart.setText("Rs. "+costt);
            adapter3=new adapter3(listitem,getActivity());
            recyclerView.setAdapter(adapter3);
            }

            return vv;

}


}
