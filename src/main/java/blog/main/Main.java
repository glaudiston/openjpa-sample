package blog.main;

import java.sql.Connection;
import java.time.temporal.ChronoUnit;
import java.time.ZonedDateTime;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import blog.entities.Topic;
import blog.entities.Post;

class Main {

	private void doNativeSelectTopic(Connection conn) {
		String selectClause = "SELECT ID, NAME FROM topic";
		try (PreparedStatement pstmt = conn.prepareStatement(selectClause)){
			try (ResultSet rs = pstmt.executeQuery() ) {
				while (rs.next()){
					System.out.println("Next... " + rs.getRow());
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EntityManagerFactory emf;
	private void initEntityManager(){
		emf = Persistence.createEntityManagerFactory("openjpa");
	}

	private void initJDBCDirectConnection() {
		System.out.println("Connecting to mysql database...");
		String userName="root";
		String password="pw@123";
		String url="jdbc:mysql://localhost:3306/test?passwordCharacterEncoding=utf8";
		String driver="com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, userName, password);
			Statement st = conn.createStatement();
			System.out.println("Connected!");
			doNativeSelectTopic(conn);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		System.out.println("Running...");
		Main m = new Main();
		// m.initJDBCDirectConnection();
		m.initEntityManager();
		EntityManager em = m.emf.createEntityManager();
		Topic t = em.find(Topic.class, 2);
		System.out.println("Topic="+t);
		System.out.println(DateBucket.bucketize(ZonedDateTime.now(), ZonedDateTime.now().plusMinutes(10), 1,  ChronoUnit.SECONDS));
		System.out.println("Done!");
	}
}
