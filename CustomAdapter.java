package com.example.ravi.listpractice;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter  extends BaseAdapter
{
    List<Person> personArrayList;
    Context Mcontext;
    LayoutInflater layoutInflater;
    Button msubmit;
    Person  person;


    public CustomAdapter(Context Mcontext, List<Person> personArrayList,Button msubmit){
         this.Mcontext=Mcontext;
        this.personArrayList=personArrayList;
        this.msubmit=msubmit;

    }

    @Override
    public int getCount() {
        return personArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return personArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) Mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null){
            view =layoutInflater.inflate(R.layout.inflator_row_item,null);
        }


        Holder holder = new Holder();
        holder.id = (TextView) view.findViewById(R.id.id);
        holder.name = (TextView) view.findViewById(R.id.name);
        holder.email = (TextView) view.findViewById(R.id.email);
        holder.address = (TextView) view.findViewById(R.id.address);
        holder.gender = (TextView) view.findViewById(R.id.gender);
        holder.mCheckBox= (CheckBox) view.findViewById(R.id.cbx);


        person = personArrayList.get(i);
        holder.id.setText(person.getId());
        holder.name.setText(person.getName());
        holder.email.setText(person.getEmail());
        holder.address.setText(person.getAddress());
        holder.gender.setText(person.getGender());
        holder.mCheckBox.setChecked(person.getchecked());


        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                person.setchecked(isChecked);


            }
        });


       msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            List<Person> personList=new ArrayList<Person>();
                for (int i1 = 0; i1 < personArrayList.size(); i1++) {

                  if(person.getchecked()){
                      personList.add(personArrayList.get(i1));
                  }


                }

                Intent intent=new Intent(Mcontext,SecondActivity.class);
                intent.putExtra("selectedValues", (Serializable) personList);
                Mcontext.startActivity(intent);

            }
        });




        return view;
    }



    private class Holder {
        private TextView id ,name,email,address,gender ;
        private CheckBox mCheckBox;

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
}
