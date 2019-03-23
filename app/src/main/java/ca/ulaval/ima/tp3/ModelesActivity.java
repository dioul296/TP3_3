package ca.ulaval.ima.tp3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ModelesActivity extends AppCompatActivity {

    private Marques.OnSimpleFragmentInteractionListener mListener;

    JSONObject jsonContent = null;
    JSONObject jsonModel = null;
    JSONObject jsonOffre = null;
    JSONObject jsonMarque = null;
    JSONArray jsonArrayOffre = null;
    ArrayList<String> listModeles= new ArrayList<>();
    ArrayList<ItemOffre> listOffre;
    String idMarqueSelectionner, urlOffre;
    Integer IdMarque, IdModele;

    String modelename, marquename, dateOffre, urlimage, stringOffre;
    Integer prixOffre, kilometrage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modeles);
        listModeles=getIntent().getStringArrayListExtra("listModele");
        idMarqueSelectionner=getIntent().getStringExtra("idMarqueSelectionner");
        IdMarque=Integer.parseInt(idMarqueSelectionner);

        ListView listView=(ListView) findViewById(R.id.IdlisteModeles);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listModeles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IdModele=position+1;
                urlOffre="http://159.203.33.206/api/v1/offer/search/?model="+IdModele.toString()+"&brand="+IdMarque.toString();

                listOffre = new ArrayList<>();

                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(urlOffre).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            stringOffre=response.body().string();

                            try {
                                jsonContent = new JSONObject(stringOffre);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                jsonArrayOffre = jsonContent.getJSONArray("content");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            for (int i = 0; i < jsonArrayOffre.length(); i++) {
                                try {
                                    jsonOffre = jsonArrayOffre.getJSONObject(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    jsonModel = jsonOffre.getJSONObject("model");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    jsonMarque = jsonModel.getJSONObject("brand");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    prixOffre = jsonOffre.getInt("price");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    marquename = jsonMarque.getString("name");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    modelename = jsonModel.getString("name");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    dateOffre = jsonOffre.getString("created");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    kilometrage = jsonOffre.getInt("kilometers");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    urlimage=jsonOffre.getString("image");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                listOffre.add(new ItemOffre(urlimage,prixOffre,marquename,modelename,kilometrage,dateOffre));
                                Log.d("Bonjour",listOffre.get(i).getMarqueItemOffre());
                            }

                            Intent intent=new Intent(ModelesActivity.this,AffichageListOffre.class).putExtra("listOffre",listOffre);
                            startActivity(intent);
                        }


                    }
                });
            }



        });

    }
}
