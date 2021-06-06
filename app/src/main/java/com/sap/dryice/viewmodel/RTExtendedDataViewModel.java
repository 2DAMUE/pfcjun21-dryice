package com.sap.dryice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sap.dryice.dbEntities.RTData;
import com.sap.dryice.dbEntities.RTExtendedData;
import com.sap.dryice.viewmodel.repositories.RTDataRepository;
import com.sap.dryice.viewmodel.repositories.RTExtendedDataRepository;

import java.util.List;

public class RTExtendedDataViewModel extends ViewModel {

    private MutableLiveData<List<RTExtendedData>> articles;
    private RTExtendedDataRepository repository = new RTExtendedDataRepository();

    public LiveData<List<RTExtendedData>> getArticles() {
        if (articles == null) {
            articles = new MutableLiveData<>();
            loadArticles();
        }
        return articles;
    }

    @Override
    protected void onCleared() {
        repository.removeListener();
    }

    private void loadArticles() {
        repository.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<RTExtendedData>() {
            @Override
            public void onSuccess(List<RTExtendedData> result) {
                articles.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                articles.setValue(null);
            }
        });
    }
}
