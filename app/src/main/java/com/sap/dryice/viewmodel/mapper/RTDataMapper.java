package com.sap.dryice.viewmodel.mapper;

import com.sap.dryice.dbEntities.RTData;
import com.sap.dryice.viewmodel.firebaseEntities.RTDataEntity;

public class RTDataMapper extends FirebaseMapper<RTDataEntity, RTData> {

    @Override
    public RTData map(RTDataEntity articleEntity) {
        RTData rtData = new RTData();
        rtData.setIdRPi(articleEntity.getIdRPi());
        rtData.setCO2(articleEntity.getCO2());
        rtData.setTemperature(articleEntity.getTemperature());
        rtData.setHumedity(articleEntity.getHumedity());
        return rtData;
    }
}
