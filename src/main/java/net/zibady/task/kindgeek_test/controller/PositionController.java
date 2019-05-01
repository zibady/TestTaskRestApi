package net.zibady.task.kindgeek_test.controller;

import net.zibady.task.kindgeek_test.entity.Position;
import net.zibady.task.kindgeek_test.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("position")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> allPositions() {

        return positionService.getAllPositions();
    }

    @GetMapping("/{id}")
    public Position getPosition(@PathVariable long id) {

        return positionService.getPosition(id);
    }

    @PostMapping
    public void addPosition(@RequestBody Position position) {

        positionService.addPosition(position);
    }

    @PutMapping
    public void updatePosition(@RequestBody Position position) {
        positionService.updatePosition(position);
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable long id) {
        positionService.deletePosition(id);
    }


}
