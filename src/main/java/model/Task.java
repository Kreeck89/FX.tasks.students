package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@DatabaseTable(tableName = "tasks")
public class Task {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField(columnName = "studentID")
    private int studentID;
    @DatabaseField(columnName = "task")
    private String task;
    @DatabaseField(columnName = "answer")
    private String answer;
}