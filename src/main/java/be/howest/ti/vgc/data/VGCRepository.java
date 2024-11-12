package be.howest.ti.vgc.data;

import be.howest.ti.vgc.domain.BoardGame;
import be.howest.ti.vgc.domain.Member;

import java.util.List;

public interface VGCRepository {

    List<BoardGame> getBoardGamesByMember(int membershipNumber);
    List<Member> getMembersByBoardGame(String name);
    BoardGame getBoardGameByName(String name);

}
