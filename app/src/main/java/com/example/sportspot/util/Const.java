package com.example.sportspot.util;

import com.example.sportspot.database.tables.Coach;
import com.example.sportspot.database.tables.Team;

public class Const {
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String SP_FILE_NAME = "sp_user_sessions";
    public static final String SP_EMAIL_KEY = "email";
    public static final String SP_PASSWORD_KEY = "password";
    public static final String DB_NAME = "sportspot_db";
    public static final String TEAMS_REPORT_INTENT_EXTRA = "teams_report_intent_extra";
    public static final String COACHES_REPORT_INTENT_EXTRA= "coaches_report_intent_extra";
    public static final String TITLES_REPORT_INTENT_EXTRA = "titles_report_intent_extra";

    public static Coach[] COACHES ={
                new Coach(1,"Fotbal", "Dan Vasile Petrescu", 51, 16, 1, "CFR Cluj"),
                new Coach(2, "fotbal", "Bogdan Arges Vintila", 47, 9, 2, "FCSB"),
                new Coach(3, "fotbal", "Victor Piturca", 63, 28, 3, "Universitatea Craiova"),
                new Coach(4, "fotbal", "Bogdan Ioan Andone", 44, 8, 4, "Astra Giurgiu"),
                new Coach(5, "fotbal", "Eduard Iordanescu", 41,9, 5, "Gaz Metan Medias"),
                new Coach(6, "fotbal", "Dusan Uhrin Jr.", 52, 17, 6, "Dinamo Bucuresti"),
                new Coach(7, "fotbal", "Eugen Neagoe", 52, 14, 7, "FC Hermannstadt"),
                new Coach(8, "handbal", "Stefan Constantin", 51, 16, 8, "Dinamo Bucuresti"),
                new Coach(9, "handbal", "Horatiu Gal", 50, 11, 9, "Potaissa Turda"),
                new Coach(10, "handbal", "Pablo Campos Sunen", 43, 14, 10, "CSM Bucuresti (MASCULIN)"),
                new Coach(11, "handbal", "Adrian Vasile", 37, 5, 11, "CSM Bucuresti (FEMININ)"),
                new Coach(12, "handbal", "Florentin Pera", 40, 17, 12, "Ramnicu Valcea"),
                new Coach(13, "handbal", "George Bogdan Burcea", 47, 24, 13, "SCM Craiova"),
                new Coach(14, "handbal", "Bozo Rudic", 48, 10, 14, "Steaua Bucuresti"),
                new Coach(15, "volei", "Sergiu Stancu", 37, 5, 15, "Arcada Galati"),
                new Coach(16,"volei", "Stevan Ljubicic", 38,7, 16, "Dinamo Bucuresti"),
                new Coach(17, "volei", "Bogdan Tanase", 41, 9, 17, "Zalau"),
                new Coach(18, "volei", "Razvan Parpala", 34, 3, 18,"Gloria Buzau"),
                new Coach(19, "volei", "Marius Lucian Dascalu", 36, 4, 19, "Unirea Dej")
    };

    public static Team[] TEAMS = {
                new Team(1,"F1", "fotbal", "CFR Cluj", "Liga I", 12, 41, 7, 8),
                new Team(2,"F2", "fotbal", "FCSB", "Liga I", 11, 36, 9, 15),
                new Team(3,"F3", "fotbal", "Universitatea Craiova", "Liga I", 11, 37, 4, 1),
                new Team(4,"F4", "fotbal", "Astra Giurgiu", "Liga I", 12, 40, 7, 2),
                new Team(5,"F5", "fotbal", "Gaz Metan Medias", "Liga I", 9, 33, 6, 0),
                new Team(6,"F6", "fotbal", "Dinamo Bucuresti", "Liga I", 8, 28, 5, 18),
                new Team(7,"F7", "fotbal", "FC Hermannstadt", "Liga I", 4, 20, 0, 0),
                new Team(8,"H1", "handbal", "Dinamo Bucuresti", "Liga nationala", 12, 36, 3, 16),
                new Team(9,"H2", "handbal", "Potaissa Turda", "Liga nationala", 9, 28, 0, 0),
                new Team(10,"H3", "handbal", "CSM Bucuresti (MASCULIN)", "Liga nationala", 8, 26, 1, 1),
                new Team(11, "H4", "handbal", "CSM Bucuresti (FEMININ)", "Liga nationala feminina", 7, 22, 1, 14),
                new Team(12,"H5", "handbal", "Ramnicu Valcea", "Liga nationala feminina", 9, 27, 0, 20),
                new Team(13,"H6", "handbal", "SCM Craiova", "Liga nationala feminina", 5, 15, 0, 1),
                new Team(14,"H7", "handbal", "Steaua Bucuresti", "Liga nationala", 7, 23, 2, 28),
                new Team(15,"V1", "volei", "Arcada Galati", "Divizia A1", 8, 24, 0, 4),
                new Team(16,"V2", "volei", "Dinamo Bucuresti", "Divizia A1", 8, 23, 1, 1),
                new Team(17,"V3", "volei", "Zalau", "Divizia A1", 7, 20, 0, 0),
                new Team(18,"V4", "volei", "Gloria Buzau", "Divizia A1", 5, 16, 1, 0),
                new Team(19,"V5", "volei", "Unirea Dej", "Divizia A1", 5, 16, 0, 0)
    };
}

