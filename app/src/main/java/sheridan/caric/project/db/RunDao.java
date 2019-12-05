package sheridan.caric.project.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RunDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Run run);

    @Delete
    void deleteRun(Run run);

    @Update
    void updateRun(Run run);

    @Query("DELETE FROM run_table WHERE id = :id")
    void deleteRunById(long id);

    @Query("SELECT * from run_table ORDER BY id ASC")
    LiveData<List<Run>> getAllRuns();

    @Query("DELETE FROM run_table")
    void deleteAllRuns();
}
