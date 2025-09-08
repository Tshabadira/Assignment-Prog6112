package seriestv;

public class SeriesModel {
    private int id;
    private String name;
    private int ageRestriction;
    private int episodes;

    public SeriesModel(int id, String name, int ageRestriction, int episodes) {
        this.id = id;
        this.name = name;
        this.ageRestriction = ageRestriction;
        this.episodes = episodes;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAgeRestriction() { return ageRestriction; }
    public void setAgeRestriction(int ageRestriction) { this.ageRestriction = ageRestriction; }

    public int getEpisodes() { return episodes; }
    public void setEpisodes(int episodes) { this.episodes = episodes; }

    // For list/report printing
    @Override
    public String toString() {
        return "SERIES ID: " + id + "\n" +
               "SERIES NAME: " + name + "\n" +
               "SERIES AGE RESTRICTION: " + ageRestriction + "\n" +
               "NUMBER OF EPISODES: " + episodes + "\n";
    }
}
