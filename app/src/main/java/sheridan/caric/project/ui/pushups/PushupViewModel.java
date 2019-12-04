package sheridan.caric.project.ui.pushups;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import sheridan.caric.project.WorkOutRepository;
import sheridan.caric.project.db.Pushup;

public class PushupViewModel extends AndroidViewModel {

    private LiveData<List<Pushup>> mAllPushups;
    private WorkOutRepository mRepository;

    public PushupViewModel(Application application) {
        super(application);
        mRepository = new WorkOutRepository(application);
        mAllPushups = mRepository.getAllPushups();
    }

    public LiveData<List<Pushup>> getAllPushups() { return mAllPushups; }
    public void insert(Pushup pushup){
        mRepository.insert(pushup);
    }
    public void edit(Pushup pushup){}
    public void deleteAll(){mRepository.deleteAllPushups();}
    public void deleteById(Pushup pushup){mRepository.deletePushup(pushup);}

}