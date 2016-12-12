package Tables;

public class Student {

    private String ist_id;
    private String ist_email;
    private String degree;
    private String campus;


    public Student(String ist_id, String ist_email, String degree, String campus)
    {
        this.ist_id = ist_id;
        this.ist_email = ist_email;
        this.degree = degree;
        this.campus = campus;
    }

    public String getIst_id()
    {
        return ist_id;
    }

    public String getIst_email()
    {
        return ist_email;
    }

    public String getDegree()
    {
        return degree;
    }

    public String getCampus()
    {
        return campus;
    }

}
