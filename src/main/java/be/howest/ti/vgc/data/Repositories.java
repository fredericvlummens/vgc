package be.howest.ti.vgc.data;

public class Repositories {

    private static final VGCRepository REPO = new MySqlVGCRepository();

    public static VGCRepository getRepository() {
        return REPO;
    }

}
