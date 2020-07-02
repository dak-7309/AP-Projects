import java.util.*;

import com.sun.javafx.runtime.SystemProperties;


class Application{
    Scanner scan=new Scanner(System.in);
    
   
    public void app_start(){
        Map< String,Hero> hero_map=new HashMap<>();
        
        while(1==1){
            Menu Men=new Menu();
            Men.user_menu();       
            int query=scan.nextInt();
            
            if(query==1){
                System.out.println("Enter username");
                String user_name=scan.next();

                Men=new Menu();
                String Hero_Type=Men.hero_menu(user_name);
               
                Hero H;

                if(Hero_Type.equals("Warrior")){
                    H=new Warrior();
                }
                if(Hero_Type.equals("Thief")){
                    H=new Thief();
                }
                if(Hero_Type.equals("Mage")){
                    H=new Mage();
                }
                else{
                    H=new Healer();
                }
                H.LEVEL=0;
                H.setType(Hero_Type);
                hero_map.put(user_name, H);
                // System.out.println(hero_map);

            }




            else if(query==2){
                System.out.println("Enter username");    
                String user_name=scan.next();
                if(!hero_map.containsKey(user_name)){
                    System.out.println("User Not Found");
                }
                else{
                    
                    System.out.println("User Found... logging in");
                    System.out.println("Welcome "+user_name);
                    Hero HH=hero_map.get(user_name);
                    
                    if(HH.LEVEL==0){
                        System.out.println("You are at the starting location. Choose path:");
                    }
                    else{
                        System.out.println("You are at location "+HH.getLocation()+". Choose path:");
                    }

                    ArrayList<Monster> Graph=new ArrayList<>();

                    for(int i=0;i<10;i++){
                        Monster M=new Monster();
                        Graph.add(M);
                    }
                    Graph.get(9).setLevel(4);
                    
                    

                    while(HH.LEVEL<=4){
                        ArrayList<Integer> temp_loc=new ArrayList<>();
                        if(HH.LEVEL==0){
                            temp_loc.add(0);
                            temp_loc.add(3);
                            temp_loc.add(6);
                        }
                        else if(HH.LEVEL==1){
                            temp_loc.add(1);
                            temp_loc.add(4);
                            temp_loc.add(7);
                        }
                        else if(HH.LEVEL==2){
                            temp_loc.add(2);
                            temp_loc.add(5);
                            temp_loc.add(8);
                        }
                        else{
                            temp_loc.add(9);
                        }
                        Men=new Menu();
                        Men.location_menu(HH,temp_loc,Graph);
                        if(HH.c==true){
                            break;
                        }
                    }
                    
                }

            }else if(query==3){
                System.exit(0);
            }



        }
    }
}




class Menu{
    Scanner scan=new Scanner(System.in);

    public void user_menu(){
        System.out.println("Welcome to ArchLegends");
        System.out.println("Choose your option");
        System.out.println("1) New User");
        System.out.println("2) Existing User");
        System.out.println("3) Exit");
    }

    public String hero_menu(String s){
        System.out.println("Choose a Hero");
        System.out.println("1) Warrior");
        System.out.println("2) Thief");
        System.out.println("3) Mage");
        System.out.println("4) Healer");

        int query=scan.nextInt();
        String type;
        if(query==1){
            type="Warrior";
        }
        else if(query==2){
            type="Thief";
        }
        else if(query==3){
            type="Mage";
        }
        else{
            type="Healer";
        }
        System.out.println("User Creation done. Username: "+s+". Hero type: "+type+". Log in to play the game. Exiting");
        return type;

    }

