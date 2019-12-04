package sheridan.caric.project.db;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PushupDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pushup pushup);

    @Delete
    void deletePushup(Pushup pushup);

    @Query("DELETE FROM pushup_table WHERE id = :id")
    void deletePushupById(long id);

    @Query("SELECT * from pushup_table ORDER BY id ASC")
    LiveData<List<Pushup>> getAllPushups();

    @Query("DELETE FROM pushup_table")
    void deleteAllPushups();
}
