package Service;

import com.grap.dto.PantryDTO;

import java.util.List;

public interface IPantryService {

    List<PantryDTO> fetchPantry() throws Exception;
}
