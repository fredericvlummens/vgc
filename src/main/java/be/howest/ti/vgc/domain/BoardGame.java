package be.howest.ti.vgc.domain;

import be.howest.ti.vgc.data.Repositories;

import java.util.List;
import java.util.Objects;

public class BoardGame {

    private final String name;
    private final String category;
    private final String publisher;

    public BoardGame(String name, String category, String publisher) {
        this.name = Objects.requireNonNull(name);
        this.category = Objects.requireNonNull(category);
        this.publisher = Objects.requireNonNull(publisher);
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return "BoardGame{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }

    public List<Member> getMembers() {
        return Repositories.getRepository().getMembersByBoardGame(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGame boardGame = (BoardGame) o;
        return Objects.equals(name, boardGame.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
