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

    //список посад
    @GetMapping
    public List<Position> allPositions() {

        return positionService.getAllPositions();
    }

    // отримати опис посади
    @GetMapping("/{id}")
    public Position getPosition(@PathVariable long id) {

        return positionService.getPosition(id);
    }

    //добавити новоу посаду
    @PostMapping
    public void addPosition(@RequestBody Position position) {

        positionService.addPosition(position);
    }

    // оновити дані про посаду
    @PutMapping("/{id}")
    public void updatePosition(@RequestBody Position position, @PathVariable long id) {
        positionService.updatePosition(id, position);
    }
    // видалити працівника
    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable long id) {
        positionService.deletePosition(id);
    }


}
