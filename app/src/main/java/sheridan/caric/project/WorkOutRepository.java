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
    public void deleteAllPushups(){new DeleteAllPushupsAsyncTask(mPushupDao).execute();}
    public void deletePushup(Pushup pushup){new DeletePushupAsyncTask(mPushupDao).execute(pushup);}

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

    private static class DeleteAllPushupsAsyncTask extends AsyncTask<Void,Void,Void>{
        private PushupDao pushupDao;

        DeleteAllPushupsAsyncTask(PushupDao pushupDao){
            this.pushupDao = pushupDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pushupDao.deleteAllPushups();
            return null;
        }
    }

    private static class DeletePushupAsyncTask extends AsyncTask<Pushup,Void,Void>{
        private PushupDao pushupDao;

        private DeletePushupAsyncTask(PushupDao pushupDao){
            this.pushupDao = pushupDao;
        }

        @Override
        protected Void doInBackground(Pushup... pushups) {
            pushupDao.deletePushup(pushups[0]);
            return null;
        }
    }
}
