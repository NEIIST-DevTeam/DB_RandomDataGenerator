package Tables;

import Names.Names;


public class Student {

    private int person_oid;
    private String ist_id;
    private String ist_email;
    private String degree;
    private String campus;

    private Names names;


    public Student(int person_oid, String ist_id, String ist_email, String degree, String campus)
    {
        this.person_oid = person_oid;
        this.ist_id = ist_id;
        this.ist_email = ist_email;
        this.degree = degree;
        this.campus = campus;
    }

}
