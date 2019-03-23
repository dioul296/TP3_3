package ca.ulaval.ima.tp3;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AffichageListOffre extends AppCompatActivity {

    private Marques.OnSimpleFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    String modelename,marquename,transmission,nomVendeur,prenomVendeur,mailVendeur, descriptionOffre,dateOffre, urlimage,urlDetail, stringDetail;
    Integer prixOffre, kilometrage,anneemodele;
    JSONObject jsonDetail=null;
    JSONObject jsonModel=null;
    JSONObject jsonMarque=null;
    JSONObject jsonSeller=null;
    JSONObject jsonContent=null;

    DetailOffre DetailObjet;
    Integer idDetail;


    ListView listViewItemOffre;
    List<ItemOffre> listOffreObjet = new ArrayList<>();
    ArrayList<String> listOffrestring=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_list_offre);

        final ListView listViewItemOffre = (ListView) findViewById(R.id.listViewItemOffre);
        listOffreObjet=getIntent().getParcelableArrayListExtra("listOffre");
        for (int i=0; i<listOffreObjet.size(); i++){
            ItemOffre objetTemp=listOffreObjet.get(i);
            String infos=objetTemp.getPrixItemOffre()+" $\n"+objetTemp.getMarqueItemOffre()+"  "+objetTemp.getModeleItemOffre()+"\n"+objetTemp.getKilomItemOffre()+" KM";
            listOffrestring.add(infos);

        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOffrestring);
        listViewItemOffre.setAdapter(adapter);

        listViewItemOffre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idDetail=position+1;
                urlDetail="htt//159.203.33.206/api/v1/offer/"+idDetail.toString()+"/details/";

                Log.d("BOnjour", "Bonjour");
                OkHttpClient clientDetail=new OkHttpClient();
                Request requestDetail=new Request.Builder().url(urlDetail).build();
                clientDetail.newCall(requestDetail).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (response.isSuccessful()){
                            final String stringOffre=response.body().string();
                            try {
                                jsonDetail=new JSONObject(stringOffre);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                jsonContent=jsonDetail.getJSONObject("content");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                jsonModel=jsonContent.getJSONObject("model");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //jsonSeller=jsonContent.getJSONObject("seller");
                            try {
                                jsonMarque=jsonModel.getJSONObject("brand");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                prixOffre=jsonContent.getInt("price");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                anneemodele=jsonContent.getInt("year");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                kilometrage=jsonContent.getInt("kilometers");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                transmission=jsonContent.getString("transmission");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                marquename=jsonMarque.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                modelename=jsonModel.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                descriptionOffre=jsonContent.getString("description");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                jsonSeller=jsonContent.getJSONObject("seller");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                nomVendeur=jsonSeller.getString("last_name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                prenomVendeur=jsonSeller.getString("first_name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                mailVendeur=jsonSeller.getString("email");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                dateOffre=jsonContent.getString("created").substring(0,9);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                urlimage=jsonContent.getString("image");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            DetailObjet=new DetailOffre(modelename, marquename, transmission, nomVendeur, prenomVendeur, mailVendeur,  descriptionOffre,
                                    dateOffre,  urlimage, prixOffre,  kilometrage,  anneemodele);

                            Intent intent=new Intent(AffichageListOffre.this,SimpleOffre.class).putExtra("DetailObjet",DetailObjet);

                        }

                    }
                });


            }
        });




    }
}
