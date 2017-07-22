package com.inti.student.androidassignment;

import android.content.Intent;
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

public class HistoryFragment extends Fragment {

    Button clearButton;
    List<Article> histoList = new ArrayList<>();
    ListView historyList;
    ArrayAdapter<Article> newsAdapter;
    HistoryDatabaseHelper mHistoryDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);

        mHistoryDatabaseHelper = new HistoryDatabaseHelper(getActivity());

        Cursor cursor = mHistoryDatabaseHelper.getHistory();

        while(cursor.moveToNext())
            histoList.add(new Article(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));


        newsAdapter = new NewsListAdapter(getActivity(), histoList);

        historyList = (ListView) view.findViewById(R.id.FragNewsHistory);
        historyList.setAdapter(newsAdapter);

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView url = (TextView) view.findViewById(R.id.url);
                String s = url.getText().toString(); //Retrieves the URL of the clicked the list item

                Intent intent = new Intent(getActivity(), InAppBrowserActivity.class); //Gets Activity Intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //Makes sure the activity task does not start if there is one already running
                intent.putExtra("url", s); //assigns the URL with a key value
                getActivity().startActivity(intent);//initiates the command to start the Activity
            }
        });

        clearButton = (Button) view.findViewById(R.id.clear_button);

        clearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mHistoryDatabaseHelper.deleteALL(); //Clears the entire SQLite Database
                newsAdapter.notifyDataSetChanged();
                getFragmentManager().beginTransaction().replace(R.id.container, new HistoryFragment()).commit(); //Reloads the ListView
            }


        });

        return view;

    }
}
