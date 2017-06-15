package com.example.ravi.listpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.ravi.listpractice.R.id.lv_items;

public class MainActivity extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener{

    ListView listView;
    List<Person> personArrayList = new ArrayList();
    private static final String Root_url="https://api.myjson.com";
    CustomAdapter customAdapter;
    android.widget.SearchView mSearchView;
    Button mSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(lv_items);
        mSubmit= (Button) findViewById(R.id.button_submit);
        insertUser();
        mSearchView= (android.widget.SearchView) findViewById(R.id.searchView1);
        setupSearchView();


    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");



    }

    private void insertUser() {

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Root_url).build();

        Apiservice  api = adapter.create(Apiservice.class);
                api.apiDetails(new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject jsonObject, Response response) {

                          try {

                              JsonObject jsonObject1 = jsonObject.getAsJsonObject();
                              JsonArray jsonArray = jsonObject1.get("contacts").getAsJsonArray();

                              for (int i = 0; i < jsonArray.size(); i++) {


                                  JsonObject jsonObject2 = jsonArray.get(i).getAsJsonObject();

                                  String ids = jsonObject2.get("id").getAsString();
                                  String names = jsonObject2.get("name").getAsString();
                                  String emails = jsonObject2.get("email").getAsString();
                                  String addresss = jsonObject2.get("address").getAsString();
                                  String genders = jsonObject2.get("gender").getAsString();

                                  Person person = new Person();
                                  person.setchecked(false);
                                  person.setId(ids);
                                  person.setName(names);
                                  person.setEmail(emails);
                                  person.setAddress(addresss);
                                  person.setGender(genders);
                                  personArrayList.add(person);
                              }


                          } catch (JsonIOException  e){
                              e.printStackTrace();
                          }




                        customAdapter=new CustomAdapter(getApplicationContext(),personArrayList,mSubmit);
                        listView.setAdapter(customAdapter);









                    }




                    @Override
                    public void failure(RetrofitError error) {

                             showToast("Retrofit error"+error.toString());

                    }
                });


    }





    public void showToast(String message){
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText.toString())) {
            listView.clearTextFilter();
        } else {
            listView.setFilterText(newText.toString());
        }
        return true;
    }
}
