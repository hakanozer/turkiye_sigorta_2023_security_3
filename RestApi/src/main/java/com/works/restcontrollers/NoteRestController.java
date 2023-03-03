package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.entities.Product;
import com.works.services.NoteService;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteRestController {

    final NoteService service;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Note note) {
        return service.save(note);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return service.list();
    }

}
