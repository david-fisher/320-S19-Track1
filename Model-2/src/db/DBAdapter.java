package db;

import javax.imageio.ImageIO;

import java.sql.*;
import java.util.ArrayList;
import login.*;
import post.*;
import registration.*;
import stripe.*;
import user.*;

public class DBAdapter {


    private String DBAddress = "jdbc:mysql://localhost:3306/TrackOneDB"; //access address for database. figure out l8tr
    private Connection conn;

    // USER FUNCTIONS
    private Connection getConnection() throws SQLException{
        //function used to get connection to database
        if(conn != null) return conn;
        Connection conn = DriverManager.getConnection(DBAddress,"root","root");
        System.out.println("Log: Connection Established!");
        return conn;
    }

    public boolean createUser(User usr) {
        try {

            this.getConnection();
            int log = (usr.loggedIn) ? 1 : 0;
            PreparedStatement statement = conn.prepareStatement("UPDATE TrackOneDB.User SET email = ?, firstName = ?, lastName = ?, password = ?, type = ?,loggedIn = ?");
            statement.setString(1, usr.email);
            statement.setString(2, usr.firstName);
            statement.setString(3, usr.lastName);
            statement.setString(4, usr.password);
            statement.setString(5, usr.firstName);
            statement.setString(6, usr.type);
            statement.setInt(7, log);
            statement.executeUpdate();
            if (usr.type == "member") {
                this.updateUser(usr.email, "address", usr.address);
                this.updateUser(usr.email, "city", usr.city);
                this.updateUser(usr.email, "state", usr.state);
                this.updateUser(usr.email, "zip", usr.zip);
                this.updateUser(usr.email, "ccNum", usr.ccNum);
                this.updateUser(usr.email, "ccv", usr.ccv);
                this.updateUser(usr.email, "ccExpMon", usr.expM);
                this.updateUser(usr.email, "ccExpYr", usr.expY);
                //this.updateUser(usr.email, "stripeID", usr.creditCard.getId());
                this.updateUser(usr.email, "phone", usr.phone);
                this.updateUser(usr.email, "birthday", usr.birthday);
                this.updateUser(usr.email, "points", usr.points);
                this.updateUser(usr.email, "inviter", usr.invitedBy);
                this.updateUser(usr.email, "hasInvited", (usr.hasInvited) ? 1 : 0);
                this.updateUser(usr.email, "validAccount", (usr.hasInvited) ? 1 : 0);
                this.updateUser(usr.email, "private", (usr.privacy) ? 1 : 0);
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
                String inviter = rs.getString("inviter");
                usr = new User (email, firstName, lastName, 0, null, "member", null); //fix when cc is implemented back in
                if (type == "member") { //set member fields
                    usr.type = type;
                    usr.address = rs.getString("address");
                    usr.city = rs.getString("city");
                    usr.state = rs.getString("state");
                    usr.zip = rs.getString("zip");
                    usr.ccNum = rs.getString("ccNum");
                    usr.ccv = rs.getString("ccv");
                    usr.expM = rs.getString("ccExpMon");
                    usr.expY = rs.getString("ccExpYr");
                    //usr.creditCard = new StripeCreditCard(email, usr.ccNum,
                    //usr.zip, usr.ccv, usr.expM, usr.expY);
                    //usr.stripeCreditCardID = rs.getString("stripeID");
                    usr.points += rs.getInt("points");
                    usr.phone = rs.getString("phone");
                    usr.password = rs.getString("password");
                    usr.loggedIn = true;
                    usr.hasInvited = (rs.getInt("hasInvited") == 1);
                    usr.isValidated = (rs.getInt("validAccount") == 1);
                    usr.privacy = rs.getBoolean("private");
                    return usr;
                }
                return (Admin)usr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private <T> String formatUserUpdateString(String email, String varname, T var) {
        return String.format("UPDATE TrackOneDB.User SET %s = '"+var+"' WHERE email = '"+email+"'", varname);
    }

    public <T> boolean updateUser(String email, String field, T newValue) {
        String query = formatUserUpdateString(email, field, newValue);
        try {
            this.getConnection();
            int rs = conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
            int rs = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.Follow (follow,userID) VALUES ('"+followee+"','"+follower+")");
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
/*
    public boolean insertFilter(Filter filter) {
        try {
            this.getConnection();
            ResultSet rs0 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Photo WHERE original = '"+filter.photoPath+"'");
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
    }
    //assuming the photoID is the original photo, and the path is the filter.

    public boolean deleteFilter(Filter filter) {
        try {
            this.getConnection();
            ResultSet rs0 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Photo WHERE original = '"+filter.photoPath+"'");
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
    }
    //I am being creative here about the format and I am not sure the format of matching two columns in the table.
    //I will fix it after asking Caroline.

    public int[] getFilters(ImagePost photo) {
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

    }
    //return a list of filterID. I have this structure in case they want us to return the path.

*/

    public boolean createURL(String original, String shortened) {
        try {
            this.getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE TrackOneDB.URL SET original = ?, shortened = ?");
            statement.setString(1, original);
            statement.setString(2, shortened);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //long and shortened URL are stored in the table

    public String getOriginalURL(String shortened){
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.URL WHERE shortened = '"+shortened+"'");
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
            PreparedStatement statement = conn.prepareStatement("UPDATE TrackOneDB.Post SET postID = ?, time = ?, text = ?, userID = ?");
            statement.setInt(1, Integer.parseInt(pst.postID));
            statement.setTimestamp(2, pst.timestamp);
            statement.setString(3, pst.text);
            statement.setString(4, pst.poster.email);
            statement.executeUpdate();
            this.updatePost(Integer.parseInt(pst.postID), "explicit", 0); //double check what the specific values are
            this.updatePost(Integer.parseInt(pst.postID), "visible", 0); //double check what the specific values are
            if (pst instanceof Comment) { //add to post table then add to comment table
                Comment com = (Comment)pst;
                this.updatePost(Integer.parseInt(com.postID), "type", "comment");
                this.updatePost(Integer.parseInt(com.postID), "parentID", Integer.parseInt(com.postID));
                int rs2 = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.Comment (text, parentID, childID) VALUES ('"+com.text+"','" +Integer.parseInt(com.associatedPostID.postID)+"','" +Integer.parseInt(com.postID)+"')");
            }
            else if (pst instanceof ImagePost) { //add to post table then add to image table & any other relevant tables
                ImagePost img = (ImagePost)pst;
                this.updatePost(Integer.parseInt(img.postID), "type", "imagePost");
                //then add image to image table and add filters to table
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
                    Comment com = new Comment(this.getUser(author),Integer.toString(id),text, parent);
                    com.timestamp = time;
                    com.flag = rs.getInt("explicit");
                    return com;
                }
                ResultSet comments = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Comments WHERE parentID = '"+postID+"'");
                ArrayList<Comment> coms = new ArrayList<Comment>();
                while(comments.next()) { //populate comments
                    coms.add((Comment)getPost(Integer.toString(comments.getInt("parentID"))));
                }
                if (type == "imagePost") { //do later
                    String path = this.getPhoto(rs.getString("photoID")).path;
                    ImagePost imgP = new ImagePost(this.getUser(author), postID, path);
                    imgP.timestamp = time;
                    imgP.flag = rs.getInt("explicit");
                    return imgP;
                }
                Post pst = new Post(this.getUser(author), postID, text);
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


    private <T> String formatPostUpdateString(int id, String varname, T var) {
        return String.format("UPDATE TrackOneDB.Post SET %s = '"+var+"' WHERE postID = '"+id+"'", varname);
    }

    public <T> boolean updatePost(int id, String field, T newValue) { //do not use when updating comments
        String query = formatPostUpdateString(id, field, newValue);
        try {
            this.getConnection();
            int rs = conn.createStatement().executeUpdate(query);
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
            //delete photos and filters before deleting post?
            int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.Post WHERE postID = '" +pstID+ "'");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int[] getUserPosts(String email) {
        //returns post IDs, not post
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
            int userID=0;
            while(rs.next()) {
                userID = rs.getInt("userID");
            }
            rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE userID =" + userID);
            ArrayList<Integer> postID = new ArrayList<Integer>();
            while(rs.next()) {
                postID.add(rs.getInt("postID"));
            }
            int[] arr = new int[postID.size()];
            for(int i =0; i<postID.size(); i++) {
                arr[i] = postID.get(i);
            }
            return arr;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    public boolean unfollowUser(String follower,String followee) {
        try {
            this.getConnection();
            int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.Follow (follow,userID) VALUES ('"+followee+"','"+follower+")");
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    // follower follows followee lol
    // #english

    Post[] getPosts(int num) {
        //general function - returns entire post object.
        Post[] arr = new Post[num];
        int pos = 0;
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post ORDER BY time DESC LIMIT " + num);
            while(rs.next()) {
                String postID = Integer.toString(rs.getInt("postID"));
                //String type = rs.getString("type");
                int explicit = rs.getInt("explicit");
                Timestamp time = rs.getTimestamp("time");
                int userID = rs.getInt("userID");
                String text = rs.getString("text");
                ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE userID = " + userID);
                String email = "";
                while(rs2.next()) {
                    email = rs2.getString("email");
                }
                arr[0] = new Post(getUser(email), postID, text);
                arr[0].timestamp = time;
                arr[0].setFlag(explicit);
                pos ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return arr;
    }

    public Post[] getPostFollowing(User usr) {
        String email = usr.email;
        int userID = 0;
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("Select * FROM TrackOneDB.User WHERE email = '"+ email + "'");
            while (rs.next()) {
                userID = rs.getInt("userID");
            }
            ArrayList<Integer> arr = new ArrayList<Integer>();
            rs = conn.createStatement().executeQuery("Select * FROM TrackOneDB.Follow WHERE userID = " + userID);
            while(rs.next()) {
                arr.add(rs.getInt("follow"));
            }
            ArrayList<Post> postArr = new ArrayList<Post>();
            for(int i=0; i<arr.size(); i++) {
                rs=conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE userID =" + arr.get(i));
                while(rs.next()) {
                    String postID = Integer.toString(rs.getInt("postID"));
                    //String type = rs.getString("type");
                    int explicit = rs.getInt("explicit");
                    Timestamp time = rs.getTimestamp("time");
                    String text = rs.getString("text");
                    ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE userID = " + arr.get(i));
                    String email2 = "";
                    while(rs2.next()) {
                        email2 = rs2.getString("email");
                    }
                    Post p = new Post(getUser(email2), postID, text);
                    p.timestamp = time;
                    p.setFlag(explicit);
                    postArr.add(p);
                }
            }
            //sort by datetime****
            return (Post[]) postArr.toArray();
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Post[] getFlaggedPost(int x) {
        ArrayList<Post> arr = new ArrayList<Post>();
        try {
            this.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE explicit = "+ x);
            while(rs.next()) {
                String postID = Integer.toString(rs.getInt("postID"));
                //String type = rs.getString("type");
                int explicit = rs.getInt("explicit");
                Timestamp time = rs.getTimestamp("time");
                int userID = rs.getInt("userID");
                String text = rs.getString("text");
                ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE userID = " + userID);
                String email = "";
                while(rs2.next()) {
                    email = rs2.getString("email");
                }
                Post p = new Post(getUser(email), postID, text);
                p.timestamp = time;
                p.setFlag(explicit);
                arr.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return (Post[]) arr.toArray();
    }
}