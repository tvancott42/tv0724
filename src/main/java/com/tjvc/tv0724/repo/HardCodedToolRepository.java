package com.tjvc.tv0724.repo;

import com.tjvc.tv0724.model.Tool;
import com.tjvc.tv0724.model.ToolType;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
public class HardCodedToolRepository implements ToolRepository {

    // Note: Could be ported to a relational DB w/ unique index or PK on tool code
    private final Map<String, Tool> tools = Map.of(
            "CHNS", new Tool("CHNS", ToolType.Chainsaw, "Stihl"),
            "LADW", new Tool("LADW", ToolType.Ladder, "Werner"),
            "JAKD", new Tool("JAKD", ToolType.Jackhammer, "DeWalt"),
            "JAKR", new Tool("JAKR", ToolType.Jackhammer, "Ridgid")
    );

    @Override
    public Collection<Tool> findAllTools() {
        return tools.values();
    }

    @Override
    public Tool findToolByCode(String toolCode) {
        return tools.get(toolCode);
    }
}
