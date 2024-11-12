package be.howest.ti.vgc.domain;

import be.howest.ti.vgc.data.Repositories;

import java.util.List;
import java.util.Objects;

public class Member {

    private final int membershipNumber;
    private final String name;
    private final String firstName;

    public Member(int membershipNumber, String name, String firstName) {
        this.membershipNumber = membershipNumber;
        this.name = Objects.requireNonNull(name);
        this.firstName = Objects.requireNonNull(firstName);
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<BoardGame> getBoardGames() {
        return Repositories.getRepository().getBoardGamesByMember(membershipNumber);
    }

    @Override
    public String toString() {
        return "Member{" +
                "membershipNumber=" + membershipNumber +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return membershipNumber == member.membershipNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(membershipNumber);
    }
}
