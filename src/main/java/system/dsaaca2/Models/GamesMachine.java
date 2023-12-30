package system.dsaaca2.Models;

import system.dsaaca2.Controllers.GameAPI;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.utils.Utilities;

import java.util.Objects;

public class GamesMachine implements Hashable{
    private String name, manufacturer, description, type, media, image;
    private int year;
    private double price;

    SillyList<Game> games = new SillyList<>();

    public GamesMachine(String name, String manufacturer, String description, String type, String media, String image, int year, double price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
        this.type = type;
        this.media = media;
        this.image = image;
        setYear(year);
        this.price = price;
    }


    public SillyList<Game> getGames() {
        return games;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getMedia() {
        return media;
    }
    public void setMedia(String media) {
        this.media = media;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        if(Utilities.intValidRange(year,1900,2023)){
            this.year = year;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addGame(Game g) {
        games.add(g);
        g.setGamesMachine(this);
    }

    public void removeGame(Game g) {
        games.remove(g);
    }

    @Override
    public String toString() {
        return
                "Name: ( " + name.toUpperCase() + " )   Manufacturer: ( "+manufacturer.toUpperCase()+" )   Released: ( "+year+" )"+
                "\n----------------------------------------------------------------" + "\n" +
                "|Description: " + description.toUpperCase() + "\n" +
                "|Machine Type: " + type.toUpperCase() + "\n" +
                "|Media Type: " + media.toUpperCase() + "\n" +
                "|Image: " + image + "\n" +
                "|Price: €" + price ;

    }

    @Override
    public boolean matchKey(String key, String keyType) {
        switch (keyType) {
            case "name": return Objects.equals(key, name);
            case "manufacturer": return Objects.equals(key, manufacturer);
            case "year": return Objects.equals(key, String.valueOf(year));
            case "description": return Objects.equals(key, description);
            case "type": return Objects.equals(key,type);
            case "media": return Objects.equals(key,media);
            default: return false;
        }
    }
}
