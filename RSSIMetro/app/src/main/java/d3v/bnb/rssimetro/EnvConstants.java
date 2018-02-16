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

public class EnvConstants extends AppCompatActivity {

    private EditText editTextMeasureID;
    private TextView textViewShowConstans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_env_constants);

        editTextMeasureID = (EditText) findViewById(R.id.editTextMeasureID);
        textViewShowConstans = (TextView) findViewById(R.id.textViewShowConstans);
    }

 /*   public void onClickCalcBtn(View v) {

        if (isEmpty(editTextMeasureID)) {
            Toast.makeText(getApplicationContext(), "Tem que preencher todos os campos", Toast.LENGTH_LONG).show();
            return;
        }

        //TODO ir buscar as coordenadas da pos a BD e os resultados das medidas
        double dB1 = Double.valueOf(Bssid1Edit.getText().toString());


        double pB1 = Double.valueOf(pBssid1Edit.getText().toString());
        double pB2 = Double.valueOf(pBssid2Edit.getText().toString());
        double pB3 = Double.valueOf(pBssid3Edit.getText().toString());
        double pB4 = Double.valueOf(pBssid4Edit.getText().toString());

        double[][] data = {{1, 10 * Math.log10(dB1)}, {1, 10 * Math.log10(dB2)},
                {1, 10 * Math.log10(dB3)}, {1, 10 * Math.log10(dB4)}};
        double[][] Pot = {{pB1}, {pB2}, {pB3}, {pB4}};
        //  double [][] inv = {{4,14}, {14,54}};
        RealMatrix M = MatrixUtils.createRealMatrix(data);
        RealMatrix RSSI = MatrixUtils.createRealMatrix(Pot);
        RealMatrix T = M.transpose();
        RealMatrix Res = T.multiply(M);

        // RealMatrix Inv = MatrixUtils.createRealMatrix(inv);

        RealMatrix Inv = new LUDecomposition(Res).getSolver().getInverse();
        //System.out.println(Inv.toString());
        RealMatrix Res2 = Inv.multiply(T);
        RealMatrix Rfinal = Res2.multiply(RSSI);
        //double exp = (Rfinal.getEntry(1)-RSSI.getEntry(0,0))/(10 * -1 * Rfinal.getEntry(1,1));
        //  double dist = Math.pow(10,exp);
        // double teste = 10 * Math.log10(7.84);
        String resultados = Rfinal.toString();
        for (int i = 0; i <= 3; i++) {
            double exp = (Rfinal.getEntry(0, 0) - RSSI.getEntry(i, 0)) / (-10 * Rfinal.getEntry(1, 0));
            double dist = Math.pow(10, exp);
            double error = Math.abs(Math.pow(10, M.getEntry(i, 1) / 10) - dist) / Math.pow(10, M.getEntry(i, 1) / 10);
            System.out.println(Math.pow(10, M.getEntry(i, 1) / 10));
            resultados += "\n distancia a " + i + " " + dist + " erro = " + error;
        }

        Intent i = new Intent(this, ShowResults.class);
        i.putExtra("resultados", resultados);
        startActivity(i);

        // mainText.setText(Double.valueOf(teste).toString());
    } */

    public double getDistance(double[] pos1, double[] pos2) {

        double distance = 0;
        for (int i = 0; i < pos1.length; i++)
            distance += Math.pow((pos1[i] - pos2[i]), 2);

        return Math.sqrt(distance);


    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
