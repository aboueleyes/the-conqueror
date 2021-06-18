package views;

import java.awt.FontFormatException;
import java.io.IOException;

import engine.City;

public class CityButton  extends StyledButton{

    private City city;
    public CityButton(String s, int size) throws FontFormatException, IOException{
        super(s,size);
        setEnabled(false);
    }
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    
}
