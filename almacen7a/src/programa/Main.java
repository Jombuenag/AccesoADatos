package programa;

public class Main {

	public static void main(String[] args) {

		Session session = HibernateUtilities.getSessionFactory().openSession();
	}

}
