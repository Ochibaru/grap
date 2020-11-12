package Service;

import com.grap.dto.PantryDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PantryService implements IPantryService{

    @Autowired
    IPantryService pantryDao;

    List<PantryDTO> itemsInPantry = new ArrayList<PantryDTO>();

    @Override
    public List<PantryDTO> fetchPantry() throws Exception {

        return itemsInPantry;
    }
}
