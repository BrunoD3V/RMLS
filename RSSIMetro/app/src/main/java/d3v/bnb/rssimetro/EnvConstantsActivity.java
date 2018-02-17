package d3v.bnb.rssimetro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;

public class EnvConstantsActivity extends AppCompatActivity {

    private EditText editTextMeasureID;
    private TextView textViewShowConstans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_env_constants);

        editTextMeasureID = (EditText) findViewById(R.id.editTextMeasureID);
        textViewShowConstans = (TextView) findViewById(R.id.textViewShowConstans);
    }

       public void onClickBtnCalc(View v) {

        if (Utils.isEmpty(editTextMeasureID)) {
            Toast.makeText(getApplicationContext(), "Please fill the field Measure ID", Toast.LENGTH_LONG).show();
            return;
        }

        //TODO ir buscar as coordenadas da pos a BD e os resultados das medidas


        double[] pos1 = {0,0,0};
        double[] pos2 = {0,0,0};
        double[] pos3 = {0,0,0};
        double[] pos4 = {0,0,0};
        double[] posM = {0,0,0};

        double dB1 = getDistance(pos1, posM);
        double dB2 = getDistance(pos2, posM);
        double dB3 = getDistance(pos3, posM);
        double dB4 = getDistance(pos4, posM);


        double pB1 = 1;
        double pB2 = 2;
        double pB3 = 3;
        double pB4 = 4;

        double[][] data = {{1, 10 * Math.log10(dB1)}, {1, 10 * Math.log10(dB2)},
                {1, 10 * Math.log10(dB3)}, {1, 10 * Math.log10(dB4)}};
        double[][] Pot = {{pB1}, {pB2}, {pB3}, {pB4}};

        RealMatrix M = MatrixUtils.createRealMatrix(data);
        RealMatrix RSSI = MatrixUtils.createRealMatrix(Pot);
        RealMatrix T = M.transpose();
        RealMatrix Res = T.multiply(M);



        RealMatrix Inv = new LUDecomposition(Res).getSolver().getInverse();

        RealMatrix Res2 = Inv.multiply(T);
        RealMatrix Rfinal = Res2.multiply(RSSI);

        String resultados = Rfinal.toString();
        for (int i = 0; i <= 3; i++) {
            double exp = (Rfinal.getEntry(0, 0) - RSSI.getEntry(i, 0)) / (-10 * Rfinal.getEntry(1, 0));
            double dist = Math.pow(10, exp);
            double error = Math.abs(Math.pow(10, M.getEntry(i, 1) / 10) - dist) / Math.pow(10, M.getEntry(i, 1) / 10);
            System.out.println(Math.pow(10, M.getEntry(i, 1) / 10));
            resultados += "\n distance to" + i + " " + dist + " error = " + error;
        }

        Intent i = new Intent(this, ShowResultsActivity.class);
        i.putExtra("results", resultados);
        startActivity(i);


    }

    public void onClickBtnSave(View v){

    }

    public double getDistance(double[] pos1, double[] pos2) {

        double distance = 0;
        for (int i = 0; i < pos1.length; i++)
            distance += Math.pow((pos1[i] - pos2[i]), 2);

        return Math.sqrt(distance);


    }






}
