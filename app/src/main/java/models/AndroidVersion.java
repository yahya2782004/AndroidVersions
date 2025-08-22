package models;

public class AndroidVersion {
    private int imageResId;
    private String codeName;
    private String version;

    //Constructor with parameters (used when creating an object with all values at once)
    public AndroidVersion(int imageResId, String codeName, String version) {
        this.imageResId = imageResId;
        this.codeName = codeName;
        this.version = version;
    }

    //Default constructor (used when creating an empty object and setting values later)
    public AndroidVersion() {
    }

    //Setter methods (allow modifying private fields)
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    //Getter methods (allow accessing private fields)
    public int getImageResId() {
        return imageResId;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getVersion() {
        return version;
    }
}
