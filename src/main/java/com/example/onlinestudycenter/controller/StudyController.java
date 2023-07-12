package com.example.onlinestudycenter.controller;

import com.example.onlinestudycenter.dto.StudyCenterAdressDto;
import com.example.onlinestudycenter.entity.Adress;
import com.example.onlinestudycenter.entity.StudyCenter;
import com.example.onlinestudycenter.repository.AdresRepository;
import com.example.onlinestudycenter.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class StudyController {
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    AdresRepository adresRepository;

    @GetMapping(value = "/study")
    public List<StudyCenter> StudyList() {
        List<StudyCenter> all = studyRepository.findAll();
        return all;
    }

    @GetMapping(value = "/study/{id}")
    public StudyCenter studyCenter(@PathVariable Integer id) {
        StudyCenter studyCenter = studyRepository.findById(id).get();
        return studyCenter;
    }

    @PostMapping(value = "/study")
    public String addStudy(@RequestBody StudyCenterAdressDto studyCenterAdressDto) {
            Adress adress = new Adress();
            StudyCenter studyCenter = new StudyCenter();
            adress.setCity(studyCenterAdressDto.getCity());
            adress.setStreet(studyCenterAdressDto.getStreet());
            adress.setDistrict(studyCenterAdressDto.getDistrict());
            adress.setHome(studyCenterAdressDto.getHome());
            studyCenter.setName(studyCenterAdressDto.getName());
            adresRepository.save(adress);
            studyCenter.setAdress(adress);
            studyRepository.save(studyCenter);
            return "StudyCenter va Adress Muvaffaqiyatli Qo'shildi!!!";

    }

    @DeleteMapping(value = "/study/{id}")
    public String deleteStudy(@PathVariable Integer id) {
        boolean b = studyRepository.existsById(id);
        if (b) {
            studyRepository.deleteById(id);
            adresRepository.deleteById(id);
            return "Study va Adress Muvaffaqiyatli O'chirildi!!!";
        } else {
            return "Error!!!";
        }
    }

    @PutMapping(value = "/study/{id}")
    public String updateStudyCenter(@PathVariable Integer id, @RequestBody StudyCenterAdressDto studyCenterAdressDto) {
        Optional<StudyCenter> byId = studyRepository.findById(id);
        StudyCenter studyCenter1 = byId.get();
        if (byId.isPresent()) {
            Adress adress = new Adress();
            studyCenter1.setName(studyCenterAdressDto.getName());
            adress.setCity(studyCenterAdressDto.getCity());
            adress.setStreet(studyCenterAdressDto.getStreet());
            adress.setDistrict(studyCenterAdressDto.getDistrict());
            adress.setHome(studyCenterAdressDto.getHome());
            adresRepository.save(adress);
            studyCenter1.setAdress(adress);
            studyRepository.save(studyCenter1);
            return "StudyCenter va Adress Muvaffaqiyatli Update Qilindi!!!";
        } else {
            return "Error!!!";
        }
    }


}
