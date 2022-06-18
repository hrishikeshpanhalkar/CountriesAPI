package com.example.countries;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private ArrayList<MainModel> arrayList;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
        dialog.show();

        recyclerView = findViewById(R.id.recyclerview);
        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mainAdapter = new MainAdapter(MainActivity.this, arrayList);
        recyclerView.setAdapter(mainAdapter);

        getData();

    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        MainInterface mainInterface = retrofit.create(MainInterface.class);
        Call<String> stringCall = mainInterface.STRING_CALL();

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dialog.dismiss();
                    try {
                        JSONArray jsonArray = new JSONArray(response.body());
                        parseArray(jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void parseArray(JSONArray jsonArray) {
        try{
            for(int i=0; i<jsonArray.length(); i++){
                MainModel mainModel = new MainModel();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject jsonObject1 = jsonObject.getJSONObject("name");
                mainModel.setCountryName(jsonObject1.getString("common"));
                if(jsonObject.has("capital")){
                    mainModel.setCapital(jsonObject.getJSONArray("capital"));
                }
                mainModel.setRegion(jsonObject.getString("region"));
                if(jsonObject.has("subregion")){
                    mainModel.setSubRegion(jsonObject.getString("subregion"));
                }
                if(jsonObject.has("languages")){
                    mainModel.setLanguages(jsonObject.getJSONObject("languages"));
                }
                if(jsonObject.has("borders")){
                    mainModel.setBorders(jsonObject.getJSONArray("borders"));
                }
                mainModel.setPopulation(jsonObject.getString("population"));
                JSONObject jsonObject2 = jsonObject.getJSONObject("flags");
                mainModel.setFlagUrl(jsonObject2.getString("png"));
                arrayList.add(mainModel);
            }
            mainAdapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}