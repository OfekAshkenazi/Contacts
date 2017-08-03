package Classes;

/**
 * Created by Ofek on 31-Jul-17.
 */

public class Helper {
    public static String generateSaveableString(Contact contact){
        String saveAble="*"+contact.getName()+"/"+contact.getSurname()+"+"+contact.getPhone();
        return saveAble;
    }
    public static Contact extractUserFromString(String saveAble){
        String name=saveAble.substring(saveAble.indexOf('*')+1,saveAble.indexOf('/'));
        String surname=saveAble.substring(saveAble.indexOf('/')+1,saveAble.indexOf('+'));
        String phone=saveAble.substring(saveAble.indexOf('+')+1,saveAble.length());
        Contact contact=new Contact(name,surname,phone);
        return contact;
    }
    public static String getTitleName(Contact contact){
        return contact.getName()+" "+contact.getSurname();
    }
}
