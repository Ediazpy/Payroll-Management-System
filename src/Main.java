public class Main {
    public static void main(String[] args) {
        try {
            DataIO dio = new DataIO(); // creates DB and table if needed
            Employee emp = new Employee(1, "John Doe", "123 Main St", 500.0, 50.0, 20.0);
            dio.add(emp);

            for (Employee e : dio.getList()) {
                System.out.println(e.getDetails());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
