import java.util.*;

class Company{
    final protected String name;
    protected ArrayList<String> criteria= new ArrayList<>();
    protected int req_students;
    protected String app_status;

    Company(String name, ArrayList<String> criteria, int req_students){
        this.name=name;
        this.criteria=criteria;
        this.req_students=req_students;
        this.app_status="OPEN";
    }
}
class Student{
    final protected int roll_no;
    final protected float cgpa;
    final protected String course;
    protected String place_stat;

    Student(int roll_no, float cgpa, String course){
        this.roll_no=roll_no;
        this.cgpa=cgpa;
        this.course=course;
        this.place_stat="Not Placed";
    }
}

public class Lab_1_2018137{
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);    
        int N=scan.nextInt();
        ArrayList<Student> student_list=new ArrayList<>();
        ArrayList<Company> company_list=new ArrayList<>();
        ArrayList<ArrayList<String>> scores=new ArrayList<>();

        for(int i=0;i<N;i++){
            float x=scan.nextFloat();   
            String y=scan.next();
            Student s= new Student(i+1,x,y);
            student_list.add(s);
        }
        System.out.println("---- students registered ----");

        
        while(1==1){
            int query1=scan.nextInt();
            
            if(query1==1){
                String comp_name=scan.next();
                System.out.print("Number of Eligible Courses = ");
                int elig=scan.nextInt();
                ArrayList<String> temp=new ArrayList<>();
                for(int i=0;i<elig;i++){
                    String c=scan.next();
                    temp.add(c);
                }

                System.out.print("Number of Required Students = ");
                int req=scan.nextInt();
                Company C=new Company(comp_name,temp,req);
                company_list.add(C);


                System.out.println(C.name);
                System.out.println("Course Criteria");
                for(int i=0;i<elig;i++){
                    System.out.println(temp.get(i));
                }
                System.out.println("Number of Required Students = "+C.req_students);
                System.out.println("Application Status = "+C.app_status);
                
                System.out.println("Enter scores for the technical round.");



                ArrayList<Integer> del= new ArrayList<>();
                
                for(int i=0;i<student_list.size();i++){
                    for(int j=0;j<temp.size();j++){
                        if(student_list.get(i).course.equals(temp.get(j)) && student_list.get(i).place_stat.equals("Not Placed")){
                            del.add(i+1);
                            break;
                        }
                    }
                }

                for(int i=0;i<del.size();i++){
                    System.out.println("Enter score for Roll no. "+del.get(i));
                    int Sc=scan.nextInt();
                    ArrayList<String> one=new ArrayList<>();
                    one.add(Integer.toString(del.get(i)));
                    one.add(Integer.toString(Sc));
                    one.add(C.name);
                    for(int p=0;p<student_list.size();p++){
                        if(student_list.get(p).roll_no==del.get(i)){
                            one.add(Float.toString(student_list.get(p).cgpa));
                            break;
                        }
                    }
                    scores.add(one);
                }
            }


            else if(query1==2){

                boolean c1=false;
                System.out.println("Accounts removed for");
                for(int i=0;i<student_list.size();i++){
                    if(student_list.get(i).place_stat.equals("Placed")){
                        c1=true;
                        System.out.println(student_list.get(i).roll_no);
                    }}
                if(c1!=true){
                    System.out.println("None");
                
                }
                
            }
            else if(query1==3){
                boolean c2=false;
                System.out.println("Accounts removed for");
                for(int i=0;i<company_list.size();i++){
                    if(company_list.get(i).app_status.equals("CLOSED")){
                        c2=true;
                        System.out.println(company_list.get(i).name);
                    }
                }

                if(c2!=true){
                    System.out.println("None");
                }
                
            }
            else if(query1==4){
                int count=0;
                for(int i=0;i<student_list.size();i++){
                    if(student_list.get(i).place_stat=="Not Placed"){
                        count+=1;
                    }
                }
                System.out.println(""+count+" students left.");
            }
            else if(query1==5){
                boolean c7=false;
                for(int i=0;i<company_list.size();i++){
                    if(company_list.get(i).app_status=="OPEN"){
                        c7=true;
                        System.out.println(company_list.get(i).name);
                    }
                }
                if(c7==false){
                    System.out.println("Applications are closed for all companies");
                }
                
            }
            else if(query1==6){
                boolean c3=false;
                for(int i=0;i<student_list.size();i++){
                    if(student_list.get(i).place_stat.equals("Not Placed")){
                        c3=true;
                    }
                }
                if(c3!=true){
                    System.out.println("All students are placed");
                    continue;
                }



                String COMPANY=scan.next();
                
                int ind=-1;
                
                for(int i=0;i<company_list.size();i++){
                    if(company_list.get(i).name.equals(COMPANY)){
                        ind=i;
                        break;
                    }
                }
                if(ind==-1){
                    System.out.println("No company with the given name has an account");
                    continue;
                }
                System.out.println("Roll Number of Selected Students");
                while(company_list.get(ind).req_students!=0){

                    int select_roll=0;
                    int max=0;
                    float gpa=0;
                    for(int k=0;k<scores.size();k++){
                        if(scores.get(k).get(2).equals(COMPANY) && (Integer.parseInt(scores.get(k).get(1))>max  || (Integer.parseInt(scores.get(k).get(1))==max &&  Float.parseFloat(scores.get(k).get(3))>gpa)  )){
                            select_roll=Integer.parseInt(scores.get(k).get(0));
                            max=Integer.parseInt(scores.get(k).get(1));
                            gpa=Float.parseFloat(scores.get(k).get(3));
                        }
                    }
                    if(select_roll==0){
                        System.out.println("None");
                        break;
                    }


                    System.out.println(select_roll);
                    for(int k=0;k<student_list.size();k++){
                        if(student_list.get(k).roll_no==select_roll){
                            student_list.get(k).place_stat=student_list.get(k).place_stat.replace(student_list.get(k).place_stat,"Placed");
                        }
                    }
                    company_list.get(ind).req_students-=1;
                    if(company_list.get(ind).req_students==0){
                        company_list.get(ind).app_status=company_list.get(ind).app_status.replace(company_list.get(ind).app_status,"CLOSED");
                    }
                    int k=0;
                    while(k<scores.size()){
                        if(scores.get(k).get(0).equals(Integer.toString(select_roll)) ){
                            scores.remove(k);
                        }
                        else{
                            k+=1;
                        }
                    }
                    int FINALE=0;
                    for(int u=0;u<student_list.size();u++){
                        if(student_list.get(u).place_stat.equals("Not Placed")){
                            FINALE+=1;
                        }
                    }
                    if(FINALE==0){
                        System.exit(0);
                    }
                }
            }
            else if(query1==7){
                boolean c4=false;
                String qq=scan.next();
                for(int i=0;i<company_list.size();i++){
                    if(company_list.get(i).name.equals(qq)){
                        c4=true;
                        System.out.println(company_list.get(i).name);
                        System.out.println("Course Criteria");
                        for(int j=0;j<company_list.get(i).criteria.size();j++){
                            System.out.println(company_list.get(i).criteria.get(j));
                        }
                        System.out.println("Number of Required Students = "+company_list.get(i).req_students);
                        System.out.println("Application Status = "+company_list.get(i).app_status);
                        break;
                    }
                }
                if(c4==false){
                    System.out.println("No company with the given name has an account");
                }
            }
            else if(query1==8){
                int query2=scan.nextInt();
                boolean c5=false;
                for(int i=0;i<student_list.size();i++){
                    if (student_list.get(i).roll_no==query2){
                        c5=true;
                        System.out.println(student_list.get(i).roll_no);
                        System.out.println(student_list.get(i).cgpa);
                        System.out.println(student_list.get(i).course);
                        System.out.println("Placement Status: "+student_list.get(i).place_stat);
                        break;
                    }
                }
                if(c5==false){
                    System.out.println("No student with the given roll number has an account");
                }
            }
            else if(query1==9){
                int R=scan.nextInt();
                boolean c6=false;
                for(int i=0;i<scores.size();i++){
                    if(scores.get(i).get(0).equalsIgnoreCase(Integer.toString(R))){
                        c6=true;
                        System.out.println(scores.get(i).get(2)+" "+scores.get(i).get(1));
                    }
                }
                if(c6==false){
                    System.out.println("No student with the given roll number has an account");
                }
            }
        }
    }
}