package com.dal;

import com.dto.RootDTO;
import java.util.List;

/**
 * Interface defining CRUD operations for Root entity.
 * Implemented by RootDAO.
 */
public interface IRootDAO {

    void addRoot(RootDTO root);
    List<RootDTO> getAllRoots();
    void updateRoot(int rootId, String newLetters);
    void deleteRoot(int rootId);
    RootDTO getRootByLetters(String letters);
    List<RootDTO> searchRoots(String partialLetters);
}
