package sheridan.caric.project.db;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "run_table")
public class Run {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name="amount")
    private long mAmount;

    @ColumnInfo(name="date")
    private Date mDate;

    public Run(@NonNull long amount,Date date){this.mAmount=amount;this.mDate=date;}

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

    public void setDate(Date date){this.mDate=date;}

    public Date getDate(){return this.mDate;}
}
