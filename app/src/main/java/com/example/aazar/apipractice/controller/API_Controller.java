//import android.net.Uri;
//import android.os.AsyncTask;
//
//import com.example.aazar.apipractice.models.QuoteObject;
//import com.example.aazar.apipractice.models.RFQ_API_Object;
//
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class API_GetCall extends AsyncTask<String, String, String> {
//
//    @Override
//    protected String doInBackground(String... params) {
//        HttpURLConnection urlConnection = null;
//        BufferedReader bufferedReader = null;
//
//        try{
//            URL url = new URL(params[0]);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.connect();
//
//            InputStream inputStream = urlConnection.getInputStream();
//
//            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//            StringBuilder stringBuilder = new StringBuilder();
//
//            String line = "";
//
//            while((line = bufferedReader.readLine()) != null){
//                stringBuilder.append(line);
//            }
//
//            String finalJSONData = stringBuilder.toString();
//
//            JSONObject finalObject = new JSONObject(finalJSONData);
//
////                int userID = finalObject.getInt("userId");
////                int id = finalObject.getInt("id");
////                String title = finalObject.getString("title");
////                String body = finalObject.getString("body");
//
//            return stringBuilder.toString();
//        }
//
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        finally{
//            if (urlConnection != null)
//                urlConnection.disconnect();
//
//            if (bufferedReader != null)
//                try {
//                    bufferedReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        super.onPostExecute(result);
//        myTextView.setText(result);
//    }
//
//
//}
//
//public class API_PostCall_Header extends AsyncTask<String, String, RFQ_API_Object>{
//
//    @Override
//    protected RFQ_API_Object doInBackground(String... params) {
//        BufferedReader bufferedReader = null;
//
//        try {
//            URL url = new URL(params[0]);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            conn.setRequestProperty("X-Mashape-Key", "xuC5j4vzIAmshOGkMx4AcfjczKnkp1SbSPQjsnw3hhzYPGqvRp");
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setRequestProperty("Accept", "application/json");
//
//            conn.connect();
//
//            InputStream inputStream = conn.getInputStream();
//
//            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//            StringBuilder stringBuilder = new StringBuilder();
//
//            String line = "";
//
//            while((line = bufferedReader.readLine()) != null){
//                stringBuilder.append(line);
//            }
//
//            String finalJSONData = stringBuilder.toString();
//
//            JSONObject finalObject = new JSONObject(finalJSONData);
//            RFQ_API_Object quote = new RFQ_API_Object();
//
//            quote.setQuote(finalObject.getString("quote"));
//            quote.setAuthor(finalObject.getString("author"));
//            quote.setCategory(finalObject.getString("category"));
//
//            return quote;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(RFQ_API_Object result) {
//        super.onPostExecute(result);
//
//        myTextView.setText(result.getQuote());
//
//        if (result.getAuthor() == null)
//            authorTextView.setText("Anonymous");
//        else
//            authorTextView.setText(result.getAuthor());
//
//        myButton.setText("GOT ROOM FOR ONE MORE?");
//    }
//}
//
//public class API_PostCall_Query extends AsyncTask<String, String, QuoteObject>{
//
//    @Override
//    protected QuoteObject doInBackground(String... params) {
//        BufferedReader bufferedReader = null;
//
//        try {
//            URL url = new URL(params[0]);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//
//            Uri.Builder builder = new Uri.Builder()
//                    .appendQueryParameter("method", "getQuote")
//                    .appendQueryParameter("format", "json")
//                    .appendQueryParameter("key", "457653")
//                    .appendQueryParameter("lang", "en");
//
//            String query = builder.build().getEncodedQuery();
//
//            OutputStream os = conn.getOutputStream();
//
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//            writer.write(query);
//            writer.flush();
//            writer.close();
//            os.close();
//
//            conn.connect();
//
//            InputStream inputStream = conn.getInputStream();
//
//            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//            StringBuilder stringBuilder = new StringBuilder();
//
//            String line = "";
//
//            while((line = bufferedReader.readLine()) != null){
//                stringBuilder.append(line);
//            }
//
//            String finalJSONData = stringBuilder.toString();
//
//            JSONObject finalObject = new JSONObject(finalJSONData);
//            QuoteObject quote = new QuoteObject();
//
//            quote.setQuoteText(finalObject.getString("quoteText"));
//            quote.setQuoteAuthor(finalObject.getString("quoteAuthor"));
//
//            return quote;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(QuoteObject result) {
//        super.onPostExecute(result);
//
////        myTextView.setText("\"" + result.getQuoteText().substring(0, result.getQuoteText().length() - 1) + "\"");
////
////        if (result.getQuoteAuthor() == null)
////            authorTextView.setText("Anonymous");
////        else
////            authorTextView.setText(result.getQuoteAuthor());
////
////        myButton.setText("GOT ROOM FOR ONE MORE?");
//    }
//}
//}