package com.example.sportspot.util;

import com.example.sportspot.database.tables.Coach;
import com.example.sportspot.database.tables.Team;

public class Const {
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String SP_FILE_NAME = "sp_user_sessions";
    public static final String SP_EMAIL_KEY = "email";
    public static final String SP_PASSWORD_KEY = "password";
    public static final String DB_NAME = "sportspot_db";

    public static Coach[] prepopulateCoachTable(){
        return new Coach[]{
                new Coach(1,"Fotbal", "Dan Vasile Petrescu", 51, 16, "F1"),
                new Coach(2, "fotbal", "Bogdan Arges Vintila", 47, 9, "F2"),
                new Coach(3, "fotbal", "Victor Piturca", 63, 28, "F3"),
                new Coach(4, "fotbal", "Bogdan Ioan Andone", 44, 8, "F4"),
                new Coach(5, "fotbal", "Eduard Iordanescu", 41,9, "F5"),
                new Coach(6, "fotbal", "Dusan Uhrin Jr.", 52, 17, "F6"),
                new Coach(7, "fotbal", "Eugen Neagoe", 52, 14, "F7"),
                new Coach(8, "handbal", "Stefan Constantin", 51, 16, "H1"),
                new Coach(9, "handbal", "Horatiu Gal", 50, 11, "H2"),
                new Coach(10, "handbal", "Pablo Campos Sunen", 43, 14, "H3"),
                new Coach(11, "handbal", "Adrian Vasile", 37, 5, "H4"),
                new Coach(12, "handbal", "Florentin Pera", 40, 17, "H5"),
                new Coach(13, "handbal", "George Bogdan Burcea", 47, 24, "H6"),
                new Coach(14, "handbal", "Bozo Rudic", 48, 10, "H7"),
                new Coach(15, "volei", "Sergiu Stancu", 37, 5, "V1"),
                new Coach(16,"volei", "Stevan Ljubicic", 38,7, "V2"),
                new Coach(17, "volei", "Bogdan Tanase", 41, 9, "V3"),
                new Coach(18, "volei", "Razvan Parpala", 34, 3, "V4"),
                new Coach(19, "volei", "Marius Lucian Dascalu", 36, 4, "V5")
        };
    }

    public static Team[] prepopulateTeamTable(){
        return new Team[]{
                new Team("F1", "fotbal", "CFR Cluj", "Liga I", 12, 41, 7, 8),
                new Team("F2", "fotbal", "FCSB", "Liga I", 11, 36, 9, 15),
                new Team("F3", "fotbal", "Universitatea Craiova", "Liga I", 11, 37, 4, 1),
                new Team("F4", "fotbal", "Astra Giurgiu", "Liga I", 12, 40, 7, 2),
                new Team("F5", "fotbal", "Gaz Metan Medias", "Liga I", 9, 33, 6, 0),
                new Team("F6", "fotbal", "Dinamo Bucuresti", "Liga I", 8, 28, 5, 18),
                new Team("F7", "fotbal", "FC Hermannstadt", "Liga I", 4, 20, 0, 0),
                new Team("H1", "handbal", "Dinamo Bucuresti", "Liga nationala", 12, 36, 3, 16),
                new Team("H2", "handbal", "Potaissa Turda", "Liga nationala", 9, 28, 0, 0),
                new Team("H3", "handbal", "CSM Bucuresti (MASCULIN)", "Liga nationala", 8, 26, 1, 1),
                new Team("H4", "handbal", "CSM Bucuresti (FEMININ)", "Liga nationala feminina", 7, 22, 1, 14),
                new Team("H5", "handbal", "Ramnicu Valcea", "Liga nationala feminina", 9, 27, 0, 20),
                new Team("H6", "handbal", "SCM Craiova", "Liga nationala feminina", 5, 15, 0, 1),
                new Team("H7", "handbal", "Steaua Bucuresti", "Liga nationala", 7, 23, 2, 28),
                new Team("V1", "volei", "Arcada Galati", "Divizia A1", 8, 24, 0, 4),
                new Team("V2", "volei", "Dinamo Bucuresti", "Divizia A1", 8, 23, 1, 1),
                new Team("V3", "volei", "Zalau", "Divizia A1", 7, 20, 0, 0),
                new Team("V4", "volei", "Gloria Buzau", "Divizia A1", 5, 16, 1, 0),
                new Team("V5", "volei", "Unirea Dej", "Divizia A1", 5, 16, 0, 0)
        };
    }
}
