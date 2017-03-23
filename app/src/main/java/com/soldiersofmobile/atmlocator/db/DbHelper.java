package com.soldiersofmobile.atmlocator.db;

import java.sql.SQLException;
import java.util.UUID;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DbHelper extends OrmLiteSqliteOpenHelper {

    public DbHelper(
            final Context context
    ) {
        super(context, "atm.db", null, 1);
    }

    @Override
    public void onCreate(
            final SQLiteDatabase database,
            final ConnectionSource connectionSource
    ) {
        try {
            TableUtils.createTable(connectionSource, Bank.class);
            TableUtils.createTable(connectionSource, Atm.class);
            Dao<Bank, UUID> bankDao = getDao(Bank.class);
            bankDao.create(new Bank("ING Bank", "500 600 700"));
            bankDao.create(new Bank("Pekao SA", "500 600 701"));
            bankDao.create(new Bank("PKO BP", "500 600 702"));
            bankDao.create(new Bank("Millenium", "500 600 703"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(
            final SQLiteDatabase database,
            final ConnectionSource connectionSource,
            final int oldVersion,
            final int newVersion
    ) {

    }
}
