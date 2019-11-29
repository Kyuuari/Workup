package sheridan.caric.project;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import sheridan.caric.project.db.Pushup;
import sheridan.caric.project.db.PushupDao;
import sheridan.caric.project.db.WorkOutRoomDatabase;

public class WorkOutRepository {
    private PushupDao mPushupDao;
    private LiveData<List<Pushup>> mAllPushups;

    public WorkOutRepository(Application application) {
        WorkOutRoomDatabase db = WorkOutRoomDatabase.getDatabase(application);
        mPushupDao = db.pushupDao();
        mAllPushups = mPushupDao.getAllPushups();
    }

    //Wrappers
    public LiveData<List<Pushup>> getAllPushups() {
        return mAllPushups;
    }

    public void insert (Pushup pushup) {
        new insertAsyncTask(mPushupDao).execute(pushup);
    }

    //Inner Class
    private static class insertAsyncTask extends AsyncTask<Pushup, Void, Void> {

        private PushupDao mAsyncTaskDao;

        insertAsyncTask(PushupDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Pushup... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
