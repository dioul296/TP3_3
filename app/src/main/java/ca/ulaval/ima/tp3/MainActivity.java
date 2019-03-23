package ca.ulaval.ima.tp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Marques.OnSimpleFragmentInteractionListener {
    private static final String TAG = "MainActivity";

    //private Integer idItemSelect;
    //private String idMarque;

    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Oncreate:starting");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Marques(), "OFFRES");
        adapter.addFragment(new Vendre(), "VENDRE");
        adapter.addFragment(new Mes_annonces(), "MES ANNONCES");
        viewPager.setAdapter(adapter);


    }


    @Override
    public void OpenActivity(ArrayList<String> mlistModele, int midMarque) {

        ArrayList<String> listModele = mlistModele;
        String idMarqueSelectionner = String.valueOf(midMarque);
        Intent modeleActivity = new Intent(this, ModelesActivity.class);
        modeleActivity.putExtra("listModele", listModele);
        modeleActivity.putExtra("idMarqueSelectionner", idMarqueSelectionner);
        startActivity(modeleActivity);


    }
}
