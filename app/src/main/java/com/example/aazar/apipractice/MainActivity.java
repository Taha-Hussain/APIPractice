package com.example.aazar.apipractice;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.aazar.apipractice.models.RFQ_API_Object;
import com.example.aazar.apipractice.controller.HttpRequest;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private TextView myTextView;
    private TextView authorTextView;
    private RadioButton radioButtonMovies;
    private RadioButton radioButtonFamous;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button) findViewById(R.id.myButton);
        myTextView = (TextView) findViewById(R.id.myTextView);
        authorTextView = (TextView) findViewById(R.id.authorTextView);
        radioButtonFamous = (RadioButton) findViewById(R.id.radio_button_famous);
        radioButtonMovies = (RadioButton) findViewById(R.id.radio_button_movies);
        progressDialog = new ProgressDialog(MainActivity.this, R.style.MyTheme);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                "http://quotes.rest/qod.json"
//                "http://api.forismatic.com/api/1.0/"

//                HashMap<String, String> queryParameters = new HashMap<String, String>();

                // ye upar walay comments ko ignore kr

                myAPI aazar = new myAPI();
                if (isNetworkAvailable(MainActivity.this)) {
                    if (radioButtonMovies.isChecked())
                        aazar.execute("movies");
                    else if (radioButtonFamous.isChecked())
                        aazar.execute("famous");
                }
                else
                    throwToast("Please check your internet connection", Toast.LENGTH_SHORT);
            }
        });
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void throwToast(String message, int duration){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.toast_text);
        text.setText(message);

        Toast toast = new Toast(MainActivity.this);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }

    public class myAPI extends AsyncTask<String, String, RFQ_API_Object> {

        @Override
        protected RFQ_API_Object doInBackground(String... params) {
            HashMap<String, String> headerParameters = new HashMap<String, String>();
            headerParameters.put("X-Mashape-Key", "xuC5j4vzIAmshOGkMx4AcfjczKnkp1SbSPQjsnw3hhzYPGqvRp");
            headerParameters.put("Content-Type", "application/x-www-form-urlencoded");
            headerParameters.put("Accept", "application/json");

            HttpRequest http_request = new HttpRequest("https://andruxnet-random-famous-quotes.p.mashape.com/?cat=" + params[0],
                    "POST",
                    headerParameters);
            return http_request.callAPI();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (myTextView.getText() != null){
                YoYo.with(Techniques.TakingOff)
                        .duration(500)
                        .playOn(findViewById(R.id.myTextView));

                YoYo.with(Techniques.FadeOutRight)
                        .duration(500)
                        .playOn(findViewById(R.id.authorTextView));
            }

            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(RFQ_API_Object result) {
            progressDialog.dismiss();
            super.onPostExecute(result);
            myTextView.setText(result.getQuote());
            authorTextView.setText(result.getAuthor());

            YoYo.with(Techniques.Landing)
                    .duration(500)
                    .playOn(findViewById(R.id.myTextView));

            YoYo.with(Techniques.FadeInLeft)
                    .duration(500)
                    .playOn(findViewById(R.id.authorTextView));
        }
    }
}