    public void location_menu(Hero HH,ArrayList<Integer> temp_loc,ArrayList<Monster> Graph){
       

        int prev=HH.getLocation();
        if(HH.getLocation()!=-1){
            HH.setPrev_location(prev);
        }

        for(int i=0;i<temp_loc.size();i++){
            System.out.println((i+1)+") Go to Location "+temp_loc.get(i));
        }
        if(HH.getPrev_location()!=-1){
            temp_loc.add(HH.getPrev_location());
            System.out.println("4) Go back");
        }
        System.out.println("Enter -1 to exit");

        int position=scan.nextInt();
        if(position==-1){
            HH.c=true;
            return;
        }
        if(temp_loc.get(position-1)==0 ||temp_loc.get(position-1)==3 ||temp_loc.get(position-1)==6){
            HH.LEVEL=1;
            
        }
        if(temp_loc.get(position-1)==1 ||temp_loc.get(position-1)==4 ||temp_loc.get(position-1)==7){
            HH.LEVEL=2;
        }
        if(temp_loc.get(position-1)==2 ||temp_loc.get(position-1)==5 ||temp_loc.get(position-1)==8){
           HH.LEVEL=3;
        }
        if(temp_loc.get(position-1)==9){
            HH.LEVEL=4;
        }




        System.out.println("Moving to location "+temp_loc.get(position-1));
        
       
        HH.setLocation(temp_loc.get(position-1));
        // System.out.println(HH.getLocation());
        // System.out.println(HH.getPrev_location());
       

        Dual D=new Dual();
        int ind=temp_loc.get(position-1);
        System.out.println("Fight Started. You're fighting a level "+Graph.get(temp_loc.get(position-1)).getLevel()+" Monster");
        if(Graph.get(temp_loc.get(position-1)).getLevel()!=4){
            D.start_fight(HH,Graph,ind);

        }
        else{
            D.start_ultrafight(HH,Graph,ind);
        }

    }
}




class Dual{
    Scanner scan=new Scanner(System.in);

