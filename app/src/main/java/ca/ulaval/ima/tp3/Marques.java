package ca.ulaval.ima.tp3;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassification;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Marques extends Fragment {

    private OnSimpleFragmentInteractionListener mListener;

    String url = "http://159.203.33.206/api/v1/brand/";
    Integer idMarque;
   String stringIdMarque, urlModele;


   ArrayList<String> listMarques= new ArrayList<>();
   ArrayList<String> listModeles= new ArrayList<>();


    public Marques() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_marques, container, false);
        final ArrayList<ListMarque> listObjetMarque= new ArrayList<>();
        //final ArrayList<String> listMarques=new ArrayList<>();
        final ArrayList<ListModele> listObjetModeles= new ArrayList<>();




        ListView listView=(ListView) view.findViewById(R.id.IdlisteMarques);

        OkHttpClient client=new OkHttpClient();

        okhttp3.Request request=new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.isSuccessful()){
                    final String stringMarque=response.body().string();
                    JSONObject jsonMarque = null;
                    try {
                        jsonMarque = new JSONObject(stringMarque);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = jsonMarque.getJSONArray("content");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject marque= null;
                        try {
                            marque = jsonArray.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            Integer idmarque=marque.getInt("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String marquename= null;
                        try {
                            marquename = marque.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listObjetMarque.add(new ListMarque(idMarque,marquename));
                        listMarques.add(marquename);

                    }


                }

            }
        });




        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listMarques);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                listModeles=new ArrayList<>();
                Integer idMarqueSelectionnner=position+1;
                stringIdMarque=idMarqueSelectionnner.toString();
                urlModele="http://159.203.33.206/api/v1/brand/"+Integer.toString(position+1)+"/models/";

                OkHttpClient clientModele=new OkHttpClient();

                okhttp3.Request requestModele=new Request.Builder().url(urlModele).build();

                clientModele.newCall(requestModele).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (response.isSuccessful()){
                            final String stringModele=response.body().string();
                            JSONObject jsonModele = null;
                            try {
                                jsonModele = new JSONObject(stringModele);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            JSONArray jsonArrayModele = null;
                            try {
                                jsonArrayModele = jsonModele.getJSONArray("content");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            for (int i=0; i<jsonArrayModele.length(); i++){
                                JSONObject modele= null;
                                try {
                                    modele = jsonArrayModele.getJSONObject(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Integer idmodele=null;
                                try {
                                    idmodele=modele.getInt("id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String modelename= null;
                                try {
                                    modelename = modele.getString("name");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                listObjetModeles.add(new ListModele(idmodele,modelename));
                                listModeles.add(modelename);


                            }

                            mListener.OpenActivity(listModeles, position+1);
                        }

                    }
                });



            }
        });




        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSimpleFragmentInteractionListener) {
            mListener = (OnSimpleFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSimpleFragmentInteractionListener {
        void OpenActivity(ArrayList<String> mlistModele, int midMarque);


    }



}
