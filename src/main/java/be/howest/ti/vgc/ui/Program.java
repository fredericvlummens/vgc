package be.howest.ti.vgc.ui;

import be.howest.ti.vgc.data.Repositories;
import be.howest.ti.vgc.domain.BoardGame;
import be.howest.ti.vgc.services.VGCService;

import java.time.LocalDate;
import java.time.Month;

public class Program {

    public static void main(String[] args) {
        new Program().run();
    }

    private void run() {

        VGCService vgcService = new VGCService();

        BoardGame uno = vgcService.getBoardGameByName("UNO");

        System.out.println(uno);
        System.out.println(uno.getMembers());

    }
}
