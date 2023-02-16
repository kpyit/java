package oop2;

import java.lang.invoke.ClassSpecializer.Factory;
import java.util.Date;
import java.time.LocalDate;

public interface Pet extends Animal{
    
    String nickname = new String();// Кличка	
    String species = ""; // Порода	
    Boolean availabilityVaccinations = false; //Наличие прививок	
	String coatColor = ""; //Цвет шерсти
	LocalDate dateBirth = LocalDate.now();//Дата рождения

	void showAffection(); //Проявлять ласку
}
