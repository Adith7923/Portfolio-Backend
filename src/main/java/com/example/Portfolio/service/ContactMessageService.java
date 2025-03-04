package com.example.Portfolio.service;

import com.example.Portfolio.model.ContactMessage;
import com.example.Portfolio.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;


    public ContactMessage saveMessage(ContactMessage contactMessage) {
        return contactMessageRepository.save(contactMessage);
    }
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }
}