import com.sun.tools.javac.Main;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestExceptions{
    @Test(expected = SnakeBiteException.class)
    public void snake_detect() throws SnakeBiteException,CricketBiteException,VultureBiteException,WhiteException,TrampolineBiteException{
        Tile T=new Snake();
        T.shake();
    }

    @Test(expected = VultureBiteException.class)
    public void vulture_detect() throws SnakeBiteException,CricketBiteException,VultureBiteException,WhiteException,TrampolineBiteException{
        Tile T=new Vulture();
        T.shake();
    }

    @Test(expected = CricketBiteException.class)
    public void cricket_detect() throws SnakeBiteException,CricketBiteException,VultureBiteException,WhiteException,TrampolineBiteException{
        Tile T=new Cricket();
        T.shake();
    }
    @Test(expected = TrampolineBiteException.class)
    public void trampoline_detect() throws SnakeBiteException,CricketBiteException,VultureBiteException,WhiteException,TrampolineBiteException{
        Tile T=new Trampoline();
        T.shake();
    }

    @Test(expected = GameWinnerException.class)
    public void winner_detect() throws NotNewLine, SnakeBiteException,CricketBiteException,VultureBiteException,WhiteException,TrampolineBiteException,GameWinnerException, IOException, ClassNotFoundException, FileNotFoundException {
        int n=100;
        int n_25=25;
        int n_50=50;
        int n_75=75;
        Track T=new Track();
        Tile T1=new Snake();
        Tile T2=new Cricket();
        Tile T3=new Vulture();
        Tile T4=new Trampoline();
        ArrayList<Tile> Game_Map= new ArrayList<>();

        for(int i=0;i<n;i++){
            Tile TT=new White();
            Game_Map.add(TT);}
        T.generateMap(T.getN_snake(),T.getN_cricket(),T.getN_vulture(),T.getN_trampoline(), n, Game_Map, T1,T2,T3,T4);
        MainGame MM=new MainGame();
        Player P=new Player("Josh",n,n_25,n_50,n_75);
        Dice D=new Dice();
        int counter=1;
        ArrayList<Integer> Bites=new ArrayList<>();
        Bites.add(0);
        Bites.add(0);
        Bites.add(0);
        Bites.add(0);
        MainGame m=new MainGame();
        m.win();
    }


    @Test(expected = BeingSerialized.class)
    public void first_serial() throws IOException , BeingSerialized {

        int n = 100;
        int n_25 = 25;
        int n_50 = 50;
        int n_75 = 75;
        Track T = new Track();
        Tile T1 = new Snake();
        Tile T2 = new Cricket();
        Tile T3 = new Vulture();
        Tile T4 = new Trampoline();
        ArrayList<Tile> Game_Map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Tile TT = new White();
            Game_Map.add(TT);
        }
        T.generateMap(T.getN_snake(), T.getN_cricket(), T.getN_vulture(), T.getN_trampoline(), n, Game_Map, T1, T2, T3, T4);
        MainGame MM = new MainGame();
        Player P = new Player("Josh", n, n_25, n_50, n_75);
        Dice D = new Dice();
        int counter = 1;
        ArrayList<Integer> Bites = new ArrayList<>();
        Bites.add(0);
        Bites.add(0);
        Bites.add(0);
        Bites.add(0);
        MainGame G = new MainGame();
        G.ser();
        G.serialize(P, T, Game_Map);

    }
}
