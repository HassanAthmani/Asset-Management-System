
package asset_management_system.usedAlot;


public class escapeChar {
    public static String addChar(String x,int p ) {
       
       char ch='\\';
    int len = x.length();
    char[] updatedArr = new char[len + 1];
    x.getChars(0, p, updatedArr, 0);
    updatedArr[p] = ch;
    x.getChars(p, len, updatedArr, p + 1);
    return new String(updatedArr);
    }
    
    public static String escapeChar1(String u){        
        
        ///SHOWS LOCATION OF THE SPECIAL CHARACTERS
         //String password = "b_---b+bjkjl;'l;a;admk'adl'aka;dkdm;ad;admk'adl'aka;dkmk'adl'aka;dkk'adl'aka;dk;admk'adl'aka;dk";
        char c[] = u.toCharArray();
        
        int r = c.length, l = 0;
        int array[] = new int[100];

        while (l < r) {
            if(!Character.isLetterOrDigit(u.charAt(0))){
             System.out.println("special characters can not start.");
             break;
         }
            else{
            if (!Character.isLetterOrDigit(c[l])) {
                array[l] = l;
            }
            
            l++;
        }
        }
        ///REMOVES ALL THE ZEROS
        int targetIndex = 0;
        for (int sourceIndex = 0; sourceIndex < array.length; sourceIndex++) {
            if (array[sourceIndex] != 0) {
                array[targetIndex++] = array[sourceIndex];
            }
        }
        int[] newArray = new int[targetIndex];
        System.arraycopy(array, 0, newArray, 0, targetIndex);

        ///PRINTS THE NEW ARRAY WITHOUT ZEROS
        
        String new1=u;
        int a=0;
        for (int j = 0; j < newArray.length; j++) {
            String old;
            int position=newArray[j]+a;
            new1=addChar(new1,position);
            old=new1;
            
        
            a++;
             if(j==newArray.length){
               
                break;
               
            }
            
                  
        }
     return new1;   
    }
    
    
}
