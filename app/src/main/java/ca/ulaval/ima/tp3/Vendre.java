package ca.ulaval.ima.tp3;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class Vendre extends Fragment {

    private Marques.OnSimpleFragmentInteractionListener mListener;



   String urlMarque="http://159.203.33.206/api/v1/brand/";
    String urlModele;
    String MarqueSelectionner;
    String stringIdMarque="";

    AwesomeValidation awesomeValidation;



    final ArrayList<ListMarque> listObjetMarques= new ArrayList<>();
    final ArrayList<String> listMarque=new ArrayList<>();

    final ArrayList<String> listTransmission=new ArrayList<>();
    final ArrayList<String> duPropieretaire=new ArrayList<>();


    final ArrayList<ListModele> listObjetModeles= new ArrayList<>();
    final ArrayList<Integer> listAnnee=new ArrayList<>();
    ArrayList<String> listModele=new ArrayList<>();



    EditText editTextKilom, editTextNom,  editTextPrenom, editTextMail;
    Spinner  spinnerMarque, spinnerModele;
    Button buttonTerminier;


    public Vendre() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        View view=inflater.inflate(R.layout.fragment_vendre, container, false);
        final Spinner spinnerMarque=(Spinner) view.findViewById(R.id.spinnerMarque);
        final Spinner spinnerModele=(Spinner) view.findViewById(R.id.spinnerModele);
        final Spinner spinnerAnne=(Spinner) view.findViewById(R.id.spinnerAnneeModele);
        final Spinner spinnerTransmission=(Spinner) view.findViewById(R.id.spinnerTransmission);
        final Spinner spinnerProprietaire=(Spinner) view.findViewById(R.id.spinnerProprietaire);
        final EditText editTextKilom=(EditText) view.findViewById(R.id.editKilom);
        final EditText editTextNom=(EditText) view.findViewById(R.id.editNom);
        final EditText editTextPrenom=(EditText) view.findViewById(R.id.editPrenom);
        final EditText editTextMail=(EditText) view.findViewById(R.id.editEmail);
        final ImageView imageViewVendre =(ImageView) view.findViewById(R.id.ImageVVendre);
        final Button buttonTrminer=(Button) view.findViewById(R.id.buttonTerminer);

        imageViewVendre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,);*/
            }
        });


        listTransmission.add("MA");
        listTransmission.add("AT");
        listTransmission.add("RB");
        duPropieretaire.add("OUI");
        duPropieretaire.add("FAUX");

        ArrayAdapter<String> adapterProprietaire = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, duPropieretaire);
        adapterProprietaire.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerTransmission.setAdapter(adapterProprietaire);

        ArrayAdapter<String> adapterTransmission = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listTransmission);
        adapterTransmission.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerTransmission.setAdapter(adapterTransmission);

        for(int i=0; i<60; i++){
            listAnnee.add(2019-i);
        }

        ArrayAdapter<Integer> adapterAnne = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listAnnee);
        adapterAnne.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerAnne.setAdapter(adapterAnne);

        OkHttpClient client=new OkHttpClient();
        okhttp3.Request request=new Request.Builder().url(urlMarque).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    final String stringMarque=response.body().string();
                    JSONObject jsonMarque = null;
                    try {
                        jsonMarque = new JSONObject(stringMarque);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray jsonArrayMarque = null;
                    try {
                        jsonArrayMarque = jsonMarque.getJSONArray("content");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for (int i=0; i<jsonArrayMarque.length(); i++){
                        JSONObject marque= null;
                        try {
                            marque = jsonArrayMarque.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Integer idmarque=null;
                        try {
                             idmarque=marque.getInt("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String marquename= null;
                        try {
                            marquename = marque.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listObjetMarques.add(new ListMarque(idmarque,marquename));
                        listMarque.add(marquename);

                    }

                    ArrayAdapter<String> adapterMarque = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listMarque);
                    adapterMarque.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinnerMarque.setAdapter(adapterMarque);
                    spinnerMarque.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            listModele=new ArrayList<>();

                            Integer idMarque=position+1;

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
                                            listModele.add(modelename);

                                        }


                                    }

                                }
                            });


                            ArrayAdapter<String> adapterModele = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listModele);
                            adapterModele.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerModele.setAdapter(adapterModele);
                            MarqueSelectionner=spinnerMarque.getSelectedItem().toString();

                        }



                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    MarqueSelectionner=spinnerMarque.getSelectedItem().toString();


                }

            }
        });

        buttonTrminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mbuilder=new AlertDialog.Builder(getContext());
                View mView=getLayoutInflater().inflate(R.layout.login, null);
                final EditText editTextEmail=(EditText) mView.findViewById(R.id.editEmail);
                final EditText editTextPassWord=(EditText) mView.findViewById(R.id.editTextPassword);
                Button buttonLogin=(Button) mView.findViewById(R.id.ButtonLogin);
                buttonLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!editTextEmail.getText().toString().isEmpty()&& !editTextPassWord.getText().toString().isEmpty()){
                            Toast.makeText(getContext(),"Login successfull",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(),"Please fill any empty fiels ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                    mbuilder.setView(mView);
                AlertDialog dialog=mbuilder.create();
                dialog.show();
            }
        });

        return view;
    }


    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nom, prenom, kilom;
            nom=editTextNom.getText().toString().trim();
            prenom=editTextPrenom.getText().toString().trim();
            kilom=editTextKilom.getText().toString().trim();

            buttonTerminier.setEnabled(!nom.isEmpty() && !prenom.isEmpty() && !kilom.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



}
