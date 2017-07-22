package com.inti.student.androidassignment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//Imports a Java serialization/deserialization library to convert Java Objects into JSON and back
import com.google.gson.Gson; //https://github.com/google/gson


public class HomeFragment extends Fragment {

    Button refreshButton;
    private NewsObject newsObject;
    RecyclerView recyclerView;
    public String apiLinkchosen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.FragNewsHome);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        apiLinkchosen = getArguments().getString("apiKeychosen"); //gets the API Key from the bundle setted in API_Fragment class


        loadApp(apiLinkchosen); //Loads the Recycler View with the API Key

        refreshButton = (Button) view.findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                loadApp(apiLinkchosen); //Reloads the Recycler View
            }
        });

        return view;

    }


    protected void loadApp(String apiKey) {
        AsyncTask<String, String, String> loadApp = new AsyncTask<String, String, String>() {

            ProgressDialog dialog = new ProgressDialog(getActivity()); //A pop-up message dialog

            @Override
            protected void onPreExecute() {
                dialog.setMessage("Loading...");
                dialog.show();
            }

            @Override
            protected String doInBackground(String... params) { //Retrieves all the data information from the API Key url
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.getHTTPData(params[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                dialog.dismiss();
                newsObject = new Gson().fromJson(s, NewsObject.class); //Gets the JSON information and converts it into Java Object
                NewsRecyclerAdapter newsRecyclerAdapter = new NewsRecyclerAdapter(newsObject, getActivity().getBaseContext());
                recyclerView.setAdapter(newsRecyclerAdapter);
            }
        };

        StringBuilder urlGetdata = new StringBuilder();
        urlGetdata.append(apiKey); //Appends the API Key onto the String Builder
        loadApp.execute(urlGetdata.toString()); //Executes this method whilst converting the appended API into a String
    }


}
