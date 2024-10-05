package gobi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static ArrayList<Album> albums = new ArrayList<>();
    public static void main(String[] args) {
        Album album = new Album("Album1","A R Rahman");
        album.addSong("Usure nee thane",3.5);
        album.addSong("Friendship",4.5);
        album.addSong("Neethane",3);
        albums.add(album);

        album = new Album("Album2","Anirudh");
        album.addSong("Megham Karukatha",3);
        album.addSong("Senjitaley",4);
        album.addSong("Kanne Kanne",4.5);
        albums.add(album);

        LinkedList<Song> playlist1 = new LinkedList<>();
        albums.get(0).addToPlaylist("Usure nee thane",playlist1);
        albums.get(0).addToPlaylist("Neethane",playlist1);
        albums.get(1).addToPlaylist("Kanne Kanne",playlist1);
        albums.get(1).addToPlaylist("Senjitaley",playlist1);

        play(playlist1);
    }

    private static void play(LinkedList<Song> playlist) {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playlist.listIterator();
        if(playlist.size() == 0) System.out.println("This playlist have no song");
        else {
            System.out.println("Now playing "+listIterator.next().toString());
            printMenu();
        }
        while(!quit) {
            int action = sc.nextInt();
            sc.nextLine();
            switch(action) {
                case 0:
                    System.out.println("playlist completed");
                    quit = true;
                    break;
                case 1:
                    if(!forward) {
                        if(listIterator.hasNext()) listIterator.next();
                        forward = true;
                    }
                    if(listIterator.hasNext()) System.out.println("Now playing "+listIterator.next().toString());
                    else {
                        System.out.println("No song available, reached to the end of the list");
                        forward = false;
                    }
                    break;
                case 2:
                    if(forward) {
                        if(listIterator.hasPrevious()) listIterator.previous();
                        forward = false;
                    }
                    if(listIterator.hasPrevious()) System.out.println("Now playing "+listIterator.previous().toString());
                    else {
                        System.out.println("We are at the first song");
                        forward = false;
                    }
                case 3:
                    if(forward){
                        if(listIterator.hasPrevious()){
                            System.out.println("Now playing "+listIterator.previous().toString());
                            forward = false;
                        }else {
                            System.out.println("we are at the start of the list");
                        }
                    }else {
                        if(listIterator.hasNext()){
                            System.out.println("now playing "+listIterator.next().toString());
                            forward = true;
                        }else {
                            System.out.println("we have reached to the end of list");
                        }
                    }
                    break;
                case 4:
                    printlist(playlist);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if(playlist.size() > 0) {
                        listIterator.remove();
                        if(listIterator.hasPrevious()) System.out.println("Now playing "+listIterator.next().toString());
                        else System.out.println("Now playing "+listIterator.previous().toString());
                    }
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("0 - to quit\n"+
                "1 - to play next song\n"+
                "2 - to play previous song\n"+
                "3 - to play current song\n"+
                "4 - list of all songs\n"+
                "5 - print all available options\n"+
                "6 - delete current song");
    }

    private static void printlist(LinkedList<Song> playlist) {
        Iterator<Song> iterator = playlist.iterator();
        System.out.println("------------------------");
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("------------------------");
    }
}