package com.sap.dryice.viewmodel.mapper;

import com.sap.dryice.dbEntities.RTData;
import com.sap.dryice.dbEntities.RTExtendedData;
import com.sap.dryice.viewmodel.firebaseEntities.RTDataEntity;
import com.sap.dryice.viewmodel.firebaseEntities.RTExtendedDataEntity;

public class RTExtendedDataMapper extends FirebaseMapper<RTExtendedDataEntity, RTExtendedData> {

    @Override
    public RTExtendedData map(RTExtendedDataEntity articleEntity) {
        RTExtendedData rtExtData = new RTExtendedData();
        rtExtData.setIdRPi(articleEntity.getIdRPi());
        rtExtData.setMaxCO2(articleEntity.getMaxCO2());
        rtExtData.setMaxTemperature(articleEntity.getMaxTemperature());
        rtExtData.setMaxRelHumedity(articleEntity.getMaxHumedity());
        rtExtData.setMinCO2(articleEntity.getMinCO2());
        rtExtData.setMinTemperature(articleEntity.getMinTemperature());
        rtExtData.setMinRelHumedity(articleEntity.getMinHumedity());
        return rtExtData;
    }
}
