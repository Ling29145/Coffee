package com.example.coffee;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DrinkActivity extends AppCompatActivity {

    //定义intend Key-Value对的Key
    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = getIntent().getIntExtra(EXTRA_DRINKNO, 0);
        SQLiteOpenHelper starDatabaseHelper = new StarDatabaseHelper(this);
        try (SQLiteDatabase db = starDatabaseHelper.getReadableDatabase()) {
            Cursor cursor = db.query("DRINK", new String[]{"NAME", "DESCRIPTION",
                            "IMAGE_RESOURCE_ID"},
                    "_id=?",
                    new String[]{Integer.toString(drinkId)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                //显示咖啡名称
                TextView name = findViewById(R.id.name);
                name.setText(nameText);
                TextView description = findViewById(R.id.description);
                description.setText(descriptionText);
                ImageView photo = findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }
            cursor.close();
        } catch (SQLiteException e) {
            Log.e("sqlite", e.getMessage());
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


//        //从Intend中取出数组的索引
//        int drinkNo = (Integer) getIntent().getExtras().get(EXTRA_DRINKNO);
//        Drink drink = Drink.drinks[drinkNo];
//        //显示咖啡图片
//        ImageView photo = findViewById(R.id.photo);
//        photo.setImageResource(drink.getImageResourceId());
//        photo.setContentDescription(drink.getName());
//        //显示咖啡名称
//        TextView name = findViewById(R.id.name);
//        name.setText(drink.getName());
//        //显示咖啡描述
//        TextView description = findViewById(R.id.description);
//        description.setText(drink.getDescription());

    }

}