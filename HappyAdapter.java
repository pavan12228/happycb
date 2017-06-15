package com.example.ravi.listpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rajasekharsanikommu on 15/06/17.
 */

public class HappyAdapter extends BaseAdapter
{

    Context mContext;
    List<Person> personList;
    LayoutInflater mLayoutInflater;
    Person person;
    public HappyAdapter(Context  mContext, List<Person> personList){
        this.mContext=mContext;
        this.personList=personList;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (mLayoutInflater==null){
            mLayoutInflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null){
           convertView= mLayoutInflater.inflate(R.layout.inflator_row_item,null);
        }




        Holder holder = new Holder();
        holder.id = (TextView) convertView.findViewById(R.id.id);
        holder.name = (TextView) convertView.findViewById(R.id.name);
        holder.email = (TextView) convertView.findViewById(R.id.email);
        holder.address = (TextView) convertView.findViewById(R.id.address);
        holder.gender = (TextView) convertView.findViewById(R.id.gender);
        holder.mCheckBox= (CheckBox) convertView.findViewById(R.id.cbx);
      //  holder.msubmitBtn= (Button) convertView.findViewById(R.id.button_submit);

        person = personList.get(position);
        holder.id.setText(person.getId());
        holder.name.setText(person.getName());
        holder.email.setText(person.getEmail());
        holder.address.setText(person.getAddress());
        holder.gender.setText(person.getGender());
        holder.mCheckBox.setChecked(person.getchecked());


//        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                person.setchecked(isChecked);
//
//
//            }
//        });






        return convertView;
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
