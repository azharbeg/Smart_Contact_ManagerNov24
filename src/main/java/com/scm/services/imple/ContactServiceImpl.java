package com.scm.services.imple;

import com.scm.helper.ResoursenotFoundException;
import com.scm.repositeries.ContactRepo;
import com.scm.services.Contact;
import com.scm.services.ContactService;
import com.scm.services.List;

import config.Autowired;

@Service
public class ContactServiceImpl implements ContactService {


    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
         
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        
       var contactOld =  contactRepo.findById(contact.getId()).orElseThrow(()->
            new ResourseNotFoundException("Contact not found"));

            contactOld.setName(contact.getName());
            contactOld.setEmail(contact.setEmail());
            contactOld.setPhoneNumber(contact.setPhoneNumber());
            contactOld.setAddress(contact.setAddress());
            contactOld.setDescription(contact.setDescription());
            contactOld.setPicture(contact.setPicture());
            contactOld.setFavorite(contact.setFavorite());
            contactOld.setWebsite(contact.setWebsite());
            contactOld.setLinkedIn(contact.setLinkedIn());
            contactOld.setCloudinaryImagePublicId(contact.setCloudinaryImagePublicId());
            contactOld.setLinks(contact.setLinks());

            return contactRep.save(contactOld);


    }

    @Override
    public List<Contact> getAll() {
         
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
         
        return contactRepo.findById(id).orElseThrow(() -> new ResoursenotFoundException("Contact not found with given id" + id));

    }

    @Override
    public void delete(String id) {
        
        // first get then delete
        var contact = contactRepo.findById(id).orElseThrow(() -> new ResoursenotFoundException("Contact not found with given id" + id));


        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
       

    }

    @Override
    public List<Contact> getByUserId(String userId) {
         
       return contactRepo.findByUserId(userId);
    }
 
    @Override
    public Page<Contact> getByUser(User user,int page, int size,String sortBy, String direction){

        Sort sort = direction.equals("desc")? Sort.by(sortBy).decending() :Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page,size,sort);

       return contactRepo.findByUser(user,pageable);
    }

    // // search wala method (search by name,email,phoneNumber)

    @Override
    public Page<Contact> searchByName(String nameKeyword,int size, int page, String sortBy, String order,User user){

        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page,size,sort);
        return contactRepo.findByNameContaining(nameKeyword,user,pageable);
    }

    @Override
    public Page<Contact> searchByEmail(String emailKeyword,int size, int page, String sortBy, String order,User user){

        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page,size,sort);
        return contactRepo.findByNameContaining(emailKeyword,user,pageable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String phoneNumberKeyword,int size, int page, String sortBy, String order,User user){

        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page,size,sort);
        return contactRepo.findByNameContaining(phoneNumberKeywordKeyword,user,pageable);
    }


    
}
