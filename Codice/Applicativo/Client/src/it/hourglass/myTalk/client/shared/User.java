/*
 * Filename: Friendship.java
 * Package: it.hourglass.myTalk.client.shared
 * Author: Lorenzo Cresce Gioele
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  ---------+------------+-----------------
 *  1.0     | 2013/07/6  | Approvazione classe
 *  ---------+------------+------------------
 *  0.1     | 2013/06/02 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.shared;
import java.util.Date;
/**
 *  Lo scopo della classe è mantenere tutti i dati personali che riguardino il
 *  profilo associato ad un utente, oltre ad offrire dei metodi per la modifica
 *  e il recupero degli stessi.
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 *
 */
public class User  implements java.io.Serializable {

     private String uniqueId;
     private String email;
     private String password;
     private String altEmail;
     private String avatar;
     private String name;
     private String lastName;
     private Date birthdate;
     private char gender;
     private String hometown;
     private String currentLocation;
     private String description;
     private String inspirations;
     private String interests;
     private String validation;
     private Boolean enabled;
     private String rapidCall;
     private String viewFriendProfile;
     /**
      * Costruttore standard della classe.
      */
    public User() {
    }
    /**
     * Secondo costruttore della classe. Inizializza le dovute variabili per mezzo dei parametri formali.
     * @param uniqueId
     * @param email
     * @param password
     * @param name
     * @param lastName
     * @param birthdate
     * @param gender
     */
    public User(String uniqueId, String email, String password, String name, String lastName, Date birthdate, char gender) {
        this.uniqueId = uniqueId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
    }
    /**
     * Terzo costruttore della classe. Inizializza tutte le variabili per mezzo dei parametri formali.
     * @param uniqueId
     * @param email
     * @param password
     * @param altEmail
     * @param avatar
     * @param name
     * @param lastName
     * @param birthdate
     * @param gender
     * @param hometown
     * @param currentLocation
     * @param description
     * @param inspirations
     * @param interests
     * @param validation
     * @param enabled
     */
    public User(String uniqueId, String email, String password, String altEmail, String avatar, String name, String lastName, Date birthdate, char gender, String hometown, String currentLocation, String description, String inspirations, String interests, String validation, Boolean enabled) {
       this.uniqueId = uniqueId;
       this.email = email;
       this.password = password;
       this.altEmail = altEmail;
       this.avatar = avatar;
       this.name = name;
       this.lastName = lastName;
       this.birthdate = birthdate;
       this.gender = gender;
       this.hometown = hometown;
       this.currentLocation = currentLocation;
       this.description = description;
       this.inspirations = inspirations;
       this.interests = interests;
       this.validation = validation;
       this.enabled = enabled;
    }
    /**
     * Ritorna la variabile "uniqueId".
     * @return
     */
    public String getUniqueId() {
        return this.uniqueId;
    }
    /**
     * Imposta la variabile "uniqueId".
     * @param uniqueId
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    /**
     * Ritorna la variabile "uniqueId".
     * @return
     */
    public String getEmail() {
        return this.email;
    }
    /**
     * Imposta la variabile "email".
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Ritorna la variabile "password".
     * @return
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * Imposta la variabile "password".
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Ritorna la variabile "altEmail".
     * @return
     */
    public String getAltEmail() {
        return this.altEmail;
    }
    /**
     * Imposta la variabile "altEmail".
     * @param altEmail
     */
    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }
    /**
     * Ritorna la variabile "avatar".
     * @return
     */
    public String getAvatar() {
        return this.avatar;
    }
    /**
     * Imposta la variabile "avatar".
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    /**
     * Ritorna la variabile "name".
     * @return
     */
    public String getName() {
        return this.name;
    }
    /**
     * Imposta la variabile "name".
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Ritorna la variabile "lastName".
     * @return
     */
    public String getLastName() {
        return this.lastName;
    }
    /**
     * Imposta la variabile "lastName".
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Ritorna la variabile "birthdate".
     * @return
     */
    public Date getBirthdate() {
        return this.birthdate;
    }
    /**
     * Imposta la variabile "birthdate".
     * @param birthdate
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    /**
     * Ritorna la variabile "gender".
     * @return
     */
    public char getGender() {
        return this.gender;
    }
    /**
     * Imposta la variabile "gender".
     * @param gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }
    /**
     * Ritorna la variabile "hometown".
     * @return
     */
    public String getHometown() {
        return this.hometown;
    }
    /**
     * Imposta la variabile "hometown".
     * @param hometown
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
    /**
     * Ritorna la variabile "currentLocation".
     * @return
     */
    public String getCurrentLocation() {
        return this.currentLocation;
    }
    /**
     * Imposta la variabile "currentLocation".
     * @param currentLocation
     */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    /**
     * Ritorna la variabile "description".
     * @return
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Imposta la variabile "description".
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Ritorna la variabile "inspirations".
     * @return
     */
    public String getInspirations() {
        return this.inspirations;
    }
    /**
     * Imposta la variabile "inspirations".
     * @param inspirations
     */
    public void setInspirations(String inspirations) {
        this.inspirations = inspirations;
    }
    /**
     * Ritorna la variabile "interests".
     * @return
     */
    public String getInterests() {
        return this.interests;
    }
    /**
     * Imposta la variabile "interests".
     * @param interests
     */
    public void setInterests(String interests) {
        this.interests = interests;
    }
    /**
     * Ritorna la variabile "validation".
     * @return
     */
    public String getValidation() {
        return this.validation;
    }
    /**
     * Imposta la variabile "validation".
     * @param validation
     */
    public void setValidation(String validation) {
        this.validation = validation;
    }
    /**
     * Ritorna la variabile "enabled". 
     * @return  <code>true</code> se il profilo risulta abilitato al login,
     * <code>false</code> altrimenti
     */
    public Boolean getEnabled() {
        return this.enabled;
    }
    /**
     * Imposta la variabile "enabled".
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    /**
     * Imposta la variabile "rapidCall".
     * @param rapid
     */
    public void setRapidCall(String rapid){
    	this.rapidCall=rapid;
    }
    /**
     * Imposta la variabile "viewFriendProfile".
     * @param rapid
     */
    public void setViewFriendProfile(String rapid){
    	this.viewFriendProfile=rapid;
    }
    /**
     * Ritorna la variabile "rapidCall".
     * @return
     */
    public String getRapidCall(){
    	return this.rapidCall;
    }
    /**
     * Ritorna la variabile "viewFriendProfile".
     * @return
     */
    public String getViewFriendProfile(){
    	return this.viewFriendProfile;
    }
   

}