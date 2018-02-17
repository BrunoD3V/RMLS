package d3v.bnb.rssimetro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowResultsActivity extends AppCompatActivity {

    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);
        mainText = (TextView) findViewById(R.id.showText);

        Intent i = getIntent();
        String resultados = i.getStringExtra("results");

        mainText.setText(resultados);
    }

    public void onClickbtnSaveConstants(View v){
        //TODO save data on BD
    }
}
