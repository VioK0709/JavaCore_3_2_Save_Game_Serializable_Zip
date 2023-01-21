import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        GameProgress gameOne = new GameProgress(10, 345, 4, 1000);
        GameProgress gameTwo = new GameProgress(15, 26, 84, 45318);
        GameProgress gameThree = new GameProgress(6, 599, 54, 432);
        saveGame("/Users/vi/Desktop/Games/savegames/gameOne.dat", gameOne);
        saveGame("/Users/vi/Desktop/Games/savegames/gameTwo.dat", gameTwo);
        saveGame("/Users/vi/Desktop/Games/savegames/gameThree.dat", gameThree);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("/Users/vi/Desktop/Games/savegames/gameOne.dat");
        arrayList.add("/Users/vi/Desktop/Games/savegames/gameTwo.dat");
        arrayList.add("/Users/vi/Desktop/Games/savegames/gameThree.dat");
        zipFiles("/Users/vi/Desktop/Games/savegames/zip.zip", arrayList);
        File g1 = new File("/Users/vi/Desktop/Games/savegames/gameOne.dat");
        File g2 = new File("/Users/vi/Desktop/Games/savegames/gameTwo.dat");
        File g3 = new File("/Users/vi/Desktop/Games/savegames/gameThree.dat");
        g1.delete();
        g2.delete();
        g3.delete();
    }

    private static void saveGame(String s, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(s);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String z, List<String> arrayList){
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(z))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    zout.write(fis.read());
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}