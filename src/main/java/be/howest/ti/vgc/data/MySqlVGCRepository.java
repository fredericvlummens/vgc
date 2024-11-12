package be.howest.ti.vgc.data;

import be.howest.ti.vgc.data.util.MySqlConnection;
import be.howest.ti.vgc.domain.BoardGame;
import be.howest.ti.vgc.domain.Member;
import be.howest.ti.vgc.util.VGCException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlVGCRepository implements VGCRepository {

    private static final String SQL_SELECT_BOARDGAMES = "SELECT boardgames.* FROM boardgames INNER JOIN boardgames_members ON boardgames.name = boardgames_members.name WHERE boardgames_members.membership_number = ?";
    private static final String SQL_SELECT_MEMBERS = "SELECT members.* FROM members INNER JOIN boardgames_members ON members.membership_number = boardgames_members.membership_number WHERE boardgames_members.name = ?";
    private static final String SQL_SELECT_BOARDGAME = "SELECT * FROM boardgames WHERE name = ?";

    private static final Logger LOGGER = Logger.getLogger(MySqlVGCRepository.class.getName());

    @Override
    public List<BoardGame> getBoardGamesByMember(int membershipNumber) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_BOARDGAMES)) {

            ps.setInt(1, membershipNumber);

            try (ResultSet rs = ps.executeQuery()) {
                List<BoardGame> boardGames = new ArrayList<>();

                while (rs.next()) {
                    boardGames.add(new BoardGame(rs.getString("name"), rs.getString("category"), rs.getString("publisher")));
                }

                return boardGames;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to get board games for member.", ex);
            throw new VGCException("Unable to get board games for member.");
        }
    }

    @Override
    public List<Member> getMembersByBoardGame(String name) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_MEMBERS)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                List<Member> members = new ArrayList<>();

                while (rs.next()) {
                    Member member = new Member(rs.getInt("membership_number"), rs.getString("name"), rs.getString("first_name"));
                    members.add(member);
                }

                return members;
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to get members for board game.", ex);
            throw new VGCException("Unable to get members for board game.");
        }
    }

    @Override
    public BoardGame getBoardGameByName(String name) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_BOARDGAME)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BoardGame(rs.getString("name"), rs.getString("category"), rs.getString("publisher"));
                } else {
                    return null;
                }
            }

        } catch (SQLException ex) {
            throw new VGCException("Unable to get board game.");
        }
    }
}