    public void start_ultrafight(Hero H, ArrayList<Monster> Graph, int ind){
        Boss B=new Boss();
        B.setHP(250);
        B.setLevel(4);

        int kish=0;
        int temp=0;
        int clock=-1;
        int cc=1;
        while(1==1){
            
            
            H.setTiktok((H.getTiktok()+1)%4);
            if(kish!=0 && temp!=3 && H.getTiktok()==1){
                H.setTiktok(0);
            }
            System.out.println("Choose move");
        
            System.out.println("1) Attack");
            System.out.println("2) Defense");
            if(H.getTiktok()==0){
                System.out.println("3) Special Attack");
            }

            int move_choice=scan.nextInt();
            temp=move_choice;
            
            if(clock==3){
                clock=-1;
                cc+=1;
                System.out.println("Special Move Deactivated");
            }
            
            if(clock>=0){
                clock=clock+1;
            }
            
            
            if(move_choice==1){
                

                double orig=B.getHP();
                ATTACK(H, B);
                
                if(clock>0 && !H.getType().equals("Thief")){
                    double orig1=H.getHP();
                    SPECIAL(H, B,cc);
                    System.out.println("Performing special attack");
                    
                    if(H.getType().equals("Warrior")){
                        // if(M.getHP()<=0){
                        //     M.setHP(0);
                        // }
                        
                        if(H.getHP()>orig1){
                            H.setHP(orig1);
                        }

                    }
                    else if(H.getType().equals("Mage")){
                    //     if(M.getHP()<=0){
                    //         M.setHP(0);
                    //     }
                    }
                    
                    




                }
            
                double diff=orig-B.getHP();
                System.out.println("You attacked and inflicted "+Math.abs(diff)+" damage to the monster");

                if(B.getHP()<=0){
                    B.setHP(0);
                }
                System.out.println("Your HP: "+H.getHP()+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+B.getHP()+"/"+((B.getLevel()+1)*50));
                
                //monster killed
                if(B.getHP()==0){
                    B.setKilled(true);
                    H.setXP(H.getXP()+(20*(B.getLevel())));

                    H.setLevel(4);
                    H.setHP(250);
                    

                    System.out.println("Lionfang Killed!");
                    System.out.println("Game Completed");
                    H.setHP(250);
                    Graph.set(ind, new Monster());
                    System.exit(0);

                }
                
                
                else{
                    double ORIG=H.getHP();
                    BOSS_ATTACK(H,B);
                    double DIFF=Math.abs(ORIG-H.getHP());
                    System.out.println("The monster attacked and afflicted "+DIFF+" damage to you");

                    if(H.getHP()<=0){
                        H.setHP(0);
                    }
                    System.out.println("Your HP: "+H.getHP()+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+B.getHP()+"/"+((B.getLevel()+1)*50));
                    
                    if(H.getHP()<=0){
                        B.setHP((B.getLevel()+1)*50);
                        System.out.println("Hero Killed!");
                        System.out.println("Fight lost, Restart");
                        H.setHP(100);
                        H.setLevel(1);
                        H.setLocation(-1);
                        H.setPrev_location(-1);
                        H.setTiktok(0);
                        H.setXP(0);
                        H.LEVEL=0;
                        break;
                    }
                }

            }
            else if(move_choice==2){

                


                double orig=H.getHP();
                DEFENSE(H, B);

                if(clock>0 && !H.getType().equals("Thief")){
                    double orig1=H.getHP();
                    SPECIAL(H, B,cc);
                    System.out.println("Performing special attack");
                    
                    if(H.getType().equals("Warrior")){
                        if(B.getHP()<=0){
                            B.setHP(0);
                        }
                        
                        // if(H.getHP()>orig1){
                        //     H.setHP(orig1);
                        // }

                    }
                    else if(H.getType().equals("Mage")){
                        if(B.getHP()<=0){
                            B.setHP(0);
                        }
                    }
                    
                    else{
                        // if(H.getHP()>orig1){
                        //     H.setHP(orig1);
                        // }

                    }




                }
                double diff=H.getHP()-orig;

                if(H.getHP()>(H.getLevel()+1)*50  ){
                    H.setHP((H.getLevel()+1)*50);
                }
                

                System.out.println("Monster attack reduced by "+diff+"!");
                System.out.println("Your HP: "+(H.getHP()-diff)+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+B.getHP()+"/"+((B.getLevel()+1)*50) );
                

                double ORIG=H.getHP();
                BOSS_ATTACK(H,B);
                double DIFF=Math.abs(ORIG-H.getHP());
                System.out.println("The monster attacked and afflicted "+DIFF+" damage to you");

                if(H.getHP()<=0){
                    H.setHP(0);
                }
                if(H.getHP()>orig){
                    H.setHP(orig);
                }
                System.out.println("Your HP: "+H.getHP()+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+B.getHP()+"/"+((B.getLevel()+1)*50));
                
                if(H.getHP()<=0){
                    System.out.println("Hero Killed!");
                    System.out.println("Fight lost, Restart");
                    H.setHP(100);
                    H.setLevel(1);
                    H.setLocation(-1);
                    H.setPrev_location(-1);
                    H.setTiktok(0);
                    H.setXP(0);
                    H.LEVEL=0;
                    break;
                }


            }
            else{
                // SPECIAL(H, M);
                System.out.println("Special Power Activated");
                clock=0;
                if(H.getType().equals("Thief")){
                    double orig=B.getHP();
                    SPECIAL(H, B,cc);
                    double diff=B.getHP()-orig;



                    System.out.println("You have stolen "+Math.abs(diff)+" HP from the Monster");
                    if(B.getHP()<=0){
                        B.setHP(0);
                    }
                    System.out.println("Your HP: "+H.getHP()+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+B.getHP()+"/"+((B.getLevel()+1)*50));

                    //monster killed
                    if(B.getHP()==0){
                        B.setKilled(true);
                        H.setXP(H.getXP()+(20*(B.getLevel())));

                        if(H.getHP()>=20 && H.getHP()<40){
                            H.setLevel(2);
                            H.setHP(150);
                        }
                        else if(H.getHP()>=40 && H.getHP()<60){
                            H.setLevel(3);
                            H.setHP(200);
                        }
                        else if(H.getHP()>=60){
                            H.setLevel(4);
                            H.setHP(250);
                        }
                        
                        H.setHP(250);
                        System.out.println("Lionfang Killed!");
                        System.out.println("Game completed");
                        Graph.set(ind, new Monster());
                        System.exit(0);

                    }
                    
                    
                    else{
                        double ORIG=H.getHP();
                        BOSS_ATTACK(H,B);
                        double DIFF=Math.abs(ORIG-H.getHP());
                        System.out.println("The monster attacked and afflicted "+DIFF+" damage to you");

                        if(H.getHP()<=0){
                            H.setHP(0);
                        }
                        System.out.println("Your HP: "+H.getHP()+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+B.getHP()+"/"+((B.getLevel()+1)*50));
                        
                        if(H.getHP()<=0){
                            B.setHP((B.getLevel()+1)*50);
                            System.out.println("Hero Killed!");
                            System.out.println("Fight lost, Restart");
                            H.setHP(100);
                            H.setLevel(1);
                            H.setLocation(-1);
                            H.setPrev_location(-1);
                            H.setTiktok(0);
                            H.setXP(0);
                            H.LEVEL=0;
                            break;
                        }
                    }




                }
            }

            kish+=1;

        }
        
  



    }









