package com.inti.student.androidassignment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class API_Fragment extends Fragment {

    HomeFragment homeFragment;
    
    Button proceedButton;
    List<API> apiList = new ArrayList<>();
    ListView apiListView;
    ArrayAdapter<API> apiAdapter;
    Cursor cursor;
    public String apiLinkchosen;

    API_DatabaseHelper mAPI_databaseHelper;

    String[] websiteName = new String[] { //20 Website Names to be inserted into SQLite Database
            "ABC News (AU)", //0
            "BBC News", //1
            "Business Insider (UK)", //2
            "Buzzfeed", //3
            "CNBC", //4
            "CNN", //5
            "Entertainment Weekly", //6
            "ESPN", //7
            "Financial Times", //8
            "Fox Sports", //9
            "Google News", //10
            "Hacker News", //11
            "IGN", //12
            "MTV News", //13
            "National Geographic", //14
            "Reddit /r/all", //15
            "Spiegel Online", //16
            "The Wall Street Journal", //17
            "The Washington Post", //18
            "Time" //19
    };

    String[] apiKey = new String[] { // 20 API Keys to be inserted into SQLite Database
            "https://newsapi.org/v1/articles?source=abc-news-au&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //0
            "https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //1
            "https://newsapi.org/v1/articles?source=business-insider-uk&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //2
            "https://newsapi.org/v1/articles?source=buzzfeed&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //3
            "https://newsapi.org/v1/articles?source=cnbc&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //4
            "https://newsapi.org/v1/articles?source=cnn&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //5
            "https://newsapi.org/v1/articles?source=entertainment-weekly&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //6
            "https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //7
            "https://newsapi.org/v1/articles?source=financial-times&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //8
            "https://newsapi.org/v1/articles?source=fox-sports&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //9
            "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //10
            "https://newsapi.org/v1/articles?source=hacker-news&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //11
            "https://newsapi.org/v1/articles?source=ign&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //12
            "https://newsapi.org/v1/articles?source=mtv-news&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //13
            "https://newsapi.org/v1/articles?source=national-geographic&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //14
            "https://newsapi.org/v1/articles?source=reddit-r-all&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //15
            "https://newsapi.org/v1/articles?source=spiegel-online&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //16
            "https://newsapi.org/v1/articles?source=the-wall-street-journal&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //17
            "https://newsapi.org/v1/articles?source=the-washington-post&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8", //18
            "https://newsapi.org/v1/articles?source=time&sortBy=top&apiKey=c3aa663499d746d796ea79cd865de1f8" //19
    };
    
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.api_fragment, container, false);

        mAPI_databaseHelper = new API_DatabaseHelper(getActivity());


        //Clears the entire database clean
        //To be used ONLY when there is Error in the String Arrays
        //mAPI_databaseHelper.deleteALL();


        //***ONLY to be used ONCE to insert into database***

        //for(int i = 0; i < 20; i++)
            //mAPI_databaseHelper.addData(websiteName[i], apiKey[i]);

        //For Loop inserts the Strings containing Website Names and API Keys into an SQLite Database


        cursor = mAPI_databaseHelper.getAPI();
        //Retrieves the entire table from SQLite Database containing Website Names and API Keys


        while(cursor.moveToNext()) //Inserts the information retrieved from SQLite Database to a List
            apiList.add(new API(cursor.getString(1), cursor.getString(2)));


        apiAdapter = new API_ListAdapter(getActivity(), apiList);
        apiListView = (ListView) view.findViewById(R.id.FragAPI);
        apiListView.setAdapter(apiAdapter);

        apiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView apiKey = (TextView) view.findViewById(R.id.apiKey);
                apiLinkchosen = apiKey.getText().toString(); //Retrieves the API Key of the clicked the list item

                homeFragment = new HomeFragment();//Get Fragment Instance
                Bundle data = new Bundle();//Use bundle to pass data
                data.putString("apiKeychosen", apiLinkchosen);//assigns the retrieved API Key into a bundle with a key value
                homeFragment.setArguments(data);//sends the bundle with the API Key to HomeFragment class
            }
        });


        proceedButton = (Button) view.findViewById(R.id.proceed_button);
        proceedButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit(); //calls HomeFragment class
            }
        });

        return view;

    }

}

