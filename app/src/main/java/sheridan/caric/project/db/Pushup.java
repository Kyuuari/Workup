package sheridan.caric.project.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pushup_table")
public class Pushup {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private long id;



    @ColumnInfo(name="amount")
    private long mAmount;

    public Pushup(@NonNull long amount){this.mAmount=amount;}

    public void setmAmount(long mAmount) {
        this.mAmount = mAmount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount(){return this.mAmount;}

    public long getId() {
        return this.id;
    }
}
