package com.example.vishal.saltnpepper;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Fragment;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class MainActivity extends AppCompatActivity {

    databasehandler mydb;

    ViewFlipper v_flipper;
    int[] image={
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.fda
    };

    Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new databasehandler(this);
        mydb.drop();

        boolean inserted=mydb.insert("Krishnas Dosa Kadai","23,Main road,Mogappair West","chennai","600037","pure veg","300 for two","4.4");
        mydb.insert("The Madras Diner","23,Main road,Mogappair West","chennai","600037","Veg and Non veg","500 for two","4.4");
        mydb.insert("KFC","23,Main road,Mogappair West","chennai","600037","non veg","600 for two","4.4");
        mydb.insert("Saravana Bhavan","23,Main road,Mylapore","chennai","600037","pure veg","300 for two","4.4");
        mydb.insert("Karpagambal Mess","23,Main road,Mylapore","chennai","600037","pure veg","300 for two","4.4");
        mydb.insert("Kalathi","23,Main road,Mylapore","chennai","600037","non veg","300 for two","4.4");
        mydb.insert("Jalpaan","23,Main road,adayar","chennai","600037","pure veg","300 for two","4.4");
        mydb.insert("Rain Tree","23,Main road,adayar","chennai","600037","non veg","300 for two","4.4");
        mydb.insert("Adayar Anandha Bhavan","23,Main road,adayar","chennai","600037","pure veg","300 for two","4.4");


        mydb.insertitems(1,"Plain Roast","South Indian","Rs. 60");
        mydb.insertitems(1,"Ghee Roast","South Indian","Rs. 70");
        mydb.insertitems(1,"Naan","North Indian","Rs. 40");
        mydb.insertitems(1,"Tomato Roast","South Indian","Rs. 65");
        mydb.insertitems(1,"Poori","South Indian","Rs. 65");
        mydb.insertitems(1,"Panner Butter Masala","North Indian","Rs. 90");
        mydb.insertitems(1,"Kadai Panner","North Indian","Rs. 100");

        mydb.insertitems(2,"Kadai Panner","North Indian","Rs. 120");
        mydb.insertitems(2,"Naan","North Indian","Rs. 50");
        mydb.insertitems(2,"Tandoori chicken","Tandoori","Rs. 140");
        mydb.insertitems(2,"Chettinad chicken","South Indian","Rs. 145");
        mydb.insertitems(2,"Pongal","South Indian","Rs. 40");

        mydb.insertitems(3,"Smoky grilled 8PC","Fast food","Rs. 265");
        mydb.insertitems(3,"Triple Treat","Fast food","Rs. 643");
        mydb.insertitems(3,"Burger","Fast food","Rs. 130");
        mydb.insertitems(3,"French Fries","Fast food","Rs. 90");
        mydb.insertitems(3,"Pepsi 500ml","Soft drink","Rs. 50");

        if(inserted)
            Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_SHORT).show();

        v_flipper=findViewById(R.id.img_flipper);

        for(int i=0;i<image.length;i++)
            flip_image(image[i]);
    }



    private void flip_image(int i) {

        ImageView view=new ImageView(this);
        view.setBackgroundResource(i);
        v_flipper.addView(view);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    public void login(View view) {

        Cursor res=mydb.getdata();
        if(res.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_SHORT).show();
        }
        else
        {
            StringBuffer buffer=new StringBuffer();
            while(res.moveToNext())
            {
                buffer.append(res.getString(0)+res.getString(1)+res.getString(2)+res.getString(3)+"\n");
            }
            Toast.makeText(getApplicationContext(),buffer,Toast.LENGTH_SHORT).show();
        }
        Intent i1=new Intent(this,login.class);
        startActivity(i1);
    }

    public void getloc(View view) {

         Intent i1=new Intent(this,login.class);
        startActivity(i1);
    }
}
