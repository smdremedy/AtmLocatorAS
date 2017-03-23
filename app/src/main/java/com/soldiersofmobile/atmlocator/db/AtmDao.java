package com.soldiersofmobile.atmlocator.db;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class AtmDao extends BaseDaoImpl<Atm, UUID> {

    public AtmDao() throws SQLException {
        super(Atm.class);
    }

    public List<Atm> queryByName(String name) throws SQLException {
        QueryBuilder<Atm, UUID> builder = atmDao.queryBuilder();
        builder.where().like("name", name);
        PreparedQuery<Atm> prepare = builder.prepare();
        return query(prepare);
    }
}