    public void start_fight(Hero H, ArrayList<Monster> Graph, int ind){
        Monster M=Graph.get(ind);
        
        int kish=0;
        int temp=0;
        int clock=-1;
        int cc=1;
        while(1==1){
            
            
            H.setTiktok((H.getTiktok()+1)%4);
            if(kish!=0 && temp!=3 && H.getTiktok()==1){
                H.setTiktok(0);
            }
            System.out.println("Choose move");
        
            System.out.println("1) Attack");
            System.out.println("2) Defense");
            if(H.getTiktok()==0){
                System.out.println("3) Special Attack");
            }

            int move_choice=scan.nextInt();
            temp=move_choice;
            
            if(clock==3){
                clock=-1;
                cc+=1;
                System.out.println("Special Move Deactivated");
            }
            
            if(clock>=0){
                clock=clock+1;
            }
            
            
            if(move_choice==1){
                

                double orig=M.getHP();
                ATTACK(H, M);
                
                if(clock>0 && !H.getType().equals("Thief")){
                    double orig1=H.getHP();
                    SPECIAL(H, M,cc);
                    System.out.println("Performing special attack");
                    
                    if(H.getType().equals("Warrior")){
                        // if(M.getHP()<=0){
                        //     M.setHP(0);
                        // }
                        
                        if(H.getHP()>orig1){
                            H.setHP(orig1);
                        }

                    }
                    if(H.getType().equals("Mage")){
                        // M.setHP((int)(M.getHP()*.95));
                    }



                }
            
                double diff=orig-M.getHP();
                System.out.println("You attacked and inflicted "+Math.abs(diff)+" damage to the monster");

                if(M.getHP()<=0){
                    M.setHP(0);
                }
                System.out.println("Your HP: "+H.getHP()+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+M.getHP()+"/"+((M.getLevel()+1)*50));
                
                //monster killed
                if(M.getHP()==0){
                    M.setKilled(true);
                    H.setXP(H.getXP()+(20*(M.getLevel())));

                    if(H.getHP()>=20 && H.getHP()<40){
                        H.setLevel(2);
                        H.setHP(150);
                    }
                    else if(H.getHP()>=40 && H.getHP()<60){
                        H.setLevel(3);
                        H.setHP(200);
                    }
                    else if(H.getHP()>=60){
                        H.setLevel(4);
                        H.setHP(250);
                    }
                    

                    System.out.println("Monster Killed!");
                    H.setHP((H.getLevel()+1)*50);
                    System.out.println("Fight won proceed to the next location");
                    Graph.set(ind, new Monster());
                    break;

                }
                
                
                else{
                    double ORIG=H.getHP();
                    MONSTER_ATTACK(H,M);
                    double DIFF=Math.abs(ORIG-H.getHP());
                    System.out.println("The monster attacked and afflicted "+DIFF+" damage to you");

                    if(H.getHP()<=0){
                        H.setHP(0);
                    }
                    System.out.println("Your HP: "+H.getHP()+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+M.getHP()+"/"+((M.getLevel()+1)*50));
                    
                    if(H.getHP()<=0){
                        M.setHP((M.getLevel()+1)*50);
                        System.out.println("Hero Killed!");
                        System.out.println("Fight lost, Restart");
                        H.setHP(100);
                        H.setLevel(1);
                        H.setLocation(-1);
                        H.setPrev_location(-1);
                        H.setTiktok(0);
                        H.setXP(0);
                        H.LEVEL=0;
                        break;
                    }
                }

            }
            else if(move_choice==2){

                


                double orig=H.getHP();
                DEFENSE(H, M);

                if(clock>0 && !H.getType().equals("Thief")){
                    double orig1=H.getHP();
                    SPECIAL(H, M,cc);
                    System.out.println("Performing special attack");
                    
                    if(H.getType().equals("Warrior")){
                        if(M.getHP()<=0){
                            M.setHP(0);
                        }
                        
                        // if(H.getHP()>orig1){
                        //     H.setHP(orig1);
                        // }

                    }
                    else if(H.getType().equals("Mage")){
                        if(M.getHP()<=0){
                            M.setHP(0);
                        }
                    }
                    
                    else{
                        // if(H.getHP()>orig1){
                        //     H.setHP(orig1);
                        // }

                    }




                }
                double diff=H.getHP()-orig;

                if(H.getHP()>(H.getLevel()+1)*50  ){
                    H.setHP((H.getLevel()+1)*50);
                }
                

                System.out.println("Monster attack reduced by "+diff+"!");
                System.out.println("Your HP: "+(H.getHP()-diff)+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+M.getHP()+"/"+((M.getLevel()+1)*50) );
                

                double ORIG=H.getHP();
                MONSTER_ATTACK(H,M);
                double DIFF=Math.abs(ORIG-H.getHP());
                System.out.println("The monster attacked and afflicted "+DIFF+" damage to you");

                if(H.getHP()<=0){
                    H.setHP(0);
                }
                if(H.getHP()>orig){
                    H.setHP(orig);
                }
                System.out.println("Your HP: "+H.getHP()+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+M.getHP()+"/"+((M.getLevel()+1)*50));
                
                if(H.getHP()<=0){
                    System.out.println("Hero Killed!");
                    System.out.println("Fight lost, Restart");
                    H.setHP(100);
                    H.setLevel(1);
                    H.setLocation(-1);
                    H.setPrev_location(-1);
                    H.setTiktok(0);
                    H.setXP(0);
                    H.LEVEL=0;
                    break;
                }


            }
            else{
                // SPECIAL(H, M);
                System.out.println("Special Power Activated");
                clock=0;
                if(H.getType().equals("Thief")){
                    double orig=M.getHP();
                    SPECIAL(H, M,cc);
                    double diff=M.getHP()-orig;



                    System.out.println("You have stolen "+Math.abs(diff)+" HP from the Monster");
                    if(M.getHP()<=0){
                        M.setHP(0);
                    }
                    System.out.println("Your HP: "+H.getHP()+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+M.getHP()+"/"+((M.getLevel()+1)*50));

                    //monster killed
                    if(M.getHP()==0){
                        M.setKilled(true);
                        H.setXP(H.getXP()+(20*(M.getLevel())));

                        if(H.getHP()>=20 && H.getHP()<40){
                            H.setLevel(2);
                            H.setHP(150);
                        }
                        else if(H.getHP()>=40 && H.getHP()<60){
                            H.setLevel(3);
                            H.setHP(200);
                        }
                        else if(H.getHP()>=60){
                            H.setLevel(4);
                            H.setHP(250);
                        }
                        

                        System.out.println("Monster Killed!");
                        H.setHP((H.getLevel()+1)*50);
                        System.out.println("Fight won proceed to the next location");
                        Graph.set(ind, new Monster());
                        break;

                    }
                    
                    
                    else{
                        double ORIG=H.getHP();
                        MONSTER_ATTACK(H,M);
                        double DIFF=Math.abs(ORIG-H.getHP());
                        System.out.println("The monster attacked and afflicted "+DIFF+" damage to you");

                        if(H.getHP()<=0){
                            H.setHP(0);
                        }
                        System.out.println("Your HP: "+H.getHP()+"/"+((H.getLevel()+1)*50)+" Monsters HP: "+M.getHP()+"/"+((M.getLevel()+1)*50));
                        
                        if(H.getHP()<=0){
                            M.setHP((M.getLevel()+1)*50);
                            System.out.println("Hero Killed!");
                            System.out.println("Fight lost, Restart");
                            H.setHP(100);
                            H.setLevel(1);
                            H.setLocation(-1);
                            H.setPrev_location(-1);
                            H.setTiktok(0);
                            H.setXP(0);
                            H.LEVEL=0;
                            break;
                        }
                    }




                }
            }

            kish+=1;

        }
        
        
        

    }

