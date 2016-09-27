package automation.qa.espressoexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTextInputLayout, btnRecyclerView, btnListViewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTextInputLayout = (Button) findViewById(R.id.btnTextInputLayout);
        btnRecyclerView = (Button) findViewById(R.id.btnRecyclerView);
        btnListViewActivity = (Button) findViewById(R.id.btnListViewActivity);

        btnTextInputLayout.setOnClickListener(this);
        btnRecyclerView.setOnClickListener(this);
        btnListViewActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnTextInputLayout:
                intent = new Intent(this, TextInputLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRecyclerView:
                intent = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btnListViewActivity:
                intent = new Intent(this, ListViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
