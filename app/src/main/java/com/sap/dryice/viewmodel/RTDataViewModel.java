package com.sap.dryice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sap.dryice.dbEntities.RTData;
import com.sap.dryice.viewmodel.repositories.RTDataRepository;

import java.util.List;

public class RTDataViewModel extends ViewModel {

    private MutableLiveData<List<RTData>> articles;
    private RTDataRepository repository = new RTDataRepository();

    public LiveData<List<RTData>> getArticles() {
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
        repository.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<RTData>() {
            @Override
            public void onSuccess(List<RTData> result) {
                articles.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                articles.setValue(null);
            }
        });
    }
}
