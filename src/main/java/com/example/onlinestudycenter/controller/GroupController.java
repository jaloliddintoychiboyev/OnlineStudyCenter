package com.example.onlinestudycenter.controller;

import com.example.onlinestudycenter.dto.GroupDto;
import com.example.onlinestudycenter.entity.Group;
import com.example.onlinestudycenter.entity.StudyCenter;
import com.example.onlinestudycenter.repository.GroupRepository;
import com.example.onlinestudycenter.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    StudyRepository studyRepository;

    @GetMapping(value = "/group")
    public List<Group> groups() {
        return groupRepository.findAll();
    }

    @GetMapping(value = "/group/{id}")
    public Group getGroup(@PathVariable Integer id) {
        Group group = groupRepository.findById(id).get();
        return group;
    }

    @PostMapping(value = "/group")
    public String addGroup(@RequestBody GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        group.setStudent_count(groupDto.getStudent_count());
        StudyCenter studyCenter = studyRepository.findById(groupDto.getStudycenter_id()).get();
        group.setStudyCenter(studyCenter);
        groupRepository.save(group);
        return "Group Muvaffaqiyatli Qo'shildi!!!";
    }

    @PutMapping(value = "/group/{id}")
    public String updateGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto) {
        Optional<Group> byId = groupRepository.findById(id);
        StudyCenter studyCenter = studyRepository.findById(groupDto.getStudycenter_id()).get();
        Group group = new Group();
        if (byId.isPresent()) {
            group.setName(groupDto.getName());
            group.setStudent_count(groupDto.getStudent_count());
            group.setStudyCenter(studyCenter);
            groupRepository.save(group);
            return "Group Muvaffaqiyatli Update Qilindi!!!";
        } else {
            return "Error!!!";
        }
    }
    @DeleteMapping(value = "/group/{id}")
    public String deleteGroup(@PathVariable Integer id){
        groupRepository.deleteById(id);
    return "Group Muvaffaqiyatli Delete Qilindi!!!";
    }
}
