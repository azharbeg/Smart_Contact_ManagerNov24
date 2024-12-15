package com.scm.controller;



// iske ander sari api likhege

@RestController    // jab bhi hum kisi cheez ko RestContoller se bnate hai iska mtlb hai hum yha se JSON Ko data return kar rhe hai 
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private ContactService contactService;
    //get contact of user

    @GetMapping("/contacts/{contactId}")
    public Contact getContect(@PathVariable String contactId){
        return contactService.getById(contactId);
    }

}
