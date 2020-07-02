import java.util.*;

interface User{
    public void Display();
}

class Merchant implements User {
    private String name;
    private int address;
    private int contribution=0;

    //item
    ArrayList<Integer> id= new ArrayList<>();
    private ArrayList<String> i_name=new ArrayList<>();
    private ArrayList<Double> i_price=new ArrayList<>();
    private ArrayList<Integer> i_quantity=new ArrayList<>();
    private ArrayList<String> i_category=new ArrayList<>();
    private ArrayList<String> offer=new ArrayList<>();
    int C=0;

    public Merchant(String name, int address, int contribution){
        this.name=name;
        this.address=address;
        this.contribution=contribution;
    }

    @Override
    public void Display(){
            System.out.println("Merchant details are:");
            System.out.println("Merchant Name: "+this.getName());
            System.out.println("Merchant Address: " +this.getAddress());
            System.out.println("Contribution: "+this.getContribution());
    }
    
    public void Edit(double price, int quantity, int ind){
        this.i_price.set(ind,price);
        this.i_quantity.set(ind,quantity);
    }

    //item to cart
    public void Add(String name, double price, int quantity, String category){
        this.i_name.add(name);
        this.i_price.add(price);
        this.i_quantity.add(quantity);
        this.i_category.add(category);
        this.offer.add("None");
        this.C+=1;
        this.id.add(C);
    }

    public int search_by_categ(Merchant m, String category){
        int IND=-100;
        for(int i=0;i<m.i_category.size();i++){
            if(m.i_category.get(i).equals(category)){
                IND=i;
            }
        }
        return IND;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAddress(){
        return address;
    }
    public void setAddress(int address){
        this.address=address;
    }
    public int getContribution(){
        return contribution;
    }
    public void setContribution(int contribution){
        this.contribution+=contribution;
    }
    public void setItem_quantity(int val, int ind){ 
        this.i_quantity.set(ind, val); 
    }
    
    public String getOffer(int ind) {
        return offer.get(ind);
    }
    public void setOffer(String val, int ind) {
        offer.set(ind, val);
    }
    public String geti_name(int ind) {
        return i_name.get(ind);
    }
    public void seti_name(int ind,String val) {
        this.i_name.set(ind,val);
    }
    public double geti_price(int ind) {
        return i_price.get(ind);
    }
    public void seti_price(int ind, double val) {
        this.i_price.set(ind,val);
    }
    public int geti_quantity(int ind) {
        return i_quantity.get(ind);
    }
    public void seti_quantity(int ind, int quantity) {
        this.i_quantity.set(ind,quantity);
    }
    public String geti_category(int ind) {
        return i_category.get(ind);
    }
    public void seti_category(int ind, String val) {
        this.i_category.set(ind,val);
    }
}




class Customer implements User{
    int Reward=0;
    private String name;
    private int address;
    int C;
    private ArrayList<String> placed_ord=new ArrayList<>();
    int reward_bal;
    int account_bal;
    int purchase=0;


    static ArrayList<String> CATEGORY=new ArrayList<>();
    ArrayList<String> cart_i_name=new ArrayList<>();
    ArrayList<Integer> cart_i_quantity=new ArrayList<>();
    ArrayList<Double> cart_i_price=new ArrayList<>();

    ArrayList<Merchant> cart_m=new ArrayList<>();
    ArrayList<String> cart_m_name=new ArrayList<>();
    ArrayList<Integer> cart_m_i_ind=new ArrayList<>();

