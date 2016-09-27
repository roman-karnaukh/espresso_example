package automation.qa.espressoexample.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import automation.qa.espressoexample.R;
import automation.qa.espressoexample.RecyclerViewActivity;
import automation.qa.espressoexample.models.Person;

/**
 * Created by Karnaukh Roman on 26.09.2016.
 */
public class ListViewAdapter extends BaseAdapter implements View.OnClickListener {

    String TAG = "appM";
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Person> persons;

    TextView tvName, tvAge;
    ImageView tvPhoto;
    CardView cardView;

    public ListViewAdapter(Context context, ArrayList<Person> persons) {
        this.ctx = context;
        this.persons = persons;

        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = lInflater.inflate(R.layout.recyclerview_item, parent, false);
        }

        tvName = (TextView) view.findViewById(R.id.person_name);
        tvAge = (TextView) view.findViewById(R.id.person_age);
        tvPhoto = (ImageView) view.findViewById(R.id.person_photo);
        cardView = (CardView) view.findViewById(R.id.cv);

        Person person = getPerson(position);

        tvName.setText(person.name);
        tvAge.setText(person.age);
        tvPhoto.setImageResource(person.photoId);

        tvName.setOnClickListener(this);
        tvAge.setOnClickListener(this);
        tvPhoto.setOnClickListener(this);
        cardView.setOnClickListener(this);

        return view;
    }

    Person getPerson(int position) {
        return ((Person) getItem(position));
    }

    @Override
    public void onClick(View view) {
        if(!(view.getId()==R.id.person_photo)){
            CharSequence message;
            if(view.getId()==R.id.cv){
                message = ((TextView) view.findViewById(R.id.person_name)).getText();
            }else{
                message = ((TextView) view).getText();
            }

            Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
        }
    }

}