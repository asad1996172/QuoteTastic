package app.com.example.asadmahmood.quotetastic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private final int DURATION = 1500;
    private Thread mSplashThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSplashThread = new Thread()
        {
            @Override
            public void run()
            {
                synchronized (this){
                    try{
                        wait(DURATION);
                    }
                    catch(InterruptedException e){}
                    finally {
                        finish();
                        Intent intent = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent);
                    }
                }

            }
        };
        mSplashThread.start();
    }
}
