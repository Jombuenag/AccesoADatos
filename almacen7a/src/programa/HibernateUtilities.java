package programa;

public class HibernateUtilities {

	private static SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory(){
		try{
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata metedata = new MetadataSources(standardRegisty).getMetadataBuilder().build();
			return metadata.getSessionFactoryBuilder().build();
			
		}catch(HibernateException e){
			System.err.println("ERROR EN HIBERNATE = " +e);
		}
		return sessionFactory;
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
