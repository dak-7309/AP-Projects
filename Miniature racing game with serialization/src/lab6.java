import java.util.*;
import java.io.*;


class BeingSerialized extends Exception{
    public BeingSerialized(String message){
        super(message);
    }
}

class NotGreaterThanTen extends Exception{
    public NotGreaterThanTen(String message) {
        super(message);}
}

class NotNewLine extends Exception{
    public NotNewLine(String message){
        super(message);}
}

class SnakeBiteException extends Exception{
    public SnakeBiteException(String message){
        super(message);}
}

class CricketBiteException extends Exception{
    public CricketBiteException(String message){
        super(message);}
}
class VultureBiteException extends Exception{
    public VultureBiteException(String message){
        super(message);}
}

class TrampolineBiteException extends Exception{
    public TrampolineBiteException(String message){
        super(message);}
}

class WhiteException extends Exception{
    public WhiteException(String message){
        super(message);}
}

class GameWinnerException extends Exception{
    public GameWinnerException(String message){
        super(message);}
}


class Application{
    Scanner scan=new Scanner(System.in);

    public void app_start() throws NotGreaterThanTen, NotNewLine, SnakeBiteException,CricketBiteException,VultureBiteException,WhiteException,TrampolineBiteException,GameWinnerException,IOException, ClassNotFoundException, BeingSerialized{
        int n=0;
        boolean BEG=false;
        while(!BEG){
            System.out.println("Enter total number of tiles on the race track");
            try{
                n=scan.nextInt();
                if(n<50){
                    throw new NotGreaterThanTen("Integer Number not >> 10, try again.");}
                BEG=true;}
            catch(InputMismatchException e){
                System.out.println("Not an Integer input, try again.");}
            catch(NotGreaterThanTen e){
                System.out.println(e.getMessage());}
            finally{
                scan.nextLine();}
        }
        System.out.println("Setting up the race track...");
        int n_25=n/4;
        int n_50=n/2;
        int n_75=(n*3)/4;

        Track T=new Track();
        System.out.println("Danger: There are "+T.getN_snake()+", "+T.getN_cricket()+", "+T.getN_vulture()+" numbers of Snakes, Crickets, and Vultures respectively on the track!");
        Tile T1=new Snake();
        Tile T2=new Cricket();
        Tile T3=new Vulture();
        Tile T4=new Trampoline();
        System.out.println("Danger: Each Snake, Cricket, and Vulture can throw you back by "+Math.abs(T1.getStep())+", "+Math.abs(T2.getStep())+", "+Math.abs(T3.getStep())+" number of Tiles respectively!");
        System.out.println("Good News: There are "+T.getN_trampoline()+" number of Trampolines on the track!");
        System.out.println("Good News: Each Trampoline can help you advance by "+Math.abs(T4.getStep())+" number of Tiles!");


        ArrayList<Tile> Game_Map= new ArrayList<>();
        for(int i=0;i<n;i++){
            Tile TT=new White();
            Game_Map.add(TT);}

        T.generateMap(T.getN_snake(),T.getN_cricket(),T.getN_vulture(),T.getN_trampoline(), n, Game_Map, T1,T2,T3,T4);
        MainGame MM=new MainGame();
        MM.create_player(T,Game_Map,n, n_25,n_50,n_75);
    }
}

class Track implements Serializable{
    public static final long serialVersionUID=42L;

    Random rand=new Random();
    final private int n_snake;
    final private int n_cricket;
    final private int n_vulture;
    final private int n_trampoline;

    public Track(){
        this.n_snake=rand.nextInt(10)+1;
        this.n_cricket=rand.nextInt(10)+1;
        this.n_vulture=rand.nextInt(10)+1;
        this.n_trampoline=rand.nextInt(10)+1;}

    public int getN_snake() {
        return this.n_snake;}
    public int getN_cricket() {
        return this.n_cricket;}
    public int getN_vulture() {
        return this.n_vulture;}
    public int getN_trampoline() {
        return this.n_trampoline;}


