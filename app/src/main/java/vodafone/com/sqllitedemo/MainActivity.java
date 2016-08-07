package vodafone.com.sqllitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button insertButton;
    Button queryButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertButton=(Button)findViewById(R.id.activity_main_button_insert);
        queryButton=(Button)findViewById(R.id.activity_main_button_query);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertSomethingInDatabase();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query();
                Toast.makeText(MainActivity.this, "Queried", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void insertSomethingInDatabase()
    {
        MySqlHelper mySqlHelper=new MySqlHelper(this);
        ContentValues contentValues=new ContentValues();
        contentValues.put(MySqlHelper.User.name,"Dhiraj Kumar");
        contentValues.put(MySqlHelper.User.email,"dhiraj.sk@gmail.com");

        SQLiteDatabase sqLiteDatabase=mySqlHelper.getWritableDatabase();
        sqLiteDatabase.insert(MySqlHelper.Tables.USER,null,contentValues);

    }

    public void query()
    {
        MySqlHelper mySqlHelper=new MySqlHelper(this);
        SQLiteDatabase sqLiteDb=mySqlHelper.getReadableDatabase();
        Cursor cursor=sqLiteDb.query(MySqlHelper.Tables.USER,null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do {
                int _id=cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
                String name=cursor.getString(cursor.getColumnIndex(MySqlHelper.User.name));
                String email=cursor.getString(cursor.getColumnIndex(MySqlHelper.User.email));
                Log.d("Main_Activity","Row "+_id+","+name+","+email);
            }while (cursor.moveToNext());
        }
    }
}
