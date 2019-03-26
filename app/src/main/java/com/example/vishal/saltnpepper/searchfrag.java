package com.example.vishal.saltnpepper;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class searchfrag extends Fragment {


    AutoCompleteTextView atv;
    TextView searching;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private List<itemrest> listitem;
    databasehandler mydb;
    String str1;


    public searchfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View vv= inflater.inflate(R.layout.fragment_searchfrag, container, false);
        mydb=new databasehandler(getActivity());
        String str2=getArguments().getString("numb");
        str1=str2;
        atv=(AutoCompleteTextView)vv.findViewById(R.id.place);
        searching=(TextView) vv.findViewById(R.id.searching);
        recyclerView=(RecyclerView)vv.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                atv.addTextChangedListener(new TextWatcher() {

                    // Before EditText text change.
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    // This method is invoked after user input text in EditText.
                    @Override
                    public void afterTextChanged(Editable editable) {
                        processButtonByTextLength();
                    }
                });

                return vv;
    }

    private void processButtonByTextLength() {

        searching.setText("Related to \" "+atv.getText().toString()+"\"");

        listitem=new ArrayList<>();

        Cursor res=mydb.restname(atv.getText().toString());

        if(res.getCount()==0)
        {
            Toast.makeText(getActivity(),"No data",Toast.LENGTH_SHORT).show();
        }

        else {

            StringBuffer buffer=new StringBuffer();
            while(res.moveToNext())
            {
                buffer.append(res.getString(0)+res.getString(1)+res.getString(2)+res.getString(3)+"\n");
                itemrest irr = new itemrest(res.getString(1) , res.getString(5), "30% offer on orders", res.getString(7)+"stars   Rs"+res.getString(6));
                listitem.add(irr);

            }

            adapter=new adapter(listitem,getActivity(),str1);
            recyclerView.setAdapter(adapter);
        }



    }

}