    public void generateMap(int n_snake, int n_cricket, int n_vulture,int n_trampoline, int n,ArrayList<Tile> Game_Map, Tile T1, Tile T2, Tile T3, Tile T4){
        int limit=n;
        int X=0;
        while(X!=n_cricket+n_snake+n_trampoline+n_vulture){
            //snake
            for(int i=0;i<n_snake;i++){
                int ind=rand.nextInt(limit);
                Tile T=T1;
                Game_Map.remove(ind);
                Game_Map.add(ind, T);
                limit-=1;
                X+=1;}
            //cricket
            for(int i=0;i<n_cricket;i++){
                int ind=rand.nextInt(limit);
                Tile T=T2;
                Game_Map.remove(ind);
                Game_Map.add(ind, T);
                limit-=1;
                X+=1;}
            //vulture
            for(int i=0;i<n_vulture;i++){
                int ind=rand.nextInt(limit);
                Tile T=T3;
                Game_Map.remove(ind);
                Game_Map.add(ind, T);
                X+=1;
                limit-=1;}
            //trampoline
            for(int i=0;i<n_trampoline;i++){
                int ind=rand.nextInt(limit);
                Tile T=T4;
                Game_Map.remove(ind);
                Game_Map.add(ind, T);
                X+=1;
                limit-=1;}
        }
    }
}


abstract class Tile implements Serializable{
    public static final long serialVersionUID=42L;

    Random rand=new Random();
    private int step;

    public Tile(){
        this.step=0;
    }
    public int getStep() {
        return this.step;}
    public void setStep(int step) {
        this.step = step;}

    abstract void shake() throws SnakeBiteException,CricketBiteException,VultureBiteException,WhiteException,TrampolineBiteException;
}


class Snake extends Tile{
    public Snake(){
        super();
        this.setStep(-(rand.nextInt(10)+1));}
    @Override
    public void shake() throws SnakeBiteException{
        throw new SnakeBiteException("Hiss...! I am a Snake, you go back "+Math.abs(this.getStep())+" tiles!");
    }
}

class Vulture extends Tile{
    public Vulture(){
        super();
        this.setStep(-(rand.nextInt(10)+1));}
    @Override
    public void shake()throws VultureBiteException{
        throw new VultureBiteException("Yapping...! I am a Vulture, you go back "+Math.abs(this.getStep())+" tiles!");

    }
}

class Cricket extends Tile{
    public Cricket(){
        super();
        this.setStep(-(rand.nextInt(10)+1));}
    @Override
    public void shake() throws CricketBiteException{
        throw new CricketBiteException("Chirp...! I am a Cricket, you go back "+Math.abs(this.getStep())+" tiles!");
    }
}

class White extends Tile{
    public White(){
        super();}
    @Override
    public void shake() throws WhiteException{
        throw new WhiteException("I am a White tile!");

    }
}

class Trampoline extends Tile {
    public Trampoline(){
        super();
        this.setStep(rand.nextInt(10)+1);}
    @Override
    public void shake() throws TrampolineBiteException{
        throw new TrampolineBiteException("PingPong! I am a Trampoline, you advance "+Math.abs(this.getStep())+" tiles!");
    }
}


class Player implements Serializable {
    public static final long serialVersionUID=42L;
    private int n;
    private int n_25;
    private int n_50;
    private int n_75;
    private String name;
    private int start_location;
    private int location;


    public Player(String name, int n, int n_25, int n_50, int n_75){
        this.name=name;
        this.n=n;
        this.n_25=n_25;
        this.n_50=n_50;
        this.n_75=n_75;
        this.start_location=1;
        this.location=1;}

    public String getName() {
        return this.name;}

