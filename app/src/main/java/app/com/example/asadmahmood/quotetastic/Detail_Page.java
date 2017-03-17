package app.com.example.asadmahmood.quotetastic;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Detail_Page extends AppCompatActivity {

    ArrayList<String> quotes = new ArrayList<String>();
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> description = new ArrayList<String>();
    DatabaseHelper db;
    TextView quotation,nam,desc;
    public int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_detail__page);
        Intent mIntent = getIntent();
        index = mIntent.getIntExtra("index", 0);
        storeData();
        quotation = (TextView)findViewById(R.id.textView2);
        quotation.setText(Html.fromHtml(" &quot; " + quotes.get(0) + ". &quot; "));
        nam=(TextView)findViewById(R.id.textView5);
        nam.setText( name.get(0) );
        desc = (TextView)findViewById(R.id.textView7);
        desc.setMovementMethod(new ScrollingMovementMethod());
        desc.setText( description.get(0));
    }

    public void clicked(View v) {
        Intent intent;
        intent = new Intent(getBaseContext(),MainActivity.class);
        startActivity(intent);
    }

    public void storeData()
    {
        Cursor res = db.getDataForIndex(index);
        //showMessage("Total",Integer.toString(res.getCount()));
        if(res.getCount() == 0)
        {
            showMessage("Error","Nothing found");
        }
        else
        {
            while(res.moveToNext()) {
                quotes.add( res.getString(1).toString() );
                name.add( res.getString(2).toString() );
                description.add( res.getString(3).toString() );
            }

        }

    }

    public void showMessage(String title , String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
