import com.grap.dao.BMI_DAO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BMI_Test {
    BMI_DAO bmi;
    @Test
    public void whenpostBMIthenCalculate() throws Exception {
        double number;
        number = bmi.fetchBMI_DAO(130, 65, "ft", "lb");
        assertEquals(21.6, number);
    }

}