    public int getStart_location() {
        return this.start_location;}
    public void setStart_location(int start_location) {
        this.start_location = start_location;}
    public int getLocation() {
        return this.location;}
    public int getN(){
        return this.n;
    }
    public void setN(int n){
        this.n=n;
    }
    public int getN_25(){
        return this.n_25;
    }
    public void setN_25(int n_25){
        this.n_25=n_25;
    }
    public int getN_50(){
        return this.n_50;
    }
    public void setN_50(int n_50){
        this.n_50=n_50;
    }
    public int getN_75(){
        return this.n_75;
    }
    public void setN_75(int n_75){
        this.n_75=n_75;
    }

    public void setLocation(int location) {
        this.location = location;}
}

class Dice{
    transient final private int n_faces;

    public Dice(){
        this.n_faces=6;}

    public int getN_faces() {
        return this.n_faces;
    }

    public int Roll(int n_faces){
        Random rand=new Random();
        return rand.nextInt(n_faces)+1;}


}
class MainGame{
    Scanner scan=new Scanner(System.in);

    public static Player deserialize1() throws IOException, ClassNotFoundException{
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("abcd.txt"));
            Player a2 = (Player) in.readObject();
            return a2;

        }
        finally{
            in.close();
        }
    }
    public static Track deserialize2() throws IOException, ClassNotFoundException{
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("aaaa.txt"));
            Track a2 = (Track) in.readObject();
            return a2;

        }
        finally{
            in.close();
        }
    }
    public static ArrayList<Tile> deserialize3() throws IOException, ClassNotFoundException{
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("zzzz.txt"));
            ArrayList<Tile> a2 = (ArrayList<Tile>) in.readObject();
            return a2;

        }
        finally{
            in.close();
        }
    }




    public void create_player(Track T, ArrayList<Tile> Game_Map, int n, int n_25, int n_50, int n_75) throws NotNewLine, SnakeBiteException,CricketBiteException,VultureBiteException,WhiteException,TrampolineBiteException, GameWinnerException,IOException, ClassNotFoundException, FileNotFoundException,BeingSerialized{
        Scanner scan=new Scanner(System.in);
        String name="";
        boolean BEG=false;
        while(!BEG){
            try{
                System.out.println("Enter the Player Name");
                name=scan.next();
                BEG=true;}
            catch(InputMismatchException e){
                System.out.println("Name should be of type string, try again.");}
            finally{
                scan.nextLine();}
        }


        Player P;
        try{
            if(name.equals(deserialize1().getName())){
                System.out.println("Username already exists, do you wish to Load the game?");
                String load_bool=scan.next();
                if(load_bool.equals("yes")){
                    P=deserialize1();
                    T=deserialize2();
                    Game_Map=deserialize3();
                }
                else{
                    P=new Player(name,n,n_25,n_50,n_75);}
            }
            else{
                P=new Player(name,n,n_25,n_50,n_75);}
        }
        // catch(FileNotFoundException e){
        catch(Exception e){
            System.out.println("The file was not found");
            P=new Player(name,n,n_25,n_50,n_75);
        }

        gameplay(P, T, Game_Map, P.getN(), P.getN_25(),  P.getN_50(),  P.getN_75());
    }
    public void win() throws GameWinnerException{
        throw new GameWinnerException("Game won!");
    }
    public void finish(Player P,Track T, ArrayList<Tile> Game_Map, int n, int counter, ArrayList<Integer> Bites) throws GameWinnerException{
        try{
            win();
        }
        catch(GameWinnerException e){
            System.out.println(e.getMessage());
        }

        System.out.println(P.getName()+" wins the race in "+counter+" rolls!");
        System.out.println("Total Snake Bites: "+Bites.get(0));
        System.out.println("Total Vulture Bites: "+Bites.get(2));
        System.out.println("Total Cricket Bites: "+Bites.get(1));
        System.out.println("Total Trampoline Bites: "+Bites.get(3));
    }


    public static void serialize(Player a1, Track T, ArrayList<Tile> Game_Map) throws IOException,BeingSerialized{
        ObjectOutputStream out1 = null;
        ObjectOutputStream out2 = null;
        ObjectOutputStream out3 = null;
        try {
            out1 = new ObjectOutputStream(new FileOutputStream("abcd.txt"));
            out2 = new ObjectOutputStream(new FileOutputStream("aaaa.txt"));
            out3 = new ObjectOutputStream(new FileOutputStream("zzzz.txt"));
            out1.writeObject(a1);
            out2.writeObject(T);
            out3.writeObject(Game_Map);
        }
        finally{
            out1.close();
            out2.close();
            out3.close();
        }
    }

    public void ser() throws BeingSerialized{
        throw new BeingSerialized("Game Saved!");
    }


    public void gameplay(Player P,Track T, ArrayList<Tile> Game_Map, int n, int n_25, int n_50, int n_75) throws NotNewLine, SnakeBiteException,CricketBiteException,VultureBiteException,WhiteException,TrampolineBiteException,GameWinnerException, IOException, BeingSerialized{
        Scanner scan=new Scanner(System.in);
        System.out.println("Starting game with "+P.getName()+" at Tile-1");
        System.out.println("Control transferred to Computer for rolling the Dice for "+P.getName());
        boolean BEG=false;
        assert BEG==false;
        System.out.println("Hit enter to start the game");
        String ee=scan.nextLine();
        System.out.println("Game started ===============>");



        Dice D=new Dice();
        int counter=1;
        ArrayList<Integer> Bites=new ArrayList<>();
        Bites.add(0);
        Bites.add(0);
        Bites.add(0);
        Bites.add(0);
        while(P.getLocation()!=n){
            int x=D.Roll(D.getN_faces());
            if(P.getLocation()==1 && x!=6){
                System.out.print("[Roll-"+counter+"]: "+P.getName()+" rolled "+x+" at Tile-"+P.getLocation()+", ");
                System.out.println("OOPs you need 6 to start");
            }
            else if(P.getLocation()==1 && x==6){
                System.out.print("[Roll-"+counter+"]: "+P.getName()+" rolled "+x+" at Tile-"+P.getLocation()+",     ");
                System.out.println("You are out of the cage!. You get a free roll");
                counter+=1;

                int y=D.Roll(D.getN_faces());
                System.out.println("[Roll-"+counter+"]: "+P.getName()+" rolled "+y+" at Tile-"+P.getLocation()+", landed on Tile "+(1+y));
                System.out.println("Trying to shake the Tile "+(1+y));
                P.setLocation(1+y);

                Tile TTT=Game_Map.get(P.getLocation()-1);
                try{
                    TTT.shake();}
                catch(SnakeBiteException e){
                    System.out.println(e.getMessage());
                    Bites.set(0, Bites.get(0)+1);
                    P.setLocation(P.getLocation()+TTT.getStep());
                    if(P.getLocation()<1){
                        P.setLocation(1);}
                }
                catch(CricketBiteException e){
                    System.out.println(e.getMessage());
                    Bites.set(1, Bites.get(1)+1);
                    P.setLocation(P.getLocation()+TTT.getStep());
                    if(P.getLocation()<1){
                        P.setLocation(1);}
                }
                catch(VultureBiteException e){
                    System.out.println(e.getMessage());
                    Bites.set(2, Bites.get(2)+1);
                    P.setLocation(P.getLocation()+TTT.getStep());
                    if(P.getLocation()<1){
                        P.setLocation(1);}
                }
                catch(TrampolineBiteException e){
                    Bites.set(3, Bites.get(3)+1);
                    int orig=P.getLocation();
                    P.setLocation(P.getLocation()+TTT.getStep());
                    if(P.getLocation()>n){
                        P.setLocation(orig);}
                    if(P.getLocation()==n){
                        continue;
                    }
                    System.out.println(e.getMessage());

                }
                catch(WhiteException e){
                    int orig=P.getLocation();
                    P.setLocation(P.getLocation()+TTT.getStep());
                    if(P.getLocation()>n){
                        P.setLocation(orig);}
                    if(P.getLocation()==n){
                        continue;
                    }
                    System.out.println(e.getMessage());

                }
                finally{
                    System.out.println(P.getName()+" moved to Tile "+P.getLocation());
                    if(P.getLocation()==n_25 || P.getLocation()==n_50 || P.getLocation()==n_75){
                        System.out.println("Checkpoint reached, do you want to Save the game?");
                        String save_bool=scan.next();
                        if(save_bool.equals("yes")){

                            serialize(P,T,Game_Map);
                            try{
                                ser();
                            }
                            catch(BeingSerialized e){
                                System.out.println(e.getMessage());
                            }
                            System.exit(0);
                        }
                    }
                }
            }
            else{
                int z=D.Roll(D.getN_faces());
                if((P.getLocation()+z)>n){
                    System.out.println("[Roll-"+counter+"]: "+P.getName()+" rolled "+z+" at Tile-"+P.getLocation()+", landed on Tile "+(P.getLocation()) );
                }
                else{
                    System.out.println("[Roll-"+counter+"]: "+P.getName()+" rolled "+z+" at Tile-"+P.getLocation()+", landed on Tile "+(P.getLocation()+z) );
                }

                int orig1=P.getLocation();
                P.setLocation(P.getLocation()+z);
                if(P.getLocation()<n){
                    System.out.println("Trying to shake the Tile "+P.getLocation());}

                if(P.getLocation()==n){
                    finish(P, T, Game_Map, n, counter, Bites);
                }

                if(P.getLocation()>n){
                    P.setLocation(orig1);
                    counter+=1;
                    continue;}

                Tile TTT=Game_Map.get(P.getLocation()-1);
                try{
                    TTT.shake();}
                catch(SnakeBiteException e){
                    System.out.println(e.getMessage());
                    Bites.set(0, Bites.get(0)+1);
                    P.setLocation(P.getLocation()+TTT.getStep());
                    if(P.getLocation()<1){
                        P.setLocation(1);}
                }
                catch(CricketBiteException e){
                    System.out.println(e.getMessage());
                    Bites.set(1, Bites.get(1)+1);
                    P.setLocation(P.getLocation()+TTT.getStep());
                    if(P.getLocation()<1){
                        P.setLocation(1);}
                }
                catch(VultureBiteException e){
                    System.out.println(e.getMessage());
                    Bites.set(2, Bites.get(2)+1);
                    P.setLocation(P.getLocation()+TTT.getStep());
                    if(P.getLocation()<1){
                        P.setLocation(1);}
                }
                catch(TrampolineBiteException e){
                    Bites.set(3, Bites.get(3)+1);
                    int orig=P.getLocation();
                    P.setLocation(P.getLocation()+TTT.getStep());
                    if(P.getLocation()>n){
                        P.setLocation(orig);}
                    if(P.getLocation()==n){
                        continue;
                    }
                    System.out.println(e.getMessage());

                }
                catch(WhiteException e){
                    int orig=P.getLocation();
                    P.setLocation(P.getLocation()+TTT.getStep());
                    if(P.getLocation()>n){
                        P.setLocation(orig);}
                    if(P.getLocation()==n){
                        continue;
                    }
                    System.out.println(e.getMessage());
                }
                finally{
                    System.out.println(P.getName()+" moved to Tile "+P.getLocation());
                    if(P.getLocation()==n_25 || P.getLocation()==n_50 || P.getLocation()==n_75){
                        System.out.println("Checkpoint reached, do you want to Save the game?");
                        String save_bool=scan.next();
                        if(save_bool.equals("yes")){
                            serialize(P,T,Game_Map);
                            try{
                                ser();
                            }
                            catch(BeingSerialized e){
                                System.out.println(e.getMessage());
                            }
                            System.exit(0);
                        }
                    }
                }
            }
            counter+=1;}
    }
}

public class lab6{
    public static void main(String[] args) throws NotGreaterThanTen, NotNewLine, SnakeBiteException,CricketBiteException,VultureBiteException,WhiteException,TrampolineBiteException,GameWinnerException,IOException, ClassNotFoundException, FileNotFoundException, BeingSerialized{
        Application A=new Application();
        A.app_start();

    }}