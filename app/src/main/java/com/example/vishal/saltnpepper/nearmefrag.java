package com.example.vishal.saltnpepper;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class nearmefrag extends Fragment {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private List<itemrest> listitem;
    databasehandler mydb;

    public nearmefrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String strt=getArguments().getString("location");
        String str2=getArguments().getString("numb");


        mydb=new databasehandler(getActivity());
        View vv= inflater.inflate(R.layout.fragment_nearmefrag, container, false);

        recyclerView=(RecyclerView)vv.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listitem=new ArrayList<>();

        Cursor res=mydb.visc(strt);

        if(res.getCount()==0)
        {
            Toast.makeText(getActivity(),"Currently not available at given location try some other location :)",Toast.LENGTH_SHORT).show();
        }

        else {

            StringBuffer buffer=new StringBuffer();
            while(res.moveToNext())
            {
                buffer.append(res.getString(0)+res.getString(1)+res.getString(2)+res.getString(3)+"\n");
                itemrest irr = new itemrest(res.getString(1) , res.getString(5), "30% offer on orders", res.getString(7)+"stars   Rs"+res.getString(6));
                listitem.add(irr);

            }

            adapter=new adapter(listitem,getActivity(),str2);
            recyclerView.setAdapter(adapter);
        }


        return vv;

    }

}
