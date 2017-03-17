package app.com.example.asadmahmood.quotetastic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int index=0;
    DatabaseHelper db;
    int max;
    ArrayList<String> quotes = new ArrayList<String>();
    Button viewAll;
    TextView quotation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
      /*  db.insertData("Anni kanjor insaan hai");
        db.insertData("me kehta hn kay na dunya gool hain");
        db.insertData("Yeh dunya pittal di tay babydol me sonay di");
*/
        storeData();
        viewAll = (Button)findViewById(R.id.button);
        quotation = (TextView)findViewById(R.id.textView) ;
        quotation.setText(Html.fromHtml(" &quot; " + quotes.get(0) + ". &quot; "));
        viewAll();
    }

    public void click(View v) {
        Intent intent;
        intent = new Intent(getBaseContext(),Detail_Page.class);
        intent.putExtra ("index", index);
        startActivity(intent);
    }

    public void viewAll(){
        viewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v)
                    {
                        Random random = new Random();
                        index = (random.nextInt((max-1) - 0 + 1) + 0);
                        quotation.setText(Html.fromHtml(" &quot; " + quotes.get(index) + ". &quot; "));
                    }
                }
        );
    }

    public void showMessage(String title , String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void storeData()
    {

        Cursor res = db.getAllData();
        max = res.getCount();
        //showMessage("Total",Integer.toString(max));
        if(res.getCount() == 0)
        {
            showMessage("Error","Nothing found");
        }
        else
        {
            int i=0;
            while(res.moveToNext()) {
                quotes.add( res.getString(1).toString() );
            }
            
        }

    }
}
