package sheridan.caric.project.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities ={Pushup.class},version = 1,exportSchema = false)
public abstract class WorkOutRoomDatabase extends RoomDatabase {
    public abstract PushupDao pushupDao();
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

        private final PushupDao mDao;
        long[] pushes = {25,30,40,10,50,20,10,15,12,5135,4123,12,443,21};

        PopulateDbAsync(WorkOutRoomDatabase db) {
            mDao = db.pushupDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for (int i = 0; i <= pushes.length - 1; i++) {
                Pushup pushup = new Pushup(pushes[i]);
                mDao.insert(pushup);
            }
            return null;
        }
    }
}