    public Customer(String name, int address, int placed_ord){
        this.name=name;
        this.address=address;
        this.C=placed_ord;
        this.reward_bal=0;
        this.account_bal=100;
        this.purchase=0;
    }
    @Override
    public void Display(){
        System.out.println("Customer details are:");
        System.out.println("Customer Name: "+this.getName());
        System.out.println("Customer Address: " +this.getAddress());
        System.out.println("No. of orders: "+this.purchase);
    
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAddress(){
        return address;
    }
    public void setAddress(int address){
        this.address = address;
    }
    public ArrayList<String> get_placed_ord(){
        return placed_ord;
    }

    public void add_CATEGORY(String category){
        if(! this.CATEGORY.contains(category)){
            this.CATEGORY.add(category);
        }
    }
    public void set_placed_ord(String placed_ord){
        this.placed_ord.add(placed_ord);
    }
}



public class Main{
    public static void main(String[] args){

        Scanner scan=new Scanner(System.in);
        
        ArrayList<Merchant> M=new ArrayList<>();
        Merchant m1=new Merchant("jack",1,0);
        M.add(m1);
        Merchant m2=new Merchant("john",2,0);
        M.add(m2);
        Merchant m3=new Merchant("james",3,0);
        M.add(m3);
        Merchant m4=new Merchant("jeff",4,0);
        M.add(m4);
        Merchant m5=new Merchant("joseph",5,0);
        M.add(m5);


        ArrayList<Customer> C=new ArrayList<>();
        Customer c1=new Customer("ali",1,0);
        C.add(c1);
        Customer c2=new Customer("nobi",2,0);
        C.add(c2);
        Customer c3=new Customer("bruno",3,0);
        C.add(c3);
        Customer c4=new Customer("borat",4,0);
        C.add(c4);
        Customer c5=new Customer("aladeen",5,0);
        C.add(c5);


        int COMPANY_gain=0;
        while(1==1){
            System.out.println("Welcome to Mercury");
            System.out.println("1) Enter as Merchant");
            System.out.println("2) Enter as Customer");
            System.out.println("3) See user details");
            System.out.println("4) Company account balance");
            System.out.println("5) Exit");
            
            int query=scan.nextInt();
            if(query==1){
                System.out.println("Choose Merchant");
                for(int i=0;i<M.size();i++){
                    System.out.println(M.get(i).getAddress()+" "+M.get(i).getName());
                }
                int ABC=-1;
                int q=scan.nextInt();
                Merchant temp=M.get(q-1);//player
                
                while(ABC!=6){
                    System.out.println("Welcome "+temp.getName());
                    System.out.println("Merchant Menu");
                    System.out.println("1) Add Item");
                    System.out.println("2) Edit item");
                    System.out.println("3) Search by category");
                    System.out.println("4) Add offer");
                    System.out.println("5) Rewards won");
                    System.out.println("6) Exit");
                    ABC=scan.nextInt();

                    if(ABC==1){
                        System.out.println("Enter item details");
                        System.out.println("item name:");
                        String name=scan.next();
                        System.out.println("item price:");
                        double price=scan.nextInt();
                        System.out.println("item quantity:");
                        int quantity=scan.nextInt();
                        System.out.println("item category:");
                        String category=scan.next();
                        temp.Add(name,price,quantity,category);
                        System.out.println(temp.C+" "+name+" "+price+" "+quantity+" "+"None "+category);
                        
                        if(! Customer.CATEGORY.contains(category)){
                            Customer.CATEGORY.add(category);
                        }
                    }


                    else if(ABC==2){
                        System.out.println("choose item by code");
                        for(int i=0;i<temp.C;i++){
                            System.out.println(temp.id.get(i)+" "+temp.geti_name(i)+" "+temp.geti_price(i)+" "+temp.geti_quantity(i)+" "+temp.getOffer(i)+" "+temp.geti_category(i));
                        }

                        int x=scan.nextInt();
                        System.out.println("Enter edit details");
                        System.out.println("item price:");
                        double price=scan.nextInt();
                        System.out.println("item quantity:");
                        int quantity=scan.nextInt();
                        temp.Edit(price,quantity,x-1);
                        System.out.println(temp.id.get(x-1)+" "+temp.geti_name(x-1)+" "+temp.geti_price(x-1)+" "+temp.geti_quantity(x-1)+" "+temp.getOffer(x-1)+" "+temp.geti_category(x-1));
                    }

                    else if(ABC==3){
                        System.out.println("Choose a Category");
                        for(int i=0;i<temp.C;i++){
                            System.out.println(temp.id.get(i)+") "+temp.geti_category(i));
                        }

                        int x=scan.nextInt();
                        for(int i=0;i<M.size();i++){
                            int ind=M.get(i).search_by_categ(M.get(i), temp.geti_category(x-1));

                            if(ind!=-100){
                                System.out.println(M.get(i).id.get(ind)+" "+M.get(i).geti_name(ind)+" "+M.get(i).geti_price(ind)+" "+M.get(i).geti_quantity(ind)+" "+M.get(i).getOffer(ind)+" "+M.get(i).geti_category(ind));
                            }
                        }
                    }


                    else if(ABC==4){
                        System.out.println("choose item by code");
                        for(int i=0;i<temp.C;i++){
                            System.out.println(temp.id.get(i)+" "+temp.geti_name(i)+" "+temp.geti_price(i)+" "+temp.geti_quantity(i)+" "+temp.getOffer(i)+" "+temp.geti_category(i));
                        }
                        int x=scan.nextInt();
                        System.out.println("1) buy one get one");
                        System.out.println("2) 25% off");
                        
                        int E=scan.nextInt();
                        if(E==1){
                            temp.setOffer("BOGO", x-1);
                        }
                        else if(E==2){
                            temp.setOffer("25%", x-1);
                        }
                        
                        System.out.println(temp.id.get(x-1)+" "+temp.geti_name(x-1)+" "+temp.geti_price(x-1)+" "+temp.geti_quantity(x-1)+" "+temp.getOffer(x-1)+" "+temp.geti_category(x-1));
                        
                    
                    }
                    else if(ABC==5){
                        System.out.println("Rewards won: "+temp.getContribution());
                    }

                }




            }
            else if(query==2){
                System.out.println("Choose Customer");
                for(int i=0;i<C.size();i++){
                    System.out.println((i+1)+" "+C.get(i).getName());
                }
                
                int q=scan.nextInt();
                Customer temp=C.get(q-1);//player

                while(1==1){
                    System.out.println("Welcome "+temp.getName());
                    System.out.println("Customer Menu");
                    System.out.println("1) Search Item");
                    System.out.println("2) Checkout Cart");
                    System.out.println("3) Reward Won");
                    System.out.println("4) Print Latest Orders");
                    System.out.println("5) Exit");
                    
                    int ABC=scan.nextInt();
                    
                    if(ABC==1){
                        System.out.println("Choose a category");
                        for(int i=0;i<Customer.CATEGORY.size();i++){
                            System.out.println((i+1)+") "+Customer.CATEGORY.get(i));
                        }

                        int x=scan.nextInt();
                        String cat_name=Customer.CATEGORY.get(x-1);
                        System.out.println("choose item by code");
                        ArrayList<Merchant> l=new ArrayList<>();
                        int track=0;

                        for(int i=0;i<M.size();i++){
                            int ind=M.get(i).search_by_categ(M.get(i), cat_name);
                            if(ind==-100){
                                // System.out.println("Error");
                            }
                            else{
                                l.add(M.get(i));
                                track+=1;
                                System.out.println(track+" "+M.get(i).geti_name(ind)+" "+M.get(i).geti_price(ind)+" "+M.get(i).geti_quantity(ind)+" "+M.get(i).getOffer(ind)+" "+M.get(i).geti_category(ind));
                            }
                        }

                        System.out.println("Enter item code");
                        int i_code=scan.nextInt();
                        System.out.println("Enter item quantity");
                        int i_quantity=scan.nextInt();
                        System.out.println("choose method of transaction");
                        System.out.println("1) Buy item");
                        System.out.println("2) Add item to cart");
                        System.out.println("3) Exit");
                        int query_tran=scan.nextInt();

                        double PRICE;
                        double del=0;

                        int QUANTITY=0;
                        int pre=(temp.purchase/5);
                        int post=(temp.purchase+1/5);
                        Merchant MM=l.get(i_code-1);
                        int ind=MM.search_by_categ(MM, cat_name);

                        if(MM.getOffer(ind).equals("25%")){
                            del=(MM.geti_price(ind)*i_quantity*75)/100;
                            PRICE=(del*100.5)/100;
                        }
                        else{
                            del=MM.geti_price(ind)*i_quantity;
                            PRICE=(MM.geti_price(ind))*(i_quantity)*(100.5/100);
                        }
                        if(MM.getOffer(ind).equals("BOGO")){
                            QUANTITY=i_quantity;
                        }


                        
                        
                        
                        //buy item
                        if(query_tran==1){
                            if(i_quantity<=MM.geti_quantity(ind)){

                                if(temp.account_bal+temp.reward_bal>=PRICE){
                                    temp.purchase+=1;
                                    if(temp.account_bal>=PRICE){
                                        temp.account_bal-=PRICE;
                                    }
                                    else{
                                        PRICE-=temp.account_bal;
                                        temp.reward_bal-=PRICE;
                                        temp.account_bal=0;
                                    }

                                    if(post>pre){
                                        int lelo=(post-pre)*10;
                                        temp.Reward+=lelo;
                                        temp.reward_bal+=lelo;
                                    }

                                    COMPANY_gain+=(PRICE*(1/100));
                                    MM.setContribution((int) (del * (0.5 / 100)));
                                    MM.seti_quantity(ind,MM.geti_quantity(ind)-i_quantity);

                                    if(MM.geti_quantity(ind)<QUANTITY){
                                        i_quantity+=MM.geti_quantity(ind);
                                        MM.setItem_quantity(0, ind);
                                    }
                                    else{
                                        i_quantity+=QUANTITY;
                                        MM.setItem_quantity(MM.geti_quantity(ind)-QUANTITY, ind);
                                    }

                                    System.out.println("Item successfully bought");
                                    String Final="Bought item "+MM.geti_name(ind)+" quantity: "+i_quantity+" for Rs. "+PRICE+" from Merchant "+MM.getName();
                                    temp.set_placed_ord(Final);
                                }
                                else{
                                    System.out.println("Insufficient balance");
                                }
                            }
                            else{
                                System.out.println("Insufficient quantity");
                            }
                        }
                        else if(query_tran==2){
                            temp.cart_i_name.add(MM.geti_name(ind));
                            temp.cart_i_price.add(PRICE);
                            temp.cart_i_quantity.add(i_quantity);
                            temp.cart_m.add(MM);
                            temp.cart_m_name.add(MM.getName());
                            temp.cart_m_i_ind.add(ind);
                            System.out.println("Item added to cart successfully");
                        }



                    }

                    else if(ABC==2){
                        for(int i=0;i<temp.cart_i_name.size();i++){
                            
                            Merchant MM=temp.cart_m.get(i);
                            int QUANTITY=0;
                            String c_i_name=temp.cart_i_name.get(i);
                            String c_m_name=temp.cart_m_name.get(i);
                            double c_price=temp.cart_i_price.get(i);
                            int c_quantity=temp.cart_i_quantity.get(i);
                            int IND=temp.cart_m_i_ind.get(i);

                            if(MM.getOffer(IND).equals("BOGO")){
                                QUANTITY=c_quantity;
                            }
                            else if(MM.getOffer(IND).equals("25%")){
                                c_price=(75/100)*c_price;
                            }


                            if(c_quantity<=MM.geti_quantity(IND)){
                                if(temp.account_bal+temp.reward_bal>=c_price){
                                    if(temp.account_bal>=c_price){
                                        temp.account_bal-=c_price;
                                    }
                                    else{
                                        c_price-=temp.account_bal;
                                        temp.reward_bal+=c_price;
                                        temp.account_bal=0;
                                    }
    
                                    int pre=(temp.purchase)/5;
                                    int post=(1+temp.purchase)/5;
                                    MM.setItem_quantity(MM.geti_quantity(IND)-c_quantity ,IND);
                                    
                                    if(post>pre){
                                        int lelo=(post-pre)*10;
                                        temp.Reward+=lelo;
                                        temp.reward_bal+=lelo;
                                    }
    
                                    if(QUANTITY>MM.geti_quantity(IND)){
                                        c_quantity+=MM.geti_quantity(IND);
                                        MM.setItem_quantity(0, IND);
                                    }
                                    else{
                                        c_quantity+=QUANTITY;
                                        MM.seti_quantity(MM.geti_quantity(IND)-QUANTITY, IND);
    
                                    }
                                    //purchased
                                    temp.purchase+=1;
                                    MM.setContribution((int)((0.5/100)*c_price));
                                    COMPANY_gain+=(1/100)*c_price;
                                    String Final="Bought item "+c_i_name+" quantity: "+c_quantity+" for Rs. "+c_price+" from Merchant "+c_m_name;
                                    temp.set_placed_ord(Final);
    
                                    }
                                }
                        }

                        //emptying cart
                        while(temp.cart_m.size()>0){
                            temp.cart_i_quantity.remove(0);
                            temp.cart_i_price.remove(0);
                            temp.cart_i_name.remove(0);
                            temp.cart_m.remove(0);
                            temp.cart_m_name.remove(0);
                            temp.cart_m_i_ind.remove(0);
                        }
                    }
                    else if(ABC==3){
                        System.out.println("Reward balance is "+temp.Reward);
                    }

                    else if(ABC==4){
                        if(temp.get_placed_ord().size()==0){
                            System.out.println("No order has been placed");
                        }
                        else{
                            if(temp.get_placed_ord().size()>10){
                                int end=temp.get_placed_ord().size()-1;
                                int cc=0;
                                while(cc!=10){
                                    cc+=1;
                                    System.out.println(temp.get_placed_ord().get(end));
                                    end-=1;

                                }

                            }
                            else{
                                int end=temp.get_placed_ord().size()-1;
                                while(end>=0){
                                    System.out.println(temp.get_placed_ord().get(end));
                                    end-=1;
                                }
                                
                            }
                        }

                    }
                    else if(ABC==5){
                        System.out.println("--End of sample test--");
                        System.exit(0);
                    }
                }
            }

            
            else if(query==3){
                System.out.println("Select a User:");
                String LL=scan.next();
                if(LL.equals("C")){
                    for(int i=0; i<C.size(); i++){
                        Customer temp=C.get(i);
                        System.out.println(temp.getAddress()+") "+temp.getName());
                    }
                    int x=scan.nextInt();
                    //polymorphism
                    Customer temp=C.get(x-1);
                    temp.Display();
                
                }

                else if(LL.equals("M")){
                    
                    for(int i=0; i<M.size(); i++){
                        Merchant temp=M.get(i);
                        System.out.println(temp.getAddress()+") "+temp.getName());
                    }
                    int x=scan.nextInt();
                    //polymorphism
                    Merchant temp=M.get(x-1);
                    temp.Display();

                }
                else{
                    System.out.println("Error");
                }
                
            }

            else if(query==4){
                System.out.println("Company Account Balance: "+COMPANY_gain);
            }
            else{
                System.out.println("--End of sample test--");
                break;
            }
        }
    }
}