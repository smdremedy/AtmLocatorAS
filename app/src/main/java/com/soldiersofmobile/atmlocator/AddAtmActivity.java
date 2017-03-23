package com.soldiersofmobile.atmlocator;

import java.sql.SQLException;
import java.util.UUID;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.j256.ormlite.dao.Dao;
import com.soldiersofmobile.atmlocator.databinding.ActivityAddAtmBinding;
import com.soldiersofmobile.atmlocator.db.Atm;
import com.soldiersofmobile.atmlocator.db.Bank;
import com.soldiersofmobile.atmlocator.db.DbHelper;

public class AddAtmActivity extends AppCompatActivity implements DialogInterface.OnCancelListener, AddAtmListener {

    public static final int REQUEST_CODE = 123;
    private ActivityAddAtmBinding binding;
    private AtmViewModel atmViewModel;
    private ArrayAdapter<Bank> adapter;
    private DbHelper dbHelper;
    private Dao<Atm, UUID> atmDao;
    private Dao<Bank, UUID> bankDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_atm);
        atmViewModel = new AtmViewModel();
        binding.setAtm(atmViewModel);
        binding.setListener(this);
        adapter = new ArrayAdapter<Bank>(this, android.R.layout.simple_list_item_1);
        binding.banks.setAdapter(adapter);
        dbHelper = new DbHelper(this);
        try {
            bankDao = dbHelper.getDao(Bank.class);
            atmDao = dbHelper.getDao(Atm.class);
            adapter.addAll(bankDao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data
    ) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Place place = PlacePicker.getPlace(this, data);
            //place.

            atmViewModel.setAddress(place.getAddress().toString());
            atmViewModel.setLatitude(place.getLatLng().latitude);
            atmViewModel.setLongitude(place.getLatLng().longitude);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCancel(final DialogInterface dialog) {

    }

    @Override
    public void pickAddress() {
        pick(null);
    }

    @Override
    public void save() {

        Atm atm = new Atm();
        atm.setBank((Bank) binding.banks.getSelectedItem());
        atm.setAddress(atmViewModel.getAddress());
        atm.setLatitude(atmViewModel.getLatitude());
        atm.setLongitude(atmViewModel.getLongitude());

        try {
            atmDao.create(atm);
            setResult(RESULT_OK);
        } catch (SQLException e) {
            e.printStackTrace();
            //setResult(RESULT_ERROR);
        }
        finish();
    }

    @BindingAdapter("android:visibility")
    public static void bindVisibility(
            View view,
            boolean visible
    ) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void pick(View view) {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            Intent intent = builder.build(this);
            startActivityForResult(intent, REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            GooglePlayServicesUtil
                    .showErrorDialogFragment(
                            e.getConnectionStatusCode(),
                            this,
                            null,
                            0,
                            this
                    );
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }
}
