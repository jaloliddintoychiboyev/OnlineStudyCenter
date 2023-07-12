package com.example.onlinestudycenter.controller;

import com.example.onlinestudycenter.entity.Adress;
import com.example.onlinestudycenter.repository.AdresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping
@RestController
public class AdressController {
    @Autowired
    AdresRepository adresRepository;

    @GetMapping(value = "/adress")
    public List<Adress> adressList() {
        List<Adress> all = adresRepository.findAll();
        return all;
    }

    @GetMapping(value = "/adress/{id}")
    public Adress getAdress(@PathVariable Integer id) {
        Adress adress = adresRepository.findById(id).get();
        return adress;
    }

    @PostMapping(value = "/adress")
    public String addAdress(@RequestBody Adress adress) {
        boolean b = adresRepository.existsById(adress.getId());
        if (!b) {
            Adress adress1 = new Adress();
            adress1.setId(adress.getId());
            adress1.setCity(adress.getCity());
            adress1.setDistrict(adress.getDistrict());
            adress1.setStreet(adress.getStreet());
            adress1.setHome(adress.getHome());
            adresRepository.save(adress1);
            return "Adress Muvaffaqiyatli Qo'shildi!";
        } else {
            return "Error!!!";
        }
    }

    @DeleteMapping(value = "/adress/{id}")
    public String deleteAdress(@PathVariable Integer id) {
        boolean b = adresRepository.existsById(id);
        if (b) {
            adresRepository.deleteById(id);
            return "Adress Muvaffaqiyatli O'chirildi!!!";
        } else {
            return "Error!!!";
        }
    }

    @PutMapping(value = "/adress/{id}")
    public String updateAdress(@PathVariable Integer id) {
        Optional<Adress> byId = adresRepository.findById(id);
        if (byId.isPresent()) {
            Adress adress = new Adress();
            adress.setId(id);
            adress.setCity(byId.get().getCity());
            adress.setDistrict(byId.get().getDistrict());
            adress.setStreet(byId.get().getStreet());
            adress.setHome(byId.get().getHome());
            adresRepository.save(adress);
            return "Update Muvaffaqiyatli Bajarildi!!!";
        }
        return "Error!!!";

    }


}
