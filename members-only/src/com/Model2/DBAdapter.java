package com.Model2;

/*import login.*;
import post.*;
import registration.*;
import stripe.*;
import user.*; */

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import java.util.Comparator;
import java.util.PriorityQueue;


public class DBAdapter {


    private String DBAddress = "jdbc:mysql://localhost:3306/TrackOneDB"; //access address for database. figure out l8tr
	//private String DBAddress = "http://2590d205.ngrok.io/TrackOneDB";
    private Connection conn;
    private String jdbcDriver = "com.mysql.jdbc.Driver";


    // USER FUNCTIONS
    private Connection getConnection() throws SQLException{
        if (conn != null) return conn;
        try {
            //System.out.println("Attempting Connection");
        	Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(DBAddress,"root","root");
            //System.out.println("Connection Established");
            //conn.close();
            return conn;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createUser(User usr) {
        try {
            this.getConnection();
            //System.out.println("Connection is null when creating User: " + (conn==null));
            int log = (usr.loggedIn) ? 1 : 0;
            //System.out.println(log);
            //PreparedStatement statement = conn.prepareStatement("INSERT INTO TrackOneDB.URL(original, shortened) VALUES(?, ?)")
            PreparedStatement statement = conn.prepareStatement("INSERT INTO TrackOneDB.User(email, firstName, lastName, password, type, loggedIn) VALUES(?, ?, ?, ?, ?, ?)");
            statement.setString(1, usr.email);
            statement.setString(2, usr.firstName);
            statement.setString(3, usr.lastName);
            statement.setString(4, usr.password);
            statement.setString(5, usr.type);
            statement.setInt(6, log);
            //System.out.println(statement.toString());
            statement.executeUpdate();
            if (this.updateUser(usr.email, "loggedIn", log) != true) return false;
            if (usr.type == "member") {
                if (this.updateUser(usr.email, "address", usr.address)== false) return false;
                if (this.updateUser(usr.email, "city", usr.city)== false) return false;
                if (this.updateUser(usr.email, "state", usr.state)== false) return false;
                if (this.updateUser(usr.email, "zip", usr.zip)== false) return false;
                if (this.updateUser(usr.email, "ccNum", usr.ccNum)== false) return false;
                if (this.updateUser(usr.email, "ccv", usr.ccv)== false) return false;
                if (this.updateUser(usr.email, "ccExpMon", usr.expM)== false) return false;
                if (this.updateUser(usr.email, "ccExpYr", usr.expY)== false) return false;
                if (this.updateUser(usr.email, "stripeID", usr.creditCard.getId())==false) return false;
                if (this.updateUser(usr.email, "phone", usr.phone)== false) return false;
                if (this.updateUser(usr.email, "birthday", usr.birthday)== false) return false;
                if (this.updateUser(usr.email, "profilePic", usr.profilePic)== false) return false;
                if (this.updateUser(usr.email, "points", usr.points)== false) return false;
                if (this.updateUser(usr.email, "inviter", usr.invitedBy)== false) return false;
                if (this.updateUser(usr.email, "hasInvited", (usr.hasInvited) ? 1 : 0)== false) return false;
                if (this.updateUser(usr.email, "validAccount", (usr.isValidated) ? 1 : 0)== false) return false;
                if (this.updateUser(usr.email, "private", (usr.privacy) ? 1 : 0)== false) return false;
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public User getUser(String email) {
        try {
            this.getConnection();
            User usr;
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
            while(rs.next()) {
                String type = rs.getString("type");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String password = rs.getString("password");
                String inviter = rs.getString("inviter");
                int points = rs.getInt("points");
                String ccNum = rs.getString("ccNum");
                String zipcode = rs.getString("zip");
                String ccExpMon = rs.getString("ccExpMon");
                String ccExpYr = rs.getString("ccExpYr");
                String ccv = rs.getString("ccv");
                usr = new User (email, firstName, lastName, points, inviter, "", ccNum, ccExpMon, ccExpYr, ccv, zipcode);
                usr.password = password;
                if (type.equals("member")) { //set member fields0
                    usr.type = type;
                    usr.address = rs.getString("address");
                    usr.city = rs.getString("city");
                    usr.state = rs.getString("state");
                    usr.zip = rs.getString("zip");
                    usr.ccNum = rs.getString("ccNum");
                    usr.ccv = rs.getString("ccv");
                    usr.expM = rs.getString("ccExpMon");
                    usr.expY = rs.getString("ccExpYr");
                    usr.creditCard = new StripeCreditCard(email, usr.ccNum,usr.zip, usr.ccv, usr.expM, usr.expY);
                    usr.stripeCreditCardID = rs.getString("stripeID");
                    usr.points += rs.getInt("points");
                    usr.phone = rs.getString("phone");
                    usr.password = rs.getString("password");
                    usr.profilePic = rs.getString("profilePic");
                    usr.description = rs.getString("blurb");
                    usr.loggedIn = true;
                    usr.hasInvited = (rs.getInt("hasInvited") == 1);
                    usr.isValidated = (rs.getInt("validAccount") == 1);
                    usr.privacy = rs.getBoolean("private");
                    usr.creditCard = new StripeCreditCard(usr.email, usr.ccNum, usr.zip,
							usr.ccv, usr.expM, usr.expY);
                    return usr;
                }
                return usr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean logUserInvite(String email, String code) {
        try {
            this.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO TrackOneDB.Invite(inviter, code) VALUES(?, ?)");
            statement.setString(1, email);
            statement.setString(2, code);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getUserInvite(String code) {
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Invite WHERE code = '"+code+"'");
            rs.next();
            String email = rs.getString("inviter");
            int rs2 = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.Invite WHERE code = '" +code+ "'");
            System.out.println(email);
            return email;
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public <T> boolean updateUser(String email, String field, T newValue) {
        //String query = formatUserUpdateString(email, field, newValue);
        try {
            this.getConnection();
            PreparedStatement statement = conn.prepareStatement(("UPDATE TrackOneDB.User SET "+field+" = ? WHERE email = ?"));
            statement.setObject(1, newValue);
            statement.setString(2, email);
            statement.executeUpdate();
            //System.out.println(statement.toString());
            return true;
            //int rs = conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String email) { //delete posts all posts users made?
        try {
            this.getConnection();
            int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.User WHERE email = '" +email+ "'");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean followUser(String follower,String followee) {
        try {
            this.getConnection();
            //"SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'"
            PreparedStatement statement = conn.prepareStatement("INSERT INTO TrackOneDB.Follow (follow,userID) VALUES(?,?)");
            statement.setString(1, follower);
            statement.setString(2, followee);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    // follower follows followee lol
    // #english

    public boolean createPhoto(String path) {
        try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE TrackOneDB.Photo SET original = ?");
            statement.setString(1, path);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ImagePost getPhoto(String id) {
        int ID = Integer.parseInt(id);
        try {
            this.getConnection();
            int rs = conn.createStatement().executeUpdate("SELECT * FROM TrackOneDB.Photo WHERE photoID = '" +ID+ "'");
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean deletePhoto(ImagePost photo) {
        try {
            this.getConnection();
            int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.Photo WHERE original = '" +photo.path+ "'");
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createURL(String original, String shortened) {
        try {
            this.getConnection();
            //System.out.println("Connection is null: " + (conn==null));
            PreparedStatement statement = conn.prepareStatement("INSERT INTO TrackOneDB.URL(original, shortened) VALUES(?, ?)");
            statement.setString(1, original);
            statement.setString(2, shortened);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }catch(NullPointerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //long and shortened URL are stored in the table

    public String getOriginalURL(String shortened){
        try {
            this.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM TrackOneDB.URL WHERE shortened = ?");
            statement.setString(1, shortened);
            ResultSet rs = statement.executeQuery();
            //ResultSet rs = conn.createStatement().executeQuery(statement);//"SELECT * FROM TrackOneDB.URL WHERE shortened = '"+shortened+"'");
            while (rs.next()) {
                String original = rs.getString("original");
                return original;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public boolean createPost(Post pst) {
        try {
            this.getConnection();
            //PreparedStatement statement = conn.prepareStatement("INSERT INTO TrackOneDB.URL(original, shortened) VALUES(?, ?)")
            PreparedStatement statement = conn.prepareStatement("INSERT INTO TrackOneDB.Post(postID, type, time, userId, text) VALUES(?,?,?,?,?)");
            statement.setInt(1, Integer.parseInt(pst.postID));
            statement.setString(2, pst.type);
            statement.setTimestamp(3, pst.timestamp);
            statement.setString(4, pst.poster.email);
            statement.setString(5, pst.text);
            System.out.println(statement.toString());
            statement.executeUpdate();
            this.updatePost(Integer.parseInt(pst.postID), "explicit", 0);
            this.updatePost(Integer.parseInt(pst.postID), "visible", 1);
            if(pst.type.equals("comment")) {
                statement = conn.prepareStatement("INSERT INTO TrackOneDB.Comment(parentId, childId) VALUES(?,?)");
                Comment com = (Comment)pst;
                //Post parent = com.associatedPostID
                statement.setInt(1, Integer.parseInt(com.associatedPostID.postID));
                statement.setInt(2, Integer.parseInt(com.postID));
                System.out.println(statement.toString());
                statement.executeUpdate();
                this.updatePost(Integer.parseInt(pst.postID), "parentID", Integer.parseInt(com.associatedPostID.postID));
            }
            if(pst.type.equals("imagePost")) {
                ImagePost imgPst = (ImagePost)pst;
                this.updatePost(Integer.parseInt(pst.postID), "photo", imgPst.path);
                System.out.println(statement.toString());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Post getPost(String postID) {
        try {
            this.getConnection();
            int id = Integer.parseInt(postID);
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE postID = '"+id+"'");
            while(rs.next()) {
                String type = rs.getString("type");
                Timestamp time = rs.getTimestamp("time");
                String author = rs.getString("userID");
                String text = rs.getString("text");
                int flag = rs.getInt("explicit");
                if (type == "comment") { //set  fields
                    Post parent = this.getPost(Integer.toString(rs.getInt("parentID")));
                    Comment com = new Comment(this.getUser(author),"comment",Integer.toString(id),text, parent);
                    com.timestamp = time;
                    com.flag = rs.getInt("explicit");
                    return com;
                }
                ResultSet comments = conn.createStatement().executeQuery("SELECT childID FROM TrackOneDB.Comment WHERE parentID = '"+postID+"'");
                ArrayList<Comment> coms = new ArrayList<Comment>();
                while(comments.next()) { //populate comments
                    Post parent = this.getPost(Integer.toString(comments.getInt("childID")));
                    Comment c = new Comment(parent.poster,"comment",Integer.toString(comments.getInt("childID")), parent.text, parent);
                    coms.add(c);
                }
                if (type == "imagePost") { //do later
                    String path = this.getPhoto(rs.getString("photoID")).path;
                    ImagePost imgP = new ImagePost(this.getUser(author), "imagePost", postID, path);
                    imgP.comments = coms;
                    imgP.timestamp = time;
                    imgP.flag = rs.getInt("explicit");
                    return imgP;
                }
                Post pst = new Post(this.getUser(author), "textPost",postID, text);
                pst.comments = coms;
                pst.flag = rs.getInt("explicit");
                pst.timestamp = time;
                return pst;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }



    public <T> boolean updatePost(int id, String field, T newValue) { //do not use when updating comments
        //String query = formatPostUpdateString(id, field, newValue);
        try {
            this.getConnection();
            PreparedStatement statement = conn.prepareStatement(("UPDATE TrackOneDB.Post SET "+field+" = ? WHERE postID = ?"));
            statement.setObject(1, newValue);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean deletePost(String id) { //delete posts all posts users made?
        try {
            this.getConnection();
            int pstID = Integer.parseInt(id);
            System.out.println(pstID);
            //int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.User WHERE email = '" +email+ "'");
            int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.Post WHERE postID = '" +id+ "'");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Post> getUserPosts(String email) {
        //returns post IDs, not post
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE userID ='"+email+"'");
            ArrayList<String> ids = new ArrayList<String>();
            while(rs.next()) { ids.add(rs.getString("postID")); }
            ArrayList<Post> posts = new ArrayList<Post>();
            for(int i = 0; i < ids.size(); i++) { posts.add(this.getPost(ids.get(i))); }
            Collections.reverse(posts);
            return posts;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Post> getFlaggedPosts() {
        //returns post IDs, not post
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE explicit > 0");
            ArrayList<String> ids = new ArrayList<String>();
            while(rs.next()) { ids.add(rs.getString("postID")); }
            ArrayList<Post> posts = new ArrayList<Post>();
            for(int i = 0; i < ids.size(); i++) { posts.add(this.getPost(ids.get(i))); }
            return posts;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public boolean unfollowUser(String follower,String followee) {
        try {
            this.getConnection();
            int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.Follow WHERE userID = '"+followee+"' AND follow = '"+follower+"'");
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Post> getPopularPosts(int postsFetched){
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE type != 'comment'");
            ArrayList<Post> posts = new ArrayList<Post>();
            while(rs.next()) { posts.add(this.getPost(Integer.toString(rs.getInt("postID")))); }
            Collections.sort(posts, new SortByComments()); 
            if (posts.size() <= postsFetched) return posts;
            ArrayList<Post> returnPosts = new ArrayList<Post>();
            for (int i = 0; i < postsFetched; i++) { returnPosts.add(posts.get(i)); }
            return returnPosts;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Post> fetchRecentPosts(int posts){
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post ORDER BY `time` DESC LIMIT '"+posts+"'");
            ArrayList<Post> p = new ArrayList<Post>();
            while(rs.next()) {
                if (rs.getString("type").equals("comments")) continue;
                String pID = Integer.toString(rs.getInt("postID"));
                p.add(this.getPost(rs.getString(pID)));
            }
            return p;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    // ========================= Not doing photo filtering in 1.0 ================================

    /*public boolean insertFilter(Filter filter) {
        try {
            this.getConnection();
            ResultSet rs0 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Photo WHERE original = '"+filter.path+"'");
            int filterID=0;
            while(rs0.next()) {
                filterID = rs0.getInt("photoID");
            }
            int rs = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.FilteredPhoto (xPos,yPos,visibleToUser,photoID,filterID) VALUES ('"+filter.xPos+"','"+filter.yPos+"','"+filter.visibleToUser+"','"+filter.photoId+"','"+filterID+"')");

        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }*/
    //assuming the photoID is the original photo, and the path is the filter.

    /*public boolean deleteFilter(Filter filter) {
        try {
            this.getConnection();
            ResultSet rs0 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Photo WHERE original = '"+filter.path+"'");
            int filterID=0;
            while(rs0.next()) {
                filterID = rs0.getInt("photoID");
            }
            int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.FilteredPhoto WHERE photoID = AND filterID ='" +filter.photoId+ "AND"+filterID+"'");
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }*/
    //I am being creative here about the format and I am not sure the format of matching two columns in the table.
    //I will fix it after asking Caroline.

    /*public int[] getFilters(Photo photo) {
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.FilterPhoto WHERE photoID = '"+photo.photoId+"'");
            ArrayList<Integer> filterID = new ArrayList<Integer>();
            while(rs.next()) {
                filterID.add(rs.getInt("filterID"));
            }
            int[] filters = new int[filterID.size()];
            for(int i=0;i<filterID.size();i++) {
                filters[i] = filterID.get(i);
            }
            return filters;
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }*/
    //return a list of filterID. I have this structure in case they want us to return the path.

    // ========================= Not doing photo filtering in 1.0 ================================

}