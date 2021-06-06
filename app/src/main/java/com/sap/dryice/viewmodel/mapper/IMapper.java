package com.sap.dryice.viewmodel.mapper;

public interface IMapper<From, To> {

    To map(From from);
}
