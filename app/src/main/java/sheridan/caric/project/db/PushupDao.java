package sheridan.caric.project.db;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PushupDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Pushup pushup);

    @Query("DELETE FROM pushup_table")
    void deleteAll();

    @Query("SELECT * from pushup_table ORDER BY id ASC")
    LiveData<List<Pushup>> getAllPushups();
}
