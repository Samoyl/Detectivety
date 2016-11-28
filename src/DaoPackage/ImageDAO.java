package DaoPackage;

import java.util.List;

import Entities.ImageTable;

public interface ImageDAO {
	
	List<ImageTable> findAll();
	
	ImageTable search(int id);
	
    boolean insert(ImageTable image);
    
    void delete(int id);
}