    public void ATTACK(Hero H, Monster M){
        System.out.println("You choose to attack");
        if(H.getType().equals("Warrior")){
            Hero W=new Warrior();
            W.attack(M);
        }
        else if(H.getType().equals("Thief")){
            Hero T=new Thief();
            T.attack(M);
        }
        else if(H.getType().equals("Mage")){
            Hero MA=new Mage();
            MA.attack(M);
        }
        else{
            Hero HE=new Healer();
            HE.attack(M);
        }

    }

    public void DEFENSE(Hero H, Monster M){
        System.out.println("You choose to defend");
        if(H.getType().equals("Warrior")){
            Warrior W=new Warrior();
            double orig=W.getHP();
            W.defense();
            double diff=W.getHP()-orig;
            H.setHP(H.getHP()+diff);
        }

        else if(H.getType().equals("Thief")){
            Thief T=new Thief();
            double orig=T.getHP();
            T.defense();
            double diff=T.getHP()-orig;
            H.setHP(H.getHP()+diff);
        }

        else if(H.getType().equals("Mage")){
            Mage MA=new Mage();
            double orig=MA.getHP();
            MA.defense();
            double diff=MA.getHP()-orig;
            H.setHP(H.getHP()+diff);
        }

        if(H.getType().equals("Healer")){
            Healer HE=new Healer();
            double orig=HE.getHP();
            HE.defense();
            double diff=HE.getHP()-orig;
            H.setHP(H.getHP()+diff);
        }
    }

