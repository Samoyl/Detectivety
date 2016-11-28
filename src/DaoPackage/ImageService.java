package DaoPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.ImageTable;;

public class ImageService implements ImageDAO {

	private static Connection connection;
	private static PreparedStatement stmt = null;

	public boolean Connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433; databaseName=Detective; integratedSecurity=true;");
			System.out.println("connected ok..");
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Disconnect() {
		try {
			if (connection != null) {
				connection.close();
			}
			connection = null;
			System.out.println("disconnected ok..");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<ImageTable> findAll() {
		Connect();
		List<ImageTable> imageTable = new ArrayList<ImageTable>();;
		ImageTable images = null;
		try {
			stmt = connection.prepareStatement("SELECT * from ImageTable");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				images = new ImageTable();
				images.setId(rs.getInt("Id"));
				images.setLinks(rs.getString("Link"));
				images.setImage(rs.getBytes("Image"));
				imageTable.add(images);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageTable;
	}

	@Override
	public boolean insert(ImageTable image) {
		Connect();
		try {
			stmt = connection.prepareStatement("INSERT INTO ImageTable (Link, Image) VALUES (?,?)");
			stmt.setString(1, image.getLinks());
			stmt.setBytes(2, image.getImage());
			boolean msg = stmt.executeUpdate() > 0;
			System.out.println(msg);
			return msg;

		} catch (Exception e) {
			
			return false;
		}

	}

	@Override
	public ImageTable search(int id) {
		Connect();
		ImageTable imageTable = null;
		try {
			stmt = connection.prepareStatement("select * from ImageTable where id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				imageTable = new ImageTable();
				imageTable.setId(rs.getInt("Id"));
				imageTable.setLinks(rs.getString("Link"));
				imageTable.setImage(rs.getBytes("Image"));
			}
		} catch (Exception e) {
			imageTable = null;
		}
		return imageTable;
	}

	@Override
	public void delete(int id) {
		Connect();
		try {
			stmt = connection.prepareStatement("DELETE FROM ImageTable WHERE id=?");
			stmt.setInt(1, id);
			stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
