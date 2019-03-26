package com.example.vishal.saltnpepper;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Items extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter2;
    private List<itemfood> listitem;
    databasehandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        Intent intent = getIntent();

        Bundle b = getIntent().getExtras();

        String str2 = b.getString("restaurant");
        String str1=b.getString("numb");

        recyclerView = (RecyclerView) findViewById(R.id.recycler2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mydb = new databasehandler(getApplicationContext());

        listitem = new ArrayList<>();



        Cursor res = mydb.getitemdata(str2);

        if (res.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Currently not available at given location try some other location :)", Toast.LENGTH_SHORT).show();
        } else {

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {

                int n=mydb.getiid(Integer.parseInt(res.getString(0)),Integer.parseInt(res.getString(1)));
                itemfood irr = new itemfood(res.getString(2), res.getString(3), res.getString(4), Integer.toString(n));
                listitem.add(irr);

            }

            adapter2 = new adapter2(listitem, getApplicationContext(),str1);
            recyclerView.setAdapter(adapter2);
        }
    }
}