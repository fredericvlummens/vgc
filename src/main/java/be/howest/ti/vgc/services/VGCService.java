package be.howest.ti.vgc.services;

import be.howest.ti.vgc.data.Repositories;
import be.howest.ti.vgc.domain.BoardGame;
import be.howest.ti.vgc.domain.Member;

import java.util.List;

public class VGCService {

    public List<BoardGame> getBoardGamesByMember(int membershipNumber) {
        return Repositories.getRepository().getBoardGamesByMember(membershipNumber);
    }

    public List<Member> getMembersByBoardGame(String name) {
        return Repositories.getRepository().getMembersByBoardGame(name);
    }

    public BoardGame getBoardGameByName(String name) {
        return Repositories.getRepository().getBoardGameByName(name);
    }


}
