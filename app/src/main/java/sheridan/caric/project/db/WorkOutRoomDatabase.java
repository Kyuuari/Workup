package sheridan.caric.project.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities ={Pushup.class,Run.class},version = 1,exportSchema = false)
public abstract class WorkOutRoomDatabase extends RoomDatabase {
    public abstract PushupDao pushupDao();
    public abstract RunDao runDao();
    private static WorkOutRoomDatabase INSTANCE;

    public static WorkOutRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WorkOutRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WorkOutRoomDatabase.class, "workout_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };



    //Inner Class
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {


        private final PushupDao pushupDao;
        private final RunDao runDao;
        long[] pushes = {25,30,40,10};
        long[] runs = {25,43,10,12};

        PopulateDbAsync(WorkOutRoomDatabase db) {
            pushupDao = db.pushupDao();
            runDao = db.runDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            pushupDao.deleteAllPushups();
            runDao.deleteAllRuns();

            for (int i = 0; i <= runs.length - 1; i++) {
                Run run = new Run(runs[i]);
                runDao.insert(run);
            }

            for (int i = 0; i <= pushes.length - 1; i++) {
                Pushup pushup = new Pushup(pushes[i]);
                pushupDao.insert(pushup);
//                Log.d("PushupID", "insert: " + pushup.getId());
            }
            return null;
        }
    }
}
