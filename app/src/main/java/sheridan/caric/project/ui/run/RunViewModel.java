package sheridan.caric.project.ui.run;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import sheridan.caric.project.WorkOutRepository;
import sheridan.caric.project.db.Pushup;
import sheridan.caric.project.db.Run;

public class RunViewModel extends AndroidViewModel{

    private LiveData<List<Run>> mAllRuns;
    private WorkOutRepository mRepository;

    public RunViewModel(Application application) {
        super(application);
        mRepository = new WorkOutRepository(application);
        mAllRuns = mRepository.getAllRuns();
    }

    public LiveData<List<Run>> getAllRuns(){return mAllRuns;}
    public void insert(Run run){mRepository.insertRun(run);}
    public void deleteAll(){mRepository.deleteAllRuns();}
    public void deleteByRun(Run run){mRepository.deleteRun(run);}
    public void update(Run run){mRepository.updateRun(run);}
}