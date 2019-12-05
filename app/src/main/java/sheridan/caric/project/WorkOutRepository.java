package sheridan.caric.project;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Update;
import sheridan.caric.project.db.Pushup;
import sheridan.caric.project.db.PushupDao;
import sheridan.caric.project.db.Run;
import sheridan.caric.project.db.RunDao;
import sheridan.caric.project.db.WorkOutRoomDatabase;

public class WorkOutRepository {
    private PushupDao mPushupDao;
    private RunDao mRunDao;
    private LiveData<List<Pushup>> mAllPushups;
    private LiveData<List<Run>> mAllRuns;

    public WorkOutRepository(Application application) {
        WorkOutRoomDatabase db = WorkOutRoomDatabase.getDatabase(application);
        mPushupDao = db.pushupDao();
        mRunDao = db.runDao();
        mAllPushups = mPushupDao.getAllPushups();
        mAllRuns = mRunDao.getAllRuns();
    }

    //Wrappers
    public LiveData<List<Pushup>> getAllPushups() {
        return mAllPushups;
    }

    public LiveData<List<Run>> getAllRuns() {
        return mAllRuns;
    }

    public void insertPushup(Pushup pushup) {
        new insertPushupAsyncTask(mPushupDao).execute(pushup);
    }

    public void insertRun(Run run) {
        new insertRunAsyncTask(mRunDao).execute(run);
    }

    public void deleteAllPushups() {
        new DeleteAllPushupsAsyncTask(mPushupDao).execute();
    }

    public void deleteAllRuns() {
        new DeleteAllRunsAsyncTask(mRunDao).execute();
    }

    public void deletePushup(Pushup pushup) {
        new DeletePushupAsyncTask(mPushupDao).execute(pushup);
    }

    public void deleteRun(Run run){
        new DeleteRunAsyncTask(mRunDao).execute(run);
    }

    public void updatePushup(Pushup pushup) {
        new UpdatePushupAsyncTask(mPushupDao).execute(pushup);
    }

    public void updateRun(Run run){
        new UpdateRunAsyncTask(mRunDao).execute(run);
    }

    //Inner Class
    private static class insertPushupAsyncTask extends AsyncTask<Pushup, Void, Void> {

        private PushupDao mAsyncTaskDao;

        insertPushupAsyncTask(PushupDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Pushup... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAllPushupsAsyncTask extends AsyncTask<Void, Void, Void> {
        private PushupDao pushupDao;

        DeleteAllPushupsAsyncTask(PushupDao pushupDao) {
            this.pushupDao = pushupDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pushupDao.deleteAllPushups();
            return null;
        }
    }

    private static class DeletePushupAsyncTask extends AsyncTask<Pushup, Void, Void> {
        private PushupDao pushupDao;

        private DeletePushupAsyncTask(PushupDao pushupDao) {
            this.pushupDao = pushupDao;
        }

        @Override
        protected Void doInBackground(final Pushup... params) {
            pushupDao.deletePushup(params[0]);
            return null;
        }
    }

    private static class UpdatePushupAsyncTask extends AsyncTask<Pushup, Void, Void> {
        private PushupDao pushupDao;

        private UpdatePushupAsyncTask(PushupDao pushupDao) {
            this.pushupDao = pushupDao;
        }

        @Override
        protected Void doInBackground(final Pushup... params) {
            pushupDao.updatePushup(params[0]);
            return null;
        }
    }


    //Run
    private static class insertRunAsyncTask extends AsyncTask<Run, Void, Void> {

        private RunDao mAsyncTaskDao;

        insertRunAsyncTask(RunDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Run... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAllRunsAsyncTask extends AsyncTask<Void, Void, Void> {
        private RunDao runDao;

        DeleteAllRunsAsyncTask(RunDao runDao) {
            this.runDao = runDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            runDao.deleteAllRuns();
            return null;
        }
    }

    private static class DeleteRunAsyncTask extends AsyncTask<Run, Void, Void> {
        private RunDao runDao;

        private DeleteRunAsyncTask(RunDao runDao) {
            this.runDao = runDao;
        }

        @Override
        protected Void doInBackground(final Run... params) {
            runDao.deleteRun(params[0]);
            return null;
        }
    }

    private static class UpdateRunAsyncTask extends AsyncTask<Run, Void, Void> {
        private RunDao runDao;

        private UpdateRunAsyncTask(RunDao runDao) {
            this.runDao = runDao;
        }

        @Override
        protected Void doInBackground(final Run... params) {
            runDao.updateRun(params[0]);
            return null;
        }
    }
}
