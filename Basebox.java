import java.util.Base64;

public class Basebox {
    //功能后端
    public String input;
    Basebox(String bs){
        this.input=bs;

    }
    public String encoding(){
        String ou=new String();
        ou=Base64.getEncoder().encodeToString(input.getBytes());
        return ou;
    }
    public String encodehex(String ou){

        String hexs=new String();
        for (int i=0;i<ou.length();i++){
            Integer tmp=ou.indexOf(i);
            hexs=(hexs + tmp.toHexString(tmp));
        }
        return hexs;
    }
    public String decoding(){
        String ou=new String();
        byte[] dw=Base64.getDecoder().decode(input);
        try {
            ou = new String(dw, "UTF-8");
        }catch (Exception e4){};
        return ou;
    }
    public String decodehex(){
        byte[] ou=new byte[]{};
        ou=Base64.getDecoder().decode(input.getBytes());
        String su=ou.toString();
        int[]hexs=new int[su.length()];
        for (int i=0;i<su.length();i++){
            hexs[i]=su.indexOf(i);
        }
        return hexs.toString();
    }

}
