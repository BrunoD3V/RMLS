package d3v.bnb.rssimetro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class MultilaterationActivity extends AppCompatActivity {

    private TextView txtShowPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multilateration);

        txtShowPosition = (TextView) findViewById(R.id.txtShowPosition);
    }

    public void onClickbtnCalcPos(View v){

    }

    public void onClickbtnSaveMultiPos(View v){

    }

   public void multilaterate() {

     /*
       double[][] distDif = {{2 * x1 - 2 * x2, 2 * y1 - 2 * y2}, {2 * x1 - 2 * x3,
               2 * y1 - 2 * y3}, {2 * x1 - 2 * x4, 2 * y1 - 2 * y4}};


       double[][] preB = {{d2 * d2 - d1 * d1 - (x2 * x2 + y2 * y2) + (x1 * x1 + y1 * y1)},
               {d3 * d3 - d1 * d1 - (x3 * x3 + y3 * y3) + (x1 * x1 + y1 * y1)},
               {d4 * d4 - d1 * d1 - (x4 * x4 + y4 * y4) + (x1 * x1 + y1 * y1)}};

       RealMatrix A = MatrixUtils.createRealMatrix(distDif);
       RealMatrix B = MatrixUtils.createRealMatrix(preB);

       RealMatrix T = A.transpose();

       A = T.multiply(A);

       A = new LUDecomposition(A).getSolver().getInverse();

       A = A.multiply(T);

       A = A.multiply(B);

       x = A.getEntry(0, 0);
       eX = getError(rX, x);
       y = A.getEntry(1, 0);
       eY = getError(rY, y);
       eDist = getDistError(x, rX, y, rY);*/
   }
}