    public void SPECIAL(Hero H, Monster M,int cc){
        // System.out.println("Special power activated");
       
        if(H.getType().equals("Warrior")){
            Warrior W=new Warrior();
            W.special_attack(M,5*cc,5*cc);
            
            H.setHP(H.getHP()+(W.getHP()-(W.getLevel()+1)*50));
            // M.setHP(M.getHP()-this.a);
            // this.setHP(this.getHP()+this.d);
        }

        else if(H.getType().equals("Mage")){
            Mage MA=new Mage();
            MA.special_attack(M);
            // M.setHP((0.95)*M.getHP());
        }

        else if(H.getType().equals("Thief")){
            Thief T=new Thief();
            T.special_attack(M);
            // M.setHP((int)(0.7*M.getHP()));
        }

        else if(H.getType().equals("Healer")){
            Healer HE=new Healer();
            HE.special_attack();
            H.setHP((int)((H.getHP()*1.05)));
            // this.setHP((int)(this.getHP()*1.05));

        }

        


    }

    public void MONSTER_ATTACK(Hero H, Monster M){
        System.out.println("Monster attack");
        M.attack(H);
    }
    public void BOSS_ATTACK(Hero H,Boss B){
        System.out.println("Lionfang attack");
        B.attack(H);
    }


    
}




abstract class Hero{
    private int level;
    private double HP;
    private double XP;
    private int location;
    private int prev_location;
    private String type;
    private int tiktok;
    int LEVEL;
    boolean c=false;
     int a=10;
     int d=3;
    
