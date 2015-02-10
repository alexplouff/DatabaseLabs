/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_labs;

/**
 *
 * @author Alex
 */
public class Hotel {
    
    private int pk;
    private String name;
    private String street_address;
    private String city;
    private String state;
    private String zip;
    
    public Hotel(String name, String address, String city, String state){
        
        setName(name);
        setStreet_address(address);
        setCity(city);
        setState(state);
    }
    
    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }
    
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getStreet_address() {
        return street_address;
    }

    private void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getState() {
        return state;
    }

    private void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    private void setZip(String zip) {
        this.zip = zip;
    }
    
    public void setPk(int pk){
        this.pk = pk;
    }
    
    public int getPk(){
        return pk;
    }
    
    public String toString(){
        return name + ", " + zip;
    }
    
    
}
