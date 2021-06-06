package com.sap.dryice.viewmodel.repositories;

import com.sap.dryice.dbEntities.RTData;
import com.sap.dryice.viewmodel.FirebaseDatabaseRepository;
import com.sap.dryice.viewmodel.mapper.RTDataMapper;

public class RTDataRepository extends FirebaseDatabaseRepository<RTData> {

    public RTDataRepository() {
        super(new RTDataMapper());
    }

    @Override
    protected String getRootNode() {
        return "RTData";
    }
}
