package org.example.streamApi;

public class Guest {
    String name;

    boolean Participating;

    int ParticipantsNumber;

    public Guest(String name, boolean participating, int participantsNumber) {
        this.name = name;
        Participating = participating;
        ParticipantsNumber = participantsNumber;
    }

    public String getName() {
        return name;
    }

    public boolean isParticipating() {
        return Participating;
    }

    public int getParticipantsNumber() {
        return ParticipantsNumber;
    }
}
