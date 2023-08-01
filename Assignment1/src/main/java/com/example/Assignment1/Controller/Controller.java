package com.example.Assignment1.Controller;


import java.util.List;
import java.util.Optional;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Assignment1.Model.Tutorial;
import com.example.Assignment1.Repository.TutorialRepository;

@RestController
@RequestMapping("/abc")
public class Controller {
    
        @Autowired
        TutorialRepository tr;

        @GetMapping("/result")
        public List<Tutorial> getAllTuto(){
            return (List<Tutorial>) tr.findAll();
        }
         
        @PostMapping("/post")
        public ResponseEntity<Tutorial> create(@RequestBody Tutorial tutor){
            
            Tutorial tuto = tr.save(new Tutorial(tutor.getId(), tutor.getName() , tutor.getCity()));
            return new ResponseEntity<Tutorial>(tuto , HttpStatus.OK);
        }

        @PutMapping("/alter/{id}")
        public ResponseEntity<Tutorial> update(@PathVariable("id") Long id,@RequestBody Tutorial tutor)
        {
            Optional<Tutorial> tut =  tr.findById(id);
            if(tut.isPresent())
            {
                Tutorial tuto = tut.get();
                tuto.setName(tutor.getName());
                tuto.setCity(tutor.getCity());

                return new ResponseEntity<Tutorial>(tr.save(tuto),HttpStatus.OK);
            }
            else{
                Tutorial tuto = tr.save(new Tutorial(tutor.getId(), tutor.getName() , tutor.getCity()));
                return new ResponseEntity<Tutorial>(tuto , HttpStatus.OK);
            }
        }

        // @DeleteMapping("/del/{id}")
        // public ResponseEntity<Tutorial> delete(@PathVariable("id") Long id){

        //     tr.deleteById(id);
        //     return new ResponseEntity<>(HttpStatus.OK);
        // }

        @DeleteMapping("/delete_all")
        public ResponseEntity<HttpStatus> deleteall()
        {
            tr.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }

}
