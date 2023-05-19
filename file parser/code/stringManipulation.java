import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class stringManipulation {

    public static String dataLooperNLU() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("file parser/input/US_Capitals.csv"));
            String line;
            String content="";
            while ((line = br.readLine()) != null) {
                content = content + getNLU(getState(line));
            }

            br.close();
            return content;

        } catch (Exception e) {
            System.out.println("An error has occurred.");
            return "ERROR";
        }
    }

    public static String dataLooperDomain() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("file parser/input/US_Capitals.csv"));
            String line;
            String content="";
            while ((line = br.readLine()) != null) {
                content = content + getDomain(line);
            }

            br.close();
            return content;

        } catch (Exception e) {
            System.out.println("An error has occurred.");
            return "ERROR";
        }
    }

    public static String dataLooperIntent() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("file parser/input/US_Capitals.csv"));
            String line;
            String content="";
            while ((line = br.readLine()) != null) {
                content = content + getIntent(line);
            }

            br.close();
            return content;

        } catch (Exception e) {
            System.out.println("An error has occurred.");
            return "ERROR";
        }
    }

    public static String dataLooperRules() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("file parser/input/US_Capitals.csv"));
            String line;
            String content="";
            while ((line = br.readLine()) != null) {
                content = content + getRules(line);
            }

            br.close();
            return content;

        } catch (Exception e) {
            System.out.println("An error has occurred.");
            return "ERROR";
        }
    }


    public static void mother() {

        //String finalStr = "NLU:\n\n" + dataLooperNLU() + "\n\nINTENT:\n\n" + dataLooperIntent() + "\n\nDOMAIN:\n\n" + dataLooperDomain() + "\n\nRULES:\n\n" + dataLooperRules();

        try {
            FileWriter myWriter = new FileWriter("file parser/output/RASA-READY-NLU.txt");
            myWriter.append(dataLooperNLU());

            myWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
                FileWriter myWriter2 = new FileWriter("file parser/output/RASA-READY-DOMAIN.txt");
                myWriter2.write("INTENT:\n\n" + dataLooperIntent() + "\n\nDOMAIN:\n\n" + dataLooperDomain() );
                myWriter2.close();

        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        try {
                FileWriter myWriter3 = new FileWriter("file parser/output/RASA-READY-RULES.txt");
                myWriter3.write(dataLooperRules());
                myWriter3.close();

        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
            
    }

    
    public static String getCapital(String line) {
        try {

            while(line != null)
            {             
                String state = line.substring(0, line.indexOf(", "));
                return state;
            }
        } catch (Exception e) {
            System.out.println("An error has occurred.");
        }

        

        return "ERROR";
    }

    public static String getState(String line) {
        try {

            while(line != null)
            {             
                String capital = line.substring(line.indexOf(", ")+1, line.length());
                return capital;
            }
        } catch (Exception e) {
            System.out.println("An error has occurred.");
        }

        return "ERROR";
    }

    public static String getNLU(String line) {
        String state = getState(line);
        try {

            while(state != null)
            {
                state=state.toLowerCase().trim();
                String nlu = "- intent: capital_" + state.replace(" ", "_") +"\n  examples: |\n    - what is the capital of " + state.trim() +"\n    - " + state.trim()+" capital\n    - capital of " +state.trim()+ "\n    - "+state.trim()+"\n\n";
                return nlu;
            }
        } catch (Exception e) {
            System.out.println("An error has occurred.");
        }

        return "ERROR";
    }

    public static String getDomain(String line) {
        try {

            while(line != null)
            {
                String state=getState(line).trim();
                String capital=getCapital(line);
    
                //state=state.toLowerCase();
                String nlu = "  utter_" + state.replace(" ", "_").toLowerCase() +":\n  - text: \"The capital of " + state+ " is " + capital+ "\"\n\n";
                
                return nlu;
            }
        } catch (Exception e) {
            System.out.println("An error has occurred.");
        }

        return "ERROR";
    }

    public static String getIntent(String line) {
        try {

            while(line != null)
            {
                String state=getState(line).trim();
    
                state=state.toLowerCase();
                String intent = "  - capital_" + state.replace(" ", "_") +"\n";
                
                return intent;
            }
        } catch (Exception e) {
            System.out.println("An error has occurred.");
        }

        return "ERROR";
    }


  public static String getRules(String line) {
    try {

        while(line != null)
        {
            String state=getState(line).trim();

            state=state.toLowerCase();
            String rule = "- rule: Give " + state.trim() + " capital\n  steps:\n  - intent: capital_" +   state.replace(" ", "_") +"\n  - action: utter_"+ state.replace(" ", "_") +"\n\n";

            return rule;
        }
    } catch (Exception e) {
        System.out.println("An error has occurred.");
    }

    return "ERROR";
}

    public static void main(String[] args) {
        mother();

    }

}