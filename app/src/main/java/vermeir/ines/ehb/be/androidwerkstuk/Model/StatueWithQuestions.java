package vermeir.ines.ehb.be.androidwerkstuk.Model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class StatueWithQuestions {

    @Embedded
    public Statue statue;

    @Relation(parentColumn = "id", entityColumn = "statue_id", entity = Question.class)
    public List<Question> questions;
}
