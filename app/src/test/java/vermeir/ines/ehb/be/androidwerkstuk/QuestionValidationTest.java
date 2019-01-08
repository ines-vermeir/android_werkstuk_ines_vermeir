package vermeir.ines.ehb.be.androidwerkstuk;

import org.junit.Before;
import org.junit.Test;

import vermeir.ines.ehb.be.androidwerkstuk.View.QuestionActivity;

import static org.junit.Assert.assertEquals;

public class QuestionValidationTest   {

    private QuestionActivity questionActivity;
    private boolean result;

    @Before
    public void preparetest(){
        questionActivity = new QuestionActivity();
    }

    @Test
    public void questionValidation_not_correct() throws Exception{
        result = questionActivity.check(Boolean.FALSE);
        assertEquals(true, result);

    }

    @Test
    public void questionValidation_correct() throws Exception{
        result = questionActivity.check(Boolean.TRUE);
        assertEquals(false, result);

    }
}




