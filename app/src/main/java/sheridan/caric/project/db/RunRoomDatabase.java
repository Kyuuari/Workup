package sheridan.caric.project.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities ={Run.class},version = 1,exportSchema = false)
public abstract class RunRoomDatabase extends RoomDatabase {
    public abstract RunDao runDao2();
//    private static RunRoomDatabase INSTANCE2;
//
//    public static RunRoomDatabase getDatabase(final Context context) {
//        if (INSTANCE2 == null) {
//            synchronized (RunRoomDatabase.class) {
//                if (INSTANCE2 == null) {
//                    // Create database here
//                    INSTANCE2 = Room.databaseBuilder(context.getApplicationContext(),
//                            RunRoomDatabase.class, "run_database")
//                            // Wipes and rebuilds instead of migrating
//                            // if no Migration object.
//                            // Migration is not part of this practical.
//                            .fallbackToDestructiveMigration()
//                            .addCallback(sRoomDatabaseCallback)
//                            .build();
//                }
//            }
//        }
//        return INSTANCE2;
//    }
//
//
//    private static RoomDatabase.Callback sRoomDatabaseCallback =
//            new RoomDatabase.Callback(){
//
//                @Override
//                public void onOpen (@NonNull SupportSQLiteDatabase db){
//                    super.onOpen(db);
//                    new PopulateDbAsync2(INSTANCE2).execute();
//                }
//            };
//
//
//
//    //Inner Class
//    private static class PopulateDbAsync2 extends AsyncTask<Void, Void, Void> {
//
//        private final RunDao runDao;
////        private final PushupDao pushupDao;
//        long[] pushes = {25,30,40,10,50,20,10,15,12,5135,4123,12,443,21};
//
//        PopulateDbAsync2(RoomDatabase db) {
////            pushupDao = db.pushupDao();
//            runDao = db.runDao2();
//        }
//
//        @Override
//        protected Void doInBackground(final Void... params) {
//            // Start the app with a clean database every time.
//            // Not needed if you only populate the database
//            // when it is first created
//            runDao.deleteAllRuns();
//
//            for (int i = 0; i <= pushes.length - 1; i++) {
////                Pushup pushup = new Pushup(pushes[i]);
//                Run run = new Run(pushes[i]);
//                runDao.insert(run);
//            }
//            return null;
//        }
//    }
}
