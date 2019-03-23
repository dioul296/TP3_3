package ca.ulaval.ima.tp3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleOffre extends AppCompatActivity {

    private TextView mTextMessage;
    String modelename,marquename,transmission,nomVendeur,prenomVendeur,mailVendeur, descriptionOffre,dateOffre, urlimage;
    Integer prixOffre, kilometrage,anneemodele;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("Appeler");
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText("Message");
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText("Sauvegarder");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_offre);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        final TextView txtPrixSimpleOffre=(TextView)findViewById(R.id.txtVPrix);
        final TextView txtMarqueSimpleOffre=(TextView)findViewById(R.id.txtMarqueDetail);
        final TextView txtDateSimpleOffre=(TextView)findViewById(R.id.txtVDate);
        final TextView txtKilomSimpleOffre=(TextView)findViewById(R.id.txtKilomDetail);
        final TextView txtTransSimpleOffre=(TextView)findViewById(R.id.txtTransmissionDetail);
        final TextView txtModeleSimpleOffre=(TextView)findViewById(R.id.txtModeleDetail);
        final TextView txtAnneeModeleSimpleOffre=(TextView)findViewById(R.id.txtAnneeDetail);
        final TextView txtNomVSimpleOffre=(TextView)findViewById(R.id.txtNomVD);
        final TextView txtPrenomVSimpleOffre=(TextView)findViewById(R.id.txtPreomVD);
        final TextView txtMailVSimpleOffre=(TextView)findViewById(R.id.txtMailVD);
        final TextView txtDescriptionVSimpleOffre=(TextView)findViewById(R.id.txtDescriptionDetail);
        final ImageView imageView=(ImageView) findViewById(R.id.imageVOffreSimple);

        /*DetailOffre detailOffre=getIntent().getExtras().getParcelable("DetailObjet");

        txtPrixSimpleOffre.setText(detailOffre.getPrixOffre().toString());
        txtAnneeModeleSimpleOffre.setText(detailOffre.getAnneemodele().toString());
        txtKilomSimpleOffre.setText(detailOffre.getKilometrage().toString());
        txtTransSimpleOffre.setText(detailOffre.getTransmission());
        txtMarqueSimpleOffre.setText(detailOffre.getMarquename());
        txtModeleSimpleOffre.setText(detailOffre.getModelename());
        txtDescriptionVSimpleOffre.setText(detailOffre.getDescriptionOffre());
        txtNomVSimpleOffre.setText(detailOffre.getNomVendeur());
        txtPrenomVSimpleOffre.setText(detailOffre.getPrenomVendeur());
        txtMailVSimpleOffre.setText(detailOffre.getMailVendeur());
        txtDateSimpleOffre.setText(detailOffre.getDateOffre());
        urlimage=detailOffre.getUrlimage();
        Picasso.with(this).load(urlimage).resize(400,198).into(imageView);
        Log.d("Bonjour","Bonjour");*/

    }

}
