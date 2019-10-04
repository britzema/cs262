package edu.calvin.cs262.bdr22;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText editText;
    private TextView authorTextView, titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.bookInput);
        authorTextView = findViewById(R.id.authorText);
        titleTextView = findViewById(R.id.titleText);
    }

    public void searchBooks(View view) {
        String searchText = editText.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && searchText.length()!=0) {
//            new FetchBook(titleTextView, authorTextView).execute(searchText);
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", searchText);
            getSupportLoaderManager().restartLoader(0, queryBundle,this);
            authorTextView.setText("");
            titleTextView.setText(R.string.loading_text);
        }

        else {
            if (searchText.length() == 0) {
                authorTextView.setText("");
                titleTextView.setText(R.string.search_prompt);
            } else {
                authorTextView.setText("");
                titleTextView.setText(R.string.network_fail);
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new BookLoader(this, bundle.getString("queryString"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            //Iterate through the results
            for(int i = 0; i<itemsArray.length(); i++){
                JSONObject book = itemsArray.getJSONObject(i); //Get the current item
                String title=null;
                String authors=null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                Log.d("volinfo", volumeInfo.toString());

                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e){
                    e.printStackTrace();
                }

                //If both a titleTextView and authorTextView exist, update the TextViews and return
                if (title != null && authors != null){
                    titleTextView.setText(title);
                    authorTextView.setText(authors);
                    return;
                }

                titleTextView.setText(R.string.no_found);
                authorTextView.setText("");
            }
        } catch (Exception e) {
            titleTextView.setText(R.string.no_found);
            authorTextView.setText("");
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