    public int getA() {
        return this.a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public void setD(int d) {
        this.d = d;
    }
    
    
    
        public int getD() {
            return this.d;
        }
    


    public Hero(){
        this.LEVEL=0;
        this.level=1;
        this.HP=100;
        this.XP=0;
        this.location=-1;
        this.prev_location=-1;
        this.tiktok=0;
    }
    public int getTiktok() {
        return this.tiktok;
    }
    public void setTiktok(int tiktok) {
        this.tiktok = tiktok;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getPrev_location() {
        return this.prev_location;
    }
    public void setPrev_location(int prev_location) {
        this.prev_location = prev_location;
    }
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public double getHP() {
    	return this.HP;
    }
    public void setHP(double HP) {
    	this.HP = HP;
    }
    public double getXP() {
    	return this.XP;
    }
    public void setXP(double XP) {
    	this.XP = XP;
    }
    public int getLocation() {
    	return this.location;
    }
    public void setLocation(int location) {
    	this.location = location;
    }
    public abstract void attack(Monster M);
}





class Warrior extends Hero{
    
    final int a=10;
    final int d=3;
   
    
    public Warrior(){
        super();
    }
    @Override
    public void attack(Monster M){
        M.setHP(M.getHP()-this.a);
    }

    public void defense(){
        this.setHP(this.getHP()+this.d);
    }

    public void special_attack(Monster M,int x,int y){
        this.setA(this.getA()+x);
        this.setD(this.getD()+y);
        M.setHP(M.getHP()-this.getA()+10);
        this.setHP(this.getHP()+this.getD()-3);
    }

}





class Mage extends Hero{
    final int a=5;
    final int d=5;
    public Mage(){
        super();
    }
    @Override
    public void attack(Monster M){
        M.setHP(M.getHP()-this.a);
    }

    public void defense(){
        this.setHP(this.getHP()+this.d);
    }

    public void special_attack(Monster M){
        M.setHP((int)((0.95)*M.getHP()));
    }

}






class Thief extends Hero{
    final int a=6;
    final int d=4;

    public Thief(){
        super();
    }
    @Override
    public void attack(Monster M){
        M.setHP(M.getHP()-this.a);
    }

    public void defense(){
        this.setHP(this.getHP()+this.d);
    }

    public void special_attack(Monster M){
        M.setHP((int)(0.7*M.getHP()));
    }

}






class Healer extends Hero{
    final int a=4;
    final int d=8;

    public Healer(){
        super();
    }
    @Override
    public void attack(Monster M){
        M.setHP(M.getHP()-this.a);
    }

    public void defense(){
        this.setHP(this.getHP()+this.d);
    }

    public void special_attack(){
        this.setHP((int)(this.getHP()*1.05));
    }

}










class Monster{
    private double HP;
    private int level;
    private boolean killed;

    
    public Monster(){
        this.killed=false;
        Random r=new Random();
        int R=r.nextInt(3);
        this.level=(int)R+1;
        
        
        if(this.level==1){
            this.HP=100;
        }
        else if(this.level==2){
            this.HP=150;
        }
        else if(this.level==3){
            this.HP=200;
        }
        else{
            this.HP=250;
        }
    }
    public double getHP() {
        return this.HP;
    }
    public void setHP(double HP) {
        this.HP = HP;
    }
    public int getLevel() {
    	return this.level;
    }
    public void setLevel(int level) {
    	this.level = level;
    }
    public boolean getKilled() {
        return this.killed;
    }
    public void setKilled(boolean killed) {
        this.killed = killed;
    }
    

    public void attack(Hero H){
        double range=10000;
        while(range>=this.getHP()/4){
            Random ran=new Random(); 
            double nxt=ran.nextGaussian(); 
            double frac=(this.getHP()/8);
            range=Math.abs((int)  (frac+(frac*nxt)) );
        }
        
        H.setHP((H.getHP()-range));
    }
}

// class Goblin extends Monster{
    
// 	public Goblin() {
//         super();
//         this.setLevel(1); 
//         this.setHP(100);
// 	}	
	
// }

// class Zombie extends Monster{
	
// 	public Zombie() {
//         super();
//         this.setLevel(1); 
//         this.setHP(100);
// 	}
// }

// class Fiend extends Monster{
	
// 	public Fiend() {
// 		super();
// 	}
// }


// class Boss extends Monster{
//     final private int level=4;
//     final private int p_gen=10;

//     public Boss(){
//         super();
//     }

//     @Override
//     public void attack(Hero H){
//         ArrayList<Integer> l=new ArrayList<>();
//         l.add(0);
//         for(int i=0;i<9;i++){
//             l.add(1);
//         }
//         Random rand = new Random(); 
//         int sel= rand.nextInt(p_gen);

//         if(l.get(sel)==0){
//             H.setHP(.5*H.getHP());
//         }
//         else{
//             super.attack(H);
//         }
//     }
// }


// public class lab3{
//     public static void main(String[] args){
//         Application A=new Application();
//         A.app_start();
//     }
// }

