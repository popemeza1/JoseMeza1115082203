package Persona;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Persona {
    
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty cedula = new SimpleStringProperty();
    public SimpleIntegerProperty edad = new SimpleIntegerProperty();
    public SimpleStringProperty correo = new SimpleStringProperty();
    
    
    public String getNombre(){
        return nombre.get();
    }
    
    public String getCedula(){
        return cedula.get();
    }
    
    public Integer getEdad (){
        return edad.get();
    }
    
    public String getCorreo(){
        return correo.get();
    }
    
}