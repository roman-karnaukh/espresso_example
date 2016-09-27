package automation.qa.espressoexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import automation.qa.espressoexample.adapters.RecyclerViewAdapter;
import automation.qa.espressoexample.data.Data;
import automation.qa.espressoexample.models.Person;

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView rvMain;
    private Data data;
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mContext = this;
        data = new Data();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data.personsData());

        rvMain = (RecyclerView) findViewById(R.id.rvMain);
        rvMain.setHasFixedSize(true);
        rvMain.setLayoutManager(linearLayoutManager);
        rvMain.setAdapter(adapter);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.getChildCount();
    }

}
