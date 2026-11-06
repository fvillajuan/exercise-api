package com.demo.exercise_api.allomonde.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/")
@RequiredArgsConstructor
public class AlloMondeControleur {

    @PostMapping("allo")
    public ResponseEntity<String> direAllo() {
        return new ResponseEntity<>("allo", HttpStatus.OK);
    }
}
