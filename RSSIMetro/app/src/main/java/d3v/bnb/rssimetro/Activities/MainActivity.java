package d3v.bnb.rssimetro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import d3v.bnb.rssimetro.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtnMeasurements(View v){
        Intent i = new Intent(this, MeasurementActivity.class);
        startActivity(i);
    }
    public void onClickBtnEnvConstants(View v){
        Intent i = new Intent(this, EnvConstantsActivity.class);
        startActivity(i);
    }

    public void onClickBtnMultilateration(View v){
        Intent i = new Intent(this, MultilaterationActivity.class);
        startActivity(i);
    }



}
