package automation.qa.espressoexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import automation.qa.espressoexample.adapters.ListViewAdapter;
import automation.qa.espressoexample.data.Data;
import automation.qa.espressoexample.models.Person;

public class ListViewActivity extends AppCompatActivity {
    ListView listView;
    private Data data = new Data();
    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);

        listView = (ListView) findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter(this, data.personsData());
        listView.setAdapter(listViewAdapter);

    }
}
