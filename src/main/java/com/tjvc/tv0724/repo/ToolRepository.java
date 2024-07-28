package com.tjvc.tv0724.repo;

import com.tjvc.tv0724.model.Tool;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ToolRepository {

    /**
     * Gets all the tools available in the data source.
     *
     * @return Collection of populated Tool objects
     */
    Collection<Tool> findAllTools();

    /**
     * Looks up a Tool by its Tool Code.
     *
     * @param toolCode Tool Code identifier for the Tool to look up
     * @return Populated Tool object
     */
    Tool findToolByCode(String toolCode);
}
