package com.example.vishal.saltnpepper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class databasehandler extends SQLiteOpenHelper {

    public static final String dbname="snp.db";
    public static final String tablename="restaurant";
    Context con;

    public databasehandler(Context context) {
        super(context, dbname, null,1);
        con=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table restaurant(restid INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,ADDR VARCHAR(60),CITY TEXT,PINCODE INT,TYPE TEXT,AVG TEXT,RATING REAL);");
        db.execSQL("create table menu(itemid INTEGER primary key autoincrement,restid INTEGER,name text,cuisine text,cost varchar(20),foreign key(restid) references restaurant(restid));");

        db.execSQL("create table if not exists cart(uid bigint,restid integer,itemid integer,num integer,primary key(itemid));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("DROP TABLE IF EXISTS restaurant;");
        db.execSQL("DROP TABLE IF EXISTS menu;");
         onCreate(db);
    }

    public boolean insert(String name,String addr,String city,String pin,String type,String avg,String rat)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("addr",addr);
        contentValues.put("city",city);
        contentValues.put("pincode",pin);
        contentValues.put("type",type);
        contentValues.put("avg",avg);
        contentValues.put("rating",rat);

        long result=db.insert("restaurant",null,contentValues);

        if(result==-1)
            return false;
        else return true;

    }

    public boolean insertitems(long restid,String name,String cuis,String cost)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("name",name);
        contentValues.put("restid",restid);
        contentValues.put("cuisine",name);
        contentValues.put("cost",cost);
        long result=db.insert("menu",null,contentValues);

        if(result==-1)
            return false;
        else return true;


    }

    public Boolean insertcart(long uid,int restid,int itid,int num)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("uid",uid);
        contentValues.put("restid",restid);
        contentValues.put("itemid",itid);
        contentValues.put("num",num);
        long result=db.insert("cart",null,contentValues);

        if(result==-1)
            return false;
        else return true;
    }

    public Cursor getnamecost(int iid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from menu where itemid="+iid+";",null);
        return res;

    }
    public Cursor getids(String name,int num,String numm)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from menu where name=\'"+name+"\';",null);

        int idd,reeid;

        if(res.getCount()!=0)
        {
            String itid="",reid="";

            while(res.moveToNext()) {
                itid = res.getString(0);
                reid=res.getString(1);
            }
            idd=Integer.parseInt(itid);
            reeid=Integer.parseInt(reid);

            Cursor res1=db.rawQuery("select * from cart where itemid="+idd,null);

            if(res1.getCount()==0&&num!=0)
            {
                long num1=Long.parseLong(numm);
                insertcart(num1,reeid,idd,num);
                Toast.makeText(con,"inserted",Toast.LENGTH_SHORT).show();
            }

            else
            {
                if(num!=0)
                {db.rawQuery("update cart set num="+num+" where itemid="+idd+";",null);
                Toast.makeText(con,"updated"+idd,Toast.LENGTH_SHORT).show();

                    long num1=Long.parseLong(numm);

                    ContentValues contentValues=new ContentValues();

                    contentValues.put("uid",num1);
                    contentValues.put("restid",reeid);
                    contentValues.put("itemid",itid);
                    contentValues.put("num",num);

                    db.update("cart",contentValues,"itemid="+idd,null);
                }

                else
                {
                    db.rawQuery("delete from cart where itemid="+idd+";",null);
                    Toast.makeText(con,"deleted"+idd,Toast.LENGTH_SHORT).show();
                    db.delete("cart","itemid="+idd,null);
                }
            }
        }

        return res;

    }

    public int getiid(int iid,int rid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select num from cart where itemid="+iid+" and restid ="+rid+";",null);

        String num="";
        int numm;

        if(res.getCount()!=0)

        {
            while (res.moveToNext())
                num = res.getString(0);

            numm=Integer.parseInt(num);
        }
        else
            numm=0;




        return numm;
    }
    public Cursor getcart()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from cart",null);
        return res;
    }
    public Cursor getdata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from restaurant",null);
        return res;
    }

    public Cursor visc(String area)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from restaurant where addr like \'%"+area+"%\';",null);
        return res;

    }

    public Cursor restname(String area)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from restaurant where name like \'%"+area+"%\';",null);
        return res;

    }

    public Cursor getitemdata(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from restaurant where name=\'"+name+"\';",null);

        int idd;

        if(res.getCount()!=0)
        {
            String id="";

            while(res.moveToNext())
            id=res.getString(0);

            idd=Integer.parseInt(id);
        }

        else idd=0;



        Cursor res1=db.rawQuery("select * from menu where restid="+idd+";",null);
        return res1;
    }

    public void drop()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS restaurant;");
        db.execSQL("DROP TABLE IF EXISTS menu;");
        db.execSQL("drop table if exists cart;");
        onCreate(db);
    }
}
