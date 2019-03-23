package ca.ulaval.ima.tp3;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */


public class ListeOffres extends Fragment {


    JSONObject jsonContent = null;
    JSONObject jsonModel = null;
    JSONObject jsonOffre = null;
    JSONObject jsonMarque = null;
    JSONArray jsonArrayOffre = null;
    final String url = "http://159.203.33.206/api/v1/offer/";
    String modelename, marquename, dateOffre, urlimage, stringOffre;
    Integer prixOffre, kilometrage;

    ArrayList<ItemOffre> arrayListItemOffre;
    ListView listViewItemOffre;


    public ListeOffres() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_liste_offres, container, false);

        final ListView listViewItemOffre = (ListView) view.findViewById(R.id.listViewItemOffre);
        /*final ArrayList<ItemOffre> arrayListItemOffre = new ArrayList<>();

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();

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

                        arrayListItemOffre.add(new ItemOffre(urlimage,prixOffre,marquename,modelename,kilometrage,dateOffre));
                    }

                    ListOffreAdapter adapterOffre=new ListOffreAdapter(
                            getContext(),R.layout.list_item_offre, arrayListItemOffre);
                    listViewItemOffre.setAdapter(adapterOffre);
                }

            }
        });*/



        return view;
    }






}
