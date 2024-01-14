import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is about the post
 * @author Yinhe Chen
 * @version 1.0
 */
public class PostArea implements Serializable{
    /**
     * Initialize the post area and ask user to post one
     * @throws IOException e
     * @throws ClassNotFoundException e
     */
    public void postOne() throws IOException, ClassNotFoundException {
        File f = new File("Social.txt");
        if (!f.exists()){
            ArrayList<String> posts = new ArrayList<>();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            posts.add("""
                Amy:
                What a fine day today!

                Mike:
                Hope the exam will be easy for me.
                
                Siri:
                I have a crush on Zhengyi Li!
                """);
            oos.writeObject(posts);
            oos.close();
            fos.close();
            displayPost();
            myPost(f);

        }else{
            displayPost();
            myPost(f);

        }
    }

    /**
     * Ask the user to post something
     * @param f the file to save all the posts
     * @throws IOException e
     * @throws ClassNotFoundException if there is not the class
     */
    private static void myPost(File f) throws IOException, ClassNotFoundException {
        FileOutputStream fos;
        ArrayList<String> posts;
        ObjectOutputStream oos;
        System.out.println();
        System.out.println("Enter what you want to post:");
        Scanner sc = new Scanner(System.in);
        String post = "I:\n" + sc.nextLine() + "\n";
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        posts = (ArrayList) ois.readObject();

        posts.add(post);
        //save
        fos = new FileOutputStream(f);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(posts);
        oos.close();
        fos.close();
        ois.close();
        fis.close();

        System.out.println("Succeed!");
    }

    /**
     * Display all the posts
     * @throws IOException e
     * @throws ClassNotFoundException e
     */
    public static void displayPost() throws IOException, ClassNotFoundException {
        File f = new File("Social.txt");

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<String> posts = (ArrayList) ois.readObject();
            for (String s : posts) {
                System.out.println(s);
        }
    }
}
