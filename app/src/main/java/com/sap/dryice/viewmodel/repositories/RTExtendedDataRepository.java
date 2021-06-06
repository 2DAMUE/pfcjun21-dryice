package com.sap.dryice.viewmodel.repositories;

import com.sap.dryice.dbEntities.RTData;
import com.sap.dryice.dbEntities.RTExtendedData;
import com.sap.dryice.viewmodel.FirebaseDatabaseRepository;
import com.sap.dryice.viewmodel.mapper.RTDataMapper;
import com.sap.dryice.viewmodel.mapper.RTExtendedDataMapper;

public class RTExtendedDataRepository extends FirebaseDatabaseRepository<RTExtendedData> {

    public RTExtendedDataRepository() {
        super(new RTExtendedDataMapper());
    }

    @Override
    protected String getRootNode() {
        return "RTExtendedData";
    }
}
